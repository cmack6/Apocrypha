package edu.ycp.cs320.booksdb.model;


public class NPC {
	
public int npcID;
public int inventoryID;
public int roomID;
public String name;
public String roomDialogue;
public String speakDialogue;
public String deathDialogue;
public int health;
public String weakness;
public String effectType;
public int effectLow;
public int effectHigh;
public int gameID;


	
	public NPC() {
		
	}



	public void setNPCID(int npcID) {
		this.npcID = npcID;
		
	}



	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;		
	}



	public void setRoomID(int roomID) {
		this.roomID = roomID;
		
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setRoomDialogue(String roomDialogue) {
		this.roomDialogue = roomDialogue;
	}



	public void setSpeakDialogue(String speakDialogue) {
		this.speakDialogue = speakDialogue;
	}



	public void setDeathDialogue(String deathDialogue) {
		this.deathDialogue = deathDialogue;
	}



	public void setHealth(int health) {
		this.health = health;
	}



	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}



	public void setEffectType(String effectType) {
		this.effectType = effectType;
	}



	public void setEffectLow(int effectLow) {
		this.effectLow = effectLow;
	}



	public void setEffectHigh(int effectHigh) {
		this.effectHigh = effectHigh;
	}



	public void setGameID(int gameID) {
		this.gameID = gameID;
	}



	public int getNPCID() {
		return npcID;
	}



	public int getInventoryID() {
		return inventoryID;
	}



	public int getRoomID() {
		return roomID;
	}



	public String getName() {
		return name;
	}



	public String getRoomDialogue() {
		return roomDialogue;
	}



	public String getSpeakDialogue() {
		return speakDialogue;
	}



	public String getDeathDialogue() {
		return deathDialogue;
	}



	public int getHealth() {
		return health;
	}



	public String getWeakness() {
		return weakness;
	}



	public String getEffectType() {
		return effectType;
	}



	public int getEffectLow() {
		return effectLow;
	}



	public int getEffectHigh() {
		return effectHigh;
	}



	public int getGameID() {
		return gameID;
	}
	
	




	
	
	
	
	
	
	
	
}