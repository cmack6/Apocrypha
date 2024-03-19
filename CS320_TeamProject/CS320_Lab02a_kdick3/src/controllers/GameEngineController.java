package controllers;

import models.GameModel;
import models.Item;
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
				move(model,input);
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
			
			else if(input.equals("inventory")||input.equals("i")) {
				inventory(model);
			}
			
			else {
				model.setInvalidCommand(input);
			}
		}
		
		
		public void addItemToInventory(GameModel model, Item item, int roomIndex) {
			model.Rooms.get(roomIndex).roomInventory.add(item);
			
		}
		
		
		




		@Override
		public void move(GameModel model, String direction) {
			//there might be a better way to do this without having to pass the user 
			//back and forth 
			int temp = 0;
			if(direction.equals("north")||direction.equals("n")) {
			temp = model.Rooms.get(model.getUser().getRoomID()).getRoomConnectionNorth();
			model.getUser().setRoomID(temp);
			}
			
			else if(direction.equals("south")||direction.equals("s")) {
				temp = model.Rooms.get(model.getUser().getRoomID()).getRoomConnectionSouth();
				model.getUser().setRoomID(temp);
				}
			
			else if(direction.equals("east")||direction.equals("e")) {
				temp = model.Rooms.get(model.getUser().getRoomID()).getRoomConnectionEast();
				model.getUser().setRoomID(temp);
				}
			
			else
				temp = model.Rooms.get(model.getUser().getRoomID()).getRoomConnectionWest();
				model.getUser().setRoomID(temp);
				
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



		
		
		@Override
		public String inventory(GameModel model) {
			
			return model.user.getInventory().toString();
			
		}



		

	}
		
	
	