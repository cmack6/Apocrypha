package edu.ycp.cs320.lab02.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Item;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllItemsController {

	private IDatabase db    = null;

	public AllItemsController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Item> getAllItems() {
		
		// get the list of (Author, Book) pairs from DB
		List<Pair<Author, Book>> bookAuthorList = db.findAllBooksWithAuthors();
		ArrayList<Book> books = null;
		
		if (bookAuthorList.isEmpty()) {
			System.out.println("No books in database");
			return null;
		}
		else {
			books = new ArrayList<Book>();
			for (Pair<Author, Book> authorBook : bookAuthorList) {
				Author author = authorBook.getLeft();
				Book book = authorBook.getRight();
				books.add(book);
				System.out.println(book.getTitle() + ", " + book.getIsbn() + ", " + book.getPublished() + ", " + author.getLastname() + ", " + author.getFirstname());
			}			
		}
		
		// return list of book,author pairs
		return bookAuthorList;
	}
}
