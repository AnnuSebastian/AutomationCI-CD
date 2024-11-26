package RahulShettyAcademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{
		//ExtentReports, ExtentSparkReporter
		
		String path = System.getProperty("user.dir") + "\\Reports\\index.html";
		//ExtentSparkReporter class expects the path in which the report need to be saved.
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//using the object created we can do configurations
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//Creating object for ExtentReports which is the main class
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Annu");
		
		return extent;
	}

}
