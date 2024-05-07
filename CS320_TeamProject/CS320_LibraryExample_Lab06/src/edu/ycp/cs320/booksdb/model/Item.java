package edu.ycp.cs320.booksdb.model;

public class Item{



private int itemID;
private String name;
private String type;
private int containerID;
private int value;
private String itemDescription;
private String useDescription;
private String combatDescription;
private String missDescription;
private boolean isEquipped;
private String category;
private String armorType;
private int defenseNumber;
private String effectType;
private int effectLow;
private int effectHigh;
private int gameID;







public Item() {

}






public int getItemID() {
	return itemID;
}






public void setItemID(int itemID) {
	this.itemID = itemID;
}






public String getName() {
	return name;
}






public void setName(String name) {
	this.name = name;
}






public String getType() {
	return type;
}






public void setType(String type) {
	this.type = type;
}






public int getContainerID() {
	return containerID;
}






public void setContainerID(int containerID) {
	this.containerID = containerID;
}






public int getValue() {
	return value;
}






public void setValue(int value) {
	this.value = value;
}






public String getItemDescription() {
	return itemDescription;
}






public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
}






public String getUseDescription() {
	return useDescription;
}






public void setUseDescription(String useDescription) {
	this.useDescription = useDescription;
}






public boolean isEquipped() {
	return isEquipped;
}






public void setEquipped(boolean isEquipped) {
	this.isEquipped = isEquipped;
}






public String getCategory() {
	return category;
}






public void setCategory(String category) {
	this.category = category;
}






public String getArmorType() {
	return armorType;
}






public void setArmorType(String armorType) {
	this.armorType = armorType;
}






public int getDefenseNumber() {
	return defenseNumber;
}






public void setDefenseNumber(int defenseNumber) {
	this.defenseNumber = defenseNumber;
}






public String getEffectType() {
	return effectType;
}






public void setEffectType(String effectType) {
	this.effectType = effectType;
}






public int getEffectLow() {
	return effectLow;
}






public void setEffectLow(int effectLow) {
	this.effectLow = effectLow;
}






public int getEffectHigh() {
	return effectHigh;
}






public void setEffectHigh(int effectHigh) {
	this.effectHigh = effectHigh;
}






public int getGameID() {
	return gameID;
}






public void setGameID(int gameID) {
	this.gameID = gameID;
}






public String getCombatDescription() {
	return combatDescription;
}






public void setCombatDescription(String combatDescription) {
	this.combatDescription = combatDescription;
}






public String getMissDescription() {
	return  missDescription;
}

public void setMissDescription(String missDescription) {
	this.missDescription = missDescription;
}




}