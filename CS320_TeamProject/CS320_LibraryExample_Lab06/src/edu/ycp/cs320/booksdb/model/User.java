package edu.ycp.cs320.booksdb.model;

import java.util.ArrayList;
import java.util.Scanner;

public class User{

	private String password;
	private String username;
	private int userID;
	
	
	
	public User() {
		
	}
	//initial user constructor
	public User(String username, String password) {
		this.password = password;
		this.username = username;
		
	}
	
	
	




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	
	
}