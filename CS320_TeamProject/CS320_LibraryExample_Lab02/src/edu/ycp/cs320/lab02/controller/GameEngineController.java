package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.lab02.model.*;

import java.util.ArrayList;
import java.util.Random;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

	



	public class GameEngineController implements Commands {

		int isAbletoMove = 0;

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
		/*
		public void setRoomDescription(int itemID, String description) {
			model.Items.get(itemID).setRoomDescription(description);
		}
		*/
		public void setNPCInteraction(int NPCID, String interaction) {
			model.NPCs.get(NPCID).setRoomDialogue(interaction);
		}
		
		public void setNPCDialogue(int NPCID, String dialogue) {
			model.NPCs.get(NPCID).setSpeakDialogue(dialogue);
		}
		
		
		public String processInput(GameModel model, String input) {
			String setOutput = "";
			String actionee = "";
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
			Boolean move = false;
			for(RoomConnection roomConnection: model.RoomConnections) {
				//System.out.println(model.getPlayer().getGameID()+ "" + roomConnection.getGameID() + "" + model.getPlayer().getRoomID() + "" + roomConnection.getStartingRoomID() + "" + input + "" + roomConnection.getCommand()); 

				if(model.getPlayer().getGameID()==roomConnection.getGameID()&&model.getPlayer().getRoomID()==roomConnection.getStartingRoomID()&&input.equals(roomConnection.getCommand())) {
					move = true;
				}
			}
			if(move) {
				Boolean isMoved = move(model,input);
				if(isMoved) {
				setOutput = "move";
				}
				else {
				setOutput = "invalidMove";
				}
			}
			else if(input.equals("jump")||input.equals("j")) {
				jump();
			}
			else if(input.equals("run")||input.equals("flee")||input.equals("escape")) {
				setOutput = "run";
			}
			else if(input.equals("crawl")||input.equals("c")) {
				crawl();
			}
			else if(input.equals("restart")||input.equals("Restart")) {
				setOutput = "restart";
			}
			else if(input.equals("fight")||input.equals("f")) {
				
				setOutput = "fight";
			}
			else if(((parts[0].equals("use") && input.length()>4))) {
				actionee = parts[1];
				setOutput = "use";
			}

			else if(input.equals("stats")) {
				
				setOutput = "stats";
			}

			else if((parts[0].equals("equip") && input.length()>5)) {
				actionee = parts[1];
				setOutput = "equip";
			}
			
			else if((parts[0].equals("unequip") && input.length()>5)) {
				actionee = parts[1];
				setOutput = "unequip";
			}

			else if(parts[0].equals("inspect") && input.length()>9){
				actionee = parts[1];
				setOutput = "container description";
			}
			else if(((parts[0].equals("take") || parts[0].equals("grab") )&& input.length()>5) || (parts[0].equals("pickup") && input.length()>7)) {
				actionee = parts[1];
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
				actionee = parts[1];
				setOutput = "item description";
			}

			else if(input.equals("score")){
				setOutput = "score";
			}
			
			else if(input.equals("save")) {
				save(model);
				setOutput = "save";
			}
			/*
			else if(countForItemDescription>0) {
				
				setOutput = "item description";
			}
			*/
			else { 
				
				
	
				model.setInvalidCommand(input);
			}
			
			return getOutput(model, setOutput, actionee);
			
		}
		
		
		
		
		
		
		private String getOutput(GameModel model, String setOutput, String actionee) {
			String output = "";
			
			boolean currentlyInCombat = model.getPlayer().isInCombat();
			
			 if(setOutput.equals("restart")) {
				restart(model);
			}
			
			
			
			if(model.getPlayer().getHealth() <= 0) {
				return "You have died! Restart";
			}
			
			if(currentlyInCombat == true && setOutput != "run" && setOutput != "use" && setOutput != "inventory" && setOutput != "item description" && setOutput != "equip" && setOutput != "unequip" && setOutput != "stats") {
				output += " " + "You are currently engaged in a fight!";
			}
			
			
			 else if(setOutput.equals("move")) {
				if((isAbletoMove != -1)) {
					
				if(model.Rooms.get(model.getPlayer().getRoomID()).getIsEntered()) {
					model.getPlayer().setScore(model.getPlayer().getScore()-10);
				}
				else {
					model.getPlayer().setScore(model.getPlayer().getScore()+50);
				}
				
				output = model.Rooms.get(model.getPlayer().getRoomID()).getDescription();
				
				
				for(int i = 0; i<model.Containers.size(); i++) {
					if(model.Containers.get(i).getRoomID() == model.getPlayer().getRoomID() && model.Containers.get(i).isOpened() == false && model.Containers.get(i).getGameID()==model.getPlayer().getGameID()) {
						output += " " + model.Containers.get(i).getInRoomDescription();
					}
				}
				for(int i=0;i<model.NPCs.size();i++) {
					if(model.NPCs.get(i).getRoomID()==model.getPlayer().getRoomID()&&model.NPCs.get(i).getGameID()==model.getPlayer().getGameID() && model.NPCs.get(i).getHealth() > 0) {
						output += "<br><br>"+model.NPCs.get(i).getRoomDialogue();
						}
					}
				}
				else {
					
					output += " " + "You cannot move that way! Try moving a different direction...";
				}
			}
			
			
			
			else if(setOutput.equals("invalidMove")) {
				output = "There is no place to go in that direction.";
			}
			
			else if(setOutput.equals("inventory")) {
				output = inventory(model);
			}
			
			else if(setOutput.equals("help")) {
				output = help(model);
			}
			 
			else if(setOutput.equals("run")) {
				output = run(model);
			}
			
			else if(setOutput.equals("talk")) {
				output = talk(model);
			}
			
			else if(setOutput.equals("fight")) {
				output = fight(model);
			}
			
			
			else if(setOutput.equals("stats")) {
				output = stats(model);
			}
			 
			else if(setOutput.equals("equip")) {
				output = equip(model, actionee);
			}
			 
			else if(setOutput.equals("unequip")) {
				output = unequip(model, actionee);
			}

	else if(setOutput.equals("use")) {
				output = use(model, actionee);
			}
			 
			else if(setOutput.equals("container description")) {
				output = inspect(model, actionee);
			}

			
			else if(setOutput.equals("item description")) {
				output = itemDescription(model, actionee);
			}
			
			else if(setOutput.equals("new item")) {
				output = pickUp(model, actionee);
			}
			
			else if(setOutput.equals("score")) {
				output = score(model);
			}
			
			else if(setOutput.equals("save")) {
				output = "Game saved successfully.";
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
		public Boolean move(GameModel model, String direction) {
			//there might be a better way to do this without having to pass the model 
			//back and forth 
			int temp = 0;

			Boolean isMoved = false;
			for(RoomConnection roomConnection: model.RoomConnections) {
				if(roomConnection.getCommand().equals(direction)&&roomConnection.getStartingRoomID()==model.getPlayer().getRoomID()) {
					temp=roomConnection.getDestinationRoomID();
				}
			}
			if(temp!=-1) {
			model.getPlayer().setRoomID(temp);
			isMoved = true;
			}
			return isMoved;
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
		public String fight(GameModel model) {
			
			
				String fightLog = "";
				
				for(int i=0;i<model.NPCs.size();i++) {
					
					//(model.NPCs.get(i).getGameID() == model.getPlayer().getGameID()) 
					//I TOOK THIS OUT OF THE FOR LOOP SOME RREAASON FIGHTING WOULDNT WORK WITH IT
					if((model.NPCs.get(i).getRoomID() == model.getPlayer().getRoomID()) && (model.NPCs.get(i).getGameID() == model.getPlayer().getGameID()) && model.NPCs.get(i).getHealth() > 0) {
						fightLog = "You have initiated combat with " + model.NPCs.get(i).getName() + "!";
						model.getPlayer().setInCombat(true);
					}
				}
				
			
				if(fightLog.length()==0) {
					fightLog = "There is no one to fight at your current location.";
				}
				
				
			
			
			
			
			return fightLog;
			
		}





		@Override
		public String pickUp(GameModel model, String nameOfItem) {
		
		
		for(int j = 0; j<model.Containers.size(); j++) {
			if(model.Containers.get(j).isOpened() == true) {
			if(model.Containers.get(j).getRoomID() == model.getPlayer().getRoomID()) {
				for(int i = 0; i<model.Items.size(); i++) {
					if(model.Items.get(i).getName().equals(nameOfItem) && model.Items.get(i).getContainerID() > 0 && model.Items.get(i).getContainerID() == model.Containers.get(j).getContainerID() && model.Containers.get(j).getGameID()==model.getPlayer().getGameID() && model.Items.get(i).getGameID() == model.getPlayer().getGameID()) {
						model.Items.get(i).setContainerID(-1);
						return "<p> You gained a new item! </p>";
						
						
					}
				}
			}
			}
			/*
			for(int i = 0; i<model.Items.size(); i++) {
				if((model.Items.get(i).getName().equals(nameOfItem)) && (model.containerList.get(j).getRoomID() == model.getPlayer().getRoomID()) && model.containerList.get(j).isOpened() == true && model.Items.get(i).getContainerID() > 0) {
					model.Items.get(i).setContainerID(-1);
					
					notDoable ++;
				}
			}
			*/
		}
		
		for(int i = 0; i<model.NPCs.size(); i++) {
			if(model.NPCs.get(i).getRoomID() == model.getPlayer().getRoomID() && model.NPCs.get(i).getHealth() <= 0) {
				for(int j = 0; j<model.Items.size(); j++) {
					if(model.Items.get(j).getName().equals(nameOfItem) && model.Items.get(j).getGameID()==model.getPlayer().getGameID()) {
						model.Items.get(j).setContainerID(-1);
						return "You gained a new item!";
					}
				}
			}
		}
		
		
		
			
			return "<p>" + nameOfItem + " is not interactable" + "</p>";
			
			 
			
			
			
			
			
			
		}





		@Override
		public String inventory(GameModel model) {
			//NOTE TO SELF FOR KORBIN, take name and value and check size then add sizes and subtract from maax size to be determined
			//after that populate the space in between with characters-----
			ArrayList<Item> equippedItems = new ArrayList<Item>();
			String helmet = "";
			String chestplate = "";
			String gloves = "";
			String pants = "";
			String boots = "";
			String offhand = "";
			String mainhand = "";
			String totalItems = "<p>" + "YOUR INVENTORY" + "</p><p>" + "--------------------------------" + "</p>";
			
			for(Item item : model.Items) {
				
				if(item.getContainerID() == -1 && item.isEquipped() == false && model.getPlayer().getGameID()==item.getGameID()) {
					totalItems = totalItems + "<p>" + item.getName() + "</p>";
					//totalItems = totalItems + " " + model.Items.get(i).getName();
					
				}
				
				
			}
			
			totalItems =  totalItems + "<p>" + "--------------------------------" + "</p>";
			
			totalItems = totalItems + "<p>" + "EQUIPPED" + "</p>";
			
			//just did this in case for the future
			for(Item item : model.Items) {
				if(item.isEquipped() && item.getContainerID() == -1 && item.getGameID()==model.getPlayer().getGameID()) {
					equippedItems.add(item);
				}
			}
			
			for(Item item : equippedItems) {
				if(item.getCategory().equals("helmet")) {
					helmet = item.getName();
				}
				else if(item.getCategory().equals("chestplate")) {
					chestplate = item.getName();
				}
				else if(item.getCategory().equals("gloves")) {
					gloves = item.getName();
				}
				else if(item.getCategory().equals("pants")) {
					pants = item.getName();
				}
				else if(item.getCategory().equals("boots")) {
					boots = item.getName();
				}
				else if(item.getCategory().equals("offhand")) {
					offhand = item.getName();
				}
				else if(item.getCategory().equals("mainhand")) {
					mainhand = item.getName();
				}
			}
			
			totalItems =  totalItems + "<p>" + "Helmet: " + helmet + "</p>"
						+	"<p>" + "Chestplate: " + chestplate + "</p>"
						+	"<p>" + "Gloves: " + gloves + "</p>"
						+	"<p>" + "Pants: " + pants + "</p>"
						+	"<p>" + "Boots: " + boots + "</p>"
						+	"<p>" + "Offhand: " + offhand + "</p>"
						+	"<p>" + "Mainhand: " + mainhand + "</p>";
			
			
			
			
			
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
					if(itemName.equals(model.Items.get(i).getName()) && model.Items.get(i).getContainerID() == -1) {
						itemDescription = itemDescription + "<p>" + model.Items.get(i).getItemDescription() + "</p>";
						
						if(model.Items.get(i).getType().equals("weapon")) {
							itemDescription = itemDescription + "<p>" + "This weapon does " + model.Items.get(i).getEffectLow() + "-" + model.Items.get(i).getEffectHigh() + " " + model.Items.get(i).getEffectType() + " damage";
						}
						if(model.Items.get(i).getType().equals("equipment")) {
							itemDescription = itemDescription + "<p>" + "This equipment provides " + model.Items.get(i).getDefenseNumber() + " " + model.Items.get(i).getArmorType() + " resistance";
						}
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
		
		
		@Override
		public void save(GameModel model) {
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			for(Item item: model.Items) {
				if(item.getGameID()==model.getPlayer().getGameID()) {
					db.updateItem(item);
				}
			}
			for(NPC npc: model.NPCs) {
				if(npc.getGameID()==model.getPlayer().getGameID()) {
					db.updateNPC(npc);
				}
			}
			db.updatePlayer(model.getPlayer());
		}



		@Override
		public String run(GameModel model) {
			
			String runAway = "";
			
			if(model.getPlayer().isInCombat() != false) {
				
				Random rand= new Random();
				
				int ran = rand.nextInt(100)+1;
				
				if(ran > 0 && ran < 25) {
					runAway = "You got away safely!";
					model.getPlayer().setInCombat(false);
				}
				else {
					runAway = "You cannot escape from combat right now! Either try again or stand your ground!";
				}
				
				
			}
			return runAway;
			
		}
		
		
		@Override
		public String inspect(GameModel model, String containerName) {
			String containerDescription = "";
			int notDoable = 0;
			int nothing = 0;
			
			for(int i = 0; i<model.Containers.size(); i++) {
				if(model.Containers.get(i).getName().equals(containerName) && model.getPlayer().getRoomID() == model.Containers.get(i).getRoomID()&&model.getPlayer().getGameID()==model.Containers.get(i).getGameID()) {
					containerDescription = model.Containers.get(i).getContainerDescription();
					
					for(int j = 0; j<model.Items.size(); j++) {
						//System.out.println(model.Items.get(j).getContainerID() + "" + model.Containers.get(i).getContainerID() + "" +  model.Items.get(j).getGameID() + "" + model.getPlayer().getGameID() + "" + model.Containers.get(i).getGameID()+ "" + model.getPlayer().getGameID());
						if(model.Items.get(j).getContainerID() == model.Containers.get(i).getContainerID() && model.Items.get(j).getGameID()==model.getPlayer().getGameID() && model.Containers.get(i).getGameID()==model.getPlayer().getGameID()) {
							containerDescription = containerDescription + "<p>" + model.Items.get(j).getName() + "</p>";
							
							nothing++;
						}
					}
					if(nothing == 0) {
						containerDescription = containerDescription + "<p> Nothing </p>";
					}
					model.Containers.get(i).setOpened(true);
				notDoable++;
				}
			}
			
			for(int i = 0; i<model.NPCs.size(); i++) {
				if(model.NPCs.get(i).getName().equals(containerName) && model.NPCs.get(i).getRoomID() == model.getPlayer().getRoomID() && model.getPlayer().isInCombat() == false && model.NPCs.get(i).getHealth() < 0 &&model.getPlayer().getGameID()==model.NPCs.get(i).getGameID()){
					containerDescription = containerDescription + "<p>" + "You loot " + model.NPCs.get(i).getName() + " to find:"+ "</p>";
					for(int j = 0; j<model.Items.size(); j++) {
						if(model.Items.get(j).getContainerID() == model.NPCs.get(i).getInventoryID()) {
							containerDescription = containerDescription + "<p>" + model.Items.get(j).getName() + "</p>";		
							nothing++;
							}
						}
					if(nothing == 0) {
						containerDescription = containerDescription + "<p> Nothing </p>";
					}
					notDoable++;
					}
				}
			
			
			if(notDoable == 0) {
				 model.setInvalidObjectInteraction(containerName);
				 return "";
			}
			else
		return containerDescription;
		}
		
		
		
		
		@Override
		public String use(GameModel model, String nameOfItem) {
			String use = "";
			int healthGained = 0;
			int damageDone = 0;
			int NPCDamageDone = 0;
			String NPCTypeOfDamage = "";
			
			
			
			
			
			for(int i = 0; i<model.Items.size(); i++) {

				if(model.Items.get(i).getName().equals(nameOfItem) && model.Items.get(i).getContainerID() == -1 && model.Items.get(i).getGameID()==model.getPlayer().getGameID()) {
				
					if(model.Items.get(i).getEffectType().equals("health")) {
						Random rand = new Random();
						healthGained = rand.nextInt(model.Items.get(i).getEffectHigh()) + model.Items.get(i).getEffectLow();
						model.getPlayer().setHealth(model.getPlayer().getHealth() + healthGained);
						model.Items.get(i).setContainerID(0);
						return "You just gained " + healthGained + " health!";
					}
					
					
					
					if(model.Items.get(i).isEquipped() && (model.Items.get(i).getCategory().equals("mainhand") || model.Items.get(i).getCategory().equals("offhand"))) {
						
						
					if(model.getPlayer().isInCombat()) {
						
						if(model.Items.get(i).getCombatDescription().equals("0")) {
							model.setInvalidObjectInteraction(nameOfItem);
							return "";
						}
						
						
						//use = model.Items.get(i).getCombatDescription();
						
						Random rand = new Random();
						damageDone = rand.nextInt(model.Items.get(i).getEffectHigh()) + model.Items.get(i).getEffectLow();
						
						for(int j = 0; j<model.NPCs.size(); j++) {
							if(model.NPCs.get(j).getRoomID() == model.getPlayer().getRoomID()) {
								
								if(model.NPCs.get(j).getHealth() <= 0) {
									return "<p>" + model.NPCs.get(j).getName() + " is already dead." + "</p>";
								}
								
								if(model.NPCs.get(j).getWeakness().equals(model.Items.get(i).getEffectType())) {
									damageDone = damageDone * 2;
								}
								
								
								model.NPCs.get(j).setHealth(model.NPCs.get(j).getHealth() - damageDone);
								use = use + "<p>" + model.Items.get(i).getCombatDescription() + "</p>";
								use = use + "<p>" + "You did " + damageDone + " damage to " + model.NPCs.get(j).getName() + "!" + "</p>";
								System.out.println(model.NPCs.get(j).getHealth());
								
								if(model.NPCs.get(j).getHealth() <=0) {
									model.getPlayer().setInCombat(false);
									return use = use + "<p>" + model.NPCs.get(j).getName() + " has died!";
								}
								
								Random rand1 = new Random();
								NPCDamageDone = rand1.nextInt(model.NPCs.get(j).getEffectHigh()) + model.NPCs.get(j).getEffectLow();
								
								
								for(int x = 0; x<model.Items.size(); x++) {
									if(model.Items.get(x).getContainerID() == -1 &&  model.Items.get(x).isEquipped() && model.Items.get(x).getType().equals("equipment")) {
										if(model.Items.get(x).getArmorType().equals(model.NPCs.get(j).getEffectType())) {
											NPCDamageDone = NPCDamageDone - model.Items.get(x).getDefenseNumber();
										}
									}
								}
								
								model.getPlayer().setHealth(model.getPlayer().getHealth() - NPCDamageDone);
								return use + "<p>" + model.NPCs.get(j).getName() + " did " + NPCDamageDone + " damage to you!" + "</p>";
								
								
								
								
								
								
								
								
								
							}
						}
					}
					
					
					
					if(model.getPlayer().isInCombat() == false) {
						return use = model.Items.get(i).getUseDescription();
					}
					
					
				}
				}
			}
			
			
			if(use.length() == 0) {
				model.setInvalidObjectInteraction(nameOfItem);
				return "";
			}
			
			
			return use;
		}
		
		
		@Override
		public String equip(GameModel model, String nameOfItem) {
			String equip = "";
			
			for(int i = 0; i<model.Items.size(); i++) {
				if(model.Items.get(i).getGameID() == model.getPlayer().getGameID() && model.Items.get(i).getName().equals(nameOfItem) && model.Items.get(i).getContainerID() == -1  && (model.Items.get(i).getType().equals("weapon") || model.Items.get(i).getType().equals("equipment") ||  model.Items.get(i).getType().equals("tool"))) {
					for(int j = 0; j<model.Items.size(); j++) {
						if(model.Items.get(i).getCategory().equals(model.Items.get(j).getCategory()) && model.Items.get(j).getContainerID() == -1) {
							model.Items.get(j).setEquipped(false);
						}
					}
					model.Items.get(i).setEquipped(true);
					equip  = "<p>" + "You have equipped " + model.Items.get(i).getName() +"!" + "</p>";				
					}
			}
			
			
			if(equip.length() == 0) {
				model.setInvalidObjectInteraction(nameOfItem);
				return "";
			}
			
			
			return equip;
		}
		
		@Override
		public String unequip(GameModel model, String nameOfItem) {
			String unequip = "";
			
			for(int i = 0; i<model.Items.size(); i++) {
				if(model.Items.get(i).getGameID() == model.getPlayer().getGameID() && model.Items.get(i).getName().equals(nameOfItem) && model.Items.get(i).getContainerID() == -1 && model.Items.get(i).isEquipped()) {
					model.Items.get(i).setEquipped(false);
					unequip  = "<p>" + "You have unequipped " + model.Items.get(i).getName() +"!" + "</p>";				
					}
			}
			
			
			if(unequip.length() == 0) {
				model.setInvalidObjectInteraction(nameOfItem);
				return "";
			}
			
			
			return unequip;
		}
		
		
		@Override
		public String stats(GameModel model) {
			
			ArrayList<Item> equippedEquipment = new ArrayList<Item>();
			
			String currentDamageOffHand = "0";
			String currentDamageTypeOffHand = "None";		
			String currentDamageMainHand = "0";
			String currentDamageTypeMainHand = "None";	
			int nuts = 0;
			int radiantResistance = 0;
			int physicalResistance = 0;
			int forceResistance = 0;
			int necroticResistance = 0;
			int poisionResistance = 0;
			int thunderResistance = 0;
			int fireResistance = 0;
			
			String stats = "<p>" + "YOUR STATS" + "</p><p>" + "--------------------------------" + "</p>";
			
			
			stats = stats + "<p>" + "Health: " + model.getPlayer().getHealth() + "</p>";
			
			for(Item item : model.Items) {
				if(item.isEquipped() && item.getContainerID() == -1 && item.getType().equals("weapon")) {
					
					if(item.getCategory().equals("offhand")) {
						currentDamageOffHand = item.getEffectLow() + "-" + item.getEffectHigh();
						currentDamageTypeOffHand = item.getEffectType();
					}
					if(item.getCategory().equals("mainhand")) {
						currentDamageMainHand = item.getEffectLow() + "-" + item.getEffectHigh();
						currentDamageTypeMainHand = item.getEffectType();
					}
					
					
				}
			}
			
			
			for(Item item : model.Items) {
				if(item.getGameID()==model.getPlayer().getGameID() && item.isEquipped() && item.getContainerID() == -1 && (item.getType().equals("equipment") || item.getCategory().equals("offhand"))) {
					equippedEquipment.add(item);
				}
			}
			
			for(int i = 0; i<equippedEquipment.size(); i++) {
				
				
			
			 if(equippedEquipment.get(i).getArmorType().equals("Radiant")) {
				radiantResistance = radiantResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			else if(equippedEquipment.get(i).getArmorType().equals("Physical")) {
				physicalResistance  = physicalResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			else if(equippedEquipment.get(i).getArmorType().equals("Force")) {
				forceResistance  = forceResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			else if(equippedEquipment.get(i).getArmorType().equals("Necrotic")) {
				necroticResistance  = necroticResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			else if(equippedEquipment.get(i).getArmorType().equals("Poision")) {
				poisionResistance  = poisionResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			else if(equippedEquipment.get(i).getArmorType().equals("Thunder")) {
				thunderResistance  = thunderResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			else if(equippedEquipment.get(i).getArmorType().equals("Fire")) {
				fireResistance  = fireResistance + equippedEquipment.get(i).getDefenseNumber();
			}
			 
			}
			
			stats =  stats + "<p>" + "Physical Resistance: " + physicalResistance + "</p>"
					+	"<p>" + "Radiant Resistance: " + radiantResistance + "</p>"
					+	"<p>" + "Force Resistance: " + forceResistance + "</p>"
					+	"<p>" + "Necrotic Resistance: " + necroticResistance + "</p>"
					+	"<p>" + "Poision Resistance: " + poisionResistance + "</p>"
					+	"<p>" + "Thunder Resistance: " + thunderResistance + "</p>"
					+	"<p>" + "Fire Resistance: " + fireResistance + "</p>"
					+	"<p>" + "Equipped Mainhand Damage Type: " + currentDamageTypeMainHand + "</p>"
					+	"<p>" + "Mainhand Damage: " + currentDamageMainHand + "</p>"
					+	"<p>" + "Equipped Offhand Damage Type: " + currentDamageTypeOffHand + "</p>"
					+	"<p>" + "Offhand Damage: " + currentDamageOffHand + "</p>";
			
			
			 return stats =  stats + "<p>" + "--------------------------------" + "</p>";
		}



		@Override
		public void restart(GameModel model) {
			
				System.out.print("game restarting");
				DatabaseProvider.setInstance(new DerbyDatabase());
				IDatabase db = DatabaseProvider.getInstance();
				db.restartGame(model.getPlayer().getGameID(), model.getPlayer().getUserID());
			
			
		}
		

	







		

	}
		
	
	