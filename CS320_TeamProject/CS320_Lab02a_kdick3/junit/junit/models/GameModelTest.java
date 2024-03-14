package junit.models;

import static org.junit.Assert.assertTrue;
import models.GameModel;
import controllers.GameEngineController;

public class GameModelTest {
private String input;

public GameModelTest(String input) {
this.input = input;
}

public void setAction(String input) {
this.input = input;
}

public String getAction() {
return input;
}
}
		
	
	