package TestSuite;

import org.testng.annotations.Test;

import Pages.HomePage;


public class SmokeTest 
{
	
	public void testing()
	{
		HomePage hd= new HomePage();
		hd.openUrl();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
	}

}
