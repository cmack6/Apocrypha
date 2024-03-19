package models;



public class Item{
	
	private String name;
	private int value;

public Item(String name, int value) {
	this.name = name;
	this.setValue(value);
	
	
}



public void setName(String name) {
	this.name = name;
}

public String getName() {
	return name;
}


public int getValue() {
	return value;
}



public void setValue(int value) {
	this.value = value;
}



}
