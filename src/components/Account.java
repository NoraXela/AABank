package components;

//Exercise 1.2.1 Creation of the Account class
public abstract class Account {
	protected String label;
	protected Double balance = 0.0;
	protected static int counter = 0;
	protected int accountNo;
	protected Client myClient;

	// Getters
	public String getLabel() {
		return label;
	}

	public Double getBalance() {
		return balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	// Setters
	public void setLabel(String newLabel) {
		this.label = newLabel;
	}

	public void setBalance(Flow newFlow) {
		switch (newFlow.getClass().toString()) {
		case "class components.Credit":
			this.balance = this.balance + newFlow.getAmount();
			break;
		case "class components.Debit":
			this.balance = this.balance - newFlow.getAmount();
			break;
		case "class components.Transfer":
			Transfer newTransferFlow = (Transfer) newFlow;
			if (this.accountNo == newTransferFlow.getTargetAccountNo()) {
				this.balance = this.balance + newTransferFlow.getAmount();
			} else if (this.accountNo == newTransferFlow.getIssuingAccountNo()) {
				this.balance = this.balance - newTransferFlow.getAmount();
			}
			break;
		default:
			System.out.println("DEFAULT");
		}
	}

	public void setAccountNo(int newAccountNo) {
		this.accountNo = newAccountNo;
	}

	// Constructor
	public Account(String inLabel, Client inClient) {
		label = inLabel;
		myClient = inClient;
		counter++;
		accountNo = counter;
		// Test for negative balance
		// balance = (double) (accountNo - 10);
	}

	@Override
	public String toString() {
		return "Label: " + label + ", Balance: " + balance + ", Account no.: " + accountNo + ", Client account: "
				+ myClient;
	}

}
