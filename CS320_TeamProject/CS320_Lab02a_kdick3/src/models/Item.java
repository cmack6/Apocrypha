package models;



public class Item{
	
	private String name;
	private int location;
	private int value;
	
	

public Item(String name, int location, int value) {
	
	this.name = name;
	this.location = location;
	this.value = value;
	
	
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

public int getLocation() {
	return location;
}



public void setLocation(int location) {
	this.location = location;
}


}
