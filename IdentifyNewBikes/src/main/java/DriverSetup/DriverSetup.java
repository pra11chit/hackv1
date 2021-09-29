package DriverSetup;



import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import utils.ExtentReportManager;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties prop;

//	public ExtentReports report = ExtentReportManager.getReportInstance();
//	public ExtentTest logger;

	public static int rowNo = 1;

	public void driverSetup() {
		if (prop == null) {
			prop = new Properties();

			try {
				prop.load(new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties")); // Loading
																											// the
																											// properties
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (prop.getProperty("browserName").matches("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");
			driver = new ChromeDriver(ops); // Initializing the new chrome driver
		}
		if (prop.getProperty("browserName").matches("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			driver = new FirefoxDriver(); // Initializing the new firefox driver
		}
		if (prop.getProperty("browserName").matches("msedge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize(); // To maximize the window
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // Waiting time to page the load completely
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Adding driver waits to timeouts

	}

	public void openUrl() // Method to open URL for smoke test
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Opening Url");
		try {
			String s = prop.getProperty("url");
			driver.get(s);

			ExtentReportManager.reportPass("URL opened, URL is :" + prop.getProperty("url"));
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReportManager.reportFail(e.getMessage());
			e.printStackTrace();
		}
	}

//	public void reportFail(String report) {
//		logger.log(Status.FAIL, report);
//		takeScreenShotOnFailure();
//	}
//
//	// Function to show the passed test cases in the report
//	public void reportPass(String report) {
//		logger.log(Status.PASS, report);
//	}

//	public void Screenshoot(String fileName) throws IOException {
//		TakesScreenshot capture = (TakesScreenshot) driver;
//		File srcFile = capture.getScreenshotAs(OutputType.FILE);
//		File destFile = new File(System.getProperty("user.dir") + "//Screenshot//" + fileName + ".png");
//		Files.copy(srcFile, destFile);
//	}
//
//	// To take Screenshot when test gets failed
//	public void takeScreenShotOnFailure() {
//
//		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
//		File src = takeScreenshot.getScreenshotAs(OutputType.FILE);
//		File dest = new File(System.getProperty("user.dir") + "//Screenshot//FailedCases//Screenshot.png");
//		try {
//			FileUtils.copyFile(src, dest);
//			logger.addScreenCaptureFromPath(
//					System.getProperty("user.dir") + "//Screenshot//FailedCases//Screenshot.png");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void closeBrowser() // method to close the browser
	{
		driver.quit(); // To close the browser
		ExtentReportManager.report.flush(); // To save the reports
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (Exception e) {
		}
	}
}
