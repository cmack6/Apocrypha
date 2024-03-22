package controllers;

import models.GameModel;
import models.Item;
import models.NPC;
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
		
		/*public void setItem(int roomIndex, int newRoomID) {
			model.Rooms.get(roomIndex).setRoomID(newRoomID);
			
		}*/
		
		public void setRoomConnections(int roomID, int north, int south, int east, int west) {
			model.Rooms.get(roomID).setRoomConnections(north, south, east, west);
			
		}
		
		public void setShortDescription(int roomIndex, String shortDescription) {
			model.Rooms.get(roomIndex).setShortDescription(shortDescription);
		
		}
		
		public void setLongDescription(int roomIndex, String longDescription) {
			model.Rooms.get(roomIndex).setLongDescription(longDescription);
		
		}
		
		public void setItemDescription(int itemID, String description) {
			model.Items.get(itemID).setItemDescription(description);
		}
		
		//FOR ITEMS
		public void setRoomDescription(int itemID, String description) {
			model.Items.get(itemID).setRoomDescription(description);
		}
		
		public void setNPCInteraction(int NPCID, String interaction) {
			model.NPCs.get(NPCID).setNPCInteraction(interaction);
		}
		
		public void setNPCDialogue(int NPCID, String dialogue) {
			model.NPCs.get(NPCID).setNPCDialogue(dialogue);
		}
		
		
		public String processInput(GameModel model, String input) {
			String setOutput = "";
			
			String splitInput = input;
			String parts[] = splitInput.split(" ", 2);
			
			
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
			else if(((parts[0].equals("take") || parts[0].equals("grab") )&& input.length()>5) || (parts[0].equals("pickup") && input.length()>7)) {
				pickUp(model, parts[1]);
			}
			
			else if(input.equals("inventory")||input.equals("i")||input.equals("inv")) {
				inventory(model);
				setOutput = "inventory";
			}
			else if(input.equals("room description") || input.equals("room")) {
				roomInventory(model);
				setOutput = "room inventory";
			}
			else if(input.equals("talk")) {
				setOutput = "talk";
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
				for(int i = 0; i<model.Items.size(); i++) {
					if(model.Items.get(i).getLocation() == model.getUser().getRoomID()) {
						output += " " + model.Items.get(i).getRoomDescription();
					}
				}
				for(int i=0;i<model.NPCs.size();i++) {
					if(model.NPCs.get(i).getRoomID()==model.getUser().getRoomID()) {
						output += "<br><br>"+model.NPCs.get(i).getNPCInteraction();
					}
				}
			}
			
			else if(setOutput.equals("inventory")) {
				output = inventory(model);
			}
			
			else if(setOutput.equals("talk")) {
				output = talk(model);
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
		
		public void addNPC(GameModel model, NPC npc) {
			model.NPCs.add(npc);
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
			//NOTE TO SELF FOR KORBIN, take name and value and check size then add sizes and subtract from maax size to be determined
			//after that populate the space in between with characters-----
			
			String totalItems = "<p>" + "YOUR INVENTORY" + "</p><p>" + "--------------------------------" + "</p><p>" + "NAME" + "----------" + "VALUE" + "</p>";
			
			for(int i = 0; i<model.Items.size(); i++) {
				
				if(model.Items.get(i).getLocation() == -1) {
					totalItems = totalItems + "<p>" + model.Items.get(i).getName() + "----" + model.Items.get(i).getValue() + "</p>";
					//totalItems = totalItems + " " + model.Items.get(i).getName();
					
				}
				
				
			}
			
			
			
			return totalItems + "<p>" + "--------------------------------" + "</p>";
		}


		@Override
		public String roomInventory(GameModel model) {
			
			/*
			String totalItems = "";
			for(int i = 0; i<model.Items.size(); i++) {
				if(model.Items.get(i).getLocation() == model.user.getRoomID()) {
					totalItems = totalItems + " " + model.Items.get(i).getName();
				}
			}
			*/
			
			
			return null ;
		}

	
		@Override
		public String talk(GameModel model) {
			String dialogue = "";
			for(int i=0;i<model.NPCs.size();i++) {
				if(model.NPCs.get(i).getRoomID()==model.getUser().getRoomID()) {
					dialogue = model.NPCs.get(i).getNPCDialogue();
				}
			}
			if(dialogue.length()==0) {
				dialogue = "There is nobody to talk to here.";
			}
			return dialogue;
		}

		

	}
		
	
	