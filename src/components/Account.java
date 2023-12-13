package components;

//Exercise 1.2.1 Creation of the Account class
public abstract class Account {
	protected String label;
	protected double balance;
	protected static int counter = 0;
	protected int accountNo;
	protected Client myClient;

	// Getters
	public String getLabel() {
		return label;
	}

	public double getBalance() {
		return balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	// Setters
	public void setLabel(String newLabel) {
		this.label = newLabel;
	}

	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}

	public void setAccountNo(int newAccountNo) {
		this.accountNo = newAccountNo;
	}

	// Constructor
	public Account(String inLabel, Client inClient) {
		label = inLabel;
		myClient = inClient;
//		myClient = new Client(inClient.getName(), inClient.getFirstName());
//		myClient.setClientNo(inClient.getClientNo());
//		myClient.setName(inClient.getName());
//		myClient.setFirstName(inClient.getFirstName());
		counter++;
		accountNo = counter;
	}

	@Override
	public String toString() {
		return "Label: " + label + ", Balance: " + balance + ", Account no.: " + accountNo + ", Client account: "
				+ myClient;
	}

}
