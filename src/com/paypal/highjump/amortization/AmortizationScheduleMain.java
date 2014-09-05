package com.paypal.highjump.amortization;

public class AmortizationScheduleMain {
	public static void main(String[] args) {
		try {
			AmortizationSchedule as;

			// gather user loan information
			as = AmortizationScheduleIO.getUserLoanInfo();

			// compute and print the schedule
			AmortizationScheduleCalculator.outputAmortizationSchedule(as);
		} catch (IllegalArgumentException e) {
			System.out.println(AmortizationConstants.PROCESSING_VALUES_FAILED);
		}
	}
}