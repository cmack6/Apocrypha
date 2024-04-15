package edu.ycp.cs320.lab02.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllAuthorsController {

	private IDatabase db = null;

	public AllAuthorsController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Author> getAllAuthors() {
		
		// get the list of (Author, Book) pairs from DB
		List<Author> authorList = db.findAllAuthors();
		ArrayList<Author> authors = null;
		
		if (authorList.isEmpty()) {
			System.out.println("No authors found in library");
			return null;
		}
		else {
			authors = new ArrayList<Author>();
			for (Author author : authorList) {
				authors.add(author);
				System.out.println(author.getLastname() + ", " + author.getFirstname());
			}			
		}
		
		// return authors for this title
		return authors;
	}
}

