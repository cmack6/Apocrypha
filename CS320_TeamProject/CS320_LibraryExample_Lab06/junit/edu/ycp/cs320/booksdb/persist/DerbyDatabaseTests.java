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
				System.out.println(item.getItemID() + ", " + item.getName() + ", " + item.getLocation() + ", " + item.getValue() + ", " + item.getItemDescription() + ", " + item.getRoomDescription() + ", " + item.getGameID());
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
				System.out.println(item.getItemID() + ", " + item.getName() + ", " + item.getLocation() + ", " + item.getValue() + ", " + item.getItemDescription() + ", " + item.getRoomDescription() + ", " + item.getGameID());
			}			
		}
	}
	
	@Test
	public void testgetPlayerFromGameID() {
		System.out.println("\n*** Testing getPlayerFromGameID ***");

		int gameID = 2;
		
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
		testPlayer.setPlayerID(3);
		
		Player player = db.updatePlayer(testPlayer);
		
		assertEquals(90, player.getScore());
		assertEquals(60, player.getHealth());
		assertEquals(5, player.getRoomID());
		assertEquals("Testing", player.getLog());
		assertEquals(3, player.getPlayerID());
		
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
		testItem.setLocation(-1);
		testItem.setItemID(4);
		
		Item item = db.updateItem(testItem);
		
		assertEquals(-1, item.getLocation());
		assertEquals(4, item.getItemID());
		
		System.out.println(item.getItemID() + "," + item.getName() + "," + item.getLocation() + ", " + item.getValue() + ", " + item.getItemDescription() + ", " + item.getRoomDescription() + ", " + item.getGameID());
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
	public void testInsertBookIntoBooksTable() {
		System.out.println("\n*** Testing insertBookIntoBooksTable ***");

		String title     = "Wired for War";
		String isbn      = "0-143-11684-3";
		int    published = 2009;
		String lastName  = "Singer";
		String firstName = "P.J.";
  
				
		// insert new book (and possibly new author) into DB
		Integer book_id = db.insertBookIntoBooksTable(title, isbn, published, lastName, firstName);

		// check the return value - should be a book_id > 0
		if (book_id > 0)
		{
			// try to retrieve the book and author from the DB
			// get the list of (Author, Book) pairs from DB
			authorBookList = db.findAuthorAndBookByAuthorLastName(lastName);
			
			if (authorBookList.isEmpty()) {
				System.out.println("No books found for author <" + lastName + ">");
				fail("Failed to insert new book <" + title + "> into Library DB");
			}
			// otherwise, the test was successful.  Now remove the book just inserted to return the DB
			// to it's original state, except for using an author_id and a book_id
			else {
				System.out.println("New book (ID: " + book_id + ") successfully added to Books table: <" + title + ">");
				
				// now delete Book (and its Author) from DB
				// leaving the DB in its previous state - except that an author_id, and a book_id have been used
				List<Author> authors = db.removeBookByTitle(title);				
			}
		}
		else
		{
			System.out.println("Failed to insert new book (ID: " + book_id + ") into Books table: <" + title + ">");
			fail("Failed to insert new book <" + title + "> into Library DB");
		}
	}

	@Test
	public void testRemoveBookByTitle() {
		System.out.println("\n*** Testing removeBookByTitle ***");
		
		String title     = "Outliers";
		String isbn      = "0-316-01793-0";
		int    published = 2010;		
		String lastName  = "Gladwell";
		String firstName = "Malcolm";
				
		// insert new book (and new author) into DB
		Integer book_id = db.insertBookIntoBooksTable(title, isbn, published, lastName, firstName);
		
		// check to see that insertion was successful before proceeding
		if (book_id > 0) {
			// now delete Book (and its Author) from DB
			List<Author> authors = db.removeBookByTitle(title);
			
			if (authors.isEmpty()) {
				System.out.println("Failed to remove Author(s) for book with title <" + title + ">");
				fail("No Author(s) removed from DB for Book with title <" + title + ">");
			}
			else {
				System.out.println("Author <" + authors.get(0).getLastname() + ", " + authors.get(0).getFirstname() + "> removed from Library DB");
			}					
			
			// get the list of (Author, Book) pairs from DB
			authorBookList = db.findAuthorAndBookByTitle(title);
			
			if (authorBookList.isEmpty()) {
				System.out.println("All Books with title <" + title + "> were removed from the Library DB");
			}
			else {
				fail("Book with title <" + title + "> remains in Library DB after delete");			
			}
		}
		else {
			System.out.println("Failed to insert new book (ID: " + book_id + ") into Books table: <" + title + ">");
			fail("Failed to insert new book <" + title + "> into Library DB");			
		}
	}
}
