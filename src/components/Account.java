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
		System.out.println(newFlow.getClass().toString());
		switch (newFlow.getClass().toString()) {
		case "class components.Credit":
			System.out.println("Case 1");
			this.balance = this.balance + newFlow.getAmount();
			break;
		case "class components.Debit":
			System.out.println("Case 2");
			this.balance = this.balance - newFlow.getAmount();
			break;
		case "class components.Transfer":
			System.out.println("Case 3");
			Transfer newTransferFlow = (Transfer) newFlow;
			System.out.println(this.accountNo);
			System.out.println(newTransferFlow.getTargetAccountNo());
			System.out.println("Old balance: " + this.balance);
			if (this.accountNo == newTransferFlow.getTargetAccountNo()) {
				this.balance = this.balance + newTransferFlow.getAmount();
			} else if (this.accountNo == newTransferFlow.getIssuingAccountNo()) {
				this.balance = this.balance - newTransferFlow.getAmount();
			}
			System.out.println("New balance: " + this.balance);
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
//		myClient = new Client(inClient.getName(), inClient.getFirstName());
//		myClient.setClientNo(inClient.getClientNo());
//		myClient.setName(inClient.getName());
//		myClient.setFirstName(inClient.getFirstName());
		counter++;
		accountNo = counter;
		// test for negative balance
		// balance = (double) (accountNo - 10);
	}

	@Override
	public String toString() {
		return "Label: " + label + ", Balance: " + balance + ", Account no.: " + accountNo + ", Client account: "
				+ myClient;
	}

}
