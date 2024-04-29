	package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.*;

public class GameModel {
		private String input;
		public List<Room> Rooms;
		public List<Item> Items = new ArrayList<Item>();
		public List<NPC> NPCs = new ArrayList<>();
		public List<RoomConnection> RoomConnections = new ArrayList<RoomConnection>();
		public List<Container> Containers = new ArrayList<Container>();
		public Player player;
		public String log;
		public String error = "working";

		public GameModel() {
			
		}
		
		public GameModel(Player player, List<Room> rooms, List<Item> items, List<NPC> NPCs, List<RoomConnection> roomConnections, List<Container> containers) {
			this.player = player;
			this.log=player.getLog();
			this.Rooms = rooms;
			this.Items = items;
			this.NPCs = NPCs;
			this.RoomConnections = roomConnections;
			this.Containers = containers;
		}

		public void setAction(String input) {
			this.input = input;
		}

		public String getAction() {
			return input;
		}
		
		public Player getPlayer() {
			return player;
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
		
	
	