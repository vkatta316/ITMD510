package models;

public class RequestModel {
	private String email ;
	private String amount ;
	public RequestModel(String email,String amount)
	{
		this.email = email;
		this.amount = amount;
	}
	public String GetEmail()
	{
		return email;
	}
	public String GetAmount()
	{
		return amount;
	}
}
