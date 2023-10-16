package com.qa.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportUtility {
	
	public static ExtentReports createExtentReports()
	{
		String path =System.getProperty("user.dir")+"\\extent-reports\\extent-report.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);

		return extent;
	}
}