package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Item;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class InventoryQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Item> itemList = db.findInventory();
		
		// check if anything was returned and output the list
		if (itemList.isEmpty()) {
			System.out.println("There are no books in the database");
		}
		else {
			for (Item item : itemList) {
				
				System.out.println(item.getItemID() + ", " + item.getName() + ", " + item.getLocation() + ", " + item.getValue()
					+ ", " + item.getItemDescription() + ", " + item.getRoomDescription());
			
			}
		}
	}
}



