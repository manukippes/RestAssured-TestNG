package com.kimatesting.qa.tests;

import com.kimatesting.qa.ApiManager;
import com.kimatesting.qa.enums.Env;
import com.kimatesting.qa.utils.ConfigLoader;
import com.kimatesting.qa.utils.ExtentReportHelper;
import com.kimatesting.qa.utils.LoggerHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Optional;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        String env = Optional.ofNullable(System.getProperty("env")).orElse(String.valueOf(Env.QA.get()));
        ConfigLoader.loadFile(env);
        LoggerHelper.createLogger();
        ApiManager.setApiManager(ConfigLoader.getProperty("petShopUrl"));
        ExtentReportHelper.createReport("Regression Test", "Pet API");
        LoggerHelper.log.info("Start Test: "+context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportHelper.closeReport();
        LoggerHelper.log.info("Finish Test: "+context.getName());
    }

    @Override		
    public void onTestStart(ITestResult result) {
        String testClassName = result.getMethod().getTestClass().getXmlClass().getName().replaceAll("com.kimatesting.qa.tests.","").replace("Test","").replace(".","-").toUpperCase();
        ExtentReportHelper.createTest(testClassName, result.getMethod().getDescription(), result.getMethod().getGroups());
        LoggerHelper.log.info("Starting test "+result.getName());
    }
    
    @Override		
    public void onTestFailedWithTimeout(ITestResult result) {
        ExtentReportHelper.addFailedTest(result.getMethod().getMethodName(), result.getThrowable());
        LoggerHelper.log.info("Timeout error: "+result.getThrowable());
    }	

    @Override		
    public void onTestFailure(ITestResult result) {
        ExtentReportHelper.addFailedTest(result.getMethod().getMethodName(), result.getThrowable());
        LoggerHelper.log.info("Error: "+result.getThrowable());
    }		

    @Override		
    public void onTestSkipped(ITestResult result) {
        ExtentReportHelper.addSkippedTest(result.getMethod().getMethodName());
        LoggerHelper.log.info("Skipped test "+result.getName());
    }		

    @Override		
    public void onTestSuccess(ITestResult result) {
        ExtentReportHelper.addPassedTest(result.getMethod().getMethodName());
        LoggerHelper.log.info("Success test "+result.getName());
    }	
}