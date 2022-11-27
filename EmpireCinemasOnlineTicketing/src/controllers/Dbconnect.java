package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Moviemodel;
import models.RequestModel;

public class Dbconnect {
	   static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
	   static final String USER = "db510";
	   static final String PASS = "510";
	   public static Connection conn = null;
	   private Dbconnect()
	   {	
	   }
	   public static Connection getconnection()
	   {
		   return conn;
	   }
	   private static void createcon(){
		   try
	       {	 conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         // Extract data from result set
	        	  
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
	   public static ResultSet QueryForResult(String q) throws SQLException
	   {	createcon();
		   	Statement st = conn.createStatement();
	        ResultSet rs= st.executeQuery(q);
	        return rs;
	   }
	   public static void RunQuery(String q) throws SQLException
	   {	createcon();
	   		Statement st = conn.createStatement();
	   		st.executeUpdate(q);
	   }
	   public static List<Moviemodel> GetAllMovies() throws SQLException
	   {
		   createcon();
		   Statement st = conn.createStatement();
		   ResultSet rs = st.executeQuery("Select * from movies_list");
		   List<Moviemodel> ls = new ArrayList<>();
		   while(rs.next())
		   {
			   Moviemodel mvm = new Moviemodel();
			   mvm.settitle(rs.getString("Movie"));
			   mvm.setprice(rs.getString("Ticket_Price"));
			   mvm.setimgsrc(rs.getString("Movie")+".jpg");
			   //mvm.setimgsrc("\\Users\\vinaychowdarykatta\\Desktop\\"+rs.getString("Movie")+".jpg");
			   ls.add(mvm);
		   }
		   rs.close();
		   st.close();
		   conn.close();
		   return ls;
	   }

	   public static List<RequestModel> GetAllRequest() throws SQLException
	   {
		   createcon();
		   Statement st = conn.createStatement();
		   ResultSet rs = st.executeQuery("Select * from RequestTable");
		   List<RequestModel> ls = new ArrayList<>();
		   while(rs.next())
		   {
			   RequestModel rm = new RequestModel(rs.getString("Email"),rs.getString("Amount"));
			   ls.add(rm);
		   }
		   rs.close();
		   st.close();
		   conn.close();
		   return ls;
	   }
	}
