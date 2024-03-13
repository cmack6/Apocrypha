
public class Weapon extends Item{
	
	
	private String name;
	private int health;
	private int dmg;
	
public class Weapon() {
	this.name = name;	
	this.health = health;
	this.dmg = dmg;

	
}

	public void setHealth(int health) {
		this.health = health;
	}	

	public int getHealth() {
		return health;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public int getDmg() {
		return dmg;
	}
	
	public void use() {
		
	}


}
