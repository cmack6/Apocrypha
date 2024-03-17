package controllers;

import java.util.ArrayList;

import models.GameModel;
import models.Room;


	public class GameEngineController {


		private GameModel model;
		private ArrayList<Room> Rooms = new ArrayList<Room>();

		public GameEngineController(GameModel model) {
			this.model = model;
		}
		
		public void addNewRoom(Room roomNum)) {
			Rooms.add(Room);
			
		}

		
		
		public String roomShortDescription() {
			
			return Room.getShortDescription();
		}
		
		

	}
		
	
	