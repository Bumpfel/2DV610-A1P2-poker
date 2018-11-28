package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";
	public final String INSTRUCTIONS = "Press 'p' to play, 'q' to quit";
	public final String INSTRUCTIONS2 = "These are your cards dealt \nPress corresponding number to throw a card, 'f' to finish (and get new cards)";
	public final String INVALID_INPUT = "Invalid input";
	public final String GAME_OVER_MSG = "Game over. You got ";
	public final int CLEAR_SPACES = 25;
	public final int PAUSE_TIME = 1000;
	
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
	
	public boolean wantsToThrowCards(String input) {
		try {
			Player player = game.getPlayer();
			int inputInt = Integer.parseInt(input);
			if(inputInt >= 1 && inputInt <= player.getHandSize()) {
				int i = 1;
				Card selectedCard = null;
				for(Card c : player.getHand()) {
					if(inputInt == i)
						selectedCard = c; 
					i ++;
				}
				game.throwCard(player, selectedCard);
			}
			else {
				cw.println(INVALID_INPUT);
				cw.pause(PAUSE_TIME);
			}
		}
		catch(NumberFormatException e) {
			if(!input.equals("f")) {
				cw.println(INVALID_INPUT);
				cw.pause(PAUSE_TIME);
			}
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
	
	public String presentHand(Player player, boolean showNumbers) {
		if(player == null) 
			return null;
		StringBuilder str = new StringBuilder();
		int i = 1;
		for(Card c : player.getHand()) {
			if(showNumbers)
				str.append(i + ". ");
			str.append(c.toString() + "\n");
			i ++;
		}
		return str.toString();
	}
	
	public void runGame() {
		game.newGame();

		// Swap cards
		do {
			throwCards();
		}
		while(wantsToThrowCards(cw.getThrowCardInput()));
		
		Player player = game.getPlayer();
		game.fillUpHand(player);
		
		
		// Game over
		Player winner = game.getWinner();
		clearScreen();
		cw.print(GAME_OVER_MSG);
		cw.println(presentScore(winner));
		cw.println(presentHand(winner, false));
	}
	
	public void throwCards() {
		clearScreen();
		cw.println(INSTRUCTIONS2);
		Player player = game.getPlayer();
		cw.println(presentHand(player, true));
	}
	
	public void clearScreen() {
		for(int i = 0; i < CLEAR_SPACES; i ++) {
			cw.println("");
		}
	}
}
