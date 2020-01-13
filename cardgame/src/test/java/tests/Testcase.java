package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import constant.Constants;
import engine.GameEngine;
import enums.PlayerName;
import model.Card;
import model.Deck;
import model.Hand;
import model.Player;
import service.DeckService;
import service.PlayerService;

public class Testcase {
	
	public Player player1; 
	public Player player2; 
	public GameEngine engine;
	
    @Before
    public void setup() {
		player1 = new Player.Builder(PlayerName.PLAYER1).initHealth(Constants.INITIAL_HEALTH)
				.initMana(Constants.INITIAL_MANA).initManaSlot(Constants.INITIAL_MANA_SLOT)
				.emptyHand(new Hand(new ArrayList<Card>()))
				.startingDeck(DeckService.generateDeck(Constants.INITIAL_DECK)).build();
		player2 = new Player.Builder(PlayerName.PLAYER2).initHealth(Constants.INITIAL_HEALTH)
				.initMana(Constants.INITIAL_MANA).initManaSlot(Constants.INITIAL_MANA_SLOT)
				.emptyHand(new Hand(new ArrayList<Card>()))
				.startingDeck(DeckService.generateDeck(Constants.INITIAL_DECK)).build();
		engine = GameEngine.getInstance(player1, player2);
    }
    
	@Test
	public void testGenerateDeck() {
		int input1[] = { 1, 1, 1};
		Deck actual = DeckService.generateDeck(input1);
		Deck expected = new Deck();
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(0,0));
		cards.add(new Card(1,1));
		cards.add(new Card(2,2));
		expected.setCards(cards);
		assertEquals(expected, actual);
	}

	@Test
	public void testGenerateDeck2() {
		int input1[] = { 2, 2, 3, 4, 3, 2, 2, 1, 1 };
		Deck actual = DeckService.generateDeck(input1);
		Deck expected = new Deck();
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(0,0));
		cards.add(new Card(0,0));
		cards.add(new Card(1,1));
		cards.add(new Card(1,1));
		cards.add(new Card(2,2));
		cards.add(new Card(2,2));
		cards.add(new Card(2,2));
		cards.add(new Card(3,3));
		cards.add(new Card(3,3));
		cards.add(new Card(3,3));
		cards.add(new Card(3,3));
		cards.add(new Card(4,4));
		cards.add(new Card(4,4));
		cards.add(new Card(4,4));
		cards.add(new Card(5,5));
		cards.add(new Card(5,5));
		cards.add(new Card(6,6));
		cards.add(new Card(6,6));
		cards.add(new Card(7,7));
		cards.add(new Card(8,8));
		expected.setCards(cards);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeckSize() {
		int expected = Arrays.stream(Constants.INITIAL_DECK).sum();
		int actual = 20;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testInitPlayersHandsAndDeck() {
		DeckService.shuffleDeck(player1.getDeck());
		PlayerService.drawInitalCards(player1);
		int actual = Constants.INITIAL_HAND_SIZE;
		int expected = player1.getHand().getCards().size();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testInitPlayersHandsAndDeck2() {
		DeckService.shuffleDeck(player1.getDeck());
		PlayerService.drawInitalCards(player1);
		int expected = Arrays.stream(Constants.INITIAL_DECK).sum() - Constants.INITIAL_HAND_SIZE;
		int actual = player1.getDeck().getCards().size();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testIsAlive() {
		boolean actual = PlayerService.isAlive(player1);
		boolean expected = true;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testIsAlive2() {
		PlayerService.takeDamage(player1, player1.getHealth());
		boolean actual = PlayerService.isAlive(player1);
		boolean expected = false;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testIsDefeated() {
		boolean actual = PlayerService.isDefeated(player1);
		boolean expected = false;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testIsDefeated2() {
		PlayerService.takeDamage(player1, player1.getHealth());
		boolean actual = PlayerService.isDefeated(player1);
		boolean expected = true;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testInitialPlayerMana() {
		int actual = player1.getMana();
		int expected = Constants.INITIAL_MANA;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testInitialPlayerSlot() {
		int actual = player1.getManaSlot();
		int expected = Constants.INITIAL_MANA_SLOT;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testInitialPlayerHealth() {
		int actual = player1.getHealth();
		int expected = Constants.INITIAL_HEALTH;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testAddManaSlot() {
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		int actual = player1.getManaSlot();
		int expected = 5;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testAddManaSlotUpperBound() {
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		int actual = player1.getManaSlot();
		int expected = Constants.MAXIMUM_MANA_SLOT;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testManaRefill() {
		PlayerService.refillManaSlots(player1);
		int actual = player1.getMana();
		int expected = 0;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testManaRefillWithManaSlot() {
		PlayerService.refillManaSlots(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.addManaSlot(player1);
		int actual = player1.getMana();
		int expected = 2;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testDrawOneCard() {
		PlayerService.drawInitalCards(player1);
		PlayerService.drawOneCard(player1);
		int actual = player1.getHand().getCards().size();
		int expected = 4;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testOverLoad() {
		PlayerService.drawInitalCards(player1);
		PlayerService.drawOneCard(player1);
		PlayerService.drawOneCard(player1);
		PlayerService.drawOneCard(player1);
		int actual = player1.getHand().getCards().size();
		int expected = 5;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testBleeding() {
		PlayerService.drawInitalCards(player1);
		PlayerService.drawInitalCards(player1);
		PlayerService.drawInitalCards(player1);
		PlayerService.drawInitalCards(player1);
		PlayerService.drawInitalCards(player1);
		PlayerService.drawInitalCards(player1);
		PlayerService.drawOneCard(player1);
		PlayerService.drawOneCard(player1);
		PlayerService.drawOneCard(player1);
		int actual = player1.getHealth();
		int expected = Constants.INITIAL_HEALTH-1;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPlayCardManaCost() {
		PlayerService.drawInitalCards(player1);
		Card cardToBePlayed = player1.getHand().getCards().get(0);
		int manaCost = cardToBePlayed.getManacost();
		for (int i = 0; i < manaCost; i++) {
			PlayerService.addManaSlot(player1);	
		}
		PlayerService.refillManaSlots(player1);
		PlayerService.playCard(player1, player2, cardToBePlayed);
		int actual = player1.getMana();
		int expected = 0;
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPlayCardDamageDealt() {
		PlayerService.drawInitalCards(player1);
		Card cardToBePlayed = player1.getHand().getCards().get(0);
		int manaCost = cardToBePlayed.getManacost();
		for (int i = 0; i < manaCost; i++) {
			PlayerService.addManaSlot(player1);	
		}
		PlayerService.refillManaSlots(player1);
		PlayerService.playCard(player1, player2, cardToBePlayed);
		int actual = Constants.INITIAL_HEALTH - player2.getHealth();
		int expected = cardToBePlayed.getDamage();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPlayCardsNoCardPlayedHandSize() {
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(7,7));
		hand.add(new Card(8,8));
		hand.add(new Card(6,6));
		player1.setHand(new Hand(hand));
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.playCards(player1, player2);
		int actual = player1.getHand().getCards().size();
		int expected = 3;
		assertEquals(actual, expected);	
	}
	
	@Test
	public void testPlayCardsNoCardPlayedEnemyHealth() {
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(7,7));
		hand.add(new Card(8,8));
		hand.add(new Card(6,6));
		player1.setHand(new Hand(hand));
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.playCards(player1, player2);
		int actual = player2.getHealth();
		int expected = Constants.INITIAL_HEALTH;
		assertEquals(actual, expected);	
	}
	
	@Test
	public void testPlayCardsNoCardPlayedPlayerMana() {
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(7,7));
		hand.add(new Card(8,8));
		hand.add(new Card(6,6));
		player1.setHand(new Hand(hand));
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.playCards(player1, player2);
		int actual = player1.getMana();
		int expected = 1;
		assertEquals(actual, expected);	
	}
	
	@Test
	public void testPlayCardsTwoPlayableCardHandSize() {
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(3,3));
		hand.add(new Card(4,4));
		hand.add(new Card(8,8));
		player1.setHand(new Hand(hand));
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.playCards(player1, player2);
		int actual = player1.getHand().getCards().size();
		int expected = 1;
		assertEquals(actual, expected);	
	}
	
	@Test
	public void testPlayCardsTwoPlayableCardMana() {
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(3,3));
		hand.add(new Card(4,4));
		hand.add(new Card(8,8));
		player1.setHand(new Hand(hand));
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.playCards(player1, player2);
		int actual = player1.getMana();
		int expected = 0;
		assertEquals(actual, expected);	
	}
	
	@Test
	public void testPlayCardsTwoPlayableCardEnemyHealth() {
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(3,3));
		hand.add(new Card(4,4));
		hand.add(new Card(8,8));
		player1.setHand(new Hand(hand));
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.addManaSlot(player1);
		PlayerService.refillManaSlots(player1);
		PlayerService.playCards(player1, player2);
		int actual = player2.getHealth();
		int expected = Constants.INITIAL_HEALTH-7;
		assertEquals(actual, expected);	
	}
	
	@Test
	public void testPlayerMatchPlayer1Wins() {
		int input1[] = { 2, 2, 3, 4, 2 };
		Deck player2Deck = DeckService.generateDeck(input1);
		player2.setDeck(player2Deck);
		engine.initPlayersHandAndDeck();
		engine.gameStart();
		String actual = engine.getWinner();
		String expected = PlayerName.PLAYER1.toString();
		assertEquals(actual, expected);	
	}
	
}
