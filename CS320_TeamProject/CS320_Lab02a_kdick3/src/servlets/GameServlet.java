package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GameEngineController;
import models.GameModel;
import models.Room;
import models.User;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> log = new ArrayList<String>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Game Servlet: doGet");	
		
		// call JSP to generate empty form
		/////////////////////////////////////////////////////////////////////////////////////
		
		GameModel model = new GameModel();
		
		GameEngineController controller = new GameEngineController(model);
		
		//making a makeshift "map" through populating an arraylist with rooms///maybe i could put this as a method in the controller and say like create map just so its moved from the servlet
		/*
		for(int i=0; i<9; i++) {
			Room a = new Room();
			controller.addNewRoom(a);
		}
		*/
		
		//populates the RoomID for each room, it corresponds with their index just for now, will change eventually^^ same with that above
		for(int i=0; i<9; i++) {
			controller.setRoomID(i, i);
		}
		
		//for setting up room connections, im going to make them using just one arraylist laid out below
		// [0][1][2]
		// [3][4][5]
		// [6][7][8]
		//it will kind of resemble this and a using the standard cardinal map
		//[1] is north of [4] and [5] is east of [4]
		//while planning each room's room connections, i plan on making [4] the starting point for the User
		//User RoomID will be 4, and north will put User in [1]
		//However, around the outside(everywhere but [4]) will have an instruction that brings the user across to the other end
		//if the User commands north from [0], the User will end up in [6]
		
		//in case time doesnt permit im treating all of the rooms as literal rooms, might switch to caves/buildings/etc.
		for(int i=0; i<9; i++) {
			controller.setShortDescription(i, "A bare room, the some walls look awfully odd...");
		}
		
		controller.setLongDescription(4, "This is just a small starting map, some rooms could be tied together through different paths.");
		controller.setShortDescription(4, "Welcome to Apocrypha!");
		
		
		controller.setLongDescription(0, "The walls to the west and north look as though they can bring you somewhere...");
		controller.setLongDescription(1, "The wall to the north looks as though it can bring you somewhere...");
		controller.setLongDescription(2, "The walls to the east and north look as though they can bring you somewhere...");
		controller.setLongDescription(3, "The wall to the west looks as though it can bring you somewhere...");
		controller.setLongDescription(5, "The wall to the east looks as though they can bring you somewhere...");
		controller.setLongDescription(6, "The walls to the south and west look as though they can bring you somewhere...");
		controller.setLongDescription(7, "The wall to the south looks as though they can bring you somewhere...");
		controller.setLongDescription(8, "The walls to the east and south look as though they can bring you somewhere...");
		
		controller.setRoomConnections(0, 6, 3, 1, 2);
		controller.setRoomConnections(1, 7, 4, 2, 0);
		controller.setRoomConnections(2, 8, 5, 0, 1);
		controller.setRoomConnections(3, 0, 6, 4, 5);
		controller.setRoomConnections(4, 1, 7, 5, 3);
		controller.setRoomConnections(5, 2, 8, 3, 4);
		controller.setRoomConnections(6, 3, 0, 7, 8);
		controller.setRoomConnections(7, 4, 1, 8, 6);
		controller.setRoomConnections(8, 5, 2, 6, 7);
		
		//user set as having roomID 4 and score of 0: see User class for explanation on parameters
		User user = new User(4, 0);
		GameModel setModel = new GameModel(user,model.Rooms.get(4).getLongDescription());
		req.setAttribute("model", setModel);
		String input = "";
		req.setAttribute("input",input);
		setModel.Rooms.get(setModel.getUser().getRoomID()).getDescription();
		String modelString = UUID.randomUUID().toString();
		req.getSession().setAttribute("modelString", setModel);
		req.setAttribute("modelString", modelString);
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		System.out.println(setModel.getUser().getRoomID());
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Game Servlet: doPost");
		
		String modelString = req.getParameter("modelString");
		Object transferModel = req.getSession().getAttribute("modelString");
		GameModel model = (GameModel)transferModel;
		req.getSession().removeAttribute("modelString");
	//	GameModel model = new GameModel();
	//	model = (GameModel)req.getAttribute("model");
		System.out.println("before move:" + model.getUser().getRoomID());
		String input = req.getParameter("input");
	//	System.out.println(input);
		GameEngineController controller = new GameEngineController(model);
		for(int i=0; i<9; i++) {
			controller.setRoomID(i, i);
		}
		for(int i=0; i<9; i++) {
			controller.setShortDescription(i, "A bare room, the some walls look awfully odd...");
		}
		
		controller.setLongDescription(4, "This is just a small starting map, some rooms could be tied together through different paths.");
		controller.setShortDescription(4, "Welcome to Apocrypha!");
		
		
		controller.setLongDescription(0, "The walls to the west and north look as though they can bring you somewhere...");
		controller.setLongDescription(1, "The wall to the north looks as though it can bring you somewhere...");
		controller.setLongDescription(2, "The walls to the east and north look as though they can bring you somewhere...");
		controller.setLongDescription(3, "The wall to the west looks as though it can bring you somewhere...");
		controller.setLongDescription(5, "The wall to the east looks as though they can bring you somewhere...");
		controller.setLongDescription(6, "The walls to the south and west look as though they can bring you somewhere...");
		controller.setLongDescription(7, "The wall to the south looks as though they can bring you somewhere...");
		controller.setLongDescription(8, "The walls to the east and south look as though they can bring you somewhere...");
		
		controller.setRoomConnections(0, 6, 3, 1, 2);
		controller.setRoomConnections(1, 7, 4, 2, 0);
		controller.setRoomConnections(2, 8, 5, 0, 1);
		controller.setRoomConnections(3, 0, 6, 4, 5);
		controller.setRoomConnections(4, 1, 7, 5, 3);
		controller.setRoomConnections(5, 2, 8, 3, 4);
		controller.setRoomConnections(6, 3, 0, 7, 8);
		controller.setRoomConnections(7, 4, 1, 8, 6);
		controller.setRoomConnections(8, 5, 2, 6, 7);
		
		controller.processInput(model,input);
		
		System.out.println("after move:" + model.getUser().getRoomID());
		String newLog = model.getLog() + "<p>" + input + "</p><p>" + model.Rooms.get(model.getUser().getRoomID()).getDescription() + "</p>";
		model.setLog(newLog);
		System.out.println(newLog);
		req.setAttribute("log",newLog);
		req.setAttribute("model",model);
		modelString = UUID.randomUUID().toString();
		req.getSession().setAttribute("modelString", model);
		req.setAttribute("modelString", modelString);
		
		
		
		/*String output = null;
		
		log.add(input);
		req.setAttribute("size", log.size());
		for(int i=0;i<log.size();i++) {
			output=log.get(i);
			req.setAttribute("input",output);
		}
		*/
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	
		
	}
}
	
	