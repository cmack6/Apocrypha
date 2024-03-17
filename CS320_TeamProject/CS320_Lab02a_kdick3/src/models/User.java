package models;

import java.util.Scanner;

public class User extends Actor{

	private String password;
	private String username;
	private int score;
	/* public statistic score */
	
	//this jaunt is all over the place
	
	public String select() {
		Scanner input = new Scanner(password);
		String select = input.next();
		input.close();
		return select;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
}