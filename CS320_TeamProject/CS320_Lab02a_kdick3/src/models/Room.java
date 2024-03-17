

public class Room {

	private int roomID; 
	private int yLoc;
	private int xLoc;
	private String shortDescription;
	private String longDescription;
	private boolean isLocked;
	
	
	public Room() {
		this.roomID = roomID;
		this.yLoc = yLoc;
		this.xLoc = xLoc;
	}
	
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getShortDescription() {
		return shortDescription;
	
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	
	}
	
	
	public String refreshroom() {
		return shortDescription;
	}
	
	public void updateScoreFirstTravelled() {
		int tempScore = User.getScore() + 50;
		 User.setScore(tempScore);
	}
	
	public int updateScore(int currentScore) {
		 int newScore = currentScore + 50;
		return newScore;
	}
	
	public boolean isValidMove (/*direction*/) {
		/*implement if a room exists and can be moved to, to return true, otherwise return false*/
	}
	
	public String display() {
		return longDescription;
	}
	
	
	
	
	
	
	
	
	
	
}
