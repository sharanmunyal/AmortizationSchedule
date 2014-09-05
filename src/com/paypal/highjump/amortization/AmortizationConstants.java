package com.paypal.highjump.amortization;

public interface AmortizationConstants {
	// constant to indicate the monthly interest divisor
	static final double MONTHLY_INTEREST_DIVISOR = 12d * 100d;

	// constants to indicate amount that can be borrowed
	static final Range BORROW_AMOUNT_RANGE = new Range(0.01d, 1000000000000d);

	// constants to indicate APR range
	static final Range APR_RANGE = new Range(0.000001d, 100d);

	// constants to indicate term range
	static final Range TERM_RANGE = new Range(1, 1000000);

	// string constants to prompt the user for input
	static final String GET_AMOUNT_MESSAGE = "Please enter the amount you would like to borrow: ";
	static final String GET_APR_MESSAGE = "Please enter the annual percentage rate used to repay the loan: ";
	static final String GET_LOAN_DURATION_MESSAGE = "Please enter the term, in years, over which the loan is repaid: ";

	// string constant when invalid input given
	static final String INVALID_VALUE_MESSAGE = "An invalid value was entered.\n";

	// string constant to indicate IO exception
	static final String IO_EXCEPTION_MESSAGE = "An IOException was encountered. Terminating program.\n";

	// message to indicate failure of input parsing
	static final String PARSE_EXCEPTION = "Input couldn't be parsed.\n";

	// message to indicate processing values failed
	static final String PROCESSING_VALUES_FAILED = "Unable to process the values entered. Terminating program.";

	// string constant when printing failed
	static final String PRINTING_ERROR = "Error printing...\n";

	// string constants to be used while showing the payment details
	static final String CURRENT_BALANCE = "CurrentBalance";
	static final String PAYMENT_AMOUNT = "PaymentAmount";
	static final String PAYMENT_INTEREST = "PaymentInterest";
	static final String PAYMENT_NUMBER = "PaymentNumber";
	static final String TOTAL_INTEREST_PAID = "TotalInterestPaid";
	static final String TOTAL_PAYMENTS = "TotalPayments";

	// following string describes all the string formats with their
	// place-holders
	static final String HEADER_FORMAT = "%1$-20s%2$-20s%3$-20s%4$s,%5$s,%6$s\n";
	static final String OUTPUT_FORMAT = "%1$-20d%2$-20.2f%3$-20.2f%4$.2f,%5$.2f,%6$.2f\n";
	static final String INPUT_FORMAT = "Please enter a positive value between %1$s and %2$s.\n";
}