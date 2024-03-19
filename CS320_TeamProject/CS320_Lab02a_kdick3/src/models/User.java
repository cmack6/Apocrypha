package models;

import java.util.ArrayList;
import java.util.Scanner;

public class User extends Actor{

	private String password;
	private String username;
	private int roomID;
	private int score;
	//public Inventory inventory;
	private ArrayList<Item> inventory;
	/* public statistic score */
	
	
	
	
	
	//maybe adding more parameters for password and username
	//i dont know how the login page will look yet though
	public User(int roomID, int score) {
		this.roomID = roomID;
		this.score = score;
		
	}
	
	public void addToInventory(Item item) {
		inventory.add(item);
	}
	
	public void removeFromInventory(Item item) {
		inventory.remove(item);
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

	public ArrayList<Item> getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	
	
	
}