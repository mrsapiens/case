package model;

import enums.PlayerName;

public class Player {

	private PlayerName playerName;
	private int health;
	private int mana;
	private int manaSlot;
	private Hand hand;
	private Deck deck;

	public static class Builder {

		private PlayerName playerName;
		private int health;
		private int mana;
		private int manaSlot;
		private Hand hand;
		private Deck deck;

		public Builder(PlayerName playerName) {
			this.playerName = playerName;
		}

		public Builder initHealth(int health) {
			this.health = health;
			return this;
		}

		public Builder initMana(int mana) {
			this.mana = mana;
			return this;
		}

		public Builder initManaSlot(int manaSlot) {
			this.manaSlot = manaSlot;
			return this;
		}

		public Builder emptyHand(Hand emptyHand) {
			this.hand = emptyHand;
			return this;
		}

		public Builder startingDeck(Deck startingDeck) {
			this.deck = startingDeck;
			return this;
		}

		public Player build() {
			Player player = new Player();
			player.playerName = this.playerName;
			player.health = this.health;
			player.mana = this.mana;
			player.manaSlot = this.manaSlot;
			player.hand = this.hand;
			player.deck = this.deck;
			return player;
		}

	}

	public PlayerName getPlayerName() {
		return playerName;
	}

	public void setPlayerName(PlayerName playerName) {
		this.playerName = playerName;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getManaSlot() {
		return manaSlot;
	}

	public void setManaSlot(int manaSlot) {
		this.manaSlot = manaSlot;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", health=" + health + ", mana=" + mana + ", manaSlot=" + manaSlot + "]";
	}
	
	
}
