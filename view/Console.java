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
		String input = cw.getInput();
		
		if(input != null) {
			game.newGame();
		}
	}
	
	public void printWelcomeMsg() {
		cw.println(WELCOME_MSG);
	}
	
}
