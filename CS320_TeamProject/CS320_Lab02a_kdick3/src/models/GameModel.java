	package models;

import java.util.ArrayList;

public class GameModel {
		private String input;
		public ArrayList<Room> Rooms;
		public User user;
		public String log;
		public String error = null;

		public GameModel() {
			Rooms = new ArrayList<Room>();
			for(int i=0; i<9; i++) {
				Room a = new Room();
				this.Rooms.add(a);
			}
		}
		
		public GameModel(User user, String log) {
			this.user=user;
			this.log=log;
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
		
		public User getUser() {
			return user;
		}
		
		public String getLog() {
			return log;
		}
		
		public void setInvalidCommand(String input) {
			error=input + "is not a valid command.";
		}
		
		public void setLog(String newLog) {
			log=newLog;
		}
		
		public String getError() {
			return error;
		}
		
		public void setError(String newError) {
			error=newError;
		}
		
		
		
		
	}
		
	
	