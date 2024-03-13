package edu.ycp.cs320.lab02.model;

public class Numbers {
	
	private Double first, second, third, result;
	private String stringFirst, stringSecond, stringThird;
	
	
	public Numbers(String stringFirst, String stringSecond, String stringThird) {
		this.stringFirst = stringFirst;
		this.stringSecond = stringSecond;
		this.stringThird = stringThird;
		
	}
	
	//methods to convert each String from the jsp to a Double if there is not a null in the Strings
	
	public void setFirstStringToDouble(String stringFirst) {
		this.first = Double.parseDouble(stringFirst);
		
	}
	
	public void setSecondStringToDouble(String stringSecond) {
		this.second = Double.parseDouble(stringSecond);
		
	}
	
	public void setThirdStringToDouble(String stringThird) {
		this.third = Double.parseDouble(stringThird);
		
	}
	
	//get and set methods for all of the String data types
	
	public String getStringFirst() {
		return stringFirst;
	}
	
	public String getStringSecond() {
		return stringSecond;
	}
	
	public String getStringThird() {
		return stringThird;
	}
	
	public void setStringFirst(String stringFirst) {
		this.stringFirst = stringFirst;
	}
	
	public void setStringSecond(String stringSecond) {
		this.stringSecond = stringSecond;
	}
	
	public void setStringThird(String stringThird) {
		this.stringThird = stringThird;
	}
	
	
	//get and set methods for all of the Double data types

	public void setFirst(Double first) {
		this.first = first;
	}
	
	public void setSecond(Double second) {
		this.second = second;
	}
	
	public void setThird(Double third) {
		this.third = third;
	}
	
	public Double getFirst() {
		return first;
	}
	
	public Double getSecond() {
		return second;
	}
	
	public Double getThird() {
		return third;
	}
	
	//get and set methods for the result
	
	public Double getResult() {
		return result;
	}
	
	public void setResult(Double result) {
		this.result = result;
	}
	
	//I took this method from the servlet and it was used as a conversion from String to Double or null
	
	public String checkString(String string) {
			if (string == null || string.equals("")) {
				return null;
			} else {
				return string;
			}	
		}
	
}
