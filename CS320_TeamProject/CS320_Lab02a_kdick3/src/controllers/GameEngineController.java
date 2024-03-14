package controllers;

import models.GameModel;


public class GameEngineController {


private GameModel model;

public GameEngineController(GameModel model) {
this.model = model;
}

public GameEngineController() {
}

public String processInput() {
	String input = model.getAction();
	return input;
}

}
		
	
	