package controllers;

import models.User;

public interface Commands {
	
	public void move(User user, String direction);
	
	public void jump();
	
	public void crawl();
	
	public void fight();
	
	public void pickUp();
	

}
