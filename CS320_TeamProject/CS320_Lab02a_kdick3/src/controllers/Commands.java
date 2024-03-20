package controllers;

import models.GameModel;
import models.User;

public interface Commands {
	
	public void move(GameModel model, String direction);
	
	public void jump();
	
	public void crawl();
	
	public void fight();
	
	public void pickUp();
	
	public String inventory(GameModel model);
	
	public void roomInventory(GameModel model);
	

}
