package view;

import model.*;

public class Console {

	public Console(Game game) {
		
	}
	
	public void play() {
		printWelcomeMsg();
	}

	public void printWelcomeMsg() {
		println("Welcome to Poker");
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
	
}
