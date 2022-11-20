package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserModel {
	public static String myHashFunc(String password)
	{
		 try {
	            // getInstance() method is called with algorithm SHA-1
	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	 
	            // digest() method is called
	            // to calculate message digest of the input string
	            // returned as array of byte
	            byte[] messageDigest = md.digest(password.getBytes());
	 
	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);
	 
	            // Convert message digest into hex value
	            String hashtext = no.toString(16);
	 
	            // Add preceding 0s to make it 32 bit
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	 
	            // return the HashText
	            return hashtext;
	        }
	 
	        // For specifying wrong message digest algorithms
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	}
	public static String Email;
	public static String Password;
	public static String FirstName;
	public static String LastName;
	public static String Balance = "10";
	public UserModel(String Email,String Password,String FirstName,String LastName)
	{
		UserModel.Email = Email;
		UserModel.Password = myHashFunc(Password);
		UserModel.FirstName = FirstName;
		UserModel.LastName = LastName;
	}
	public UserModel(String Email,String Password)
	{
		UserModel.Email = Email;
		UserModel.Password = myHashFunc(Password);
		System.out.println(UserModel.Email+UserModel.Password);
	}
	public boolean loginstatus()
	{
		return true;
	}
}
