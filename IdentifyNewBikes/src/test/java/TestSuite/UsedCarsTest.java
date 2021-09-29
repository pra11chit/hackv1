package TestSuite;

import org.testng.annotations.Test;


import Pages.ChennaiUsedCars;


public class UsedCarsTest 
{
	public void testingCars()
	{
		ChennaiUsedCars cu = new ChennaiUsedCars();
		cu.openUrl();
		cu.clickUsedCars();
		cu.clickPopularModels();
	}

}
