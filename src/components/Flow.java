package components;

import java.time.LocalDate;

//Exercise 1.3.2 Creation of the Flow class
public abstract class Flow {
	private String comment;
	private static int counter = 0;
	private int identifier;
	private double amount;
	private int targetAccountNo;
	private boolean effect;
	private LocalDate dateOfFlow;

	// Getters
	public String getComment() {
		return comment;
	}

	public double getAmount() {
		return amount;
	}

	public int getIdentifier() {
		return identifier;
	}

	public int getTargetAccountNo() {
		return targetAccountNo;
	}

	public boolean isEffect() {
		return effect;
	}

	public LocalDate getDateOfFlow() {
		return dateOfFlow;
	}

	// Setters
	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setTargetAccountNo(int targetAccountNo) {
		this.targetAccountNo = targetAccountNo;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public void setDateOfFlow(LocalDate dateOfFlow) {
		this.dateOfFlow = dateOfFlow;
	}

	// Constructor
	public Flow(double amount, int targetAccountNo, LocalDate dateOfFlow) {
		this.amount = amount;
		this.targetAccountNo = targetAccountNo;
		this.dateOfFlow = dateOfFlow;
		counter++;
		identifier = counter;
	}

	@Override
	public String toString() {
		return "Flow identifier: " + identifier + ", Amount: " + amount + ", Target account no.: " + targetAccountNo
				+ ", Effect: " + effect + ", Date: " + dateOfFlow;
	}

}
