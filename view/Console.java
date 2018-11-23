package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";
	public final String INSTRUCTIONS = "Press 'p' to play, 'q' to quit";

	private ConsoleWrapper cw;
	
	public Console(Game game, ConsoleWrapper newCW) {
		cw = newCW;
	}
	
	public void play() {
		printWelcomeMsg();
		println(INSTRUCTIONS);
		cw.getInput();
	}
	
	public void printWelcomeMsg() {
		println(WELCOME_MSG);
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
	
}
