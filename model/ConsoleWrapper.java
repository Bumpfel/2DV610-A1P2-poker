package model;

import java.util.Scanner;

public class ConsoleWrapper {
	Scanner in = new Scanner(System.in);
	// Found no way of testing this method, so I will just implement it at GREEN
	public String getInput() {
		return in.nextLine();
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
	
	//made this for testing verification purposes
	public String getThrowCardInput() {
		return getInput();
	}
}
