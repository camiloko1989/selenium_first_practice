package com.guru99.tests;

import com.aventstack.extentreports.Status;
import com.guru99.Pages.LoginPage;
import Utils.ConfigUtils;
import Utils.ReportUtils;
import Utils.ScreenshotUtils;
import com.guru99.Implementation.CommonDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;
public class BaseTest {
    CommonDriver commonDriver;
    String url;
    String browserType;
    WebDriver driver;
    LoginPage loginPage;
    String currentWorkingDirectory;
    Properties configProperty;
    String configFileName;
    ReportUtils reportUtils;
    String reportFileName;
    ScreenshotUtils screenshot;

    @BeforeSuite
    public void preSetup() throws Exception {
        currentWorkingDirectory = System.getProperty("user.dir");
        configFileName = currentWorkingDirectory + "/config/config.properties";
        reportFileName = currentWorkingDirectory + "/reports/guru99TestReport.html";
        configProperty = ConfigUtils.readProperties(configFileName);
        reportUtils = new ReportUtils(reportFileName);

    }

    @BeforeClass
    public void setup() throws Exception {

        url = configProperty.getProperty("baseUrl");
        browserType = configProperty.getProperty("browserType");
        commonDriver = new CommonDriver(browserType);
        driver = commonDriver.getDriver();
        loginPage = new LoginPage(driver);
        screenshot = new ScreenshotUtils(driver);

        commonDriver.navigateToURL(url);
    }

    @AfterMethod
    public void postTestAction(ITestResult result) throws Exception{

        String testCaseName = result.getName();
        Long executionTime = System.currentTimeMillis();
        String screenshotFileName = currentWorkingDirectory +
                "/screenshots" + testCaseName + executionTime +".jpg";

        if(result.getStatus() == ITestResult.FAILURE) {
            reportUtils.addTestLog(Status.FAIL, "One or more steps failed");
            screenshot.captureAndSaveScreenshot(screenshotFileName);
            reportUtils.attachScreenshotToReport(screenshotFileName);
        }

    }

    @AfterClass
    public void clear() {

        commonDriver.closeAllBrowser();

    }

    @AfterSuite
    public void postClear() {
        reportUtils.flushreport();
    }
}

