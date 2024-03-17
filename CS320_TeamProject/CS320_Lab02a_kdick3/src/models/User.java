package models;

import java.util.Scanner;

public class User extends Actor{

	private String password;
	private String username;
	private int roomID;
	private int score;
	/* public statistic score */
	
	
	//this jaunt is all over the place
	
	
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
	
}