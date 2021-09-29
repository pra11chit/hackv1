package TestSuite;

import java.io.IOException;


import org.testng.annotations.Test;


import Pages.HondaDetails;


public class BikesTest
{
	
	public void testing() throws InterruptedException, IOException
	{
		HondaDetails hd= new HondaDetails();   
		hd.openUrl();
		//hd.closeLoginPopUp();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
		hd.viewMore();
		hd.printDetails();
	}
}
