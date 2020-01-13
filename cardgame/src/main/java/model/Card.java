package model;

public class Card {

	private int manacost;
	private int damage;
	
	//TO-DO
	// damage same as manacost
	public Card(int manacost, int damage) {
		this.manacost = manacost;
		this.damage = damage;
	}
	
	public int getManacost() {
		return manacost;
	}
	
	public void setManacost(int manacost) {
		this.manacost = manacost;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + damage;
		result = prime * result + manacost;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (damage != other.damage)
			return false;
		if (manacost != other.manacost)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [manacost=" + manacost + ", damage=" + damage + "]";
	}
	
	
	
}
