package records;

/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: BankRecords.java
 * Read the data from file and print first 25 bank details records in columnar format
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankRecords extends Client {
	private String id;
	private int age;
	private String sex;
	private String region;
	private double income;
	private Boolean isMarried;
	private int noOfChildren;
	private Boolean hasCar;
	private Boolean saveAct;
	private Boolean currentAct;
	private Boolean hasMortagae;
	private String pep;

	static String TRUE_VALUE = "YES";
	protected static BankRecords brRecords[];
	static ArrayList<List<String>> recordData = new ArrayList<List<String>>();
	private static int status = 1;

	/**
	 * Reads the data from the Bank details CSV File
	 */
	public void readData() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("bank-Detail.csv")));
			String line;
			while ((line = br.readLine()) != null) {
				recordData.add(Arrays.asList(line.split(",")));
			}
		} catch (FileNotFoundException e) {
			status = 0;
			System.out.println("Unable to load the file \n Error Message: " + e.getMessage());
		} catch (IOException io) {
			System.out.println("File reading error \n Error Message: " + io.getMessage());
		}
		if (status != 0) {
			processData();
		}
	}

	/**
	 * Stores the data into instance variables
	 */
	public void processData() {
		int counter = 0;
		brRecords = new BankRecords[recordData.size()];
		for (List<String> record : recordData) {
			if (counter == recordData.size()) {
				break;
			}
			try {
				brRecords[counter] = new BankRecords();
				brRecords[counter].setId(record.get(0));
				brRecords[counter].setAge(Integer.parseInt(record.get(1)));
				brRecords[counter].setSex(record.get(2));
				brRecords[counter].setRegion(record.get(3));
				brRecords[counter].setIncome(Double.parseDouble(record.get(4)));
				brRecords[counter].setIsMarried(record.get(5).equals(TRUE_VALUE) ? true : false);
				brRecords[counter].setNoOfChildren(Integer.parseInt(record.get(6)));
				brRecords[counter].setHasCar(record.get(7).equals(TRUE_VALUE) ? true : false);
				brRecords[counter].setSaveAct(record.get(8).equals(TRUE_VALUE) ? true : false);
				brRecords[counter].setCurrentAct(record.get(9).equals(TRUE_VALUE) ? true : false);
				brRecords[counter].setHasMortagae(record.get(10).equals(TRUE_VALUE) ? true : false);
				brRecords[counter].setPep(record.get(11));
			} catch (ArrayIndexOutOfBoundsException aie) {
				status = 0;
				System.out.println("Error while processing the data \n Error Message: " + aie.getMessage());
			}
			counter += 1;
		}
	}

	/**
	 * Prints the first 25 records of Bank details file into columnar format
	 */
	public void printData() {
		int count = 1;
		System.out.printf("--------------------------------------------------------------------------%n");
		System.out.printf("| %-4s | %-8s | %-4s | %-8s | %-10s | %-10s | %-8s |%n", "S.No", "ID", "AGE", "SEX",
				"REGION", "INCOME", "MORTGAGE");
		System.out.printf("--------------------------------------------------------------------------%n");
		try {
			for (BankRecords br : brRecords) {
				System.out.printf("| %4d | %-8s | %-4s | %-8s | %-10s | %-10.02f | %-8s |%n", count, br.getId(),
						br.getAge(), br.getSex(), br.getRegion(), br.getIncome(), br.getHasMortagae());
				count += 1;
			}
			System.out.printf("--------------------------------------------------------------------------%n");
		} catch (Exception e) {
			System.out.println("Error while printing the data \n Error Message: " + e.getMessage());
		}
	}

	// Getters & Setters
	// Getters & Setters
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the income
	 */
	public double getIncome() {
		return income;
	}

	/**
	 * @param income the income to set
	 */
	public void setIncome(double income) {
		this.income = income;
	}

	/**
	 * @return the isMarried
	 */
	public Boolean getIsMarried() {
		return isMarried;
	}

	/**
	 * @param isMarried the isMarried to set
	 */
	public void setIsMarried(Boolean isMarried) {
		this.isMarried = isMarried;
	}

	/**
	 * @return the noOfChildren
	 */
	public int getNoOfChildren() {
		return noOfChildren;
	}

	/**
	 * @param noOfChildren the noOfChildren to set
	 */
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	/**
	 * @return the hasCar
	 */
	public Boolean getHasCar() {
		return hasCar;
	}

	/**
	 * @param hasCar the hasCar to set
	 */
	public void setHasCar(Boolean hasCar) {
		this.hasCar = hasCar;
	}

	/**
	 * @return the saveAct
	 */
	public Boolean getSaveAct() {
		return saveAct;
	}

	/**
	 * @param saveAct the saveAct to set
	 */
	public void setSaveAct(Boolean saveAct) {
		this.saveAct = saveAct;
	}

	/**
	 * @return the currentAct
	 */
	public Boolean getCurrentAct() {
		return currentAct;
	}

	/**
	 * @param currentAct the currentAct to set
	 */
	public void setCurrentAct(Boolean currentAct) {
		this.currentAct = currentAct;
	}

	/**
	 * @return the hasMortagae
	 */
	public Boolean getHasMortagae() {
		return hasMortagae;
	}

	/**
	 * @param hasMortagae the hasMortagae to set
	 */
	public void setHasMortagae(Boolean hasMortagae) {
		this.hasMortagae = hasMortagae;
	}

	/**
	 * @return the pep
	 */
	public String getPep() {
		return pep;
	}

	/**
	 * @param pep the pep to set
	 */
	public void setPep(String pep) {
		this.pep = pep;
	}

}
