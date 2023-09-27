package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtils {
    ExtentSparkReporter sparkReport;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public ReportUtils(String htmlReportFilename){
        htmlReportFilename = htmlReportFilename.trim();

        sparkReport = new ExtentSparkReporter(htmlReportFilename);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReport);
    }

    public void createATestCase(String testCaseName) {
        extentTest = extentReports.createTest(testCaseName);

    }

    public void addTestLog(Status status, String comment ) {
        extentTest.log(status, comment);
    }

    public void attachScreenshotToReport(String filename) throws Exception {
        extentTest.addScreenCaptureFromPath(filename);
    }

    public void flushreport() {
        extentReports.flush();
    }
}
