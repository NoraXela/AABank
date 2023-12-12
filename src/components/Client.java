package components;

//import java.util.LinkedList;

// Exercise 1.1.1 Creation of the client class
public class Client {
	private static int counter = 0;
	private int clientNo;
//	private LinkedList<Integer> clientNoList = new LinkedList<Integer>();
	private String name;
	private String firstName;

	// Getters
	public int getClientNo() {
		return clientNo;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	// Setters
	public void setClientNo(int newClientNo) {
		this.clientNo = newClientNo;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	public Client(String inName, String inFirstName) {
		name = inName;
		firstName = inFirstName;
		counter++;
		clientNo = counter;
//		clientNoList.add(clientNo++);
//		System.out.println(clientNoList);
//		clientNo = clientNoList.getLast();
	}

	@Override
	public String toString() {
		return "Client " + clientNo + ", Name: " + name + ", First Name: " + firstName;
	}

	public static void main(String[] args) {
		Client myClient1 = new Client("name1", "firstname1");
		System.out.println(myClient1.toString());

		Client myClient2 = new Client("name2", "firstname2");
		System.out.println(myClient2.toString());
	}

}
