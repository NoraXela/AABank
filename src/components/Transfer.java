package components;

import java.time.LocalDate;

//Exercise 1.3.3 Creation of the Transfert, Credit, Debit classes
public class Transfer extends Flow {

	public int issuingAccountNo;

	public int getIssuingAccountNo() {
		return issuingAccountNo;
	}

	public void setIssuingAccountNo(int issuingAccountNo) {
		this.issuingAccountNo = issuingAccountNo;
	}

	public Transfer(double amount, int targetAccountNo, LocalDate dateOfFlow, int issuingAccountNo) {
		super(amount, targetAccountNo, dateOfFlow);
		// TODO Auto-generated constructor stub
		this.issuingAccountNo = issuingAccountNo;
	}

	@Override
	public String toString() {
		return super.toString() + ", Issuing account no.: " + issuingAccountNo;
	}

}
