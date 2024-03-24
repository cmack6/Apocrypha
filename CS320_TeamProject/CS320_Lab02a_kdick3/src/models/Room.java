package models;

import java.util.ArrayList;

public class Room {

	private int roomID; 
	private int north;
	private int south;
	private int west;
	private int east;
	private String shortDescription;
	private String longDescription;
	private boolean isAlreadyEntered = false;
	//public Inventory roomInventory;
	//private ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Room() {
		
	}
	/*
	public void addToInventory(Item item) {
		inventory.add(item);
	}
	
	public void removeFromInventory(Item item) {
		inventory.remove(item);
	}
	*/
	public boolean getIsAlreadyEntered() {
		return isAlreadyEntered;
	}
	
	public void setIsAlreadyEntered(boolean isAlreadyEntered) {
		this.isAlreadyEntered = isAlreadyEntered;
	}
	
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getShortDescription() {
		return shortDescription;
	
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	
	}
	
	public void setRoomConnections(int north, int south, int east, int west) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		
	}
	
	public int getRoomConnectionNorth() {
		return north;
	}
	
	public int getRoomConnectionSouth() {
		return south;
	}
	
	public int getRoomConnectionEast() {
		return east;
	}
	
	public int getRoomConnectionWest() {
		return west;
	}
	
	public String getDescription(){
		if(isAlreadyEntered==false) {
			isAlreadyEntered=true;
			return longDescription;
		}
		else {
			return shortDescription;
		}
	}
	
	/*

	public Inventory getRoomInventory() {
		return roomInventory;
	}
	
	public String getRoomInventoryAsString() {
		return roomInventory.toString();
	}

	
	public void addToRoomInventory(Item item) {
		roomInventory.add(item);
	}
	
	public void removeFromRoomInventory(Item item) {
		roomInventory.remove(item);
	}
	
	*/
	
	//^^^korbins work^^^*plz dont touch yet*
	
	
	
	
	
	/*
	
	public String refreshroom() {
		return shortDescription;
	}
	
	public void updateScoreFirstTravelled() {
		int tempScore = User.getScore() + 50;
		 User.setScore(tempScore);
	}
	
	public int updateScore(int currentScore) {
		 int newScore = currentScore + 50;
		return newScore;
	}
	
	public boolean isValidMove () {
		/*implement if a room exists and can be moved to, to return true, otherwise return false*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}
