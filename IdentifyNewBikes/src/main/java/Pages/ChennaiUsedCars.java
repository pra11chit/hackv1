package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverSetup.DriverSetup;
import utils.ExtentReportManager;
import utils.excel;

public class ChennaiUsedCars extends DriverSetup
{
	By ucars=By.linkText("Used Cars");
	By chennai=By.linkText("Chennai");
	By lclose=By.id("alternate-login-close");
	By popularmodels=By.xpath("//ul[contains(@class,'usedCarMakeModelList')]");
	By list=By.tagName("li");
	
	public void clickUsedCars()  // Method to click used_cars
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Used Cars and Popular Model");
		try{
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ucars));
			WebElement w1=driver.findElement(ucars);
		Actions act=new Actions(driver);
		act.moveToElement(w1).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(chennai));
		driver.findElement(chennai).click();
		String usedCars = driver.findElement(By.xpath("//h1[@id='usedcarttlID']")).getText();
		if (usedCars.contains("Used Cars in Chennai")) 
			ExtentReportManager.reportPass("Used Cars in chennai are displayed");
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
		
	}
	public void clickPopularModels() // Method to click popular_models
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Obtaining Popular Models");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(popularmodels));
		WebElement w1=driver.findElement(popularmodels);
		System.out.println("*******************************************************");
		System.out.println("            Popular Used Cars in Chennai:");
		System.out.println("*******************************************************");
		wait.until(ExpectedConditions.visibilityOfElementLocated(list));
		List<WebElement> ls= w1.findElements(list);
		excel.writeToExcel("Popular Used Cars", 0, 3);
		for(int i=0;i<10;i++)
		{
			System.out.println(ls.get(i).getText());
			excel.writeToExcel(ls.get(i).getText(), i + 1, 3);
		}
		ExtentReportManager.reportPass("Popular models are printed");
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
	}


}
