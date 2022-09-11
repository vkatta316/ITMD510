/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: AccountHolderTest.java
 * Main class to execute different transactions
 */
package lab1;

import java.util.Scanner;

public class AccountHolderTest {

	public static void main(String args[]) {
		double initialBalance;
		double depositAmount;
		double withdrawAmount = 0;
		AccountHolder.annualInterestRate = 4.0;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your initial balance");
		initialBalance = AccountHolder.userInputValidation("Initial Balance", input);

		AccountHolder accHolder = new AccountHolder(initialBalance);

		System.out.println("Please enter the deposit amount");
		depositAmount = AccountHolder.userInputValidation("Deposit", input);
		accHolder.deposit(depositAmount);

		Scanner in = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("Please enter the amount to withdraw");
				withdrawAmount = AccountHolder.userInputValidation("Withdraw", input);
				accHolder.withdraw(withdrawAmount);
				break;
			} catch (UnsupportedOperationException e) {
				System.out.println("Please press ENTER/RETURN to continue or any KEY to abort");
				String n = in.nextLine();
				if (n.length() != 0) {
					break;
				}
			}
		}
		accHolder.monthlyInterest();
		System.out.printf("\nBalance with interest applied = $%.2f", accHolder.balance);
		in.close();
		input.close();
	}
}