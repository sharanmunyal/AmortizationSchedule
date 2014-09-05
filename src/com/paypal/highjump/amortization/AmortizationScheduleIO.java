package com.paypal.highjump.amortization;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;


public class AmortizationScheduleIO {
	//ideally this func should be in some util class
	private static boolean isNull(Object o){
		return (o == null);
	}
	private static String getUserInput(String message) {
		String input = "";

		if (!isNull(message)) {
			try {
				input = ConsoleUtil.readLine(message);
			} catch (IOException e) {
				ConsoleUtil.print(AmortizationConstants.IO_EXCEPTION_MESSAGE);
				System.exit(1);
			}
		}
		return input;
	}

	private static boolean isValidUserInput(Range range, Number value) {
		boolean isValid = false;
		if(!isNull(range) && !isNull(value)) {
			isValid = ((range.start.doubleValue() <= value.doubleValue()) && (value.doubleValue() <= range.end.doubleValue()));			
		}
		return isValid;
	}

	private static long calculateMonthlyPayment(AmortizationSchedule as) {
		/**
		 * M = P * (J / (1 - (Math.pow(1/(1 + J), N)))); 
		 * Where: P = Principal 
		 * I = Interest 
		 * J = Monthly Interest in decimal form: I / (12 * 100) 
		 * N = Number of months of loan 
		 * M = Monthly Payment Amount 
		 * calculate J
		 */
		double apr, monthlyInterest;
		int initialTermMonths;
		long amountBorrowed;
		
		apr = as.getApr();
		initialTermMonths = as.getInitialTermMonths();
		amountBorrowed = as.getAmountBorrowed();
		
		monthlyInterest = apr / AmortizationConstants.MONTHLY_INTEREST_DIVISOR;
		as.setMonthlyInterest(monthlyInterest);
		
		double tmp = Math.pow(1d + monthlyInterest, -1);
		// this is Math.pow(1/(1 + J), N)
		tmp = Math.pow(tmp, initialTermMonths);
		// this is 1 / (1 - (Math.pow(1/(1 + J), N))))
		tmp = Math.pow(1d - tmp, -1);
		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		double rc = amountBorrowed * monthlyInterest * tmp;
		return Math.round(rc);
	}

	public static AmortizationSchedule getUserLoanInfo() {
		
		AmortizationSchedule as = new AmortizationSchedule();
		
		double amount = 0;
		int years = 0;
		long amountBorrowed;
		double apr;
		int initialTermMonths;
		long monthlyPaymentAmount;
		
		// get the borrowed amount
		amount = getLoanInfo(AmortizationConstants.BORROW_AMOUNT_RANGE, AmortizationConstants.GET_AMOUNT_MESSAGE).doubleValue();
		amountBorrowed = Math.round(amount * 100);
		as.setAmountBorrowed(amountBorrowed);
		
		// get the APR
		apr = getLoanInfo(AmortizationConstants.APR_RANGE, AmortizationConstants.GET_APR_MESSAGE).doubleValue();
		as.setApr(apr);
		
		// get the loan duration
		years = getLoanInfo(AmortizationConstants.TERM_RANGE, AmortizationConstants.GET_LOAN_DURATION_MESSAGE).intValue();
		initialTermMonths = years * 12;
		as.setInitialTermMonths(initialTermMonths);
		
		//calculate monthly payment
		monthlyPaymentAmount = calculateMonthlyPayment(as);
		as.setMonthlyPaymentAmount(monthlyPaymentAmount);
		
		/**
		 * the following shouldn't happen with the available valid ranges for
		 * borrow amount, apr, and term; however, without range validation,
		 * monthlyPaymentAmount as calculated by calculateMonthlyPayment() may
		 * yield incorrect values with extreme input values
		 */
		if (monthlyPaymentAmount > amountBorrowed) {
			throw new IllegalArgumentException();
		}
		return as;
	}
	private static Number getLoanInfo(Range range, String message) {
		Number value = null;
		String input = "";
		boolean isValidValue = false;
		while (!isValidValue) {
			input = getUserInput(message);
			if (input != null) {
				try {
					try{
						value = NumberFormat.getInstance().parse(input);
					} catch(ParseException e) {
						//this indicates input couldn't be parsed
						ConsoleUtil.print(AmortizationConstants.PARSE_EXCEPTION);
					}// this indicates if the number is out of range
					if (!isValidUserInput(range, value)) {
						ConsoleUtil
								.printf(AmortizationConstants.INPUT_FORMAT, range.start.toString(), range.end.toString());
					} else {
						isValidValue = true;
					}
				} catch (NumberFormatException e) {
					// this indicates an invalid character like a/$ was entered
					ConsoleUtil
							.print(AmortizationConstants.INVALID_VALUE_MESSAGE);
				}
			} else {
				isValidValue = true;
			}
		}
		return value;
	}
}