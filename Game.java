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
	
}
