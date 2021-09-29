package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;
import utils.excel;

public class HondaDetails extends Base {
	
	
	By nbikes = By.linkText("New Bikes");
	By ubikes = By.linkText("Upcoming Bikes");
	By smanuf = By.id("makeId");
	By lclose = By.id("alternate-login-close");//alternate-login-close
	By viewButton = By.xpath("//span[@class='zw-cmn-loadMore']");
	By BikeNames = By.xpath("//strong[@class='lnk-hvr block of-hid h-height']");
	By BikePrices = By.xpath("//div[@class='b fnt-15']");
	By BikeLaunch = By.xpath("//div[@class='clr-try fnt-14']");
	int count = 0, count1 = 0;

	public void clickUpcomingBikes() // Method to click Upcoming_bikes
	{
		logger = report.createTest("Upcoming Bikes");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(nbikes));
			WebElement w1 = driver.findElement(nbikes);
			Actions act = new Actions(driver);
			act.moveToElement(w1).perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(ubikes));
			driver.findElement(ubikes).click();
			//String str = driver.findElement(By.xpath("(//span[@itemprop='name'])[2]")).getText();/html/body/div[10]/ol/li[2]/span
			String str = driver.findElement(By.xpath("/html/body/div[10]/ol/li[2]/span")).getText();
			if (str.contains("Upcoming Bikes"))
				reportPass("Upcoming bikes has been opened");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void selectManufacturer() // Method to select the Manufacturer
	{
		logger = report.createTest("Honda Manufacturer");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(smanuf));
			WebElement drop = driver.findElement(smanuf);
			Select select = new Select(drop);
			select.selectByValue("53");
			//String str1 = driver.findElement(By.xpath("(//span[@itemprop='name'])[3]")).getText();/html/body/div[10]/ol/li[3]/span
			String str1 = driver.findElement(By.xpath("/html/body/div[10]/ol/li[3]/span")).getText();
			if (str1.contains("Honda Bikes"))
				reportPass("Manufacturer is HONDA");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void closeLoginPopUp() // Method to close the login-popup
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(lclose));
		driver.findElement(lclose).click();
	}

	public void viewMore() // Method to click viewmore
	{
		logger = report.createTest("Accessing View More");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(viewButton));
			WebElement element = driver.findElement(viewButton);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			reportPass("View More is clicked");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void printDetails() throws IOException // Method to print details on the console
	{
		logger = report.createTest("Obtaining bike prices");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(BikeNames));
		List<WebElement> bikeNames = driver.findElements(BikeNames);
		List<WebElement> bikePrices = driver.findElements(BikePrices);
		List<WebElement> bikeLaunch = driver.findElements(BikeLaunch);
		count = bikeNames.size();
		String priceTxt;
		System.out.println("*******************************************************");
		System.out.println("              Upcoming Bike Details:");
		System.out.println("*******************************************************");
		String str;
		excel.writeToExcel("Bike Name", 0, 0);
		excel.writeToExcel("Bike Price", 0, 1);
		excel.writeToExcel("Lauch Date", 0, 2);
		try {
			for (int i = 0; i < count; i++) {
				priceTxt = bikePrices.get(i).getText();
				float price = Float.parseFloat(priceTxt.replaceAll("Rs. ", "").replaceAll(" Lakh", ""));
				if (price < 4) {
					str = bikeNames.get(i).getText() + "\t" + bikePrices.get(i).getText() + "\t"
							+ bikeLaunch.get(i).getText();
					System.out.println(str);
					
					excel.writeToExcel(bikeNames.get(i).getText(), rowNo, 0);
					excel.writeToExcel(bikePrices.get(i).getText(), rowNo, 1);
					excel.writeToExcel(bikeLaunch.get(i).getText().substring(14), rowNo, 2);

					rowNo++;
				}
			}
			reportPass("Bike Prices are Obtained");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
}
