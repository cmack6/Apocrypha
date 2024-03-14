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
	GameModel model = new GameModel(input);
	GameEngineController controller = new GameEngineController();
	
}

@Test
public void testGetAction() {
	String input = "south";
	controller.setAction(input);
	assertTrue(controller.getAction().equals("south"));

	
}

}
		
	
	