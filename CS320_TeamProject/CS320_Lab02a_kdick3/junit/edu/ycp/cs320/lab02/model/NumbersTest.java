package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;


import edu.ycp.cs320.lab02.controller.NumbersController;

import org.junit.Before;
import org.junit.Test;



public class NumbersTest {
	private Numbers model;
	private NumbersController controller;
	
	@Before
	public void setUp() {
		model = new Numbers(null, null, null);
		controller = new NumbersController();
		controller.setModel(model);
	}
	
	@Test
	public void testSetFirst() {
		model.setFirst(3.0);
		assertTrue(model.getFirst().equals(3.0));
	}
	
	@Test
	public void testSetSecond() {
		model.setSecond(2.0);
		assertTrue(model.getSecond().equals(2.0));
	}
	
	@Test
	public void testSetThird() {
		model.setThird(4.0);
		assertTrue(model.getThird().equals(4.0));
	}
	
	@Test
	public void setResult() {
		model.setResult(24.0);
		assertTrue(model.getResult().equals(24.0));
	}
	
	@Test
	public void testSetStringFirst() {
		model.setStringFirst("100");
		assertTrue(model.getStringFirst().equals("100"));
	}
	
	@Test
	public void testSetStringSecond() {
		model.setStringSecond("world");
		assertTrue(model.getStringSecond().equals("world"));
	}
	
	@Test
	public void testSetStringThird() {
		model.setStringThird("4.0");
		assertTrue(model.getStringThird().equals("4.0"));
	}
	
	
	@Test
	public void testSetFirstStringToDouble() {
		model.setStringFirst("34");
		model.setFirstStringToDouble(model.getStringFirst());
		assertTrue(model.getFirst().equals(34.0));
	}
	
	@Test
	public void testSetSecondStringToDouble() {
		model.setStringSecond("4");
		model.setSecondStringToDouble(model.getStringSecond());
		assertTrue(model.getSecond().equals(4.0));
	}
	
	@Test
	public void testSetThirdStringToDouble() {
		model.setStringThird("67");
		model.setThirdStringToDouble(model.getStringThird());
		assertTrue(model.getThird().equals(67.0));
	}
	
	@Test
	public void testCheckString() {
		model.setStringFirst("66.6");
		assertTrue(model.checkString(model.getStringFirst()).equals("66.6"));
	}
	
	
}
