package edu.ycp.cs320.lab02.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AuthorsController {

	private IDatabase db = null;

	public AuthorsController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Author> getAuthorByTitle(String title) {
		
		// get the list of (Author, Book) pairs from DB
		List<Pair<Author, Book>> authorBookList = db.findAuthorAndBookByTitle(title);
		ArrayList<Author> authors = null;
		
		if (authorBookList.isEmpty()) {
			System.out.println("No book found in library with title <" + title + ">");
			return null;
		}
		else {
			authors = new ArrayList<Author>();
			for (Pair<Author, Book> authorBook : authorBookList) {
				Author author = authorBook.getLeft();
				Book   book   = authorBook.getRight();
				authors.add(author);
				System.out.println(author.getLastname() + "," + author.getFirstname() + ", " + book.getTitle() + "," + book.getIsbn());
			}			
		}
		
		// return authors for this title
		return authors;
	}
}

