package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";
	public final String INSTRUCTIONS = "Press 'p' to play, 'q' to quit";
	public final String INSTRUCTIONS2 = "Press numbers 1-5 to throw a card, 'f' to finish (and get new cards)";
	public final int CLEAR_SPACES = 25;
	
	private Game game;
	private ConsoleWrapper cw;
	
	public Console(Game newGame, ConsoleWrapper newCW) {
		game = newGame;
		cw = newCW;
	}
	
	public void play() {
		cw.println(WELCOME_MSG);
		cw.println(INSTRUCTIONS);
		
		while(wantsToPlay(cw.getInput())) {
			runGame();
		}
	}
	
	private boolean wantsToPlay(String input) {
		if(input == null)
			return false;
		return input.equals("p");
	}
	
	private boolean wantsToThrowCards(String input) {
		try {
			Integer.parseInt(input);
			game.throwCard(null, null);
		}
		catch(NumberFormatException e) {
		}
		return !input.equals("f");
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

		do {
			throwCards();
		}
		while(wantsToThrowCards(cw.getThrowCardInput()));
		
		Player winner = game.getWinner();
		cw.println(presentScore(winner));
		cw.println(presentHand(winner));
	}
	
	public void throwCards() {
		clearScreen();
		cw.println(INSTRUCTIONS2);
		Player player = game.getPlayer();
		cw.println(presentHand(player));
	}
	
	public void clearScreen() {
		for(int i = 0; i < CLEAR_SPACES; i ++) {
			cw.println("");
		}
	}
}
