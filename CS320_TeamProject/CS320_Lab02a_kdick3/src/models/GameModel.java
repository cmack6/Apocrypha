	package models;

import java.util.ArrayList;

public class GameModel {
		private String input;
		public ArrayList<Room> Rooms;


		public GameModel() {
			Rooms = new ArrayList<Room>();
			for(int i=0; i<9; i++) {
				Room a = new Room();
				this.Rooms.add(a);
			}
		}
		

		public void setAction(String input) {
			this.input = input;
		}

		public String getAction() {
			return input;
		}
		
		
		
		
		
	}
		
	
	