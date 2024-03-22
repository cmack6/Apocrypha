package models;


public class NPC extends Actor{
	
	public String NPCInteraction;
	public int NPCID;
	public NPC(int roomID, int NPCID) {
		this.roomID = roomID;
		this.NPCID = NPCID;
	}
	
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setNPCInteraction (String n) {
		this.NPCInteraction = n;
	}
	
	public String getNPCInteraction () {
		return NPCInteraction;
	}
	

	
	
}