package view;

import model.*;

public class Console {
	
	public final String WELCOME_MSG = "Welcome to Poker";

	public Console(Game game) {
		
	}
	
	public void play() {
		printWelcomeMsg();
		println("Press 'p' to play, 'q' to quit");
	}

	public void printWelcomeMsg() {
		println(WELCOME_MSG);
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
	
}
