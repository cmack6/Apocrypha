package edu.ycp.cs320.lab02.controller;


import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.lab02.model.GameModel;


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
	
	public String help(GameModel model);
	
	public String score(GameModel model);

}