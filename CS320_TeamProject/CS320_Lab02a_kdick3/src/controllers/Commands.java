package controllers;

import models.GameModel;
import models.User;

public interface Commands {
	
	public void move(GameModel model, String direction);
	
	public void jump();
	
	public void crawl();
	
	public void fight();
	
	public void pickUp(GameModel model, String nameOfItem);
	
	public String inventory(GameModel model);
	
	public String roomInventory(GameModel model);
	
	public String talk(GameModel model);
	
	public String itemDescription(GameModel model, String input);
	

}
