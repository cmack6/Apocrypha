package models;

import java.util.ArrayList;

public class Inventory{
	
	private ArrayList<Item> items;

public Inventory() {
	//testing
	items = new ArrayList<Item>();
}

public void add(Item item) {
	items.add(item);
}

public void remove(Item item) {
	items.remove(item);
}









}
