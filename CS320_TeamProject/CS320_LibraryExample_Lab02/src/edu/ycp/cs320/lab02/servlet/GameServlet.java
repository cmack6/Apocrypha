package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import controllers.GameEngineController;
//import models.GameModel;
//import models.Item;
//import models.NPC;
//import models.User;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.lab02.controller.GameEngineController;
import edu.ycp.cs320.lab02.model.GameModel;
//import edu.yc

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
		
		
		controller.setLongDescription(4, "This is Apocrypha! Your goal is to collect to the highest score you can! Use the command *help* to see how to play! You begin at a small campsite, starting with only 25 dollas, you must get your money up.");
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
		
		
		//when the jsp is first created, the first model created will be setModel, getting passed
		//the user created above and the first long description of the starting room being the first log
		GameModel setModel = new GameModel(user,model.Rooms.get(4).getLongDescription());
		
		Item sword = new Item("Moonveil", -1, 150, 0);
		Item torch = new Item("Flashlight", -1, 25, 1);
		Item shield = new Item("Ketheric's Shield", 4, 125, 2);
		Item potion = new Item("Health Potion", 7, 10, 3);
		controller.addItem(setModel,sword);
		controller.addItem(setModel, torch);
		controller.addItem(setModel, shield);
		controller.addItem(setModel, potion);
		
		NPC John = new NPC(1,0);
		controller.addNPC(setModel, John);
		
		//sets what log is to display in the jsp model.log
		req.setAttribute("model", setModel);
		
		//sets input for loading the page for the first time to nothing
		String input = "";
		
		//puts nothing basically onto the jsp
		req.setAttribute("input",input);
		
		//prints description of the room the user is in depending if the user has entered already or not
		//if first time = long description. if second time = short description
		setModel.Rooms.get(setModel.getUser().getRoomID()).getDescription();
		
		//universally unique identifier = UUID, this gets a random string of 36 characters, this
		//basically sets the modelString as this unique string
		String modelString = UUID.randomUUID().toString();
		
		//req.getSession() calls the current session, and if there isnt one, it creates one
		//this line calls the current session and sets 
		
		
		
		//?
		req.getSession().setAttribute("modelString", setModel);
		
		
		
		
		//?
		req.setAttribute("modelString", modelString);
		
		//
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
		System.out.println();
		String input = req.getParameter("input");
	//	System.out.println(input);
		GameEngineController controller = new GameEngineController(model);
		
		for(int i=0; i<9; i++) {
			controller.setRoomID(i, i);
		}
		
		
		
		controller.setLongDescription(4, "This is Apocrypha! Your goal is to collect to the highest score you can! You begin at a small campsite, starting with only 25 dollas, you must get your money up.");
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
		
		controller.setItemDescription(0, "The Moonveil. I honestly have no clue what this does but it's like insanely sharp so prolly use with caution or something.");
		controller.setItemDescription(1, "A classic. Lights the way so you can see. You can use it in dark areas.");
		controller.setItemDescription(2, "Ketheric Thorm's shield. One of the Chosen Three of the Absolute as well as a Chosen of Myrkul. Bro was a half-elf who lost his shield which is a pretty good deterent against projectiles...");
		controller.setItemDescription(3, "Yet another classic. You drink this thing and it'll restore some of your health, simple.");
		controller.setRoomDescription(2, "Ketheric's Shield lies on the ground, it wants to be picked up.");
		controller.setRoomDescription(3, "A Health Potion lies on it's side.");
		
		controller.setNPCInteraction(0,"You see a decrepit old man. He grumbles nothing but the words, \"Get your money up.\"");
		controller.setNPCDialogue(0, "\"I'm Old Man John, and... you disgust me. You and your foul torch and stupid looking sword. The only thing that has value in this world is... money! And you got none of that, do ya? Broke boy, lil baby guy. Get ya money up sonny!\"");
		
		
		
		
		
		String output = controller.processInput(model,input); 
		
		System.out.println("after move:" + model.getUser().getRoomID());
		String newLog;
		if(model.getError().equals("working")) {
			newLog = model.getLog() + "<p>" + input + "</p><p>" + output + "</p>";

		}
		else {
			newLog = model.getLog() + "<p>" + input + "</p><p>" + model.getError() + "</p>";
			model.setError("working");
		}
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
	
	