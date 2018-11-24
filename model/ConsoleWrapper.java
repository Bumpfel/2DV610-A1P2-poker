package model;

import java.util.Scanner;

public class ConsoleWrapper {
	Scanner in = new Scanner(System.in);
	// Found no way of testing this method, so I will just implement it at GREEN
	public String getInput() {
		String input = in.nextLine();
		return input;
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
}
