package com.itlearn.utility;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class iTestListenerClass  implements ITestListener{
	ExtentSparkReporter htmlReporter ;
	ExtentReports reports;
	ExtentTest test;
	
	public void configureReport(){
		htmlReporter=new ExtentSparkReporter("ExtentListenerReportDemo1.html");
		reports =new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add system information and enviormnet info to reports
		reports.setSystemInfo("Machine","raviPc");
		reports.setSystemInfo("OS","Windows10");
		
		htmlReporter.config().setDocumentTitle("Extent Listner Report demo");
		htmlReporter.config().setReportName("This is my first report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
	}
	
//start method of Listener
	
	public void onTestStart(ITestResult result) {
		
	}
	
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of the method successfully executed " +result.getName());
		test =reports.createTest(result.getName());
		test.log(Status.PASS,MarkupHelper.createLabel("Name of the skip test is : "+ result.getName(),ExtentColor.GREEN));
		
		
		}
	
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of the method failed : " + result.getName());
		test=reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the skip test is : "+ result.getName(),ExtentColor.RED));
		String screenshotPath=System.getProperty("usre.dir")+"\\Screenshot"+result.getName()+ ".png";
		File screenshotFile=new File(screenshotPath);
		
		if(screenshotFile.exists()) {
			test.fail("Captured Screenshot is below : "+ test.addScreenCaptureFromPath(screenshotPath));
			
		}
	}
		
		
		
		
		public void onTestSkipped(ITestResult result) {
			
			System.out.println("Name of test methid skipped: "+ result.getName());
			test=reports.createTest(result.getName());
			test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test is : "+ result.getName(),ExtentColor.YELLOW));
		}
		
	
	
	public void onTestFailedButwithinSuccessPercentage(ITestResult result) {
		
	}
	
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
		
	}
	
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("on start method invoked....");
		
	}
	
	
	
	public void onFinish (ITestContext context) {
		System.out.println("on Finish Method invoked....");
		reports.flush();// it is mandatory to call flush method to ensure information is written ti the started reporter.
	}
	
	
	
	
	
}
