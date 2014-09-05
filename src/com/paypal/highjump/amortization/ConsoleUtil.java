package com.paypal.highjump.amortization;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;


public class ConsoleUtil {
	private static Console console = System.console();

	public static void printf(String formatString, Object... args) throws IllegalFormatException {
		try {
			if (console != null) {
				console.printf(formatString, args);
			} else {
				System.out.print(String.format(formatString, args));
			}
		} catch (IllegalFormatException e) {
			System.err.print(AmortizationConstants.PRINTING_ERROR);
		}
	}

	public static void print(String s) {
		printf("%s", s);
	}

	public static String readLine(String userPrompt) throws IOException {
		String line = "";
		if (console != null) {
			line = console.readLine(userPrompt);
		} else {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
			print(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}
}
