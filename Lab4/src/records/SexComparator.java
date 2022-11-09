package records;
/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: BankRecords.java
 * Comparator class to sort by Sex
*/

import java.util.Comparator;

public class SexComparator implements Comparator<BankRecords>{
	public int order;
	
	SexComparator(int order){
		this.order = order;
	}

	@Override
	public int compare(BankRecords obj1, BankRecords obj2) {
		int res = obj1.getSex().compareTo(obj2.getSex());
		
		return res*order;
	}
	
}