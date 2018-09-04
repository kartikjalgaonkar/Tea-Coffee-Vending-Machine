package com.fwf.tcvm.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.fwf.tcvm.controller.InputScanner;

public class InputScannerTest {

	@Test
	public void getInputString() {

		String input = "add 5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		InputScanner obj = new InputScanner(new Scanner(System.in));

		String actual = obj.getString();

		assertEquals("add 5", actual);

	}

	@Test
	public void getInputDouble() {

		String input = "5.5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		InputScanner obj = new InputScanner(new Scanner(System.in));
		Double s = obj.nextDouble();

		assertEquals(new Double(5.5), s);

	}

	@Test
	public void getInputInteger() {

		String input = "5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		InputScanner obj = new InputScanner(new Scanner(System.in));
		Integer s = obj.nextInt();

		assertEquals(new Integer(5), s);

	}

}
