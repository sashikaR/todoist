package com.todolist.api.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.log4j.Log4j2;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
;import java.lang.reflect.Method;
import java.util.Collection;

@Log4j2
public class TestBase {
    private int passed = 0;
    private int failed = 0;
    private int skipped = 0;
    private ExtentTest extentTest;
    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extentReports;

    @BeforeSuite(alwaysRun = true)
    public void setExtent() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/ToDoList-API-TestReport.html");

        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Automation Test Report of ToDoList API");
        htmlReporter.config().setReportName("ToDoList API Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

    }

    @BeforeMethod(alwaysRun = true)
    public void initMethod(Method method) {
        log.info("Test Details : " + method.getName());
    }

    @AfterSuite(alwaysRun = true)
    public void getResult(ITestContext context) {
        /* Generating the report */
        printSuiteResults(context.getSuite());
        extentReports.flush();
    }


    private void printSuiteResults(ISuite suite) {
        Collection<ISuiteResult> suiteResults = suite.getResults().values();
        for (ISuiteResult suiteResult : suiteResults) {
            printAllResults1(suiteResult.getTestContext());
        }
    }

    private void printAllResults1(ITestContext context) {
        printAllResults(context.getPassedTests().getAllResults());
        printAllResults(context.getFailedTests().getAllResults());
        printAllResults(context.getSkippedTests().getAllResults());
    }


    private void printAllResults(Collection<ITestResult> results) {
        for (ITestResult result : results) {

            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest = extentReports.createTest(result.getName(), result.getMethod().getDescription());
                extentTest.log(Status.FAIL, "Test Case FAILED is : " + result.getMethod().getDescription());
                extentTest.fail("Test Failure Reason is : " + result.getThrowable());
                extentTest.info("Test Priority is  : " + result.getMethod().getPriority());
                failed++;


            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest = extentReports.createTest(result.getName(), result.getMethod().getDescription());
                extentTest.log(Status.SKIP, "Test Case SKIPPED is " + result.getMethod().getDescription());
                extentTest.info("Test Method  is       " + result.getMethod().getMethodName());
                extentTest.info("Test Priority is      " + result.getMethod().getPriority());
                skipped++;

            } else if (result.getStatus() == ITestResult.SUCCESS) {

                extentTest = extentReports.createTest(result.getName(), result.getMethod().getDescription());
                extentTest.log(Status.PASS, "Test Case Passed is " + result.getMethod().getDescription());
                extentTest.info("Test Method  is       " + result.getMethod().getMethodName());
                extentTest.info("Test Priority is      " + result.getMethod().getPriority());
                passed++;

            }
        }
    }
}
