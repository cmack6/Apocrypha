package edu.ycp.cs320.booksdb.model;
public class ContainerItem{

private int containerID;
private String itemName;
private int gameID;





public ContainerItem(String itemName, int containerID, int gameID) {
this.containerID = containerID;
this.itemName = itemName;
this.gameID = gameID;
}

public ContainerItem() {
	// TODO Auto-generated constructor stub
	}




public int getContainerID() {
	return containerID;
}





public void setContainerID(int containerID) {
	this.containerID = containerID;
}





public String getItemName() {
	return itemName;
}





public void setItemName(String itemName) {
	this.itemName = itemName;
}





public int getGameID() {
	return gameID;
}





public void setGameID(int gameID) {
	this.gameID = gameID;
}






}
