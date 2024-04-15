package edu.ycp.cs320.booksdb;


import java.util.Scanner;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class InsertNewBookWithAuthor {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.print("Enter the author's last name: ");
		String lastName = keyboard.nextLine();
		
		System.out.print("Enter the author's first name: ");
		String firstName = keyboard.nextLine();
		
		System.out.print("Enter the book's title: ");
		String title = keyboard.nextLine();
		
		System.out.print("Enter the book's ISBN: ");
		String isbn = keyboard.nextLine();
		
		System.out.print("Enter the year the book was published: ");
		int published = keyboard.nextInt();
		
		// get the DB instance and execute the transaction
		IDatabase db = DatabaseProvider.getInstance();
		Integer book_id = db.insertBookIntoBooksTable(title, isbn, published, lastName, firstName);

		// check if the insertion succeeded
		if (book_id > 0)
		{
			System.out.println("New book (ID: " + book_id + ") successfully added to Books table: <" + title + ">");
		}
		else
		{
			System.out.println("Failed to insert new book (ID: " + book_id + ") into Books table: <" + title + ">");			
		}
	}
}
