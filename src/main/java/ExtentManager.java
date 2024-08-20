import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Initialize ExtentReports
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static void startTest(String testName) {
        test = getInstance().createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void endTest() {
        getInstance().flush();
    }
}
