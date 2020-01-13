package service;

import java.util.Iterator;
import java.util.List;

import constant.Constants;
import model.Card;
import model.Player;
import utils.ListUtils;

public class PlayerService {
	

	public static ListUtils<Card> listUtils = new ListUtils<Card>();
	
	public static void drawInitalCards(Player player) {
		List<Card> playerCardsDeck = player.getDeck().getCards();
		List<Card> playerCardsHand = player.getHand().getCards();
		listUtils.moveElements(playerCardsDeck, playerCardsHand, Constants.INITIAL_HAND_SIZE);
	}
	
	public static void drawOneCard(Player player) {
		List<Card> playerCardsDeck = player.getDeck().getCards();
		List<Card> playerCardsHand = player.getHand().getCards();
		if (playerCardsDeck.size() < 1) {
			takeDamage(player, 1); //Bleeding
			return;
		}
		if (playerCardsHand.size() > 4) {
			player.getDeck().getCards().remove(0); //Overload
			return;
		}
		listUtils.moveElements(playerCardsDeck, playerCardsHand, 1); //Draw Card
	}
	
	public static boolean isAlive(Player player) {
		return player.getHealth() > 0;
	}
	
	public static boolean isDefeated(Player player) {
		return player.getHealth() < 1;
	}
	
	public static void refillManaSlots(Player player) {
		player.setMana(player.getManaSlot());
	}
	
	public static void addManaSlot(Player player) {
		if(player.getManaSlot()<Constants.MAXIMUM_MANA_SLOT) {
			player.setManaSlot(player.getManaSlot()+1);
		}
	}
	
	public static void playCards(Player player, Player enemy) {
		boolean endTurn = false;
		while (player.getHand().getCards().size()>0 && !endTurn) {
			Iterator<Card> iter = player.getHand().getCards().iterator();
			while(iter.hasNext()) {
			    Card card = iter.next();
			    if (card.getManacost() <= player.getMana()) {
					playCard(player, enemy, card);
					iter.remove();
			    }
			}
			endTurn = true;
		}
	}
	
	public static void takeDamage(Player player, int damage) {
		player.setHealth(player.getHealth() - damage);
	}
	
	public static void playCard(Player player, Player enemy, Card card) {
		System.out.println(player.getPlayerName() + " played card with " + card.getManacost() + " manacost");
		player.setMana(player.getMana()-card.getManacost());
		takeDamage(enemy, card.getDamage());
		System.out.println(enemy.getPlayerName() + " current health: " + enemy.getHealth());
	}
	
	
}
