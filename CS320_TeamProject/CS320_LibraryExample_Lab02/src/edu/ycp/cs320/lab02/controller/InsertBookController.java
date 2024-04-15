package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class InsertBookController {

	private IDatabase db = null;

	public InsertBookController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public boolean insertBookIntoLibrary(String title, String isbn, int published, String lastName, String firstName) {
		
		// insert new book (and possibly new author) into DB
		Integer book_id = db.insertBookIntoBooksTable(title, isbn, published, lastName, firstName);

		// check if the insertion succeeded
		if (book_id > 0)
		{
			System.out.println("New book (ID: " + book_id + ") successfully added to Books table: <" + title + ">");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new book (ID: " + book_id + ") into Books table: <" + title + ">");
			
			return false;
		}
	}
}
