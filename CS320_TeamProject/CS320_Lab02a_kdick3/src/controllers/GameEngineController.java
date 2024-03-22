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
		
		public void setItem(int roomIndex, int newRoomID) {
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
		
		public String processInput(GameModel model, String input) {
			String setOutput = "";
			String splitInput = input;
			String parts[] = splitInput.split(" ", 2);
			//System.out.println(String.format("cr: %s, cdr: %s", parts[0], parts[1]));
			
			if(input.equals("north")||input.equals("west")||input.equals("east")||input.equals("south")||input.equals("n")||input.equals("s")||input.equals("e")||input.equals("w")) {
				move(model,input);
				setOutput = "move";
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
			else if(parts[0].equals("take") || parts[0].equals("grab") || parts[0].equals("pick up")) {
				pickUp(model, parts[1]);
			}
			
			else if(input.equals("inventory")||input.equals("i")) {
				inventory(model);
				setOutput = "inventory";
			}
			else if(input.equals("room inventory") || input.equals("room items")) {
				roomInventory(model);
				setOutput = "room inventory";
			}
			
			else {
				
	
				model.setInvalidCommand(input);
			}
			
			return getOutput(model, setOutput);
			
		}
		
		
		
		
		
		
		private String getOutput(GameModel model, String setOutput) {
			String output = "";
			if(setOutput.equals("move")) {
				output = model.Rooms.get(model.getUser().getRoomID()).getDescription();
			}
			
			else if(setOutput.equals("inventory")) {
				output = inventory(model);
			}
			return output;
			
			
		}



		public void addItem(GameModel model, Item item) {
			model.Items.add(item);
		}
		
		public void removeItem(GameModel model, Item item) {
			model.Items.remove(item);
		}
		
		public void getRoomInventory(GameModel model, int location) {
			model.Items.get(location);
		}
		
		public void moveItem(GameModel model, int newLocation) {
			//model.Items
		}
		
		




		@Override
		public void move(GameModel model, String direction) {
			//there might be a better way to do this without having to pass the model 
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
		public void pickUp(GameModel model, String nameOfItem) {
		int notDoable = 0;
			for(int i = 0; i<model.Items.size(); i++) {
				if((model.Items.get(i).getName().equals(nameOfItem)) && (model.Items.get(i).getLocation() == model.getUser().getRoomID())) {
					model.Items.get(i).setLocation(-1);
					notDoable ++;
				}
			}
			if(notDoable == 0) {
				model.setInvalidObjectInteraction(nameOfItem);
			}
			
			
			
			
		}



		@Override
		public String inventory(GameModel model) {
			String totalItems = "";
			for(int i = 0; i<model.Items.size(); i++) {
				if(model.Items.get(i).getLocation() == -1) {
					totalItems = totalItems + " " + model.Items.get(i).getName();
				}
			}
			
			return totalItems;
		}


		@Override
		public String roomInventory(GameModel model) {
			String totalItems = "";
			for(int i = 0; i<model.Items.size(); i++) {
				if(model.Items.get(i).getLocation() == model.user.getRoomID()) {
					totalItems = totalItems + " " + model.Items.get(i).getName();
				}
			}
			
			return totalItems;
		}

	


		

	}
		
	
	