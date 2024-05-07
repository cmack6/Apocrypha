package edu.ycp.cs320.booksdb.persist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ycp.cs320.booksdb.model.*;

public class DerbyDatabaseTests {

	private IDatabase db = null;
	
	ArrayList<Author> authors = null;
	ArrayList<Book>   books   = null;
	ArrayList<Item>   items = null;
	ArrayList<Item>   inventory = null;
	ArrayList<Room>   rooms = null;
	ArrayList<User>   users = null;
	ArrayList<Player> players = null;
	ArrayList<NPC>    npcs = null;
	ArrayList<RoomConnection> roomConnections = null;
	List<Pair<Author, Book>> bookAuthorList = null;
	List<Pair<Author, Book>> authorBookList = null;	
	List<User> userList = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAllItems() {

		System.out.println("\n*** Testing findAllItems ***");

		// get the list of (Author, Book) pairs from DB
		List<Item> itemList = db.findAllItems();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (itemList.isEmpty()) {
			System.out.println("No items found in library");
			fail("No items returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			items = new ArrayList<Item>();
			for (Item item : itemList) {
				items.add(item);
				System.out.println(item.getItemID() + ", " + item.getName() + ", " + item.getContainerID() + ", " + item.getValue() + ", " + item.getItemDescription() + ", " + item.getUseDescription() + ", " + item.getCombatDescription() + ", " + item.getCategory() + ", " + item.getArmorType() + ", " + item.getDefenseNumber() + ", " + item.getEffectType() + ", " + item.getEffectLow() + ", " + item.getEffectHigh() + ", " + item.getGameID());
			}			
		}
	}
	
	@Test
	public void testFindAllRooms() {

		System.out.println("\n*** Testing findAllRooms ***");

		// get the list of (Author, Book) pairs from DB
		List<Room> roomList = db.findAllRooms();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (roomList.isEmpty()) {
			System.out.println("No rooms found in library");
			fail("No rooms returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			rooms = new ArrayList<Room>();
			for (Room room : roomList) {
				rooms.add(room);
				System.out.println(room.getRoomID() + ", " + room.getLongDescription() + ", " + room.getShortDescription() + ", " + room.getGameID());
			}			
		}
	}
	
	@Test
	public void testFindAllPlayers() {

		System.out.println("\n*** Testing findAllPlayers ***");

		// get the list of (Author, Book) pairs from DB
		List<Player> playerList = db.findAllPlayers();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (playerList.isEmpty()) {
			System.out.println("No players found in library");
			fail("No players returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			players = new ArrayList<Player>();
			for (Player player : playerList) {
				players.add(player);
				System.out.println(player.getPlayerID() + ", " + player.getScore() + ", " + player.getHealth() + ", " + player.getRoomID() + ", " + player.getGameID() + ", " + player.getUserID());
			}			
		}
	}
	
	@Test
	public void testFindAllNPCs() {

		System.out.println("\n*** Testing findAllNPCs ***");

		// get the list of (Author, Book) pairs from DB
		List<NPC> npcList = db.findAllNPCs();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (npcList.isEmpty()) {
			System.out.println("No NPCs found in library");
			fail("No NPCs returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			npcs = new ArrayList<NPC>();
			for (NPC npc : npcList) {
				npcs.add(npc);
				System.out.println(npc.getNPCID() + ", " + npc.getRoomDialogue() + ", " + npc.getSpeakDialogue() + ", " + npc.getRoomID() + ", " + npc.getHealth() + ", " + npc.getGameID());
			}			
		}
	}
	
	@Test
	public void testFindAllContainers() {

		System.out.println("\n*** Testing findAllContainers ***");

		// get the list of (Author, Book) pairs from DB
		List<Container> containerList = db.findAllContainers();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (containerList.isEmpty()) {
			System.out.println("No containers found in library");
			fail("No containers returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			ArrayList<Container> containers = new ArrayList<Container>();
			for (Container container : containerList) {
				containers.add(container);
				System.out.println(container.getContainerID() + ", " + container.getName() + ", " + container.getRoomID() + ", " + container.getContainerDescription() + ", " + container.getInRoomDescription() + ", " + container.getGameID() );
			}			
		}
	}
	
	@Test
	public void testFindInventory() {

		System.out.println("\n*** Testing findInventory ***");

		// get the list of (Author, Book) pairs from DB
		List<Item> inventoryList = db.findInventory();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (inventoryList.isEmpty()) {
			System.out.println("No items found in library");
			fail("No items returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			inventory = new ArrayList<Item>();
			for (Item item : inventoryList) {
				inventory.add(item);
				System.out.println(item.getItemID() + ", " + item.getName() + ", " + item.getContainerID() + ", " + item.getValue() + ", " + item.getItemDescription() + ", " + item.getUseDescription() + ", " + item.getCombatDescription() + ", " + item.getCategory() + ", " + item.getArmorType() + ", " + item.getDefenseNumber() + ", " + item.getEffectType() + ", " + item.getEffectLow() + ", " + item.getEffectHigh() + ", " + item.getGameID());
			}			
		}
	}
	
	@Test
	public void testgetPlayerFromGameID() {
		System.out.println("\n*** Testing getPlayerFromGameID ***");

		int gameID = 1;
		
		Player player = db.getPlayerFromGameID(gameID);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (player.equals(null)) {
			System.out.println("No player found for ID <" + gameID + ">");
			fail("No player for ID <" + gameID + "> returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
				System.out.println(player.getPlayerID() + "," + player.getScore() + "," + player.getHealth() + ", " + player.getRoomID() + ", " + player.getGameID() + ", " + player.getUserID());
		}			
	}
	
	@Test
	public void testGetRoomConnectionsByRoomID() {
		System.out.println("\n*** Testing GetRoomConnectionsByRoomID ***");

		int roomID = 1;
		
		List<RoomConnection> roomConnectionList = db.getRoomConnectionsByRoomID(roomID);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (roomConnectionList.isEmpty()) {
			System.out.println("No room connection found for ID <" + roomID + ">");
			fail("No room connections for ID <" + roomID + "> returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			roomConnections = new ArrayList<RoomConnection>();
			for (RoomConnection connection : roomConnectionList) {
				roomConnections.add(connection);
				System.out.println(connection.getStartingRoomID() + "," + connection.getCommand() + "," + connection.getDestinationRoomID());
			}			
		}
	}
	
	@Test
	public void testupdatePlayer() {
		System.out.println("\n*** Testing updatePlayer ***");
		
		Player testPlayer = new Player();
		testPlayer.setScore(90);
		testPlayer.setHealth(60);
		testPlayer.setRoomID(5);
		testPlayer.setLog("Testing");
		testPlayer.setPlayerID(2);
		
		Player player = db.updatePlayer(testPlayer);
		
		assertEquals(90, player.getScore());
		assertEquals(60, player.getHealth());
		assertEquals(5, player.getRoomID());
		assertEquals("Testing", player.getLog());
		assertEquals(2, player.getPlayerID());
		
		System.out.println(player.getPlayerID() + "," + player.getScore() + "," + player.getHealth() + ", " + player.getRoomID() + ", " + player.getGameID() + ", " + player.getUserID());
	}
	
	@Test
	public void testGetRoomListByGameID() {
		System.out.println("\n*** Testing getRoomListByGameID ***");

		int gameID = 1;
		
		List<Room> roomList = db.getRoomListByGameID(gameID);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (roomList.isEmpty()) {
			System.out.println("No room connection found for ID <" + gameID + ">");
			fail("No room connections for ID <" + gameID + "> returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			rooms = new ArrayList<Room>();
			for (Room room : roomList) {
				rooms.add(room);
				System.out.println(room.getRoomID() + "," + room.getLongDescription() + "," + room.getShortDescription() + ", " + room.getGameID());
			}			
		}
	}
	
	@Test
	public void testFindRoomConnections() {

		System.out.println("\n*** Testing findRoomConnections ***");

		// get the list of (Author, Book) pairs from DB
		List<RoomConnection> roomConnectionList = db.findRoomConnections();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (roomConnectionList.isEmpty()) {
			System.out.println("No room connections found in library");
			fail("No room connections returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			roomConnections = new ArrayList<RoomConnection>();
			for (RoomConnection connection : roomConnectionList) {
				roomConnections.add(connection);
				System.out.println(connection.getStartingRoomID() + ", " + connection.getCommand() + ", " + connection.getDestinationRoomID());
			}			
		}
	}
	
	@Test
	public void testupdateNPC() {
		System.out.println("\n*** Testing updateNPC ***");
		
		NPC testNPC = new NPC();
		testNPC.setHealth(45);
		testNPC.setNPCID(2);
		
		NPC npc = db.updateNPC(testNPC);
		
		assertEquals(45, npc.getHealth());
		assertEquals(2, npc.getNPCID());
		
		System.out.println(npc.getNPCID() + "," + npc.getRoomDialogue() + "," + npc.getSpeakDialogue() + ", " + npc.getRoomID() + ", " + npc.getHealth()+ ", " + npc.getGameID());
	}
	
	@Test
	public void testupdateItem() {
		System.out.println("\n*** Testing updateItem ***");
		
		Item testItem = new Item();
		testItem.setContainerID(-1);
		testItem.setItemID(4);
		
		Item item = db.updateItem(testItem);
		
		assertEquals(-1, item.getContainerID());
		assertEquals(4, item.getItemID());
		
		System.out.println(item.getItemID() + ", " + item.getName() + ", " + item.getContainerID() + ", " + item.getValue() + ", " + item.getItemDescription() + ", " + item.getUseDescription() + ", " + item.getCombatDescription() + ", " + item.getCategory() + ", " + item.getArmorType() + ", " + item.getDefenseNumber() + ", " + item.getEffectType() + ", " + item.getEffectLow() + ", " + item.getEffectHigh() + ", " + item.getGameID());
	}
	
	@Test
	public void testInsertNewUser() {
		System.out.println("\n*** Testing insertNewUser ***");

		String username     = "Jwaight";
		String password      = "321";
  
		User testUser = new User();
				
		testUser = db.insertNewUser(username, password);

		assertEquals("Jwaight", testUser.getUsername());
		assertEquals("321", testUser.getPassword());
		
		System.out.println(testUser.getUserID() + ", " + testUser.getUsername() + ", " + testUser.getPassword());
		
		// Removing new user
		List<User> removeNewUser = new ArrayList<User>();
		removeNewUser = db.removeUserByUserId(testUser.getUserID());
		
		//Making sure user was removed
		System.out.println("Making Sure new user was removed");
		
		userList = new ArrayList<User>();
		for (User user : removeNewUser) {
			userList.add(user);
			System.out.println(user.getUserID() + ", " + user.getUsername() + ", " + user.getPassword());
		}
	}
	
	@Test
	public void testFindAllUsers() {

		System.out.println("\n*** Testing findAllUsers ***");

		// get the list of (Author, Book) pairs from DB
		List<User> userList = db.findAllUsers();

		// NOTE: this is a simple test to check if no results were found in the DB
		if (userList.isEmpty()) {
			System.out.println("No users found in library");
			fail("No users returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			users = new ArrayList<User>();
			for (User user : userList) {
				users.add(user);
				System.out.println(user.getUserID() + ", " + user.getUsername() + ", " + user.getPassword());
			}			
		}
	}
	
	@Test
	public void testCreateNewGame() {
		System.out.println("\n*** Testing createNewGame ***");
		
		Player testPlayer = new Player();
		testPlayer.setUserID(7);
		
		Player player = db.createNewGame(testPlayer.getUserID());
		
		assertEquals(1, player.getPlayerID());
		assertEquals(100, player.getScore());
		assertEquals(75, player.getHealth());
		assertEquals(0, player.getRoomID());
		assertEquals(2, player.getUserID());
		assertEquals("Welcome to Apocrypha! Tiny hint- try moving.", player.getLog());
		
		System.out.println(player.getPlayerID() + "," + player.getScore() + "," + player.getHealth() + ", " + player.getRoomID() + ", " + player.getGameID() + ", " + player.getUserID());
	}
	
	/*@Test
	public void testRestartGame(){
		System.out.println("\n*** Testing createNewGame ***");
		
		Player testPlayer = new Player();
		testPlayer.setUserID(2);
		
		Player player = db.createNewGame(testPlayer.getUserID());
		
		assertEquals(1, player.getPlayerID());
		assertEquals(100, player.getScore());
		assertEquals(75, player.getHealth());
		assertEquals(0, player.getRoomID());
		assertEquals(2, player.getUserID());
		assertEquals("Welcome to Apocrypha! Tiny hint- try moving.", player.getLog());
		
		System.out.println(player.getPlayerID() + "," + player.getScore() + "," + player.getHealth() + ", " + player.getRoomID() + ", " + player.getGameID() + ", " + player.getUserID());
	}*/
}
