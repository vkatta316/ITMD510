/**
 * @author Katta Vinay Chowdary
 *
 * Student ID: A20519089
 */
package lab1;

import java.util.Scanner;

public class AccountHolderTest {

	public static void main(String args[]) {
		double initialBalance;
		double depositAmount;

		Scanner input = new Scanner(System.in);
		AccountHolder.annualInterestRate = 4.0;

		System.out.println("Please enter your initial balance");
		initialBalance = AccountHolder.userInputValidation("Initial Balance", input);

		AccountHolder accHolder = new AccountHolder(initialBalance);

		System.out.println("Please enter the deposit amount");

		depositAmount = AccountHolder.userInputValidation("Deposit", input);
		accHolder.deposit(depositAmount);

		double withdrawAmount = 0;
		Scanner in = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("Please enter the amount to withdraw");
				withdrawAmount = AccountHolder.userInputValidation("Withdraw", input);
				accHolder.withdraw(withdrawAmount);
				break;
			} catch (UnsupportedOperationException e) {
				System.out
						.println("If you want to continue, please press 'Enter' key else enter any other key to Exit");
				String n = in.nextLine();
				if (n.length() != 0) {
					break;
				}
			}
		}
		accHolder.monthlyInterest();
		System.out.printf("Balance with interest applied = $%.2f \n", accHolder.balance);
		in.close();
		input.close();
	}
}