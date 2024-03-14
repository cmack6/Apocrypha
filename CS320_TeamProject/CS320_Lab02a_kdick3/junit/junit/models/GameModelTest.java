package junit.models;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.GameModel;
import controllers.GameEngineController;


public class GameModelTest {

private GameModel model;
private GameEngineController controller;



@Before
public void setUp() {
	String input = "south";
	model = new GameModel(input);
	controller = new GameEngineController(model);
	
}

@Test
public void testSetAction() {
	String input = "north";
	model.setAction(input);
	assertTrue(model.getAction().equals("north"));

	
}



}