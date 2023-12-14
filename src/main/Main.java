package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;

//Exercise 1.1.2 Creation of the Main class
public class Main {

	// Creating Client List
	static ArrayList<Client> createClientList(int noOfClients) {
		ArrayList<Client> clientList = new ArrayList<Client>();
		for (int i = 0; i < noOfClients; i++) {
			String newClientName = "name" + (i + 1);
			String newClientFirstName = "firstname" + (i + 1);
			Client newClient = new Client(newClientName, newClientFirstName);
			clientList.add(newClient);
		}
		return clientList;
	}

	// Showing client list
	static void showClientList(ArrayList<Client> dispClientList) {
		System.out.println("Clients:");
		for (int i = 0; i < dispClientList.size(); i++) {
			System.out.println(dispClientList.get(i));
		}
	}

	// Exercise 1.2.3 Creation of the tablea account
	// Creating Account List
	static ArrayList<Account> createAccountList(ArrayList<Client> inClientList) {
		ArrayList<Account> accountList = new ArrayList<Account>();
		for (int i = 0; i < inClientList.size(); i++) {
			// Current account for client i
			String newAccountLabel = "Current Account";
			CurrentAccount newAccount1 = new CurrentAccount(newAccountLabel, inClientList.get(i));
			accountList.add(newAccount1);
			// Savings account for client i
			newAccountLabel = "Savings Account";
			SavingsAccount newAccount2 = new SavingsAccount(newAccountLabel, inClientList.get(i));
			accountList.add(newAccount2);
		}
		return accountList;
	}

	// Showing account list
	static void showAccountList(ArrayList<Account> dispAccountList) {
		System.out.println("");
		System.out.println("Accounts:");
		for (int i = 0; i < dispAccountList.size(); i++) {
			System.out.println(dispAccountList.get(i));
		}
	}

	// Exercise 1.3.1 Adaptation of the table of accounts
	// Creating HashMap
	static HashMap<Integer, Account> createAccountTable(ArrayList<Account> inAccountList) {
		HashMap<Integer, Account> accountTable = new HashMap<Integer, Account>();
		for (int i = 0; i < inAccountList.size(); i++) {
			// Current account for client i + 1
			// Ex method working fine, but not for sorted table, a pitty really
			// accountTable.put(inAccountList.get(i).getAccountNo(), inAccountList.get(i));
			accountTable.put((i + 1), inAccountList.get(i));
		}
		return accountTable;
	}

	// Showing HashMap
	static void showAccountTable(HashMap<Integer, Account> dispAccountTable) {
		System.out.println("");
		System.out.println("HashMap:");
		for (int i = 0; i < dispAccountTable.size(); i++) {
			System.out.println(dispAccountTable.get(i + 1));
		}
	}

	// Showing Sorted HashMap
	static void showSortedAccountTable(ArrayList<Account> inAccountList) {
		ArrayList<Account> clonedAccountList = (ArrayList<Account>) inAccountList.clone();
		clonedAccountList.sort((o1, o2) -> o1.getBalance().compareTo(o2.getBalance()));
		HashMap<Integer, Account> dispAccountTable2 = createAccountTable(clonedAccountList);
		System.out.println("");
		System.out.println("Sorted HashMap:");
		for (int i = 0; i < dispAccountTable2.size(); i++) {
			System.out.println(dispAccountTable2.get(i + 1));
		}
	}

	// Exercise 1.3.4 Creation of the flow array
	// Creating Flow Array
	static ArrayList<Flow> createFlowList(ArrayList<Account> inAccountList) {
		ArrayList<Flow> flowList = new ArrayList<Flow>();
//		System.out.println("");
//		System.out.println("Flows:");
		LocalDate flowDate = LocalDate.now().plusDays(2);
		// Flow 1
		Debit newDebitFlow = new Debit(50.0, 1, flowDate);
		flowList.add(newDebitFlow);
		// Flow 2 and 3 for each current account and savings account respectively
		for (int i = 0; i < inAccountList.size(); i++) {
			if (inAccountList.get(i).getLabel() == "Current Account") {
				Credit newCreditFlow = new Credit(100.50, inAccountList.get(i).getAccountNo(), flowDate);
				flowList.add(newCreditFlow);
			} else {
				Credit newCreditFlow = new Credit(1500, inAccountList.get(i).getAccountNo(), flowDate);
				flowList.add(newCreditFlow);
			}
		}
		// Flow 4
		Transfer newTransferFlow = new Transfer(50.0, 2, flowDate, 1);
		flowList.add(newTransferFlow);
		// DISPLAY FLOW ARRAY
		System.out.println("");
		System.out.println("Flow list:");
		for (int i = 0; i < flowList.size(); i++) {
			System.out.println(flowList.get(i));
		}
		return flowList;
	}

	public static void main(String[] args) {
		// Exercise 1.1.2 Creation of main class for tests
		// Creating client list
		ArrayList<Client> myClientList = createClientList(3);
		showClientList(myClientList);

		// Exercise 1.2.3 Creation of the tablea account
		// Creating accounts list
		ArrayList<Account> myAccountList = createAccountList(myClientList);
		showAccountList(myAccountList);

		// Exercise 1.3.1 Adaptation of the table of accounts
		// Hashmap
		HashMap<Integer, Account> myAccountTable = createAccountTable(myAccountList);
		showAccountTable(myAccountTable);
		showSortedAccountTable(myAccountList);

		// Exercise 1.3.4 Creation of the flow array
		ArrayList<Flow> myFlowList = createFlowList(myAccountList);
	}
}
