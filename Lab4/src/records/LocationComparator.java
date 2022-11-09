package records;

/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: BankRecords.java
 * Comparator class to sort by Location
*/

import java.util.Comparator;

public class LocationComparator implements Comparator<BankRecords> {
	public int order;

	LocationComparator(int order) {
		this.order = order;
	}

	@Override
	public int compare(BankRecords obj1, BankRecords obj2) {
		int res = obj1.getRegion().compareTo(obj2.getRegion());
		return res * order;
	}
}
