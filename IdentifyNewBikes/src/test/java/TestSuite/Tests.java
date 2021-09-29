package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import Pages.ChennaiUsedCars;
import Pages.HomePage;
import Pages.HondaDetails;
import Pages.LoginPage;
import Base.Base;

public class Tests {
	Base bs = new Base();
	
	@Test(priority=0)
	public void basic() {
		
		bs.driverSetup();
		bs.openUrl();
	}
	
	@Test(priority=1)
	public void testing()
	{
		HomePage hd= new HomePage();
		//hd.openUrl();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
	}
	
	@Test(priority=2)
	public void testing1() throws InterruptedException, IOException
	{
		HondaDetails hd= new HondaDetails();   
		//hd.openUrl();
		//hd.closeLoginPopUp();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
		hd.viewMore();
		hd.printDetails();
	}
	
	@Test(priority=3)
	public void testingCars()
	{
		ChennaiUsedCars cu = new ChennaiUsedCars();
		//cu.openUrl();
		cu.clickUsedCars();
		cu.clickPopularModels();
	}
	
	@Test(priority=4)
	public void  testingLogin() throws InterruptedException
	{
		LoginPage l= new LoginPage();
		l.openUrl();
		l.clickLogin();
		l.clickGoogleSignIn();
		l.captureErrorMessage();	
	}
	
	@Test(priority=5)
	public void lastStep() {
		bs.closeBrowser();
	}
}
