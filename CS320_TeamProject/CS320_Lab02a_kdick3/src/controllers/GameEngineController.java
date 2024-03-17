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

		
		
		public void setShortDescription(int roomIndex, String shortDescription) {
			model.Rooms.get(roomIndex).setShortDescription(shortDescription);
		
		}
		
		public void setLongDescription(int roomIndex, String longDescription) {
			model.Rooms.get(roomIndex).setLongDescription(longDescription);
		
		}
		

	}
		
	
	