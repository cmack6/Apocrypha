	package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;

import edu.ycp.cs320.booksdb.model.Item;
import edu.ycp.cs320.booksdb.model.NPC;
import edu.ycp.cs320.booksdb.model.Room;
import edu.ycp.cs320.booksdb.model.User;

public class GameModel {
		private String input;
		public ArrayList<Room> Rooms;
		public ArrayList<Item> Items = new ArrayList<Item>();
		public ArrayList<NPC> NPCs = new ArrayList<>();
		public User user;
		public String log;
		public String error = "working";

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
		
		public Room getRoom(int roomIndex) {
			return Rooms.get(roomIndex);
		}
		
		public String getLog() {
			return log;
		}
		
		public void setInvalidCommand(String input) {
			error="''" + input + "'' is not a valid command.";
		}
		
		public void setInvalidObjectInteraction(String object) {
			error = object + " is not interactable";
			//error = "''" + object + "'' is not interactable at this moment";
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
		
	
	