package edu.ycp.cs320.booksdb;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Item;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllTestBooksQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Book> bookList = db.findAllTestBooks();
		
		// check if anything was returned and output the list
		if (bookList.isEmpty()) {
			System.out.println("There are no books in the database");
		}
		else {
			for (Book book : bookList) {
				
				System.out.println(book.getTitle() + ", " + book.getIsbn() + ", " + book.getPublished() + ", ");
			}
		}
	}
}
