package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;
import edu.ycp.cs320.lab02.controller.LoginController;
import edu.ycp.cs320.lab02.model.Library;

public class FileSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Library model;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nFileSelectServlet: doGet");
		int userID;
		if(req.getSession().getAttribute("userID")!=null) {
			userID = (Integer)req.getSession().getAttribute("userID");
		}
		else {
			userID = 1;
			req.getSession().setAttribute("userID", userID);
		}
		System.out.println(userID);
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		List<Player> playerList = db.findAllPlayers();
		List<Player> userGames = new ArrayList<Player>();
		for(Player player: playerList) {
			if(player.getUserID()==userID) {
				userGames.add(player);
			}
		}
		req.setAttribute("count",userGames.size());
		
		req.getRequestDispatcher("/_view/file-select.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nFileSelectServlet: doPost");
		
		int userID = (Integer)req.getSession().getAttribute("userID");
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		List<Player> playerList = db.findAllPlayers();
		List<Player> userGames = new ArrayList<Player>();
		for(Player player: playerList) {
			if(player.getUserID()==userID) {
				userGames.add(player);
			}
		}
		System.out.println(req.getParameter("submit"));
		int gameID = Integer.valueOf(req.getParameter("submit"));
		if(gameID == 0) {
			Player newPlayer = db.createNewGame(userID);
			req.getSession().setAttribute("gameID", newPlayer.getGameID());
		}
		else {
			gameID = userGames.get(gameID+1).getGameID();
			req.getSession().setAttribute("gameID", gameID);
		}
		resp.sendRedirect("http://localhost:8081/lab02/game");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/file-select.jsp").forward(req, resp);
	}
}
