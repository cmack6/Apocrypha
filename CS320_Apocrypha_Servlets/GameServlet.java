
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.NumbersController;
import edu.ycp.cs320.lab02.model.Numbers;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("game Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
	
	String userInput = req.getParameter("userInput");
	
	User userModel = new User();
	
	GameEngine GEmodel = new GameEngine();
	
	Room roomModel = new Room();
	
	String errorMessage = null;
	
	//envisioning this as passing whatever String the user inputs either before or after
	//checking if the String is a viable command
	//the game engine will then execute the command and give it back
	//might have to implement controller
	//maybe check user command viablity through Commands implemented interface
	GEmodel.setUserInput(userInput);
	
	
	req.setAttribute("errorMessage", errorMessage);
	req.setAttribute("GameEngine", GEmodel);
	
	req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	
	
	
}
	
	