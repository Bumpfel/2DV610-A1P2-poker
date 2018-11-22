public class Game {
	
	public final int CARDS_TO_DEAL = 5;
	private Player player;
	
	public Game(Player newPlayer) {
		player = newPlayer;
	}
	
	public void newGame() {
		Card c = new Deck().getTopCard();
		for(int i = 0; i < CARDS_TO_DEAL; i ++) {
			player.dealCard(c);
		}
	}
	
}
