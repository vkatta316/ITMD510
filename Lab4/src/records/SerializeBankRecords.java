package records;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class SerializeBankRecords {

	public static void deserialize() {
		BankRecords br = new BankRecords();
		try {
			FileInputStream fileIn = new FileInputStream("banksrecords.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			br = (BankRecords) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Bank Records class not found");
			c.printStackTrace();
			return;
		}

		
		System.out.println("\nDeserialized Bank Records...");
		br.readData();
		// br.printData();
	}

	public static void serialize() {

		BankRecords br = new BankRecords();

		try {

			Map<Integer, BankRecords> bankRecords = new TreeMap<Integer, BankRecords>();
			bankRecords.put(1, br);

			FileOutputStream fileOut = new FileOutputStream("banksrecords.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(br);

			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved as banksrecords.ser \n");

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void main(String[] args) {
		serialize();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nTime difference between serialize and Deserialize Bank Records Objects is 5 seconds");
		deserialize();
	}
}
