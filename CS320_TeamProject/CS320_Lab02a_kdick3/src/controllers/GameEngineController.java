package controllers;

import models.GameModel;

public class GameEngineController {
private String input;

private GameModel model;

public GameEngineController(GameModel model) {

this.model = model;
}

public void setAction(String input) {
model.setAction(input);
this.input = input;
}

public String getAction() {
return model.getAction(input);
}

}
		
	
	