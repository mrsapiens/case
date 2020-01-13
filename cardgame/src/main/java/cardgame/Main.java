package cardgame;

import java.util.ArrayList;

import constant.Constants;
import engine.GameEngine;
import enums.PlayerName;
import model.Card;
import model.Hand;
import model.Player;
import service.DeckService;

public class Main {
	
	public static void main(String[] args) {
		Player player1 = new Player.Builder(PlayerName.PLAYER1).initHealth(Constants.INITIAL_HEALTH)
				.initMana(Constants.INITIAL_MANA).initManaSlot(Constants.INITIAL_MANA_SLOT)
				.emptyHand(new Hand(new ArrayList<Card>()))
				.startingDeck(DeckService.generateDeck(Constants.INITIAL_DECK)).build();

		Player player2 = new Player.Builder(PlayerName.PLAYER2).initHealth(Constants.INITIAL_HEALTH)
				.initMana(Constants.INITIAL_MANA).initManaSlot(Constants.INITIAL_MANA_SLOT)
				.emptyHand(new Hand(new ArrayList<Card>()))
				.startingDeck(DeckService.generateDeck(Constants.INITIAL_DECK)).build();

		GameEngine engine = GameEngine.getInstance(player1, player2);
		engine.initPlayersHandAndDeck();
		engine.gameStart();
		engine.resetGame();
	}

}
