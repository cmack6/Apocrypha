package edu.ycp.cs320.lab02.model;

public class Room {

	public int roomID; 
	public int yLoc;
	public int xLoc;
	public String shortDescription;
	public String longDescription;
	
	
	public String refreshroom() {
		return shortDescription;
	}
	
	public int updateScore(int currentScore) {
		private int newScore = updateScore + 50;
		return newScore;
	}
	
	public boolean isValidMove (/*direction*/) {
		/*implement if a room exists and can be moved to, to return true, otherwise return false*/
	}
	
	public String display() {
		return longDescription;
	}
	
	
	
	
	
	
	
	
	
	
}
