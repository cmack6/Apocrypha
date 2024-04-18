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
}
