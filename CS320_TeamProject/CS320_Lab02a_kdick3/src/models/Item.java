package models;



public class Item{
	
	private String name;
	private int location;
	private int value;
	private int itemID;
	private String itemDescription;
	private String roomDescription;
	
	

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



public String getItemDescription() {
	return itemDescription;
}

public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
}



public int getItemID() {
	return itemID;
}



public void setItemID(int itemID) {
	this.itemID = itemID;
}



public String getRoomDescription() {
	return roomDescription;
}



public void setRoomDescription(String roomDescription) {
	this.roomDescription = roomDescription;
}




}
