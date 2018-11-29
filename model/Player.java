package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	
	public final int MAX_HAND_SIZE = 5;
	private ArrayList<Card> hand = new ArrayList<>();
	private String name;
	
	public enum Score { HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH };
	
	public Player(String newName) {
		name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public Iterable<Card> getHand() {
		return hand;
	}
	
	public void removeCard(Card c) {
		hand.remove(c);
	}
	
	public int getHandSize() {
		return hand.size();
	}
	
	public void dealCard(Card c) {
		if(c == null)
			throw new IllegalArgumentException();
		hand.add(c);
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public Score getScore() {
		int values[] = new int[14];
		for(Card c : hand) {
			int value = c.getDenomination().ordinal();
			values[value] ++;
		}
		int[] sortedValues = Arrays.copyOf(values, values.length);
		Arrays.sort(sortedValues);
		// If there's an Ace, add it at the end (to calc high straights)
		if(values[0] == 1)
			values[13] = 1;
		
		if(isStraight(values) && isFlush() && hand.size() == 5)
			return Score.STRAIGHT_FLUSH;
		else if(sortedValues[13] == 4)
			return Score.FOUR_OF_A_KIND;
		else if(sortedValues[13] == 3 && sortedValues[12] == 2)
			return Score.FULL_HOUSE;
		else if(isFlush() && hand.size() == 5)
			return Score.FLUSH;
		else if(isStraight(values) && hand.size() == 5)
			return Score.STRAIGHT;
		else if(sortedValues[13] == 3)
			return Score.THREE_OF_A_KIND;
		else if(sortedValues[13] == 2 && sortedValues[12] == 2)
			return Score.TWO_PAIR;
		else if(sortedValues[13] == 2)
			return Score.PAIR;
		else
			return Score.HIGH_CARD;
	}
	
	private boolean isStraight(int[] values) {
		for(int i = 0; i < values.length; i ++) {
			if(values[i] == 1) {
				if(i == 0 && values[i] == 1 && values[i + 1] == 0)
					continue;
				for(int j = 1; j < hand.size(); j ++) {
					if(values[i + j] != 1) {
						return false;
					}
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
	
	public void sortByDenomination() {
	}
}
