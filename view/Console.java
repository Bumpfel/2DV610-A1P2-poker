package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";
	public final String INSTRUCTIONS = "Press 'p' to play, 'q' to quit";
	
	public Console(Game game, ConsoleWrapper newCW) {
	}
	
	public void play() {
		printWelcomeMsg();
		println(INSTRUCTIONS);
	}
	
	public void printWelcomeMsg() {
		println(WELCOME_MSG);
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
	
}
