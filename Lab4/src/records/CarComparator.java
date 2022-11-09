package records;
/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: BankRecords.java
 * Comparator class to sort by Car
*/

import java.util.Comparator;

public class CarComparator implements Comparator<BankRecords>{
	
	public int order;
	
	CarComparator(int order){
		this.order = order;
	}
	
	@Override
	public int compare(BankRecords obj1, BankRecords obj2) {
		int res = obj1.getHasCar().compareTo(obj2.getHasCar());	
		return res*order;
	}
}
