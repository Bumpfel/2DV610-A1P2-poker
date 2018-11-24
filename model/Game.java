package model;

public class Game {
	
	public final int CARDS_TO_DEAL = 5;
	private Player player;
	private Deck deck;
	
	public Game(Player newPlayer, Deck newDeck) {
		player = newPlayer;
		deck = newDeck;
	}
	
	public void newGame() {
		deck.reset();
		deck.shuffle();
		player.clearHand();
		for(int i = 0; i < CARDS_TO_DEAL; i ++) {
			Card c = deck.getTopCard();
			player.dealCard(c);
		}
	}
	
	public String presentScore(Player player) {
		String score = player.getScore().toString();
		String str = score.substring(0, 1) + score.substring(1).toLowerCase();
		String ret = str.replace('_', ' ');
		return ret;
	}
	
	public String presentHand(Player player) {
		StringBuilder str = new StringBuilder();
		for(Card c : player.getHand()) {
			str.append(c.toString() + "\n");
		}
		return str.toString();
	}
	
	public Player getWinner() {
		return null;
	}

}
