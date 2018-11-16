import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<>();
	
	public enum Score { HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH };
	
	public Iterable<Card> showHand() {
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
		
		for(int i = 0; i < hand.size(); i ++) {
			Card.Suit firstCardSuit = hand.get(0).getSuit();
			if(hand.get(i).getSuit() != firstCardSuit)
				break;
			else if(i == 4)
				return Score.FLUSH;
		}
		if(isStraight(values))
			return Score.STRAIGHT;
		else if(sortedValues[12] == 3)
			return Score.THREE_OF_A_KIND;
		else if(sortedValues[12] == 2 && sortedValues[11] == 2)
			return Score.TWO_PAIR;
		else if(sortedValues[12] == 2)
			return Score.PAIR;
		else if(sortedValues[12] == 1)
			return Score.HIGH_CARD;
		else
			return null;
	}
	
	private boolean isStraight(int[] arr) {
		for(int i = 0; i < arr.length; i ++) {
			if(arr[i] == 1) {
				for(int j = 1; j < 5; j ++) {
					if(arr[i + j] != 1)
						return false;
				}
				return true;
			}
		}
		return false;
	}
}
