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

		
		
		public String roomShortDescription() {
			
			return Room.getShortDescription();
		}
		
		

	}
		
	
	