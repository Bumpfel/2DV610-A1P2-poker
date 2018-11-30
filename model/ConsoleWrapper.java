package model;

import java.util.Scanner;

public class ConsoleWrapper {
	Scanner in = new Scanner(System.in);
	// Found no way of testing this method, so I will just implement it at GREEN
	public String getInput() {
		System.out.print(": ");
		return in.nextLine();
	}
	
	public void println(String msg) {
		System.out.println(msg);
	}
	
	public void print(String msg) {
		System.out.print(msg);
	}
	
	//made this for testing verification purposes
	public String getThrowCardInput() {
		return getInput();
	}
	
	public void pause(int time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException e) {
		}
	}
}
