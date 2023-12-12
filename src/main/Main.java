package main;

import java.util.ArrayList;

import components.Client;

//Exercise 1.1.2 Creation of the Main class
public class Main {

	static void createClientList(int noOfClients) {
		ArrayList<Client> clientList = new ArrayList<Client>();
		for (int i = 0; i < noOfClients; i++) {
			String newClientName = "name" + (i + 1);
			String newClientFirstName = "firstname" + (i + 1);
			Client newClient = new Client(newClientName, newClientFirstName);
			clientList.add(newClient);
		}
		System.out.println(clientList);
	}

	public static void main(String[] args) {
		createClientList(2);
//		Client myClient = new Client("name1", "firstname1");
//		System.out.println(myClient.toString());
	}
}
