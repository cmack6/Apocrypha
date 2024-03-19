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
		
		// [plateau][canyon][desert]
		// [forest][campsite][glacier]
		// [hill][marsh][valley]
		
		//in case time doesnt permit im treating all of the rooms as literal rooms, might switch to caves/buildings/etc.
		for(int i=0; i<9; i++) {
			controller.setShortDescription(i, "A bare room, the some walls look awfully odd...");
		}
		
		controller.setLongDescription(4, "This is just a small starting map, some rooms could be tied together through different paths. You can move through rooms using commands such as north, south, east or west. You are at a small campsite, starting with only 25 dollas, you must get your money up.");
		controller.setShortDescription(4, "A small campsite stands in an large open area.");
		
		
		controller.setShortDescription(0, "You are at a plateau.");
		controller.setShortDescription(1, "You are at a canyon.");
		controller.setShortDescription(2, "You are at a desert.");
		controller.setShortDescription(3, "You are at a forest.");
		controller.setShortDescription(5, "You are at a glacier.");
		controller.setShortDescription(6, "You are at a hill.");
		controller.setShortDescription(7, "You are at a marsh.");
		controller.setShortDescription(8, "You are at a valley.");

		controller.setLongDescription(0, "You are at a plateau. To the north is a hill. To the west is a desert. To the east is a canyon. To the south is a forest.");
		controller.setLongDescription(1, "You are at a canyon. To the north is a marsh. To the west is a plateau. To the east is a desert. To the south is a campsite.");
		controller.setLongDescription(2, "You are at a desert. To the north is a valley. To the west is a canyon. To the east is a plateau. To the south is a glacier.");
		controller.setLongDescription(3, "You are at a forest. To the north is a plateau. To the west is a glacier. To the east is a campsite. To the south is a hill.");
		controller.setLongDescription(5, "You are at a glacier. To the north is a desert. To the west is a campsite. To the east is a forest. To the south is a valley.");
		controller.setLongDescription(6, "You are at a hill. To the north is a forest. To the west is a valley. To the east is a marsh. To the south is a plateau.");
		controller.setLongDescription(7, "You are at a marsh. To the north is a campsite. To the west is a hill. To the east is a valley. To the south is a canyon.");
		controller.setLongDescription(8, "You are at a valley. To the north is a glacier. To the west is a marsh. To the east is a hill. To the south is a desert.");
		
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
		
		
		controller.setLongDescription(4, "This is just a small starting map, some rooms could be tied together through different paths. You can move through rooms using commands such as north, south, east or west. You are at a small campsite, starting with only 25 dollas, you must get your money up.");
		controller.setShortDescription(4, "A small campsite stands in an large open area.");
		
		
		controller.setShortDescription(0, "You are at a plateau.");
		controller.setShortDescription(1, "You are at a canyon.");
		controller.setShortDescription(2, "You are at a desert.");
		controller.setShortDescription(3, "You are at a forest.");
		controller.setShortDescription(5, "You are at a glacier.");
		controller.setShortDescription(6, "You are at a hill.");
		controller.setShortDescription(7, "You are at a marsh.");
		controller.setShortDescription(8, "You are at a valley.");

		controller.setLongDescription(0, "You are at a plateau. To the north is a hill. To the west is a desert. To the east is a canyon. To the south is a forest.");
		controller.setLongDescription(1, "You are at a canyon. To the north is a marsh. To the west is a plateau. To the east is a desert. To the south is a campsite.");
		controller.setLongDescription(2, "You are at a desert. To the north is a valley. To the west is a canyon. To the east is a plateau. To the south is a glacier.");
		controller.setLongDescription(3, "You are at a forest. To the north is a plateau. To the west is a glacier. To the east is a campsite. To the south is a hill.");
		controller.setLongDescription(5, "You are at a glacier. To the north is a desert. To the west is a campsite. To the east is a forest. To the south is a valley.");
		controller.setLongDescription(6, "You are at a hill. To the north is a forest. To the west is a valley. To the east is a marsh. To the south is a plateau.");
		controller.setLongDescription(7, "You are at a marsh. To the north is a campsite. To the west is a hill. To the east is a valley. To the south is a canyon.");
		controller.setLongDescription(8, "You are at a valley. To the north is a glacier. To the west is a marsh. To the east is a hill. To the south is a desert.");
		
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
	
	