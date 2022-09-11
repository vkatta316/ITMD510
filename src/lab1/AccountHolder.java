/**
 * @author Katta Vinay Chowdary
 * Student ID: A20519089
 * File Name: AccountHolder.java
 * Program that performs various bank transactions
 */
package lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountHolder {

	static double annualInterestRate;
	double balance;

	AccountHolder(double initialBalance) {
		if (initialBalance < 0) {
			throw new IllegalArgumentException("Account can't have a negative balance");
		}
		this.balance = initialBalance;
	}

	/**
	 * Increase the account holder’s current balance on every deposit.
	 * 
	 * @param amount Deposit amount
	 */
	public void deposit(double amount) {
		if (!validate(amount)) {
			throw new IllegalArgumentException("Deposit amount can't be negative");
		}
		balance += amount;
	}

	/**
	 * Decrease the account holder’s current balance and validate accounts minimum maintaining balance on withdrawal.
	 * 
	 * @param amount withdrawal amount
	 */
	public void withdraw(double amount) {
		if (!validate(amount)) {
			throw new IllegalArgumentException("Withdraw amount can't be negative");
		}
		if (balance == 0 || amount > balance) {
			System.out.println("Insufficient funds to withdraw, please provide a lower amount");
			throw new UnsupportedOperationException();
		} else if ((balance - amount) < 50) {
			System.out.println(
					"Withdrawal not allowed, as $50.00 is the minimum balance to be maintained in the account");
			throw new UnsupportedOperationException();
		}

		balance -= amount;
	}

	/**
	 * Calculate interest amount that bank pays into your account monthly based on the balance
	 */
	public void monthlyInterest() {
		if (annualInterestRate < 0) {
			throw new IllegalArgumentException("Interest rate cannot be negative");
		}
		this.balance += computeInterest(annualInterestRate, balance);
	}

	/**
	 * Interest Computation Utility
	 * 
	 * @param rate   Rate of interest
	 * @param amount Balance of the account holder
	 * @return Total ending balance, including added monthly interest
	 */
	public double computeInterest(double rate, double amount) {
		return amount * (rate / (12.0 * 100));
	}


	// Utility methods to validate negative inputs and in-valid keys
	/**
	 * 
	 * @param amount Account holders initial, deposit and withdraw amount
	 * @return true when amount is positive value
	 */
	public Boolean validate(double amount) {
		if (amount < 0) {
			return false;
		}
		return true;
	}

	/**
	 * @param value is either Initial Balance, Deposit or Withdraw Text
	 * @param in Scanner class object
	 * @return amount of Account holders
	 */
	public static double userInputValidation(String value, Scanner in) {
		double amount = 0;
		while (true) {
			try {
				amount = in.nextDouble();
				if (amount < 0) {
					System.out.printf("%s amount can't be negative, please re-enter the value \n", value);
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Entry. Please re-enter your " + value + "");
				in.nextLine();
			}

		}
		return amount;
	}
}