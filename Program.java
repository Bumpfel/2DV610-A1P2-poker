import model.*;
import view.Console;

public class Program {
	public static void main(String[] args) {
		Player player = new Player("Eric");
		Deck deck = new Deck();
		Game game = new Game(player, deck);
		ConsoleWrapper cw = new ConsoleWrapper();
		
		Console view = new Console(game, cw);
		view.play();
	}
}
