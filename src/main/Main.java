package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

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

	// Exercise 1.3.5 Updating accounts
	static void updateAccounts(ArrayList<Flow> inFlowList, HashMap<Integer, Account> inAccountTable) {
		System.out.println("");
		System.out.println("Updating accounts...");
		for (int i = 0; i < inFlowList.size(); i++) {
			// For transfers, two updates, one for target account, one for issuing account
			int compAccountNo;
			String flowType = inFlowList.get(i).getClass().toString();
			if (flowType.equals("class components.Transfer")) {
				Transfer inTransferFlow = (Transfer) inFlowList.get(i);
				int updAccountNo = inTransferFlow.getTargetAccountNo();
				inAccountTable.get(updAccountNo).setBalance(inTransferFlow);
				updAccountNo = inTransferFlow.getIssuingAccountNo();
				inAccountTable.get(updAccountNo).setBalance(inTransferFlow);
				compAccountNo = updAccountNo;
			} else {
				int updAccountNo = inFlowList.get(i).getTargetAccountNo();
				inAccountTable.get(updAccountNo).setBalance(inFlowList.get(i));
				compAccountNo = updAccountNo;
			}
			// checking if account below zero
			Predicate<Double> lessThanZero = j -> (j < 0.0);
			if (lessThanZero.test(inAccountTable.get(compAccountNo).getBalance())) {
				System.out.println("***********************************************");
				System.out.println("!!! Account number " + compAccountNo + " has balance below zero !!!");
				System.out.println(
						"!!! Because of Flow no: " + (inFlowList.get(i).getIdentifier()) + "                   !!!");
				System.out.println("!!! Balance: " + (inAccountTable.get(compAccountNo).getBalance())
						+ "                          !!!");
				System.out.println("***********************************************");
			}
		}
	}

	// Exercise 2.1 JSON file of flows
	// General function for writing new line (both JSON and XML file)
	static void writeNewLine(String inStringToWrite, String inPath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(inPath, true))) {
			writer.newLine();
			writer.write(inStringToWrite);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static void writeJsonFile(ArrayList<Flow> inFlowList) {
		System.out.println("");
		System.out.println("Writing JSON File...");
		String path = "JSON Flow Array.JSON";

		// Write begin bracket [
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write("[");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < inFlowList.size(); i++) {
			// Start of individual Flow
			String stringToWrite = "\t{";
			writeNewLine(stringToWrite, path);
			// Body
			// comment
			if (inFlowList.get(i).getComment() == null) {
				stringToWrite = "\t\t\"comment\": " + inFlowList.get(i).getComment() + ",";
			} else {
				stringToWrite = "\t\t\"comment\": \"" + inFlowList.get(i).getComment() + "\",";
			}
			writeNewLine(stringToWrite, path);
			// identifier
			stringToWrite = "\t\t\"identifier\": " + inFlowList.get(i).getIdentifier() + ",";
			writeNewLine(stringToWrite, path);
			// amount
			stringToWrite = "\t\t\"amount\": " + inFlowList.get(i).getAmount() + ",";
			writeNewLine(stringToWrite, path);
			// target account number
			stringToWrite = "\t\t\"targetAccountNo\": " + inFlowList.get(i).getTargetAccountNo() + ",";
			writeNewLine(stringToWrite, path);
			// effect
			stringToWrite = "\t\t\"effect\": " + inFlowList.get(i).isEffect() + ",";
			writeNewLine(stringToWrite, path);
			// Date of Flow
			stringToWrite = "\t\t\"dateOfFlow\": \"" + inFlowList.get(i).getDateOfFlow() + "\"";
			writeNewLine(stringToWrite, path);
			// End of individual Flow
			if (i != (inFlowList.size() - 1)) {
				stringToWrite = "\t},";
				writeNewLine(stringToWrite, path);
			} else {
				stringToWrite = "\t}";
				writeNewLine(stringToWrite, path);
			}
		}
		// Write ending bracket ]
		String stringToWrite = "]";
		writeNewLine(stringToWrite, path);
	}

	// 2.2 XML file of account
	static void writeXmlFile(HashMap<Integer, Account> inAccountList) {
		System.out.println("");
		System.out.println("Writing XML File...");
		String path = "XML Account Array.XML";

		// Write header
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write("<?xml version=\"1.0\"?>");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// Write root element
		String stringToWrite = "<accounts>";
		writeNewLine(stringToWrite, path);

		for (int i = 0; i < inAccountList.size(); i++) {
			stringToWrite = "\t<instance>";
			writeNewLine(stringToWrite, path);
			// HashMap Index
			stringToWrite = "\t\t<index>" + (i + 1) + "</index>";
			writeNewLine(stringToWrite, path);
			// Account
			stringToWrite = "\t\t<account>";
			writeNewLine(stringToWrite, path);
			stringToWrite = "\t\t\t<label>" + inAccountList.get(i + 1).getLabel() + "</label>";
			writeNewLine(stringToWrite, path);
			stringToWrite = "\t\t\t<balance>" + inAccountList.get(i + 1).getBalance() + "</balance>";
			writeNewLine(stringToWrite, path);
			stringToWrite = "\t\t\t<account-number>" + inAccountList.get(i + 1).getAccountNo() + "</account-number>";
			writeNewLine(stringToWrite, path);
			// Client
			stringToWrite = "\t\t\t<client>";
			writeNewLine(stringToWrite, path);
			stringToWrite = "\t\t\t\t<client-account>" + inAccountList.get(i + 1).getClient().getClientNo()
					+ "</client-account>";
			writeNewLine(stringToWrite, path);
			stringToWrite = "\t\t\t\t<client-name>" + inAccountList.get(i + 1).getClient().getName() + "</client-name>";
			writeNewLine(stringToWrite, path);
			stringToWrite = "\t\t\t\t<client-first-name>" + inAccountList.get(i + 1).getClient().getFirstName()
					+ "</client-first-name>";
			writeNewLine(stringToWrite, path);
			// End-Client
			stringToWrite = "\t\t\t</client>";
			writeNewLine(stringToWrite, path);
			// End-Account
			stringToWrite = "\t\t</account>";
			writeNewLine(stringToWrite, path);
			// Write end-instance
			stringToWrite = "\t</instance>";
			writeNewLine(stringToWrite, path);
		}
		// Write end-root
		stringToWrite = "</accounts>";
		writeNewLine(stringToWrite, path);
	}

	public static void main(String[] args) {
		// Exercise 1.1.2 Creation of main class for tests
		// Creating client list
		ArrayList<Client> myClientList = createClientList(4);
		showClientList(myClientList);

		// Exercise 1.2.3 Creation of the tablea account
		// Creating accounts list
		ArrayList<Account> myAccountList = createAccountList(myClientList);
		showAccountList(myAccountList);

		// Exercise 1.3.1 Adaptation of the table of accounts
		// Hashmap
		HashMap<Integer, Account> myAccountTable = createAccountTable(myAccountList);
		showAccountTable(myAccountTable);
		// showSortedAccountTable(myAccountList);

		// Exercise 1.3.4 Creation of the flow array
		ArrayList<Flow> myFlowList = createFlowList(myAccountList);

		// Exercise 1.3.5 Updating accounts
		updateAccounts(myFlowList, myAccountTable);
		showAccountTable(myAccountTable);
		showSortedAccountTable(myAccountList);

		// Exercise 2.1 JSON file of flows
		writeJsonFile(myFlowList);

		// Exercise 2.2 XML file of account
		writeXmlFile(myAccountTable);
	}
}
