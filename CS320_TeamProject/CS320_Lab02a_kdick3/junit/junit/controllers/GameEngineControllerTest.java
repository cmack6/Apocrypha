package junit.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.GameModel;
import controllers.GameEngineController;


public class GameEngineControllerTest {

private GameModel model;
private GameEngineController controller;



@Before
public void setUp() {
	String input = "south";
	model = new GameModel(input);
	controller = new GameEngineController(model);
	
}

@Test
public void testProcessInput() {
	String input = "south";
	model.setAction(input);
	assertTrue(controller.processInput().equals("south"));

	
}

}
		
	
	