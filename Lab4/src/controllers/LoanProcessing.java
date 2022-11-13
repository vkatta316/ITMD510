package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.DaoModel;
import records.BankRecords;
import records.Records;
import views.LoanView;

public class LoanProcessing extends BankRecords {
	
	
	public static void main(String[] args) throws SQLException {
		BankRecords br = new BankRecords();
		br.readData();
		DaoModel dao = new DaoModel();
		//dao.createTable();
		//dao.insertRecords(brRecords); // perform inserts
		ResultSet rs;
		rs = dao.retrieveRecords();
		new LoanView().runView(rs);
		
		// analyzing the data
		System.out.println("**********Loan Analysis Report**********");
		Records.averageIncomePerGender();
		Records.femalesWithMortgageAndSavingsAccount();
		Records.malesWithCarAndChildPerLocation();
	}

}
