package edu.ycp.cs320.booksdb.model;

public class RoomConnection {

private int startingRoomID;
private String command;
private int destinationRoomID;

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



}