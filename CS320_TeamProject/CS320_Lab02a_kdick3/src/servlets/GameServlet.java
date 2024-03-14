package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GameEngineController;
import models.GameModel;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Game Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doPost");
		
		
		String input = req.getParameter("input");
		
		GameModel model = new GameModel(input);
		
		GameEngineController GE = new GameEngineController(model);
		
		
		
		req.setAttribute("gamemodel", model);
		
		
		
		
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		
	}
}
	
	