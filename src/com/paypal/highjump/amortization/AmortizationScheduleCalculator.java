package com.paypal.highjump.amortization;

public class AmortizationScheduleCalculator {

	/**
	 * The output should include: The first column identifies the payment
	 * number. The second column contains the amount of the payment. The third
	 * column shows the amount paid to interest. The fourth column has the
	 * current balance. The total payment amount and the interest paid fields.
	 */
	public static void outputAmortizationSchedule(AmortizationSchedule as) {

		/**
		 * To create the amortization table, create a loop in your program and
		 * follow these steps: 1. Calculate H = P x J, this is your current
		 * monthly interest 2. Calculate C = M - H, this is your monthly payment
		 * minus your monthly interest, so it is the amount of principal you pay
		 * for that month 3. Calculate Q = P - C, this is the new balance of 4.
		 * Set P equal to Q and go back to Step 1: You thusly loop around until
		 * the value Q (and hence P) goes to zero.
		 */

		String formatString = AmortizationConstants.HEADER_FORMAT;
		ConsoleUtil.printf(formatString, AmortizationConstants.PAYMENT_NUMBER,
				AmortizationConstants.PAYMENT_AMOUNT,
				AmortizationConstants.PAYMENT_INTEREST,
				AmortizationConstants.CURRENT_BALANCE,
				AmortizationConstants.TOTAL_PAYMENTS,
				AmortizationConstants.TOTAL_INTEREST_PAID);

		long balance = as.getAmountBorrowed();
		int paymentNumber = 0;
		long totalPayments = 0;
		long totalInterestPaid = 0;
		long curMonthlyInterest, curPayoffAmount, curMonthlyPaymentAmount, curMonthlyPrincipalPaid, curBalance;
		// output is in dollars
		formatString = AmortizationConstants.OUTPUT_FORMAT;
		ConsoleUtil.printf(formatString, paymentNumber++, 0d, 0d,
				((double) balance) / 100d, ((double) totalPayments) / 100d,
				((double) totalInterestPaid) / 100d);

		final int maxNumberOfPayments = as.getInitialTermMonths() + 1;
		double monthlyInterest = as.getMonthlyInterest();
		long monthlyPaymentAmount = as.getMonthlyPaymentAmount();

		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {
			// Calculate H = P x J, this is your current monthly interest
			curMonthlyInterest = Math.round(((double) balance)
					* monthlyInterest);

			// the amount required to payoff the loan
			curPayoffAmount = balance + curMonthlyInterest;

			/**
			 * the amount to payoff the remaining balance may be less than the
			 * calculated monthlyPaymentAmount
			 */
			curMonthlyPaymentAmount = Math.min(monthlyPaymentAmount,
					curPayoffAmount);

			/**
			 * it's possible that the calculated monthlyPaymentAmount is 0, or
			 * the monthly payment only covers the interest payment - i.e. no
			 * principal so the last payment needs to payoff the loan
			 */

			if ((paymentNumber == maxNumberOfPayments)
					&& ((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest))) {
				curMonthlyPaymentAmount = curPayoffAmount;
			}

			/**
			 * Calculate C = M - H, this is your monthly payment minus your
			 * monthly interest, so it is the amount of principal you pay for
			 * that month
			 */
			curMonthlyPrincipalPaid = curMonthlyPaymentAmount
					- curMonthlyInterest;

			/**
			 * Calculate Q = P - C, this is the new balance of your principal of
			 * your loan.
			 */
			curBalance = balance - curMonthlyPrincipalPaid;
			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;

			// output is in dollars
			ConsoleUtil.printf(formatString, paymentNumber++,
					((double) curMonthlyPaymentAmount) / 100d,
					((double) curMonthlyInterest) / 100d,
					((double) curBalance) / 100d,
					((double) totalPayments) / 100d,
					((double) totalInterestPaid) / 100d);
			/**
			 * Set P equal to Q and go back to Step 1: You thusly loop around
			 * until the value Q (and hence P) goes to zero.
			 */
			balance = curBalance;
		}
	}
}