package edu.ycp.cs320.booksdb.persist;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.*;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	// transaction that retrieves a Book, and its Author by Title
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					stmt.setString(1, title);
					
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + title + "> was not found in the books table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves a list of Books with their Authors, given Author's last name
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(final String lastName) {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Authors and Books based on Author's last name, passed into query
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where authors.lastname = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc, books.published asc"
					);
					stmt.setString(1, lastName);
					
					// establish the list of (Author, Book) Pairs to receive the result
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					// execute the query, get the results, and assemble them in an ArrayLsit
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves all Books in Library, with their respective Authors
	@Override
	public List<Pair<Author, Book>> findAllBooksWithAuthors() {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from authors, books, bookAuthors " +
							"  where authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc"
					);
					
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					// check if any books were found
					if (!found) {
						System.out.println("No books were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}	
	
	
	// transaction that retrieves all Authors in Library
	@Override
	public List<Author> findAllAuthors() {
		return executeTransaction(new Transaction<List<Author>>() {
			@Override
			public List<Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from authors " +
							" order by lastname asc, firstname asc"
					);
					
					List<Author> result = new ArrayList<Author>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						
						result.add(author);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No authors were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<User> findAllUsers() {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from users "
					);
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result.add(user);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No users were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Player getPlayerFromGameID(int gameID) {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		List<Player> playerList = db.findAllPlayers();
		Player player = new Player();
		for(int i=0;i<playerList.size();i++) {
			if(playerList.get(i).getGameID()==gameID) {
				player=playerList.get(i);
				i+=playerList.size();
			}
		}
		return player;
	}
	
	
	@Override
	public List<Player> findAllPlayers() {
		return executeTransaction(new Transaction<List<Player>>() {
			@Override
			public List<Player> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from players "
					);
					
					List<Player> result = new ArrayList<Player>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Player player = new Player();
						loadPlayer(player, resultSet, 1);
						
						result.add(player);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No players were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<NPC> findAllNPCs() {
		return executeTransaction(new Transaction<List<NPC>>() {
			@Override
			public List<NPC> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from NPCs "
					);
					
					List<NPC> result = new ArrayList<NPC>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						NPC npc = new NPC();
						loadNPC(npc, resultSet, 1);
						
						result.add(npc);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No NPCs were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	
	@Override
	public Player updatePlayer(Player player) {
		return executeTransaction(new Transaction<Player>() {
			@Override
			public Player execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				try {
					stmt1 = conn.prepareStatement(
							"update players  " +
							"  set score = ?, health = ?, roomID = ?, log = ?, isInCombat = ?  " +
							"  where player_id = ? "
					);
					
					
					/*stmt9 = conn.prepareStatement(
							"create table players (" +
							"	player_id integer primary key" +
							"		generated always as identity (start with 1, increment by 1), " +
							"	score integer," +
							"	health integer," +
							"	roomID integer," +
							"	gameID integer," +
							"	userID integer," +
							"	log clob" +
							")"
					);
						*/	
					stmt1.setInt(1, player.getScore());
					stmt1.setInt(2, player.getHealth());
					stmt1.setInt(3, player.getRoomID());
					
					System.out.println(player.getLog());
					Clob myClob = new javax.sql.rowset.serial.SerialClob(player.getLog().toCharArray());
					stmt1.setClob(4, myClob);
					
					stmt1.setBoolean(5, player.isInCombat());
					
					stmt1.setInt(6, player.getPlayerID());

					
					Player result = new Player();
					
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"select * from players  " +
							"  where player_id = ?"
					);
					stmt2.setInt(1, player.getPlayerID());
					
					
					resultSet = stmt2.executeQuery();
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						loadPlayer(result,resultSet,1);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No players were found in the database for the specified playerID");
						result = null;
					}
					else {
						System.out.println("Player updated with ID "+result.getPlayerID());
					}
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	
	@Override
	public Player createNewGame(int userID) {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		return executeTransaction(new Transaction<Player>() {
			@Override
			
			public Player execute(Connection conn) throws SQLException {
				List<Item> itemList;
				List<Room> roomList;
				List<RoomItem> roomItemList;
				List<Player> playerList;
				List<Player> newPlayerList;
				List<RoomConnection> roomConnectionList;
				List<NPC> NPCList;
				List<Container> containerList;
				List<RoomContainer> roomContainerList;
				List<ContainerItem> containerItemList;
				try {
				itemList           = InitialData.getItems();
				roomList           = InitialData.getRooms();
				roomItemList       = InitialData.getRoomItems();
				playerList         = InitialData.getPlayers();
				roomConnectionList = InitialData.getRoomConnections();
				NPCList            = InitialData.getNPCs();
				containerList      = InitialData.getContainers();
				roomContainerList  = InitialData.getRoomContainers();
				containerItemList  = InitialData.getContainersItems();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read CSV data", e);
				}
				
				PreparedStatement insertItem           = null;
				PreparedStatement insertRoom           = null;
				PreparedStatement insertRoomItem       = null;
				PreparedStatement insertPlayer         = null;
				PreparedStatement insertRoomConnection = null;
				PreparedStatement insertNPC            = null;
				PreparedStatement insertContainer      = null;
				PreparedStatement insertRoomContainer  = null;
				PreparedStatement insertContainerItem  = null;
				
				
				try { 
					Player player = playerList.get(0);
					newPlayerList = db.findAllPlayers();
					player.setGameID(newPlayerList.size()+1);
					player.setUserID(userID);
					insertPlayer = conn.prepareStatement("insert into players (score, health, roomID, gameID, userID, isInCombat, log) values (?, ?, ?, ?, ?, ?, ?)");
					System.out.println(player.getScore() + "" + player.getHealth() + "" + player.getRoomID() + "" + (playerList.size()+1) + "" + player.getUserID() + "" + player.isInCombat() + "" + player.getLog());
					insertPlayer.setInt(1, player.getScore());
					insertPlayer.setInt(2, player.getHealth());
					insertPlayer.setInt(3, player.getRoomID());
					insertPlayer.setInt(4, player.getGameID());
					insertPlayer.setInt(5, player.getUserID());
					insertPlayer.setBoolean(6, player.isInCombat());
					Clob myClob = new javax.sql.rowset.serial.SerialClob(player.getLog().toCharArray());
					insertPlayer.setClob(7, myClob);
					insertPlayer.executeUpdate();
					System.out.println("new player inserted");	
					
					insertRoom = conn.prepareStatement("insert into rooms (roomID, longDescription, shortDescription, gameID) values (?, ?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setInt(1,  room.getRoomID());
						insertRoom.setString(2,  room.getLongDescription());
						insertRoom.setString(3,  room.getShortDescription());
						insertRoom.setInt(4,  player.getGameID());
						
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();
					
					System.out.println("Rooms table updated");
					
					insertRoomConnection = conn.prepareStatement("insert into roomConnections (startingRoomID, command, destinationRoomID, gameID) values (?, ?, ?, ?)");
					for (RoomConnection roomConnection: roomConnectionList) {
						insertRoomConnection.setInt(1, roomConnection.getStartingRoomID());
						insertRoomConnection.setString(2, roomConnection.getCommand());
						insertRoomConnection.setInt(3, roomConnection.getDestinationRoomID());
						insertRoomConnection.setInt(4, player.getGameID());
						insertRoomConnection.addBatch();
					}
					insertRoomConnection.executeBatch();	
					
					System.out.println("roomConnections table updated");	
					
					
					insertNPC = conn.prepareStatement("insert into NPCs (npcID, roomDialogue, speakDialogue, roomID, health, name, gameID) values (?, ?, ?, ?, ?, ?, ?)");
					for (NPC npc: NPCList) {
						insertNPC.setInt(1,npc.getNPCID());
						insertNPC.setString(2, npc.getRoomDialogue());
						insertNPC.setString(3, npc.getSpeakDialogue());
						insertNPC.setInt(4, npc.getRoomID());
						insertNPC.setInt(5, npc.getHealth());
						
						insertNPC.setString(6, npc.getName());
						
						insertNPC.setInt(7, player.getGameID());
						insertNPC.addBatch();
					}
					insertNPC.executeBatch();	
					
					System.out.println("NPCs table updated");	
					
					
					insertContainer = conn.prepareStatement("insert into containers (name, roomID, containerDescription, inRoomDescription, isOpened, gameID) values (?, ?, ?, ?, ?, ?)");
					for (Container container : containerList) {
					//insertAuthor.setInt(1, author.getAuthorId()); // auto-generated primary key, don't insert this
					insertContainer.setString(1, container.getName());
					insertContainer.setInt(2, container.getRoomID());
					insertContainer.setString(3, container.getContainerDescription());
					insertContainer.setString(4, container.getInRoomDescription());
					insertContainer.setBoolean(5, container.isOpened());
<<<<<<< Updated upstream
					insertContainer.setInt(6, player.getGameID());
=======
					insertContainer.setInt(6, container.getGameID());
>>>>>>> Stashed changes

					insertContainer.addBatch();
					}
					insertContainer.executeBatch();

					System.out.println("containers table updated");



					insertContainerItem = conn.prepareStatement("insert into containerItems (itemName, containerID, gameID) values (?, ?, ?)");
					for (ContainerItem containerItem : containerItemList) {
					//insertAuthor.setInt(1, author.getAuthorId()); // auto-generated primary key, don't insert this
					insertContainerItem.setString(1, containerItem.getItemName());
					insertContainerItem.setInt(2, containerItem.getContainerID());
<<<<<<< Updated upstream
					insertContainerItem.setInt(3, player.getGameID());
=======
					insertContainerItem.setInt(3, containerItem.getGameID());
>>>>>>> Stashed changes
					insertContainerItem.addBatch();
					}
					insertContainerItem.executeBatch();

					System.out.println("containerItems table updated");



					insertRoomContainer = conn.prepareStatement("insert into roomContainers (containerID, roomID, gameID) values (?, ?, ?)");
					for (RoomContainer roomContainer : roomContainerList) {
					//insertAuthor.setInt(1, author.getAuthorId()); // auto-generated primary key, don't insert this
					insertRoomContainer.setInt(1, roomContainer.getContainerID());
					insertRoomContainer.setInt(2, roomContainer.getRoomID());
<<<<<<< Updated upstream
					insertRoomContainer.setInt(3, player.getGameID());
=======
					insertRoomContainer.setInt(3, roomContainer.getGameID());
>>>>>>> Stashed changes
					insertRoomContainer.addBatch();
					}
					insertRoomContainer.executeBatch();

					System.out.println("roomContainers table updated");
<<<<<<< Updated upstream
					
					insertItem = conn.prepareStatement("insert into items (itemID, name, type, containerID, value, itemDescription, useDescription, combatDescription, isEquipped, category, armorType, defenseNumber, effectType, effectLow, effectHigh, gameID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						
						insertItem.setInt(1, item.getItemID());
						insertItem.setString(2, item.getName());
						insertItem.setString(3, item.getType());
						insertItem.setInt(4, item.getContainerID());
						insertItem.setInt(5, item.getValue());
						insertItem.setString(6, item.getItemDescription());
						insertItem.setString(7, item.getUseDescription());
						insertItem.setString(8, item.getCombatDescription());
						insertItem.setBoolean(9, item.isEquipped());
						insertItem.setString(10, item.getCategory());
						insertItem.setString(11, item.getArmorType());
						insertItem.setInt(12, item.getDefenseNumber());
						insertItem.setString(13, item.getEffectType());
						insertItem.setInt(14, item.getEffectLow());
						insertItem.setInt(15, item.getEffectHigh());
						insertItem.setInt(16, player.getGameID());
						
						insertItem.addBatch();
					
						
					}
					
				
					insertItem.executeBatch();
					System.out.println("Items table updated");	

=======
>>>>>>> Stashed changes
					return player;
				} finally {					
					DBUtil.closeQuietly(insertItem);	
					DBUtil.closeQuietly(insertRoom);
					DBUtil.closeQuietly(insertRoomItem);
					DBUtil.closeQuietly(insertPlayer);
					DBUtil.closeQuietly(insertRoomConnection);
					DBUtil.closeQuietly(insertContainer);
					DBUtil.closeQuietly(insertContainerItem);
					DBUtil.closeQuietly(insertRoomContainer);
				}
			}
		});
	}
	
	

	
	@Override
	public User insertNewUser(String username, String password) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				try {
					stmt1 = conn.prepareStatement(
							"insert into users (username, password)  " +
							"  values (?,?)  "
					);
					
					
				
					stmt1.setString(1, username);
					stmt1.setString(2, password);
					
					

					
					User result = new User();
					
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"select * from users  " +
							"  where username = ?"
					);
					stmt2.setString(1, username);
					
					
					resultSet = stmt2.executeQuery();
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						loadUser(result,resultSet,1);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No users were found in the database for the specified userID");
						result = null;
					}
					else {
						System.out.println("User inserted with ID "+result.getUserID());
					}
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	@Override
	public List<User> removeUserByUserId(int ID) {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
							"delete from users" +
							"  where user_Id = ? "
					);
					
					stmt1.setInt(1, ID);
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement("select * from users");
					resultSet1 = stmt2.executeQuery();
					
					List<User> userList = new ArrayList<User>();
					
					while (resultSet1.next()) {
						User user = new User();
						loadUser(user,resultSet1,1);
						userList.add(user);
					}
					
					return userList;
					
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	

	@Override
	public Item updateItem(Item item) {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				try {
					stmt1 = conn.prepareStatement(
							"update items  " +
							"  set containerID = ?, isEquipped = ? " +
							"  where itemID = ? "
					);
					
					
					/*stmt7 = conn.prepareStatement(
							"create table items (" +
							"	itemID integer primary key" +
							"		generated always as identity (start with 1, increment by 1), " +
							"	name varchar(70)," +
							"	location integer," +
							"   value integer," +
							"   itemDescription varchar(100)," +
							"   roomDescription varchar(100)," +
							"   gameID integer" +
							")"
						*/	
					stmt1.setInt(1, item.getContainerID());
					stmt1.setBoolean(2,  item.isEquipped());
					stmt1.setInt(3, item.getItemID());

					
					Item result = new Item();
					
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"select * from items  " +
							"  where itemID = ?"
					);
					stmt2.setInt(1, item.getItemID());
					
					
					resultSet = stmt2.executeQuery();
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						loadItem(result,resultSet,1);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No items were found in the database for the specified itemID");
						result = null;
					}
					else {
						System.out.println("Item updated with ID "+result.getItemID());
					}
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}

		
		
		@Override
		public NPC updateNPC(NPC NPC) {
			return executeTransaction(new Transaction<NPC>() {
				@Override
				public NPC execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					ResultSet resultSet = null;
					try {
						stmt1 = conn.prepareStatement(
								"update NPCs  " +
								"  set roomID = ?, health = ? " +
								"  where npcID = ? "
						);
						
						
						/*stmt11 = conn.prepareStatement(
							"create table NPCs (" +
							"	npc_id integer primary key" +
							"		generated always as identity (start with 1, increment by 1), " +
							"	roomDialogue varchar(250)," +
							"	speakDialogue varchar(250)," +
							"   roomID integer, " +
							"   health integer, " +
							"   gameID integer " +
							")"
					);
							*/	
						stmt1.setInt(1, NPC.getRoomID());
						stmt1.setInt(2, NPC.getHealth());
						stmt1.setInt(3, NPC.getNPCID());

						
						NPC result = new NPC();
						
						stmt1.executeUpdate();
						
						stmt2 = conn.prepareStatement(
								"select * from NPCs  " +
								"  where npcID = ?"
						);
						stmt2.setInt(1, NPC.getNPCID());
						
						
						resultSet = stmt2.executeQuery();
						// for testing that a result was returned
						Boolean found = false;
						
						while (resultSet.next()) {
							found = true;
							
							loadNPC(result,resultSet,1);
						}
						
						// check if any authors were found
						if (!found) {
							System.out.println("No NPCs were found in the database for the specified NPCID");
							result = null;
						}
						else {
							System.out.println("NPC updated with ID "+result.getNPCID());
						}
						return result;
						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
	}

	
	@Override
	public List<RoomConnection> getRoomConnectionsByRoomID(int roomID) {
		
		return executeTransaction(new Transaction<List<RoomConnection>>() {
			@Override
			public List<RoomConnection> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from roomConnections " +
							" where startingRoomID = ? "
					);
					
					
					/*"create table roomConnections (" +
					"    startingRoomID integer, " +
					"    command varchar(70), " +
					"    destinationRoomID integer" +
					")"
					);
						*/	
					stmt.setInt(1, roomID);

					
					List<RoomConnection> result = new ArrayList<RoomConnection>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						RoomConnection roomConnection = new RoomConnection();
						loadRoomConnection(roomConnection, resultSet, 1);
						
						result.add(roomConnection);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No RoomConnections were found in the database for the specified room");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that inserts new Book into the Books table
	// also first inserts new Author into Authors table, if necessary
	// and then inserts entry into BookAuthors junction table
	@Override
	public Integer insertBookIntoBooksTable(final String title, final String isbn, final int published, final String lastName, final String firstName) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				Integer author_id = -1;
				Integer book_id   = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select author_id from authors " +
							"  where lastname = ? and firstname = ? "
					);
					stmt1.setString(1, lastName);
					stmt1.setString(2, firstName);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if Author was found then save author_id					
					if (resultSet1.next())
					{
						author_id = resultSet1.getInt(1);
						System.out.println("Author <" + lastName + ", " + firstName + "> found with ID: " + author_id);						
					}
					else
					{
						System.out.println("Author <" + lastName + ", " + firstName + "> not found");
				
						// if the Author is new, insert new Author into Authors table
						if (author_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into authors (lastname, firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New author <" + lastName + ", " + firstName + "> inserted in Authors table");						
						
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select author_id from authors " +
									"  where lastname = ? and firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								author_id = resultSet3.getInt(1);
								System.out.println("New author <" + lastName + ", " + firstName + "> ID: " + author_id);						
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("New author <" + lastName + ", " + firstName + "> not found in Authors table (ID: " + author_id);
							}
						}
					}
					
					// now insert new Book into Books table
					// prepare SQL insert statement to add new Book to Books table
					stmt4 = conn.prepareStatement(
							"insert into books (title, isbn, published) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, title);
					stmt4.setString(2, isbn);
					stmt4.setInt(3, published);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New book <" + title + "> inserted into Books table");					

					// now retrieve book_id for new Book, so that we can set up BookAuthor entry
					// and return the book_id, which the DB auto-generates
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select book_id from books " +
							"  where title = ? and isbn = ? and published = ? "
									
					);
					stmt5.setString(1, title);
					stmt5.setString(2, isbn);
					stmt5.setInt(3, published);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						book_id = resultSet5.getInt(1);
						System.out.println("New book <" + title + "> ID: " + book_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New book <" + title + "> not found in Books table (ID: " + book_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					
					return book_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	
	// transaction that deletes Book (and possibly its Author) from Library
	@Override
	public List<Author> removeBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<Author>>() {
			@Override
			public List<Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;							
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
				ResultSet resultSet5    = null;
				
				try {
					// first get the Author(s) of the Book to be deleted
					// just in case it's the only Book by this Author
					// in which case, we should also remove the Author(s)
					stmt1 = conn.prepareStatement(
							"select authors.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					
					// get the Book's Author(s)
					stmt1.setString(1, title);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Authors from query
					List<Author> authors = new ArrayList<Author>();					
				
					while (resultSet1.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet1, 1);
						authors.add(author);
					}
					
					// check if any Authors were found
					// this shouldn't be necessary, there should not be a Book in the DB without an Author
					if (authors.size() == 0) {
						System.out.println("No author was found for title <" + title + "> in the database");
					}
										
					// now get the Book(s) to be deleted
					// we will need the book_id to remove associated entries in BookAuthors (junction table)
				
					stmt2 = conn.prepareStatement(
							"select books.* " +
							"  from  books " +
							"  where books.title = ? "
					);
					
					// get the Book(s)
					stmt2.setString(1, title);
					resultSet2 = stmt2.executeQuery();
					
					// assemble list of Books from query
					List<Book> books = new ArrayList<Book>();					
				
					while (resultSet2.next()) {
						Book book = new Book();
						loadBook(book, resultSet2, 1);
						books.add(book);
					}
					
					// first delete entries in BookAuthors junction table
					// can't delete entries in Books or Authors tables while they have foreign keys in junction table
					// prepare to delete the junction table entries (bookAuthors)
					stmt3 = conn.prepareStatement(
							"delete from bookAuthors " +
							"  where book_id = ? "
					);
					
					// delete the junction table entries from the DB for this title
					// this works if there are not multiple books with the same name
					stmt3.setInt(1, books.get(0).getBookId());
					stmt3.executeUpdate();
					
					System.out.println("Deleted junction table entries for book(s) <" + title + "> from DB");									
					
					// now delete entries in Books table for this title
					stmt4 = conn.prepareStatement(
							"delete from books " +
							"  where title = ? "
					);
					
					// delete the Book entries from the DB for this title
					stmt4.setString(1, title);
					stmt4.executeUpdate();
					
					System.out.println("Deleted book(s) with title <" + title + "> from DB");									
					
					// now check if the Author(s) have any Books remaining in the DB
					// only need to check if there are any entries in junction table that have this author ID
					for (int i = 0; i < authors.size(); i++) {
						// prepare to find Books for this Author
						stmt5 = conn.prepareStatement(
								"select books.book_id from books, bookAuthors " +
								"  where bookAuthors.author_id = ? "
						);
						
						// retrieve any remaining books for this Author
						stmt5.setInt(1, books.get(i).getAuthorId());
						resultSet5 = stmt5.executeQuery();						

						// if nothing returned, then delete Author
						if (!resultSet5.next()) {
							stmt6 = conn.prepareStatement(
								"delete from authors " +
								"  where author_id = ?"
							);
							
							// delete the Author from DB
							stmt6.setInt(1, authors.get(i).getAuthorId());
							stmt6.executeUpdate();
							
							System.out.println("Deleted author <" + authors.get(i).getLastname() + ", " + authors.get(i).getFirstname() + "> from DB");
							
							// we're done with this, so close it, since we might have more to do
							DBUtil.closeQuietly(stmt6);
						}
						
						// we're done with this, so close it since we might have more to do
						DBUtil.closeQuietly(resultSet5);
						DBUtil.closeQuietly(stmt5);
					}
					return authors;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);					
				}
			}
		});
	}
	
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/apps/laptopDBTEST/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// retrieves Author information from query result set
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	// retrieves Author information from query result set
	private void loadItem(Item item, ResultSet resultSet, int index) throws SQLException {
		
		
		item.setItemID(resultSet.getInt(index++));
		item.setName(resultSet.getString(index++));
		item.setType(resultSet.getString(index++));
		item.setContainerID(resultSet.getInt(index++));
		item.setValue(resultSet.getInt(index++));
		item.setItemDescription(resultSet.getString(index++));
		item.setUseDescription(resultSet.getString(index++));
		item.setCombatDescription(resultSet.getString(index++));
		item.setEquipped(resultSet.getBoolean(index++));
		item.setCategory(resultSet.getString(index++));
		item.setArmorType(resultSet.getString(index++));
		item.setDefenseNumber(resultSet.getInt(index++));
		item.setEffectType(resultSet.getString(index++));
		item.setEffectLow(resultSet.getInt(index++));
		item.setEffectHigh(resultSet.getInt(index++));
	
		
		item.setGameID(resultSet.getInt(index++));
	}
	
	private void loadContainer(Container container, ResultSet resultSet, int index) throws SQLException {
		container.setContainerID(resultSet.getInt(index++));
		container.setName(resultSet.getString(index++));
		container.setRoomID(resultSet.getInt(index++));
		container.setContainerDescription(resultSet.getString(index++));
		container.setInRoomDescription(resultSet.getString(index++));
		container.setOpened(resultSet.getBoolean(index++));
		container.setGameID(resultSet.getInt(index++));
		
	}

		
		private void loadRoom(Room room, ResultSet resultSet, int index) throws SQLException {
			room.setRoomID(resultSet.getInt(index++));
			room.setLongDescription(resultSet.getString(index++));
			room.setShortDescription(resultSet.getString(index++));
			room.setGameID(resultSet.getInt(index++));
			
		}
		
		private void loadRoomItems(RoomItem roomItem, ResultSet resultSet, int index) throws SQLException {
			roomItem.setItemID(resultSet.getInt(index++));
			roomItem.setRoomID(resultSet.getInt(index++));
			roomItem.setGameID(resultSet.getInt(index++));
		}
		
		private void loadPlayer(Player player, ResultSet resultSet, int index) throws SQLException {
			player.setPlayerID(resultSet.getInt(index++));
			player.setScore(resultSet.getInt(index++));
			player.setHealth(resultSet.getInt(index++));
			player.setRoomID(resultSet.getInt(index++));
			player.setGameID(resultSet.getInt(index++));
			player.setUserID(resultSet.getInt(index++));
			player.setInCombat(resultSet.getBoolean(index++));
			Clob clob = resultSet.getClob(index++);
			Reader r = clob.getCharacterStream();
			StringBuffer buffer = new StringBuffer();
			int ch;
			try {
				while ((ch = r.read())>0) {
				   buffer.append(""+(char)ch);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			String clobAsString = buffer.toString();
			player.setLog(clobAsString);

		}
		
	
	// retrieves Book information from query result set
	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));
	}
	
	// retrieves WrittenBy information from query result set
	private void loadBookAuthors(BookAuthor bookAuthor, ResultSet resultSet, int index) throws SQLException {
		bookAuthor.setBookId(resultSet.getInt(index++));
		bookAuthor.setAuthorId(resultSet.getInt(index++));
	}
	
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException{
		user.setUserID(resultSet.getInt(index++));
		user.setUsername(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
	}
	
	private void loadRoomConnection(RoomConnection roomConnection, ResultSet resultSet, int index) throws SQLException{
		roomConnection.setStartingRoomID(resultSet.getInt(index++));
		roomConnection.setCommand(resultSet.getString(index++));
		roomConnection.setDestinationRoomID(resultSet.getInt(index++));
		roomConnection.setGameID(resultSet.getInt(index++));
	}
	
	private void loadNPC(NPC npc, ResultSet resultSet, int index) throws SQLException{
		npc.setNPCID(resultSet.getInt(index++));
		npc.setInventoryID(resultSet.getInt(index++));
		npc.setRoomID(resultSet.getInt(index++));
		npc.setName(resultSet.getString(index++));
		npc.setRoomDialogue(resultSet.getString(index++));
		npc.setSpeakDialogue(resultSet.getString(index++));
		npc.setDeathDialogue(resultSet.getString(index++));
		npc.setHealth(resultSet.getInt(index++));
		npc.setWeakness(resultSet.getString(index++));
		npc.setEffectType(resultSet.getString(index++));
		npc.setEffectLow(resultSet.getInt(index++));
		npc.setEffectHigh(resultSet.getInt(index++));
		npc.setGameID(resultSet.getInt(index++));
		
		
	}
		
	
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;			
				PreparedStatement stmt4 = null;	
				PreparedStatement stmt5 = null;	
				PreparedStatement stmt6 = null;	
				PreparedStatement stmt7 = null;	
				PreparedStatement stmt8 = null;
				PreparedStatement stmt9 = null;
				PreparedStatement stmt10 = null;
				PreparedStatement stmt11 = null;
				PreparedStatement stmt13 = null;
				PreparedStatement stmt18 = null;
				PreparedStatement stmt19 = null;
			
				try {
					stmt1 = conn.prepareStatement(
						"create table authors (" +
						"	author_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	lastname varchar(40)," +
						"	firstname varchar(40)" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Authors table created");
					
					stmt2 = conn.prepareStatement(
							"create table books (" +
							"	book_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
//							"	author_id integer constraint author_id references authors, " +  	// this is now in the BookAuthors table
							"	title varchar(70)," +
							"	isbn varchar(15)," +
							"   published integer" +
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Books table created");	
					
					
					
					stmt6 = conn.prepareStatement(
							"create table rooms (" +
							"	roomID integer, " +
							"	longDescription varchar(800)," +
							"	shortDescription varchar(800)," +
							"   gameID integer" +
							")"
					);
					
					stmt6.executeUpdate();
					
					System.out.println("rooms table created");	
					
					
					
					
					stmt7 = conn.prepareStatement(
							"create table items (" +
							"	itemID integer," +
							"	name varchar(70)," +
							"	type varchar(70)," +
							"	containerID integer," +
							"   value integer," +
							"   itemDescription varchar(800)," +
							"   useDescription varchar(800)," +
							"   combatDescription varchar(800)," +
							"   isEquipped boolean," +
							"   category varchar(25)," +
							"   armorType varchar(25)," +
							"   defenseNumber integer," +
							"   effectType varchar(15)," +
							"   effectLow integer," +
							"   effectHigh integer," +
							"   gameID integer" +
							
							")"
					);
					
					stmt7.executeUpdate();
					
					System.out.println("Items table created");	

					
					stmt4 = conn.prepareStatement(
							"create table roomItems (" +
									"	itemID integer, " +
									"	roomID integer," +
									"	gameID integer" +
							")"
					);
					
					stmt4.executeUpdate();
					
					System.out.println("roomItems table created");		
					
					
					
					stmt3 = conn.prepareStatement(
							"create table bookAuthors (" +
							"	book_id   integer constraint book_id references books, " +
							"	author_id integer constraint author_id references authors " +
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("BookAuthors table created");		
					
					stmt5 = conn.prepareStatement(
							"create table testBooks (" +
							"	book_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
//							"	author_id integer constraint author_id references authors, " +  	// this is now in the BookAuthors table
							"	title varchar(70)," +
							"	isbn varchar(15)," +
							"   published integer" +
							")"
					);
					stmt5.executeUpdate();
					
					System.out.println("testBooks table created");	
					
					stmt8 = conn.prepareStatement(
							"create table users (" +
							"	user_id integer primary key" +
							"		generated always as identity (start with 1, increment by 1), " +
							"	username varchar(70)," +
							"	password varchar(70)" +
							")"
					);
					stmt8.executeUpdate();
					
					System.out.println("users table created");
					
					
					stmt9 = conn.prepareStatement(
							"create table players (" +
							"	player_id integer primary key" +
							"		generated always as identity (start with 1, increment by 1), " +
							"	score integer," +
							"	health integer," +
							"	roomID integer," +
							"	gameID integer," +
							"	userID integer," +
							"	isInCombat boolean," +
							"	log clob" +
							")"
					);
					stmt9.executeUpdate();
					
					System.out.println("players table created");
					
					stmt10 = conn.prepareStatement(
							
							"create table roomConnections (" +
							"    startingRoomID integer, " +
							"    command varchar(70), " +
							"    destinationRoomID integer, " +
							"    gameID integer" +
							")"
							);
									
					stmt10.executeUpdate();
					
					System.out.println("roomConnections table created");
					
					
					stmt11 = conn.prepareStatement(
							"create table NPCs (" +
							"	npcID integer," +
							"	inventoryID integer," +
							"	roomID integer," +
							"   name varchar(50), " +
							"	roomDialogue varchar(250)," +
							"	speakDialogue varchar(250)," +
							"	deathDialogue varchar(250)," +
							"   health integer, " +
							"   weakness varchar(20), " +
							"   effectType varchar(20), " +
							"	effectLow integer," +
							"	effectHigh integer," +
							"   gameID integer " +
							")"
					);
					stmt11.executeUpdate();
					
					System.out.println("NPC table created");
					
					
					stmt13 = conn.prepareStatement(
							"create table containers (" +
							" containerID integer primary key " +
							" generated always as identity (start with 1, increment by 1), " +
							" name varchar(80)," +
							" roomID integer," +
							" containerDescription varchar(80)," +
							" inRoomDescription varchar(80)," +
							" isOpened boolean," +
							" gameID integer" +
							")"
							);
							stmt13.executeUpdate();

							System.out.println("containers table created");


							stmt18 = conn.prepareStatement(
							"create table containerItems (" +
							" itemName varchar(80)," +
							" containerID integer," +
							" gameID integer" +
							")"
							);
							stmt18.executeUpdate();

							System.out.println("containerItems table created");

							stmt19 = conn.prepareStatement(
							"create table roomContainers (" +
							" containerID integer," +
							" roomID integer," +
							" gameID integer" +
							")"
							);
							stmt19.executeUpdate();

							System.out.println("roomContainers table created");
							
							
							
							
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt13);
					DBUtil.closeQuietly(stmt18);
					DBUtil.closeQuietly(stmt19);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Author> authorList;
				List<Book> bookList;
				List<BookAuthor> bookAuthorList;
				List<Item> itemList;
				List<Book> testBookList;
				List<Room> roomList;
				List<RoomItem>  roomItemList;
				List<User> userList;
				List<Player> playerList;
				List<RoomConnection> roomConnectionList;
				List<NPC> NPCList;
				List<Container> containerList;
				List<ContainerItem> containerItemList;
				List<RoomContainer> roomContainerList;
				
				try {
					authorList         = InitialData.getAuthors();
					bookList           = InitialData.getBooks();
					bookAuthorList     = InitialData.getBookAuthors();	
					itemList           = InitialData.getItems();
					testBookList       = InitialData.getTestBooks();
					roomList           = InitialData.getRooms();
					roomItemList       = InitialData.getRoomItems();
					userList           = InitialData.getUsers();
					playerList         = InitialData.getPlayers();
					roomConnectionList = InitialData.getRoomConnections();
					NPCList            = InitialData.getNPCs();
					containerList      = InitialData.getContainers();
					containerItemList  = InitialData.getContainersItems();
					roomContainerList  = InitialData.getRoomContainers();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAuthor         = null;
				PreparedStatement insertBook           = null;
				PreparedStatement insertBookAuthor     = null;
				PreparedStatement insertItem           = null;
				PreparedStatement insertRoom           = null;
				PreparedStatement insertRoomItem       = null;
				PreparedStatement insertTestBook       = null;
				PreparedStatement insertUser           = null;
				PreparedStatement insertPlayer         = null;
				PreparedStatement insertRoomConnection = null;
				PreparedStatement insertNPC            = null;
				PreparedStatement insertContainer      = null;
				PreparedStatement insertRoomContainer  = null;
				PreparedStatement insertContainerItem  = null;
				

				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertAuthor = conn.prepareStatement("insert into authors (lastname, firstname) values (?, ?)");
					for (Author author : authorList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertAuthor.setString(1, author.getLastname());
						insertAuthor.setString(2, author.getFirstname());
						insertAuthor.addBatch();
					}
					insertAuthor.executeBatch();
					
					System.out.println("Authors table populated");
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertBook = conn.prepareStatement("insert into books (title, isbn, published) values (?, ?, ?)");
					for (Book book : bookList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getAuthorId());	// this is now in the BookAuthors table
						insertBook.setString(1, book.getTitle());
						insertBook.setString(2, book.getIsbn());
						insertBook.setInt(3, book.getPublished());
						insertBook.addBatch();
					}
					insertBook.executeBatch();
					
					System.out.println("Books table populated");	
					
					insertRoom = conn.prepareStatement("insert into rooms (roomID, longDescription, shortDescription, gameID) values (?, ?, ?, ?)");
					for (Room room : roomList) {
						insertRoom.setInt(1,  room.getRoomID());
						insertRoom.setString(2,  room.getLongDescription());
						insertRoom.setString(3,  room.getShortDescription());
						insertRoom.setInt(4,  room.getGameID());
						
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();
					
					System.out.println("Rooms table populated");
					
					
					insertItem = conn.prepareStatement("insert into items (itemID, name, type, containerID, value, itemDescription, useDescription, combatDescription, isEquipped, category, armorType, defenseNumber, effectType, effectLow, effectHigh, gameID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						
						insertItem.setInt(1, item.getItemID());
						insertItem.setString(2, item.getName());
						insertItem.setString(3, item.getType());
						insertItem.setInt(4, item.getContainerID());
						insertItem.setInt(5, item.getValue());
						insertItem.setString(6, item.getItemDescription());
						insertItem.setString(7, item.getUseDescription());
						insertItem.setString(8, item.getCombatDescription());
						insertItem.setBoolean(9, item.isEquipped());
						insertItem.setString(10, item.getCategory());
						insertItem.setString(11, item.getArmorType());
						insertItem.setInt(12, item.getDefenseNumber());
						insertItem.setString(13, item.getEffectType());
						insertItem.setInt(14, item.getEffectLow());
						insertItem.setInt(15, item.getEffectHigh());
						insertItem.setInt(16, item.getGameID());
						
						insertItem.addBatch();
					
						
					}
					
				
					insertItem.executeBatch();
System.out.println("Items table populated");	

insertContainer = conn.prepareStatement("insert into containers (name, roomID, containerDescription, inRoomDescription, isOpened, gameID) values (?, ?, ?, ?, ?, ?)");
for (Container container : containerList) {
//insertAuthor.setInt(1, author.getAuthorId()); // auto-generated primary key, don't insert this
insertContainer.setString(1, container.getName());
insertContainer.setInt(2, container.getRoomID());
insertContainer.setString(3, container.getContainerDescription());
insertContainer.setString(4, container.getInRoomDescription());
insertContainer.setBoolean(5, container.isOpened());
insertContainer.setInt(6, container.getGameID());

insertContainer.addBatch();
}
insertContainer.executeBatch();

System.out.println("containers table populated");



insertContainerItem = conn.prepareStatement("insert into containerItems (itemName, containerID, gameID) values (?, ?, ?)");
for (ContainerItem containerItem : containerItemList) {
//insertAuthor.setInt(1, author.getAuthorId()); // auto-generated primary key, don't insert this
insertContainerItem.setString(1, containerItem.getItemName());
insertContainerItem.setInt(2, containerItem.getContainerID());
insertContainerItem.setInt(3, containerItem.getGameID());
insertContainerItem.addBatch();
}
insertContainerItem.executeBatch();

System.out.println("containerItems table populated");



insertRoomContainer = conn.prepareStatement("insert into roomContainers (containerID, roomID, gameID) values (?, ?, ?)");
for (RoomContainer roomContainer : roomContainerList) {
//insertAuthor.setInt(1, author.getAuthorId()); // auto-generated primary key, don't insert this
insertRoomContainer.setInt(1, roomContainer.getContainerID());
insertRoomContainer.setInt(2, roomContainer.getRoomID());
insertRoomContainer.setInt(3, roomContainer.getGameID());
insertRoomContainer.addBatch();
}
insertRoomContainer.executeBatch();

System.out.println("roomContainers table populated");

					
					
					
					/*
					
					insertTestBook = conn.prepareStatement("insert into testBooks (title, isbn, published) values (?, ?, ?)");
					for (Book book : testBookList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getAuthorId());	// this is now in the BookAuthors table
						insertTestBook.setString(1, book.getTitle());
						insertTestBook.setString(2, book.getIsbn());
						insertTestBook.setInt(3, book.getPublished());
						insertTestBook.addBatch();
					}
					insertTestBook.executeBatch();
					
					System.out.println("testBooks table populated");	
					
					
					*/
					
					insertRoomItem = conn.prepareStatement("insert into roomItems (itemID, roomID, gameID) values (?, ?, ?)");
					for (RoomItem roomItem : roomItemList) {
						
						insertRoomItem.setInt(1, roomItem.getItemID());
						insertRoomItem.setInt(2, roomItem.getRoomID());
						insertRoomItem.setInt(3, roomItem.getGameID());
						insertRoomItem.addBatch();
					}
					insertRoomItem.executeBatch();	
					
					System.out.println("RoomItems table populated");
					
					
					// must wait until all Books and all Authors are inserted into tables before creating BookAuthor table
					// since this table consists entirely of foreign keys, with constraints applied
					insertBookAuthor = conn.prepareStatement("insert into bookAuthors (book_id, author_id) values (?, ?)");
					for (BookAuthor bookAuthor : bookAuthorList) {
						insertBookAuthor.setInt(1, bookAuthor.getBookId());
						insertBookAuthor.setInt(2, bookAuthor.getAuthorId());
						insertBookAuthor.addBatch();
					}
					insertBookAuthor.executeBatch();	
					
					System.out.println("BookAuthors table populated");					
					
					
					insertUser = conn.prepareStatement("insert into users (username, password) values (?, ?)");
					for (User user: userList) {
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getPassword());
						insertUser.addBatch();
					}
					insertUser.executeBatch();	
					
					System.out.println("Users table populated");
					
					insertRoomConnection = conn.prepareStatement("insert into roomConnections (startingRoomID, command, destinationRoomID, gameID) values (?, ?, ?, ?)");
					for (RoomConnection roomConnection: roomConnectionList) {
						insertRoomConnection.setInt(1, roomConnection.getStartingRoomID());
						insertRoomConnection.setString(2, roomConnection.getCommand());
						insertRoomConnection.setInt(3, roomConnection.getDestinationRoomID());
						insertRoomConnection.setInt(4, roomConnection.getGameID());
						insertRoomConnection.addBatch();
					}
					insertRoomConnection.executeBatch();	
					
					System.out.println("roomConnections table populated");	
					
					
					
					insertPlayer = conn.prepareStatement("insert into players (score, health, roomID, gameID, userID, isInCombat, log) values (?, ?, ?, ?, ?, ?, ?)");
					for (Player player: playerList) {
						insertPlayer.setInt(1, player.getScore());
						insertPlayer.setInt(2, player.getHealth());
						insertPlayer.setInt(3, player.getRoomID());
						insertPlayer.setInt(4, player.getGameID());
						insertPlayer.setInt(5, player.getUserID());
						insertPlayer.setBoolean(6, player.isInCombat());
						Clob myClob = new javax.sql.rowset.serial.SerialClob(player.getLog().toCharArray());
						insertPlayer.setClob(7, myClob);
						insertPlayer.addBatch();
					}
					insertPlayer.executeBatch();	
					
					System.out.println("Players table populated");	
					
					
					insertNPC = conn.prepareStatement("insert into NPCs (npcID, inventoryID, roomID, name, roomDialogue, speakDialogue, deathDialogue, health, weakness, effectType, effectLow, effectHigh, gameID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (NPC npc: NPCList) {
						
						
						insertNPC.setInt(1, npc.getNPCID());
						insertNPC.setInt(2, npc.getInventoryID());
						insertNPC.setInt(3, npc.getRoomID());
						insertNPC.setString(4, npc.getName());
						insertNPC.setString(5, npc.getRoomDialogue());
						insertNPC.setString(6, npc.getSpeakDialogue());
						insertNPC.setString(7, npc.getDeathDialogue());
						insertNPC.setInt(8, npc.getHealth());
						insertNPC.setString(9, npc.getWeakness());
						insertNPC.setString(10, npc.getEffectType());
						insertNPC.setInt(11, npc.getEffectLow());
						insertNPC.setInt(12, npc.getEffectHigh());
						insertNPC.setInt(13, npc.getGameID());
					
						
						insertNPC.addBatch();
					}
insertNPC.executeBatch();	
					
					
					
					System.out.println("NPCs table populated");	
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertBook);
					DBUtil.closeQuietly(insertAuthor);
					DBUtil.closeQuietly(insertBookAuthor);					
					DBUtil.closeQuietly(insertItem);	
					DBUtil.closeQuietly(insertRoom);
					DBUtil.closeQuietly(insertRoomItem);
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertPlayer);
					DBUtil.closeQuietly(insertRoomConnection);
					DBUtil.closeQuietly(insertContainer);
					DBUtil.closeQuietly(insertRoomContainer);
					DBUtil.closeQuietly(insertContainerItem);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}


	@Override
	public List<Item> findAllItems() {
		return executeTransaction(new Transaction<List<Item>>() {
			@Override
			public List<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from items " 
							
					);
					
					List<Item> result = new ArrayList<Item>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Item item = new Item();
						loadItem(item, resultSet, 1);
						
						result.add(item);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No items were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public List<Book> findAllTestBooks() {
		return executeTransaction(new Transaction<List<Book>>() {
			@Override
			public List<Book> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from testBooks " 
							
					);
					
					List<Book> result = new ArrayList<Book>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Book book = new Book();
						loadBook(book, resultSet, 1);
						
						result.add(book);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No items were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public List<Room> findAllRooms() {
		return executeTransaction(new Transaction<List<Room>>() {
			@Override
			public List<Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from rooms " 
							
					);
					
					List<Room> result = new ArrayList<Room>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Room room = new Room();
						loadRoom(room, resultSet, 1);
						
						result.add(room);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No rooms were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	@Override
	public List<Item> findInventory() {
		return executeTransaction(new Transaction<List<Item>>() {
			@Override
			public List<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select *" +
							"from items, roomItems" +
							"  where roomItems.roomID = -1" +
							"  and items.itemID = roomItems.itemID" 
							
					);
					
					//stmt.setString(1, title);
					
					List<Item> result = new ArrayList<Item>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Item item = new Item();
						loadItem(item, resultSet, 1);
						
						
						result.add(item);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("nothing was not found in the items table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public List<Room> getRoomListByGameID(int gameID) {
		return executeTransaction(new Transaction<List<Room>>() {
			@Override
			public List<Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from rooms " +
							" where gameID = ? "
					);
					
					
					/*"create table roomConnections (" +
					"    startingRoomID integer, " +
					"    command varchar(70), " +
					"    destinationRoomID integer" +
					")"
					);
						*/	
					stmt.setInt(1, gameID);

					
					List<Room> result = new ArrayList<Room>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Room room = new Room();
						loadRoom(room, resultSet, 1);
						
						result.add(room);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No Rooms were found in the database for the specified room");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public List<RoomConnection> findRoomConnections() {

		return executeTransaction(new Transaction<List<RoomConnection>>() {
			@Override
			public List<RoomConnection> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from roomConnections "
							
					);
					
					
					/*"create table roomConnections (" +
					"    startingRoomID integer, " +
					"    command varchar(70), " +
					"    destinationRoomID integer" +
					")"
					);
						*/	
					//stmt.setInt(1, roomID);

					
					List<RoomConnection> result = new ArrayList<RoomConnection>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						RoomConnection roomConnection = new RoomConnection();
						loadRoomConnection(roomConnection, resultSet, 1);
						
						result.add(roomConnection);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No RoomConnections were found in the database for the specified room");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	@Override
	public List<Container> findAllContainers() {
		return executeTransaction(new Transaction<List<Container>>() {
			@Override
			public List<Container> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from containers" 
						
							
					);
					
					//stmt.setString(1, title);
					
					List<Container> result = new ArrayList<Container>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Container container = new Container();
						loadContainer(container, resultSet, 1);
						
						
						result.add(container);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("nothing was not found in the containers table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}





}

