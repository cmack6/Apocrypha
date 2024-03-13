package edu.ycp.cs320.lab02.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.model.Numbers;

public class NumbersControllerTest {
	private Numbers model;
	private NumbersController controller;
	
	@Before
	public void setUp() {
		model = new Numbers(null, null, null);
		controller = new NumbersController();
		controller.setModel(model);
		model.setFirst(3.5);
		model.setSecond(4.0);
		model.setThird(3.5);
	}
	
	@Test
	public void testAdd() {
		model.setResult(controller.add(model.getFirst(), model.getSecond(), model.getThird()));
		//uses a boolean condition technically to compare result stored in the model and 11, basically comparing Strings whereas Double result in the model, 
		//"The Double class wraps a value of the primitive type double in an object. An object of type Double contains a single field whose type is double. 
		//In addition, this class provides several methods for converting a double to a String and a String to a double, as well as other constants and 
		//methods useful when dealing with a double."
		assertTrue(model.getResult().equals(11.0));
	}
	
	

	@Test
	public void testMultiply() {
		model.setResult(controller.multiply(model.getFirst(), model.getSecond(), model.getThird()));
		//uses a boolean condition technically to compare result stored in the model and 49, and same thing as above, 
		//the Double result can be converted to a String since it is technically an object
		assertTrue(model.getResult().equals(49.0));
	}
}
