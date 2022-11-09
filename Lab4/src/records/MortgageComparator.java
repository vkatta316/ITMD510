package records;

/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: BankRecords.java
 * Comparator class to sort by Mortgage
*/

import java.util.Comparator;

public class MortgageComparator implements Comparator<BankRecords> {
	public int order;

	MortgageComparator(int order) {
		this.order = order;
	}

	@Override
	public int compare(BankRecords obj1, BankRecords obj2) {
		int res = obj1.getHasMortagae().compareTo(obj2.getHasMortagae());
		return res * order;
	}

}
