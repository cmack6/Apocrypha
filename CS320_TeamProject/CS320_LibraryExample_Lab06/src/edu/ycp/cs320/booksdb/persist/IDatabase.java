package edu.ycp.cs320.booksdb.persist;

import java.util.List;

import edu.ycp.cs320.booksdb.model.*;

public interface IDatabase {
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title);
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(String lastName);
	public Integer insertBookIntoBooksTable(String title, String isbn, int published, String lastName, String firstName);
	public List<Pair<Author, Book>> findAllBooksWithAuthors();
	public List<Author> findAllAuthors();
	public List<Author> removeBookByTitle(String title);	
	public List<Item> findAllItems();
	public List<Room> findAllRooms();
	public List<Book> findAllTestBooks();
	public List<User> findAllUsers();
	public List<Player> findAllPlayers();
	public List<NPC> findAllNPCs();
	public List<Item> findInventory();
	public Player getPlayerFromGameID(int gameID);
	public List<RoomConnection> getRoomConnectionsByRoomID(int roomID);
	public Player updatePlayer(Player player);
	public List<Room> getRoomListByGameID(int gameID);
	public List<RoomConnection> findRoomConnections();
	public Item updateItem(Item item);
	public NPC updateNPC(NPC NPC);
	public User insertNewUser(String username, String password);
	public List<User> removeUserByUserId(int ID);
	public Player createNewGame(int userID);
	public List<Container> findAllContainers();
	public Player restartGame(int gameID, int userID);
	public Container updateContainer(Container container);
}
