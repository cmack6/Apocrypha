package edu.ycp.cs320.booksdb.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.booksdb.model.*;

public class InitialData {

	// reads initial Author data from CSV file and returns a List of Authors
	public static List<Author> getAuthors() throws IOException {
		List<Author> authorList = new ArrayList<Author>();
		ReadCSV readAuthors = new ReadCSV("authors.csv");
		try {
			// auto-generated primary key for authors table
			Integer authorId = 1;
			while (true) {
				List<String> tuple = readAuthors.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Author author = new Author();

				// read author ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// auto-generate author ID, instead
				author.setAuthorId(authorId++);				
				author.setLastname(i.next());
				author.setFirstname(i.next());
				authorList.add(author);
			}
			System.out.println("authorList loaded from CSV file");
			return authorList;
		} finally {
			readAuthors.close();
		}
	}
	
	// reads initial Book data from CSV file and returns a List of Books
	public static List<Book> getBooks() throws IOException {
		List<Book> bookList = new ArrayList<Book>();
		ReadCSV readBooks = new ReadCSV("books.csv");
		try {
			// auto-generated primary key for table books
			Integer bookId = 1;
			while (true) {
				List<String> tuple = readBooks.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Book book = new Book();
				
				// read book ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file
				Integer.parseInt(i.next());
				// auto-generate book ID, instead
				book.setBookId(bookId++);				
//				book.setAuthorId(Integer.parseInt(i.next()));  // no longer in books table
				book.setTitle(i.next());
				book.setIsbn(i.next());
				book.setPublished(Integer.parseInt(i.next()));
				
				bookList.add(book);
			}
			System.out.println("bookList loaded from CSV file");			
			return bookList;
		} finally {
			readBooks.close();
		}
	}
	
	public static List<Book> getTestBooks() throws IOException {
		List<Book> bookList = new ArrayList<Book>();
		ReadCSV readBooks = new ReadCSV("testBooks.csv");
		try {
			// auto-generated primary key for table books
			Integer bookId = 1;
			while (true) {
				List<String> tuple = readBooks.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Book book = new Book();
				
				// read book ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file
				//Integer.parseInt(i.next());
				// auto-generate book ID, instead
				book.setBookId(bookId++);				
//				book.setAuthorId(Integer.parseInt(i.next()));  // no longer in books table
				book.setTitle(i.next());
				book.setIsbn(i.next());
				//book.setPublished(Integer.parseInt(i.next()));
				
				bookList.add(book);
			}
			System.out.println("bookList loaded from CSV file");			
			return bookList;
		} finally {
			readBooks.close();
		}
	}
	
	
		public static List<Item> getItems() throws IOException {
			List<Item> itemList = new ArrayList<Item>();
			ReadCSV readItems = new ReadCSV("items.csv");
			try {
				// auto-generated primary key for table items
				Integer itemID = 1;
				while (true) {
					List<String> tuple = readItems.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					Item item = new Item();
					
					Integer.parseInt(i.next());
					
				
					item.setItemID(itemID++);	
					
					item.setName(i.next());
					item.setLocation(Integer.parseInt(i.next()));
					item.setValue(Integer.parseInt(i.next()));
					item.setItemDescription(i.next());
					item.setRoomDescription(i.next());
					
					item.setLowestPiercingDamage(Integer.parseInt(i.next()));
					item.setHighestPiercingDamage(Integer.parseInt(i.next()));
					
					item.setLowestSlashingDamage(Integer.parseInt(i.next()));
					item.setHighestSlashingDamage(Integer.parseInt(i.next()));
					
					item.setLowestBludgeoningDamage(Integer.parseInt(i.next()));
					item.setHighestBludgeoningDamage(Integer.parseInt(i.next()));
					
					item.setLowestThrownDamage(Integer.parseInt(i.next()));
					item.setHighestThrownDamage(Integer.parseInt(i.next()));
					
					item.setGameID(Integer.parseInt(i.next()));
					
					itemList.add(item);
				}
				System.out.println("itemList loaded from CSV file");			
				return itemList;
			} finally {
				readItems.close();
			}
		}
	
		
		public static List<Room> getRooms() throws IOException {
			List<Room> roomList = new ArrayList<Room>();
			ReadCSV readRooms = new ReadCSV("rooms.csv");
			try {
				// auto-generated primary key for table items
				while (true) {
					List<String> tuple = readRooms.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					Room room = new Room();
										
				
					room.setRoomID(Integer.parseInt(i.next()));
					room.setLongDescription(i.next());
					room.setShortDescription(i.next());
					room.setGameID(Integer.parseInt(i.next()));
					
					roomList.add(room);
				}
				System.out.println("roomList loaded from CSV file");			
				return roomList;
			} finally {
				readRooms.close();
			}
		}
		
		
		public static List<RoomItem> getRoomItems() throws IOException {
			List<RoomItem> roomItemList = new ArrayList<RoomItem>();
			ReadCSV readRoomItems = new ReadCSV("roomItems.csv");
			try {
				while (true) {
					List<String> tuple = readRoomItems.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					RoomItem roomItem = new RoomItem();
					
					roomItem.setItemID(Integer.parseInt(i.next()));
					roomItem.setRoomID(Integer.parseInt(i.next()));				
					roomItem.setGameID(Integer.parseInt(i.next()));
					roomItemList.add(roomItem);
				}
				System.out.println("roomItemList loaded from CSV file");			
				return roomItemList;
			} finally {
				readRoomItems.close();
			}
		}
		
		public static List<RoomConnection> getRoomConnections() throws IOException {
			List<RoomConnection> roomConnectionList = new ArrayList<RoomConnection>();
			ReadCSV readRoomConnections = new ReadCSV("roomConnections.csv");
			try {
				while (true) {
					List<String> tuple = readRoomConnections.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					RoomConnection roomConnection = new RoomConnection();
					roomConnection.setStartingRoomID(Integer.parseInt(i.next()));				
					roomConnection.setCommand(i.next());
					roomConnection.setDestinationRoomID(Integer.parseInt(i.next()));
					roomConnection.setGameID(Integer.parseInt(i.next()));
					roomConnectionList.add(roomConnection);
				}
				System.out.println("roomConnectionList loaded from CSV file");			
				return roomConnectionList;
			} finally {
				readRoomConnections.close();
			}
		}
		
		public static List<User> getUsers() throws IOException{
			List<User> userList = new ArrayList<User>();
			ReadCSV readUsers = new ReadCSV("users.csv");
			try {
				Integer userID = 1;
				while (true) {
					List<String> tuple = readUsers.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					User user = new User();
					
					
					user.setUserID(userID++);	
					
					user.setUsername(i.next());				
					user.setPassword(i.next());
					userList.add(user);
				}
				System.out.println("userList loaded from CSV file");			
				return userList;
			} finally {
				readUsers.close();
			}
		}
		
		public static List<Player> getPlayers() throws IOException{
			List<Player> playerList = new ArrayList<Player>();
			ReadCSV readPlayers = new ReadCSV("players.csv");
			try {
				Integer playerID = 1;
				while (true) {
					List<String> tuple = readPlayers.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					Player player = new Player();
					
					
					player.setPlayerID(playerID++);	
					
					player.setScore(Integer.parseInt(i.next()));				
					player.setHealth(Integer.parseInt(i.next()));
					player.setRoomID(Integer.parseInt(i.next()));
					player.setGameID(Integer.parseInt(i.next()));
					player.setUserID(Integer.parseInt(i.next()));
					player.setInCombat(Boolean.parseBoolean(i.next()));
					player.setLog(i.next());
					playerList.add(player);
				}
				System.out.println("playerList loaded from CSV file");			
				return playerList;
			} finally {
				readPlayers.close();
			}
		}
		
		public static List<NPC> getNPCs() throws IOException {
			List<NPC> NPCList = new ArrayList<NPC>();
			ReadCSV readNPCs = new ReadCSV("npcs.csv");
			try {
				// auto-generated primary key for table items
				while (true) {
					List<String> tuple = readNPCs.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					NPC npc = new NPC();
					
					npc.setNPCID(Integer.parseInt(i.next()));
					
				
					
					npc.setRoomDialogue(i.next());
					npc.setSpeakDialogue(i.next());
					npc.setRoomID(Integer.parseInt(i.next()));
					npc.setHealth(Integer.parseInt(i.next()));
					npc.setName(i.next());
					npc.setGameID(Integer.parseInt(i.next()));
					NPCList.add(npc);
				}
				System.out.println("NPCList loaded from CSV file");			
				return NPCList;
			} finally {
				readNPCs.close();
			}
		}
		
		
	// reads initial BookAuthor data from CSV file and returns a List of BookAuthors
	public static List<BookAuthor> getBookAuthors() throws IOException {
		List<BookAuthor> bookAuthorList = new ArrayList<BookAuthor>();
		ReadCSV readBookAuthors = new ReadCSV("book_authors.csv");
		try {
			while (true) {
				List<String> tuple = readBookAuthors.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setBookId(Integer.parseInt(i.next()));				
				bookAuthor.setAuthorId(Integer.parseInt(i.next()));
				bookAuthorList.add(bookAuthor);
			}
			System.out.println("bookAuthorList loaded from CSV file");			
			return bookAuthorList;
		} finally {
			readBookAuthors.close();
		}
	}
}