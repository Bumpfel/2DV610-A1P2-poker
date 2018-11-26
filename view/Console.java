package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";
	public final String INSTRUCTIONS = "Press 'p' to play, 'q' to quit";

	private Game game;
	private ConsoleWrapper cw;
	
	public Console(Game newGame, ConsoleWrapper newCW) {
		game = newGame;
		cw = newCW;
	}
	
	public void play() {
		printWelcomeMsg();
		cw.println(INSTRUCTIONS);
		
		while(wantsToPlay(cw.getInput())) {
			runGame();
		}
	}
	
	public void printWelcomeMsg() {
		cw.println(WELCOME_MSG);
	}
	
	
	private boolean wantsToPlay(String input) {
		if(input == null)
			return false;
		return input.equals("p");
	}
	
	public String presentScore(Player player) {
		if(player == null) 
			return null;
		String score = player.getScore().toString();
		String str = score.substring(0, 1) + score.substring(1).toLowerCase();
		String ret = str.replace('_', ' ');
		return ret;
	}
	
	public String presentHand(Player player) {
		if(player == null) 
			return null;
		StringBuilder str = new StringBuilder();
		for(Card c : player.getHand()) {
			str.append(c.toString() + "\n");
		}
		return str.toString();
	}
	
	public void runGame() {
		game.newGame();
		game.getWinner();
	}
}
