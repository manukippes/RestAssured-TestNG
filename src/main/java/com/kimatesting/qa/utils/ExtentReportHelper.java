package com.kimatesting.qa.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportHelper {

    public static ExtentReports extentReport;

    public static ExtentTest test;

    public static ExtentTest method;

    private static Map<String, ExtentTest> tests = new HashMap<>();

    public static void createReport(String reportName, String documentTitle){

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/test-report.html");
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setTimelineEnabled(true);
        extentSparkReporter.config().setProtocol(Protocol.HTTPS);
        extentSparkReporter.config().setJs("js-string");
        extentSparkReporter.config().setCss("css-string");
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd yyyy, HH:mm:ss");
        extentSparkReporter.viewConfigurer().viewOrder().as(new ViewName[] {
                ViewName.DASHBOARD,
                ViewName.TEST,
                ViewName.CATEGORY,
                ViewName.AUTHOR,
                ViewName.DEVICE,
                ViewName.EXCEPTION,
                ViewName.LOG
        }).apply();

        extentReport = new ExtentReports();
        extentReport.setAnalysisStrategy( AnalysisStrategy.CLASS);
        extentReport.attachReporter(extentSparkReporter);
    }

    public static void createTest(String testName,String stepName, String[] groups){
        if (test == null || !testName.equals(test.getModel().getName())) {
            test = extentReport.createTest(testName);
        }
        method = test
                .createNode(stepName)
                .assignCategory(groups);
        tests.put(testName, method);
    }

    public static void addPassedTest(String testName) {
        method.log(Status.PASS, "Test: "+ testName + " is Passed.");
        LoggerHelper.log.info("Test: "+ testName + " is Passed.");
    }

    public static void addFailedTest(String testName, Throwable throwable) {
        method.log(Status.FAIL, "Test: "+testName+ " is Failed.");
        method.log(Status.FAIL, throwable);
        LoggerHelper.log.error(testName,throwable);
    }

    public static void addSkippedTest(String testName) {
        method.log(Status.SKIP, "Test: "+testName+ " is Skipped.");
    }

    public static void addInfoAsString(String testInfo) {
        method.log(Status.INFO, testInfo);
        LoggerHelper.log.info(testInfo);
    }

    public static void addInfoAsJson(String json) {
        method.log(Status.INFO, MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

    public static void addInfoAsUnorderedList(HashMap map) {
        method.log(Status.INFO,MarkupHelper.createUnorderedList(map).getMarkup());
    }

    public static void addLabel(String label) {
        method.log(Status.INFO, MarkupHelper.createLabel(label, ExtentColor.INDIGO));
    }

    public static void closeReport(){
        if (extentReport != null) {
            extentReport.flush();
        }
    }

}