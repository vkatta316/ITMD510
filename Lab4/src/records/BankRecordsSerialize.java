package records;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class BankRecordsSerialize {
	
	BankRecords br = null;

	public void serialize() {
		Map<Integer, BankRecords> bankRecords = new TreeMap<Integer, BankRecords>();
		bankRecords.put(1, br);
		try {
			FileOutputStream fos = new FileOutputStream("bankrecords.ser");

			ObjectOutputStream fout = new ObjectOutputStream(fos);

			fout.writeObject(bankRecords);
			fout.close();
			fos.close();

		} catch (IOException io) {
			io.printStackTrace();
			// TODO: handle exception
		}
	}

	public void deserialize() {
		try {
			FileInputStream fis = new FileInputStream("bankrecords.ser");

			ObjectInputStream fInput = new ObjectInputStream(fis);
			br= (BankRecords) fInput.readObject();
			
			fInput.close();
			fis.close();

		} catch (IOException io) {
			io.printStackTrace();
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Serialized data is retrieved from Bank Records");
		 System.out.println("Name: " + br.getIncome());
	     
	}

}
