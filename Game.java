public class Game {
	
	private Player player;
	
	public Game(Player newPlayer) {
		player = newPlayer;
	}
	
	public void newGame() {
		Card c = new Deck().getTopCard();
		player.dealCard(c);
		player.dealCard(c);
		player.dealCard(c);
		player.dealCard(c);
		player.dealCard(c);
	}
	
}
