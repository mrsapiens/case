package constant;

import java.util.ArrayList;
import java.util.List;

import model.Card;

public class Constants {
	public static final int INITIAL_HEALTH = 30;
	public static final int INITIAL_MANA = 0;

	public static final int INITIAL_MANA_SLOT = 0;
	public static final int MAXIMUM_MANA_SLOT = 10;

	public static final int[] INITIAL_DECK = { 2, 2, 3, 4, 3, 2, 2, 1, 1 }; // Index defines mana cost and value defines
																			// count of the cards with that mana cost

	public static final int INITIAL_HAND_SIZE = 3;
	public static final int MAXIMUM_HAND_SIZE = 5;
}