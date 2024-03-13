package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.lab02.model.Numbers;

public class NumbersController {
	private Numbers model;
	
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	public Double add(Double first, Double second, Double third) {
		Double result = model.getFirst() + model.getSecond() + model.getThird();
		return result;
	}
	
	public Double multiply(Double first, Double second, Double third) {
		Double result = model.getFirst() * model.getSecond() * model.getThird();
		return result;
	}
	
	public void setNumbersDouble(String stringFirst, String stringSecond, String stringThird) {
		model.setFirstStringToDouble(stringFirst);
		model.setSecondStringToDouble(stringSecond);
		model.setThirdStringToDouble(stringThird);
	}

	public void setResult(Double result) {
		model.setResult(result);		
	}
}

