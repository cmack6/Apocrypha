	package models;

import java.util.ArrayList;

public class GameModel {
		private String input;
		public ArrayList<Room> Rooms = new ArrayList<Room>();


		public GameModel() {
		}

		public void setAction(String input) {
			this.input = input;
		}

		public String getAction() {
			return input;
		}
		
		
		
		
	}
		
	
	