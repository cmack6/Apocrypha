package controllers;

import java.util.ArrayList;

import models.GameModel;
import models.Room;


	public class GameEngineController {


		private GameModel model;
		
		public GameEngineController(GameModel model) {
			this.model = model;
		}
		
		
		
		
		public void addNewRoom(Room roomNum) {
			model.Rooms.add(roomNum);
			
		}
		
		public void setRoomID(int roomIndex, int newRoomID) {
			model.Rooms.get(roomIndex).setRoomID(newRoomID);
			
		}
		
		public void setRoomConnections(int roomID, int north, int south, int east, int west) {
			model.Rooms.get(roomID).setRoomConnections(north, south, east, west);
			
		}
		
		public int getRoomConnectionNorth() {
			model.Rooms.get(index)
		}
		
		public int getRoomConnectionSouth() {
			return south;
		}
		
		public int getRoomConnectionEast() {
			return east;
		}
		
		public int getRoomConnectionWest() {
			return west;
		}

		
		
		public void setShortDescription(int roomIndex, String shortDescription) {
			model.Rooms.get(roomIndex).setShortDescription(shortDescription);
		
		}
		
		public void setLongDescription(int roomIndex, String longDescription) {
			model.Rooms.get(roomIndex).setLongDescription(longDescription);
		
		}
		
		public boolean isValidMove(String direction, int userRoomID) {
			if() {
				
			}
		}

	}
		
	
	