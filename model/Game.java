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
			dealFromDeck(player);
		}
	}
	
	public Player getWinner() {
		return player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void fillUpHand(Player player) {
		for(int i = player.getHandSize(); i < CARDS_TO_DEAL; i ++) {
			dealFromDeck(player);
		}
	}
	
	public void throwCard(Player p, Card c) {
		p.removeCard(c);
	}
	
	private void dealFromDeck(Player player) {
		Card c = deck.getTopCard();
		player.dealCard(c);
	}

}
