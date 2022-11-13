package records;
/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: Records.java
 * Data analytics from its clients for loan application process.
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Records extends BankRecords {
	static FileWriter fw = null;
	private static String ownerName = "VinayKatta";
	private static String delimiter = "_";
	private static final String dateFormat = "yyyy-MM-dd_HH-mm-ss";

	public Records() {
		try {
			LocalDateTime currentDateTime = LocalDateTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			String formattedDateTime = currentDateTime.format(formatter);

			String fileName = "bankrecords" + delimiter + ownerName + delimiter + formattedDateTime + ".txt";

			fw = new FileWriter(fileName);
			fw.write("Data analytic results: ");
			fw.write("\n\n");

		} catch (IOException e) {
			System.out.println("IO Exception while Writing to the file : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unknown Exception while writing to the file : " + e.getMessage());
		}
	}

	/*
	 * Calculate average income per gender
	 */
	public static void averageIncomePerGender() {
		Arrays.sort(brRecords, new SexComparator(1));

		// variables to gather number & to determine average income by sex
		int maleCt = 0, femCt = 0;
		double maleInc = 0, femInc = 0;

		for (BankRecords brRecord : brRecords) {
			if (brRecord.getSex().equals("FEMALE")) {
				femCt++;
				femInc += brRecord.getIncome();
			} else {
				maleCt++;
				maleInc += brRecord.getIncome();
			}
		}

		// Display resulting averages to console and to file
		System.out.printf("Average income for Females: $%.2f", (femInc / femCt));
		System.out.printf("\nAverage income for Males: $%.2f\n", (maleInc / maleCt));

	}

	/*
	 * Calculate Number of females with a mortgage and savings account
	 */
	public static void femalesWithMortgageAndSavingsAccount() {
		Arrays.sort(brRecords, new MortgageComparator(-1));
		Arrays.sort(brRecords, new SavingAccountComparator(-1));
		Arrays.sort(brRecords, new SexComparator(1));
		// variables for count
		int femCt = 0;

		for (BankRecords brRecord : brRecords) {
			if (!brRecord.getHasMortagae()) {
				break;
			}
			femCt++;
		}

		// Display result to console and to file
		System.out.printf("\nNumber of females with a mortgage and savings account: %d\n", femCt);

	}

	/*
	 * Calculate Males with both a car and children per location
	 */
	public static void malesWithCarAndChildPerLocation() {
		Arrays.sort(brRecords, new LocationComparator(1));
		Arrays.sort(brRecords, new CarComparator(-1));
		Arrays.sort(brRecords, new SexComparator(-1));

		Integer maleCt = 0;
		Map<String, Integer> locationVsMaleCount = new HashMap<String, Integer>();

		for (BankRecords br : brRecords) {
			if (!br.getHasCar()) {
				break;
			} else if (br.getNoOfChildren() == 1) {
				if (locationVsMaleCount.containsKey(br.getRegion())) {
					maleCt = locationVsMaleCount.get(br.getRegion());
					maleCt += 1;
				} else {
					maleCt = 1;
				}
				locationVsMaleCount.put(br.getRegion(), maleCt);
			}
		}

		// Display result to console and to file
		for (String loc : locationVsMaleCount.keySet()) {
			System.out.printf("\n%s males with both a car & 1 child per location: %d",
					loc.substring(0, 1).toUpperCase() + loc.substring(1).toLowerCase(), locationVsMaleCount.get(loc));
		}
	}

	public static void main(String[] args) {
		Records bankRecords = new Records();
		bankRecords.readData();
		
		// call methods to perform data analytics
		averageIncomePerGender();
		femalesWithMortgageAndSavingsAccount();
		malesWithCarAndChildPerLocation();

		// *** close out file object ***//
		try {
			fw.close();
		} catch (IOException e) {
			System.out.println("IO Exception while closing the file : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unknown exception while closing the file: " + e.getMessage());
		}
	}

}