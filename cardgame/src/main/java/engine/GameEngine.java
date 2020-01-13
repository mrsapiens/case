package engine;

import java.util.ArrayList;

import constant.Constants;
import enums.PlayerName;
import model.Card;
import model.Hand;
import model.Player;
import service.DeckService;
import service.PlayerService;

public class GameEngine {
	private static GameEngine instance;
	private Player player1;
	private Player player2;
	private String winner;

	private GameEngine(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public static GameEngine getInstance(Player player1, Player player2) {
		if (instance == null) {
			instance = new GameEngine(player1, player2);
		}
		return instance;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}


	public void resetGame() {
		this.player1 = new Player.Builder(PlayerName.PLAYER1).initHealth(Constants.INITIAL_HEALTH)
				.initMana(Constants.INITIAL_MANA).initManaSlot(Constants.INITIAL_MANA_SLOT)
				.emptyHand(new Hand(new ArrayList<Card>()))
				.startingDeck(DeckService.generateDeck(Constants.INITIAL_DECK)).build();

		this.player2 = new Player.Builder(PlayerName.PLAYER2).initHealth(Constants.INITIAL_HEALTH)
				.initMana(Constants.INITIAL_MANA).initManaSlot(Constants.INITIAL_MANA_SLOT)
				.emptyHand(new Hand(new ArrayList<Card>()))
				.startingDeck(DeckService.generateDeck(Constants.INITIAL_DECK)).build();
		this.winner = "";
	}

	public void gameStart() {
		boolean player1Turn = true, player2Turn = true; 
		while (true) {
			while (PlayerService.isAlive(player1) && player1Turn) {
				System.out.println(player1.getPlayerName() + "'s Turn");
				PlayerService.addManaSlot(player1);
				PlayerService.refillManaSlots(player1);
				PlayerService.drawOneCard(player1);
				System.out.println(player1.getPlayerName() + ": " + player1.toString());
				PlayerService.playCards(player1, player2);
				System.out.println(player1.getPlayerName() +" End Turn");
				player1Turn = false;
				player2Turn = true;
			}
		
			if(PlayerService.isDefeated(player1)) {
				setWinner(player2.getPlayerName().toString());
				break;
			}
			
			while (PlayerService.isAlive(player2) && player2Turn) {
				System.out.println(player2.getPlayerName() + "'s Turn");
				PlayerService.addManaSlot(player2);
				PlayerService.refillManaSlots(player2);
				PlayerService.drawOneCard(player2);
				System.out.println(player2.getPlayerName() + ": " + player2.toString());
				PlayerService.playCards(player2, player1);
				System.out.println(player2.getPlayerName() + " End Turn");
				player1Turn = true;
				player2Turn = false;
			}
			
			if(PlayerService.isDefeated(player2)) {
				setWinner(player1.getPlayerName().toString());
				break;
			}
		}
		System.out.println("Winner is " + this.winner);
	}

	public void initPlayersHandAndDeck() {
		this.shuffleDecks();
		this.drawInitialCards();
	}

	private void shuffleDecks() {
		DeckService.shuffleDeck(this.player1.getDeck());
		DeckService.shuffleDeck(this.player2.getDeck());
	}

	private void drawInitialCards() {
		PlayerService.drawInitalCards(this.player1);
		PlayerService.drawInitalCards(this.player2);
	}

}
