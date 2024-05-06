package edu.ycp.cs320.booksdb.model;

public class RoomConnection {

private int startingRoomID;
private String command;
private int destinationRoomID;
private int itemID;
private String itemMissingMsg;
private int gameID;


public RoomConnection() {

}

public int getStartingRoomID() {
return startingRoomID;
}

public void setStartingRoomID(int startingRoomID) {
this.startingRoomID = startingRoomID;
}

public String getCommand() {
return command;
}

public void setCommand(String command) {
this.command = command;
}

public int getDestinationRoomID() {
return destinationRoomID;
}

public void setDestinationRoomID(int destinationRoomID) {
this.destinationRoomID = destinationRoomID;
}

public int getGameID() {
	// TODO Auto-generated method stub
	return gameID;
}

public void setGameID(int gameID) {
	this.gameID = gameID;
}

public int getItemID() {
	return itemID;
}

public String getItemMissingMsg() {
	// TODO Auto-generated method stub
	return itemMissingMsg;
}



}