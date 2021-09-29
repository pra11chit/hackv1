package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import DriverSetup.DriverSetup;



public class ExtentReportManager extends DriverSetup{
	public static ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger= report.createTest("Opening Url");
public static ExtentReports getReportInstance(){
		
		if(report== null){
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Report/Report.html");
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "10.8.1");
			report.setSystemInfo("Browser", "Chrome");
			
			htmlReporter.config().setDocumentTitle("UI Automation Results");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}
		
		return report;
	}

	public static void reportFail(String report) {
		logger.log(Status.FAIL, report);
		Screenshot.takeScreenShotOnFailure(driver);
	}
	
	// Function to show the passed test cases in the report
	public static void reportPass(String report) {
		logger.log(Status.PASS, report);
	}
	
	public static void reportInfo(String report) {
		logger.log(Status.INFO, report);
	}

}
