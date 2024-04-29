package edu.ycp.cs320.booksdb.model;
public class Container{

	
	
	private int containerID;
	private String name;
	private int roomID;
	private String containerDescription;
	private String inRoomDescription;
	private boolean isOpened;
	private int gameID;


public Container() {
// TODO Auto-generated constructor stub
}


public int getContainerID() {
	return containerID;
}


public void setContainerID(int containerID) {
	this.containerID = containerID;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public int getRoomID() {
	return roomID;
}


public void setRoomID(int roomID) {
	this.roomID = roomID;
}


public String getContainerDescription() {
	return containerDescription;
}


public void setContainerDescription(String containerDescription) {
	this.containerDescription = containerDescription;
}


public String getInRoomDescription() {
	return inRoomDescription;
}


public void setInRoomDescription(String inRoomDescription) {
	this.inRoomDescription = inRoomDescription;
}


public boolean isOpened() {
	return isOpened;
}


public void setOpened(boolean isOpened) {
	this.isOpened = isOpened;
}


public int getGameID() {
	return gameID;
}


public void setGameID(int gameID) {
	this.gameID = gameID;
}



}