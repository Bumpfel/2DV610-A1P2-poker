package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	
	public final int MAX_HAND_SIZE = 5;
	private ArrayList<Card> hand = new ArrayList<>();
	private String name;
	
	public enum Score { HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH };
	
	public Player() {
	}
	
	public Player(String newName) {
		name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public Iterable<Card> getHand() {
		return hand;
	}
	
	public int getSize() {
		return hand.size();
	}
	
	public void dealCard(Card c) {
		if(c != null)
			hand.add(c);
		//Don't want to be forced to handle exceptions every time I call dealCard(). A muted error is fine
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public Score getScore() {
		int values[] = new int[13];
		for(Card c : hand) {
			int value = c.getDenomination().ordinal();
			values[value] ++;
		}
		int[] sortedValues = Arrays.copyOf(values, values.length);
		Arrays.sort(sortedValues);
		
		if(hand.size() != MAX_HAND_SIZE)
			return null;
		else if(isStraight(values) && isFlush())
			return Score.STRAIGHT_FLUSH;
		else if(sortedValues[12] == 4)
			return Score.FOUR_OF_A_KIND;
		else if(sortedValues[12] == 3 && sortedValues[11] == 2)
			return Score.FULL_HOUSE;
		else if(isFlush())
			return Score.FLUSH;
		else if(isStraight(values))
			return Score.STRAIGHT;
		else if(sortedValues[12] == 3)
			return Score.THREE_OF_A_KIND;
		else if(sortedValues[12] == 2 && sortedValues[11] == 2)
			return Score.TWO_PAIR;
		else if(sortedValues[12] == 2)
			return Score.PAIR;
		else
			return Score.HIGH_CARD;
	}
	
	private boolean isStraight(int[] values) {
		for(int i = 0; i < values.length; i ++) {
			if(values[i] == 1) {
				for(int j = 1; j < hand.size(); j ++) {
					if(values[i + j] != 1)
						return false;
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean isFlush() {
		Card.Suit firstCardSuit = hand.get(0).getSuit();
		for(int i = 0; i < hand.size(); i ++) {
			if(hand.get(i).getSuit() != firstCardSuit)
				return false;
		}
		return true;
	}
}
