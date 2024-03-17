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
		
		public GameEngineController() {
		}

		
		
		public String roomShortDescription() {
			
			return Rooms.get()
		}
		
		

	}
		
	
	