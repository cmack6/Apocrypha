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

public class PlayerInventoryController {

	private IDatabase db    = null;

	public PlayerInventoryController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Item> getInventory() {
		
		
		List<Item> inventoryList = db.findInventory();
		ArrayList<Item> items = null;
		
		if (inventoryList.isEmpty()) {
			System.out.println("No items found in inventory");
			return null;
		}
		else {
			items = new ArrayList<Item>();
			for (Item item : inventoryList) {
				items.add(item);
				
			}			
		}
		
		// return of books for this author
		return items;
	}
}
