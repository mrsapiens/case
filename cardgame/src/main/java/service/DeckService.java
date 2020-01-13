package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Card;
import model.Deck;

public class DeckService {

	public static Deck generateDeck(int[] deskDescriptor) {
		Deck initialDeck = new Deck();
		List<Card> emptyDeck = new ArrayList<Card>();
		//Nested intStream.range() can be combined for this operation
		//However it would has poor performance
		for (int i = 0; i < deskDescriptor.length; i++) {
			for (int j = 0; j < deskDescriptor[i]; j++) {
				emptyDeck.add(new Card(i,i)); 
			}
		}
		initialDeck.setCards(emptyDeck);
		return initialDeck;
	}
	
	public static void shuffleDeck(Deck deck){
		Collections.shuffle(deck.getCards());
	}
	
}

