package Utilities;

import java.util.Scanner;

public class Scan {
	private static Scanner scanner;
	
	private Scan() {
		
	}
	
	public static Scanner getScanner() {
		if(scanner == null) {
			return new Scanner(System.in);
		}
		return scanner;
	}
	
}
