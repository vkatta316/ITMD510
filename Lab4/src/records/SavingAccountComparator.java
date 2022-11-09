package records;
/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: BankRecords.java
 * Comparator class to sort by Account
*/

import java.util.Comparator;

public class SavingAccountComparator implements Comparator<BankRecords>{
	public int order;
	
	SavingAccountComparator(int order){
		this.order = order;
	}
	
	@Override
	public int compare(BankRecords obj1, BankRecords obj2) {
		int res = obj1.getSaveAct().compareTo(obj2.getSaveAct());
		
		return res*order;
	}

}
