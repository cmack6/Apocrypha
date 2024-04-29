package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;
import edu.ycp.cs320.booksdb.model.*;

public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Account Servlet: doGet");
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		Player player = db.getPlayerFromGameID((Integer)req.getSession().getAttribute("gameID"));//CHANGE THIS WHEN GAMEID GETS PASSED!!!!!! ! ! !!
		req.setAttribute("player", player);
		System.out.println("Player ID is "+player.getPlayerID());
		String playerString = UUID.randomUUID().toString();
		req.getSession().setAttribute("playerString", player);
		req.setAttribute("playerString", player);
		List<Item> itemList = db.findAllItems();
		String items = "";
		//FIX ITEM LIST WHEN TALKING TO KORBIN
		/*for(Item item: itemList) { 
			if(item.getLocation()==-1&&item.getGameID()==player.getGameID()) {
				if(items.equals("")) {
				items = item.getName();
				}
				else {
					items = items + ", " + item.getName();
				}
			}
		}*/
		req.setAttribute("items", items);
		
		req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
	}
}
