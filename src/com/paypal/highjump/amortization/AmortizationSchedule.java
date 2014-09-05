package com.paypal.highjump.amortization;

public class AmortizationSchedule {
	private long amountBorrowed; // in cents
	private double apr;
	private int initialTermMonths;
	private double monthlyInterest;
	private long monthlyPaymentAmount; // in cents

	public AmortizationSchedule() {

		// initialize all the member variables to 0
		amountBorrowed = 0;
		apr = 0.0;
		initialTermMonths = 0;
		monthlyPaymentAmount = 0;
		monthlyInterest = 0d;
	}

	// getters
	public long getAmountBorrowed() {
		return this.amountBorrowed;
	}

	public double getApr() {
		return this.apr;
	}

	public int getInitialTermMonths() {
		return this.initialTermMonths;
	}

	public double getMonthlyInterest() {
		return this.monthlyInterest;
	}

	public long getMonthlyPaymentAmount() {
		return this.monthlyPaymentAmount;
	}

	// setters
	public void setAmountBorrowed(long amountBorrowed) {
		this.amountBorrowed = amountBorrowed;
	}

	public void setApr(double apr) {
		this.apr = apr;
	}

	public void setInitialTermMonths(int months) {
		this.initialTermMonths = months;
	}

	public void setMonthlyInterest(double interest) {
		this.monthlyInterest = interest;
	}

	public void setMonthlyPaymentAmount(long monthlyPaymentAmount) {
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}
}