import model.*;
import view.Console;

public class Program {
	public static void main(String[] args) {
		Player player = new Player();
		Deck deck = new Deck();
		Game game = new Game(player, deck);
		
		Console view = new Console(game);
		view.play();
	}
}
