package models;

import java.util.ArrayList;
import java.util.Scanner;

public class User extends Actor{

	private String password;
	private String username;
	private int score;
	//public Inventory inventory;
	//private ArrayList<Item> inventory = new ArrayList<Item>();
	/* public statistic score */
	
	
	
	
	
	//maybe adding more parameters for password and username
	//i dont know how the login page will look yet though
	public User(int roomID, int score) {
		this.roomID = roomID;
		this.score = score;
		
		
	}
	
	
	
	
	public String select() {
		Scanner input = new Scanner(password);
		String select = input.next();
		input.close();
		return select;
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
	
	public int getScore() {
		return score;
	}

	public void getInventory() {
		//check old cs201 labs
		
		
	}

	
	
	
	
}