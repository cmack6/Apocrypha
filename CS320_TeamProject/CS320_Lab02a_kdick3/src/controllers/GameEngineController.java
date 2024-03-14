package controllers;

import models.GameModel;


public class GameEngineController {
private String input;

private GameModel model;

public GameEngineController(GameModel model) {
this.model = model;
}

public GameEngineController() {
}

public void setAction(String input) {
model.setAction(input);
this.setInput(input);
}

public String getAction() {
return 
}

public String getInput() {
	return input;
}

public void setInput(String input) {
	this.input = input;
}

}
		
	
	