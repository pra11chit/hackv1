package utils;

import org.openqa.selenium.JavascriptExecutor; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/*
 * Highlighter class to highlight the selected elements
 */
public class Highlighter{

   public void highlightElement(WebDriver driver,WebElement element) 
   { 
	  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
      jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", element);
   }

   public void removeHighlight(WebDriver driver,WebElement element) {
		  
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
   }
}
