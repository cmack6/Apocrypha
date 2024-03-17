package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GameEngineController;
import models.GameModel;
import models.Room;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> log = new ArrayList<String>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Game Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		
		GameModel model = new GameModel();
		
		GameEngineController controller = new GameEngineController(model);
		
		Room r1 = new Room();
		Room r2 = new Room();
		Room r3 = new Room();
		Room r4 = new Room();
		Room r5 = new Room();
		Room r6 = new Room();
		
		
		
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doPost");
		
		
		String input = req.getParameter("input");
		
		/*GameModel model = new GameModel(input);
		
		GameEngineController GE = new GameEngineController(model);
		
		model.setAction(input);
		
		GE.processInput();*/
		String output = null;
		
		log.add(input);
		req.setAttribute("size", log.size());
		for(int i=0;i<log.size();i++) {
			output=log.get(i);
			req.setAttribute("input",output);
		}
		
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	
		
	}
}
	
	