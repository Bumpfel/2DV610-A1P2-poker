package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";
	public final String INSTRUCTIONS = "Press 'p' to play, 'q' to quit";
	public final String SWAP_INSTRUCTIONS = "These are your cards dealt. You may now swap out cards for new ones \nPress corresponding number to throw a card, 'f' to finish (and get new cards)";
	public final String INVALID_INPUT = "Invalid input";
	public final String GAME_OVER_MSG = "Game over. You got ";
	public final String SWAP_MSG1 = "You have ";
	public final String SWAP_MSG2 = " swap round(s) left";
	public final int CLEAR_SPACES = 25;
	public final int PAUSE_TIME = 1000;
	public final int SWAP_ROUNDS = 2;
	
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
		if(!input.equals("p") && !input.equals("q")) {
			printInvalidInputMsg();
			clearScreen();
			play();
		}
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
			else
				printInvalidInputMsg();
		}
		catch(NumberFormatException e) {
			if(!input.equals("f"))
				printInvalidInputMsg();
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
		Player player = game.getPlayer();

		// Swap cards
		for(int i = SWAP_ROUNDS; i > 0; i --) {
			do {
				throwCards(i);
			}
			while(wantsToThrowCards(cw.getThrowCardInput()));
			game.fillUpHand(player);
		}
		
		// Game over
		Player winner = game.getWinner();
		clearScreen();
		cw.print(GAME_OVER_MSG);
		cw.println(presentScore(winner));
		cw.println(presentHand(winner, false));
		cw.println(INSTRUCTIONS);
	}
	
	public void throwCards(int roundsLeft) {
		clearScreen();
		cw.println(SWAP_INSTRUCTIONS);
		Player player = game.getPlayer();
		cw.println(presentHand(player, true));
		cw.println(SWAP_MSG1 + roundsLeft + SWAP_MSG2);
		presentScore(player);
	}
	
	public void clearScreen() {
		for(int i = 0; i < CLEAR_SPACES; i ++) {
			cw.println("");
		}
	}
	
	private void printInvalidInputMsg() {
		cw.println(INVALID_INPUT);
		cw.pause(PAUSE_TIME);
	}
}
