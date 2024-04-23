package edu.ycp.cs320.booksdb.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player{

	private int score;
	private int health;
	private int roomID;
	private int gameID;
	private int userID;
	private String log;
	private int playerID;
	private boolean isInCombat;
	
	//public Inventory inventory;
	//private ArrayList<Item> inventory = new ArrayList<Item>();
	/* public statistic score */
	
	
	
	
	
	//maybe adding more parameters for password and username
	//i dont know how the login page will look yet though
	public Player(int roomID, int score) {
		this.roomID = roomID;
		this.score = score;
		
		
	}
	
	public Player() {
		
	}
	
	
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void incrementScore(int add) {
		score+=add;
	}
	
	public int getScore() {
		return score;
	}





	public int getHealth() {
		return health;
	}




	public void setHealth(int health) {
		this.health = health;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log=log;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID=playerID;
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public void setGameID(int gameID) {
		// TODO Auto-generated method stub
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}

	public void setUserID(int userID) {
		// TODO Auto-generated method stub
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}

	public boolean isInCombat() {
		return isInCombat;
	}

	public void setInCombat(boolean isInCombat) {
		this.isInCombat = isInCombat;
	}

	
	
}