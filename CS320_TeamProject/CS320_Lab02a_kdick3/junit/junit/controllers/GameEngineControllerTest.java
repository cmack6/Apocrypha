package junit.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.GameModel;
import models.Item;
import models.NPC;
import models.User;
import controllers.GameEngineController;


public class GameEngineControllerTest {

private GameModel model;
private GameEngineController controller;
private User user;



@Before
public void setUp() {
	 user = new User(4, 0);
	 model = new GameModel(user, "log");
	 controller = new GameEngineController(model);
	
	
	
	//populates the RoomID for each room, it corresponds with their index just for now, will change eventually^^ same with that above
	for(int i=0; i<9; i++) {
		controller.setRoomID(i, i);
	}
	
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
	
	
	
	//when the jsp is first created, the first model created will be setModel, getting passed
	//the user created above and the first long description of the starting room being the first log
	 
	
	Item sword = new Item("Moonveil", -1, 150, 0);
	Item torch = new Item("Flashlight", -1, 25, 1);
	Item shield = new Item("Ketheric's Shield", 4, 125, 2);
	Item potion = new Item("Health Potion", 7, 10, 3);
	controller.addItem(model,sword);
	controller.addItem(model, torch);
	controller.addItem(model, shield);
	controller.addItem(model, potion);
	
	NPC John = new NPC(1,0);
	controller.addNPC(model, John);
	
	
}

@Test
public void testMove() {
	controller.processInput(model, "north");
	controller.processInput(model, "south");
	controller.processInput(model, "east");
	controller.processInput(model, "south");
	controller.processInput(model, "east");
	assertEquals(6, user.getRoomID());

	
}

@Test
public void testSetRoomID() {
	controller.setRoomID(0, 5);
	assertEquals(5, model.Rooms.get(0).getRoomID());
}

@Test 
public void testSetRoomConnections() {
	controller.setRoomConnections(0, 2, 2, 3, 4);
	assertEquals(2, model.Rooms.get(0).getRoomConnectionNorth());
}

@Test
public void testSetLongDescription() {
	controller.setLongDescription(0, "New long description");
	assertTrue(model.Rooms.get(0).getLongDescription().equals("New long description"));
	
}

@Test
public void testSetShortDescription() {
	controller.setShortDescription(0, "New short description");
	assertTrue(model.Rooms.get(0).getShortDescription().equals("New short description"));
	
}

@Test
public void testSetItemDescription() {
	controller.setItemDescription(0, "new description");
	assertTrue(model.Items.get(0).getItemDescription().equals("new description"));
}

@Test
public void testSetRoomDescription() {
	controller.setRoomDescription(0, "new description");
	assertTrue(model.Items.get(0).getRoomDescription().equals("new description"));
}

@Test
public void testNPCInteraction() {
	controller.setNPCInteraction(0, "hello");
	assertTrue(model.NPCs.get(0).NPCInteraction.equals("hello"));
}

@Test
public void testSetNPCDialogue() {
	controller.setNPCDialogue(0, "hello");
	assertTrue(model.NPCs.get(0).NPCDialogue.equals("hello"));
}

@Test
public void testProcessInputMove() {
	controller.processInput(model, "north");
	assertEquals(model.getUser().getRoomID(), 1);
}

@Test
public void testProcessInputTalk() {
	//odd to test because its an output to the console
	
	
}

@Test
public void testInventory() {
	
	
}

@Test
public void testProcessInputHelp() {

}

@Test
public void testProcessInputTake() {
	controller.processInput(model, "take Ketheric's Shield");
	assertTrue(model.Items.get(2).getName().equals("Ketheric's Shield"));
	
}

@Test
public void testGetOutput() {
	
}

@Test
public void testAddItem() {
	Item newItem = new Item("newItem", -1, 100, 4);
	controller.addItem(model, newItem);
	assertTrue(model.Items.get(4).getName().equals("newItem"));
}



@Test
public void testAddNPC() {
	NPC newNPC = new NPC(2, 1);
	controller.addNPC(model, newNPC);
	controller.setNPCInteraction(1, "dialogue");
	assertTrue(model.NPCs.get(1).getNPCInteraction().equals("dialogue"));
}
}
		
	
	