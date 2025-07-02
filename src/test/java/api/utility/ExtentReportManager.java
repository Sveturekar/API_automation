package api.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
        	
        	String reportDir = "reports";
        	File dir = new File(reportDir);
        	if (!dir.exists()) 
        	{
        	    dir.mkdirs();
        	}
        	

String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

String reportPath = reportDir + "/ExtentReport_" + timestamp + ".html";

System.out.println("Extent report path " +System.getProperty("user.dir"));
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setDocumentTitle("API Automation Report");
            reporter.config().setReportName("API Automation Test Results");
            reporter.config().setEncoding("utf-8");
           
            
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Saiprasad");
        }
        return extent;
    }
}
