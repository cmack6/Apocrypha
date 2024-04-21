package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.lab02.model.*;
import edu.ycp.cs320.booksdb.model.*;

	



	public class GameEngineController implements Commands {

		//int isAbletoMove = 0;

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
			model.NPCs.get(NPCID).setRoomDialogue(interaction);
		}
		
		public void setNPCDialogue(int NPCID, String dialogue) {
			model.NPCs.get(NPCID).setSpeakDialogue(dialogue);
		}
		
		
		public String processInput(GameModel model, String input) {
			String setOutput = "";
			String itemRequested = "";
			//int countForItemDescription = 0;
			//input=input.toLowerCase();
			String splitInput = input;
			String parts[] = splitInput.split(" ", 2);
			
			/*
			
			for(int i = 0; i<model.Items.size(); i++) {
				if(input.equals(model.Items.get(i).getName()) && model.Items.get(i).getLocation() == -1) {
					 itemRequested = model.Items.get(i).getName();
					 countForItemDescription++;
				}
			}
			*/
			
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
				setOutput = "new item";
			}
			
			else if(input.equals("inventory")||input.equals("i")) {
				inventory(model);
				setOutput = "inventory";
			}
			
			else if(input.equals("talk")) {
				setOutput = "talk";
			}
			
			else if(input.equals("help")||input.equals("HELP")||input.equals("Help")) {
				setOutput = "help";
			}
			

			else if(parts[0].equals("inv") && input.length()>4){
				itemRequested = parts[1];
				setOutput = "item description";
			}

			else if(input.equals("score")){
				setOutput = "score";
			}
			/*
			else if(countForItemDescription>0) {
				
				setOutput = "item description";
			}
			*/
			else { 
				
				
	
				model.setInvalidCommand(input);
			}
			
			return getOutput(model, setOutput, itemRequested);
			
		}
		
		
		
		
		
		
		private String getOutput(GameModel model, String setOutput, String itemName) {
			String output = "";
			if(setOutput.equals("move")) {
				//if(isAbletoMove != -1) {
				if(model.Rooms.get(model.getPlayer().getRoomID()).getIsEntered()) {
					model.getPlayer().setScore(model.getPlayer().getScore()-10);
				}
				else {
					model.getPlayer().setScore(model.getPlayer().getScore()+50);
				}
				output = model.Rooms.get(model.getPlayer().getRoomID()).getDescription();
				for(int i = 0; i<model.Items.size(); i++) {
					if(model.Items.get(i).getLocation() == model.getPlayer().getRoomID()) {
						output += " " + model.Items.get(i).getRoomDescription();
					}
				}
				for(int i=0;i<model.NPCs.size();i++) {
					if(model.NPCs.get(i).getRoomID()==model.getPlayer().getRoomID()) {
						output += "<br><br>"+model.NPCs.get(i).getRoomDialogue();
						}
					}
				}
				//else {
				//	output += " " + "You cannot move that way!";
				//}
			//}
			
			else if(setOutput.equals("inventory")) {
				output = inventory(model);
			}
			
			else if(setOutput.equals("help")) {
				output = help(model);
			}
			
			else if(setOutput.equals("talk")) {
				output = talk(model);
			}
			
			else if(setOutput.equals("item description")) {
				output = itemDescription(model, itemName);
			}
			
			else if(setOutput.equals("new item")) {
				output = "You gained a new item!";
			}
			
			else if(setOutput.equals("score")) {
				output = score(model);
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
			for(RoomConnection roomConnection : model.RoomConnections) {
				if(roomConnection.getCommand().equals(direction)&&roomConnection.getStartingRoomID()==model.getPlayer().getRoomID()) {
					temp=roomConnection.getDestinationRoomID();
				}
			}
			if(temp!=-1) {
			model.getPlayer().setRoomID(temp);
			}
			//else {
			//	isAbletoMove = -1;
			//}
			
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
				if((model.Items.get(i).getName().equals(nameOfItem)) && (model.Items.get(i).getLocation() == model.getPlayer().getRoomID())) {
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
			
			String totalItems = "<p>" + "YOUR INVENTORY" + "</p><p>" + "--------------------------------" + "</p>";
			
			for(int i = 0; i<model.Items.size(); i++) {
				
				if(model.Items.get(i).getLocation() == -1) {
					totalItems = totalItems + "<p>" + model.Items.get(i).getName() + "</p>";
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
				if(model.NPCs.get(i).getRoomID()==model.getPlayer().getRoomID()) {
					dialogue = model.NPCs.get(i).getSpeakDialogue();
				}
			}
			if(dialogue.length()==0) {
				dialogue = "There is nobody to talk to here.";
			}
			return dialogue;
		}

		@Override
		public String score(GameModel model) {
			return "Your score is currently: "+model.getPlayer().getScore()+"<br>You can increase your score by entering new rooms, and lose score by entering rooms you already entered.";
		}

		@Override
		public String itemDescription(GameModel model, String itemName) {
				String itemDescription = "";
				int notDoable = 0;
				
				for(int i = 0; i<model.Items.size(); i++) {
					if(itemName.equals(model.Items.get(i).getName())) {
						itemDescription = itemDescription + "<p>" + model.Items.get(i).getItemDescription() + "</p>";
					notDoable++;
					}
				}
				if(notDoable == 0) {
					 model.setInvalidObjectInteraction(itemName);
					 return "";
				}
				else
			return itemDescription;
		}

		@Override
		public String help(GameModel model) {
			//String dash1 = "-";
			//String dash2 = "-";
			//int count1 = 75;
			//int count2 = 30;
		
			String help = "<p>" + "OBJECTIVE:" + "</p>";
			help = help + "<p>" + "----------------------------------------------------------------------------------" + "</p>"; 
			help = help + "<p>" + "Your objective is to collect the most amount of bag you can" + "</p>";
			help = help + "<p>" + "----------------------------------------------------------------------------------" + "</p>";
			help = help + "<p>" + 	"COMMANDS:" + "</p><p>" + "------------------------------------------------------" + "</p>";
			help = help + "<p>" + "Move: north, south, east, west" + "</p><p>" + "Grab Item: grab, take, pickup *item*" + "</p>";
			help = help + "<p>" + "Display Item Description: inv *item*" + "</p>";
			help = help + "<p>" + "Talk To NPC: talk" + "</p><p>" + "Open Inventory: inventory, i" + "</p>";
			help = help + "<p>" + "See your score: score" + "</p>";

			help = help + "<p>" + "------------------------------------------------------" + "</p>";
			return help;
		}
		

	}
		
	
	