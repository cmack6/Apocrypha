package models;

import java.util.ArrayList;

public class Inventory{
	
	private ArrayList<Item> items;

public Inventory() {
	items = new ArrayList<Item>();
		Item a = new Item(10);
		this.items.add(a);
}

public void add(Item item) {
	items.add(item);
}









}
