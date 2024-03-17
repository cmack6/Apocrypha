package controllers;

import java.util.ArrayList;

import models.GameModel;
import models.Room;
import models.User;


	public class GameEngineController implements Commands {


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
		
		public void setShortDescription(int roomIndex, String shortDescription) {
			model.Rooms.get(roomIndex).setShortDescription(shortDescription);
		
		}
		
		public void setLongDescription(int roomIndex, String longDescription) {
			model.Rooms.get(roomIndex).setLongDescription(longDescription);
		
		}
		
		
		
		
		
		
		




		@Override
		public void move(User user, String direction) {
			//there might be a better way to do this without having to pass the user 
			//back and forth 
			int temp = 0;
			
			if(direction.equals("north")) {
			temp = model.Rooms.get(user.getRoomID()).getRoomConnectionNorth();
			user.setRoomID(temp);
			}
			
			else if(direction.equals("south")) {
				temp = model.Rooms.get(user.getRoomID()).getRoomConnectionSouth();
				user.setRoomID(temp);
				}
			
			else if(direction.equals("east")) {
				temp = model.Rooms.get(user.getRoomID()).getRoomConnectionEast();
				user.setRoomID(temp);
				}
			
			else
				temp = model.Rooms.get(user.getRoomID()).getRoomConnectionWest();
				user.setRoomID(temp);
				
		}




		@Override
		public void jump() {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void crawl() {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void fight() {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void pickUp() {
			// TODO Auto-generated method stub
			
		}

	}
		
	
	