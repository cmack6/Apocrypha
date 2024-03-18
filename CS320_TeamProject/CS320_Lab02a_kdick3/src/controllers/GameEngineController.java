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
		
		
		
		/*
		public void addNewRoom() {
			Room a = new Room();
			model.Rooms.add(a);
			
		}
		*/
		
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
		
		public void processInput(GameModel model, String input) {
			if(input.equals("north")||input.equals("west")||input.equals("east")||input.equals("south")||input.equals("n")||input.equals("s")||input.equals("e")||input.equals("w")) {
				move(model.getUser(),input);
			}
			else if(input.equals("jump")||input.equals("j")) {
				jump();
			}
			else if(input.equals("crawl")||input.equals("c")) {
				crawl();
			}
			else if(input.equals("fight")||input.equals("f")) {
				fight();
			}
			else if(input.equals("pickup")||input.equals("grab")) {
				pickUp();
			}
			else {
				model.setInvalidCommand(input);
			}
		}
		
		
		




		@Override
		public void move(User user, String direction) {
			//there might be a better way to do this without having to pass the user 
			//back and forth 
			int temp = 0;
			GameModel tempModel = new GameModel();//this is a temp model for the hard written tests
			if(direction.equals("north")) {
			temp = tempModel.Rooms.get(user.getRoomID()).getRoomConnectionNorth();
			user.setRoomID(temp);
			}
			
			else if(direction.equals("south")) {
				temp = tempModel.Rooms.get(user.getRoomID()).getRoomConnectionSouth();
				user.setRoomID(temp);
				}
			
			else if(direction.equals("east")) {
				temp = tempModel.Rooms.get(user.getRoomID()).getRoomConnectionEast();
				user.setRoomID(temp);
				}
			
			else
				temp = tempModel.Rooms.get(user.getRoomID()).getRoomConnectionWest();
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
		
	
	