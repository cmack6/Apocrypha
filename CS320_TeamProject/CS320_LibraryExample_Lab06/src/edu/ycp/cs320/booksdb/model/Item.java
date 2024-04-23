package edu.ycp.cs320.booksdb.model;

public class Item{

private int itemID;
private String name;
private int location;
private int value;
private String itemDescription;
private String roomDescription;
private int lowestPiercingDamage;
private int highestPiercingDamage;
private int lowestSlashingDamage;
private int highestSlashingDamage;
private int lowestBludgeoningDamage;
private int highestBludgeoningDamage;
private int lowestThrownDamage;
private int highestThrownDamage;
private int gameID;



public Item(String name, int location, int value, int itemID) {

this.name = name;
this.location = location;
this.value = value;
this.setItemID(itemID);


}



public Item() {
// TODO Auto-generated constructor stub
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



public void setGameID(int gameID) {
this.gameID = gameID;
}

public int getGameID() {
return gameID;
}



public int getLowestPiercingDamage() {
	return lowestPiercingDamage;
}



public void setLowestPiercingDamage(int lowestPiercingDamage) {
	this.lowestPiercingDamage = lowestPiercingDamage;
}



public int getHighestPiercingDamage() {
	return highestPiercingDamage;
}



public void setHighestPiercingDamage(int highestPiercingDamage) {
	this.highestPiercingDamage = highestPiercingDamage;
}



public int getLowestSlashingDamage() {
	return lowestSlashingDamage;
}



public void setLowestSlashingDamage(int lowestSlashingDamage) {
	this.lowestSlashingDamage = lowestSlashingDamage;
}



public int getHighestSlashingDamage() {
	return highestSlashingDamage;
}



public void setHighestSlashingDamage(int highestSlashingDamage) {
	this.highestSlashingDamage = highestSlashingDamage;
}



public int getLowestBludgeoningDamage() {
	return lowestBludgeoningDamage;
}



public void setLowestBludgeoningDamage(int lowestBludgeoningDamage) {
	this.lowestBludgeoningDamage = lowestBludgeoningDamage;
}


public int getLowestThrownDamage() {
	return lowestThrownDamage;
}



public void setLowestThrownDamage(int lowestThrownDamage) {
	this.lowestThrownDamage = lowestThrownDamage;
}



public int getHighestThrownDamage() {
	return highestThrownDamage;
}



public void setHighestThrownDamage(int highestThrownDamage) {
	this.highestThrownDamage = highestThrownDamage;
}



public int getHighestBludgeoningDamage() {
	return highestBludgeoningDamage;
}



public void setHighestBludgeoningDamage(int highestBludgeoningDamage) {
	this.highestBludgeoningDamage = highestBludgeoningDamage;
}




}