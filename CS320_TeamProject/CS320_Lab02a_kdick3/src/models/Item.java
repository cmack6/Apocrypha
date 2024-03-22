package models;



public class Item{
	
	private String name;
	private int location;
	private int value;
	private int itemID;
	private String description;
	
	

public Item(String name, int location, int value, int itemID) {
	
	this.name = name;
	this.location = location;
	this.value = value;
	this.setItemID(itemID);
	
	
}



public void setName(String name) {
	this.name = name;
}

public String getName() {
	return name;
}


public int getValue() {
	return value;
}



public void setValue(int value) {
	this.value = value;
}

public int getLocation() {
	return location;
}



public void setLocation(int location) {
	this.location = location;
}



public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}



public int getItemID() {
	return itemID;
}



public void setItemID(int itemID) {
	this.itemID = itemID;
}




}
