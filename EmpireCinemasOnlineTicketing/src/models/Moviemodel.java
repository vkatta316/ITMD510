package models;

public class Moviemodel {
	public static int Ticketprice = 0;
	private String title;
	private String price;
	private String imgsrc;
	public void setimgsrc(String src)
	{
		imgsrc = src;
	}
	public String getimgsrc()
	{
		return imgsrc;
	}
	public void settitle(String t)
	{
		title = t;
	}
	public String gettitle()
	{
		return title;
	}
	public void setprice(String p)
	{
		price = p;
	}
	public String getprice()
	{
		return price;
	}
}
