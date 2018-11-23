package model;

import java.util.Scanner;

public class ConsoleWrapper {
	
	// Found no way of testing this method, so I will just implement it at GREEN
	public String getInput() {
		try(Scanner in = new Scanner(System.in)) {
			String input = in.nextLine();
			return input;
		}
	}
}
