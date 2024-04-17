package edu.ycp.cs320.booksdb.model;


public class NPC {
	
	public String roomDialogue;
	public String speakDialogue;
	public int NPCID;
	public int roomID;
	public int health;
	public int gameID;
	public NPC(int roomID, int NPCID) {
		this.roomID = roomID;
		this.NPCID = NPCID;
	}
	
	public NPC() {
		
	}
	
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomDialogue (String n) {
		this.roomDialogue = n;
	}
	
	public String getRoomDialogue () {
		return roomDialogue;
	}
	
	public void setSpeakDialogue(String n) {
		this.speakDialogue = n;
	}
	
	public String getSpeakDialogue() {
		return speakDialogue;
	}

	public void setNPCID(int npcID) {
		this.NPCID = npcID;
	}
	
	public int getNPCID() {
		return NPCID;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
}