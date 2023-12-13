package main;

import java.util.ArrayList;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

//Exercise 1.1.2 Creation of the Main class
public class Main {

	// Creating Client List
	static ArrayList<Client> createClientList(int noOfClients) {
		ArrayList<Client> clientList = new ArrayList<Client>();
		for (int i = 0; i < noOfClients; i++) {
			String newClientName = "name" + (i + 1);
			String newClientFirstName = "firstname" + (i + 1);
			Client newClient = new Client(newClientName, newClientFirstName);
//			System.out.println(newClient);
			clientList.add(newClient);
		}
//		System.out.println(clientList);
		return clientList;
	}

	// Showing client list
	static void showClientList(ArrayList<Client> dispClientList) {
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
//			System.out.println(newAccount1);
			accountList.add(newAccount1);
			// Savings account for client i
			newAccountLabel = "Savings Account";
			SavingsAccount newAccount2 = new SavingsAccount(newAccountLabel, inClientList.get(i));
//			System.out.println(newAccount2);
			accountList.add(newAccount2);
		}
		// System.out.println(clientList);
		return accountList;
	}

	// Showing account list
	static void showAccountList(ArrayList<Account> dispAccountList) {
		for (int i = 0; i < dispAccountList.size(); i++) {
			System.out.println(dispAccountList.get(i));
		}
	}

	public static void main(String[] args) {
		// Creating client list
		ArrayList<Client> myClientList = createClientList(3);
//		Client myClient = new Client("name1", "firstname1");
		showClientList(myClientList);

		// Creating accounts list
		ArrayList<Account> myAccountList = createAccountList(myClientList);
		showAccountList(myAccountList);

		// Test Account
//		CurrentAccount newAccount = new CurrentAccount("label1", myClientList.get(0));
//		System.out.println(newAccount);
	}
}
