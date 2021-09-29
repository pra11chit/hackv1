package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class HomePage extends Base 
{
		By nbikes = By.linkText("New Bikes");
		By ubikes = By.linkText("Upcoming Bikes");
		By smanuf = By.id("makeId");
		
		public void clickUpcomingBikes() // Method to click Upcoming_Bikes
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
			String str = driver.findElement(By.xpath("/html/body/div[10]/ol/li[2]/span")).getText();
			if (str.contains("Upcoming Bikes"))
				reportPass("Upcoming bikes has been opened");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
			
		}

		public void selectManufacturer() //Method to select Manufacturer
		{   
			logger = report.createTest("Honda Manufacturer");
			try {
			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.visibilityOfElementLocated(smanuf));
			WebElement drop = driver.findElement(smanuf);
			Select select = new Select(drop);
			select.selectByValue("53");
			String str1 = driver.findElement(By.xpath("/html/body/div[10]/ol/li[3]/span")).getText();
			if (str1.contains("Honda Bikes"))
				reportPass("Manufacturer is HONDA");
			} catch (Exception e) {
				reportFail(e.getMessage());
			}
		}
	}

