package com.fwf.tcvm.controller;

import java.util.Scanner;

public class InputScanner {
	
	private final Scanner scanner;

	public InputScanner() {
		this.scanner = new Scanner(System.in);
	}
	
/*	public InputScanner(Scanner scanner) {
		super();
		this.scanner = scanner;
	}*/

	public String getString() {
		return scanner.next();
	}

	public double nextDouble() {
		// TODO Auto-generated method stub
		return scanner.nextDouble();
	}

	public int nextInt() {
		// TODO Auto-generated method stub
		return scanner.nextInt();
		
	}
	
	

	

}
