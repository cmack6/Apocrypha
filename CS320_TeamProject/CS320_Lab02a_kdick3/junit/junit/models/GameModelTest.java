package junit.models;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.GameModel;
import models.Item;
import models.NPC;
import controllers.GameEngineController;
import models.User;


public class GameModelTest {

private GameModel model;
private GameEngineController controller;
private User user;



@Before
public void setUp() {
	 model = new GameModel();
	
	 controller = new GameEngineController(model);
	
	
	
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
	
	
	controller.setLongDescription(4, "This is just a small starting map, some rooms could be tied together through different paths. You can move through rooms using commands such as north, south, east or west. You are at a small campsite, starting with only 25 dollas, you must get your money up.");
	controller.setShortDescription(4, "A small campsite stands in an large open area. A shield lies on the ground.");
	
	
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
	
	
}

@Test
public void testSetAction() {
	String input = "north";
	model.setAction(input);
	assertTrue(model.getAction().equals("north"));

	
}


@Test
public void testSetInvalidObjectInteraction() {
	model.setInvalidObjectInteraction("potion");
	assertTrue(model.getError().equals("potion is not interactable"));
}

@Test
public void testGetError() {
	assertTrue(model.getError().equals("working"));
}



}