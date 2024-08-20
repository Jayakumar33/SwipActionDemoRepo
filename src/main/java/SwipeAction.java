import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import java.net.URL;
import java.time.Duration;

public class SwipeAction {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-android" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");


    AppiumDriver driver;
    private WebDriverWait wait;

    @Test
    @Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "SwipeActions");
            capabilities.setCapability("name", "SwipeActions");
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", app_id); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("idleTimeout", "600");

            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
            driver = new AppiumDriver(new URL(hub), capabilities);

            ExtentManager.startTest("SwipeActionTest");

            WebDriverWait Wait = new WebDriverWait(driver, 30);
            Thread.sleep(3000);

            //Opens the browser(Tap action)
            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();

            MobileElement url = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/url");
            url.sendKeys("https://www.lambdatest.com");

            MobileElement find = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/find");
            find.click();

            Thread.sleep(4000);

            // Perform Swipe Action
            TouchAction touchAction = new TouchAction(driver);

// Define start and end points for the swipe
            Point tapPoint = new Point(1015, 570);

            // Perform tap action
            touchAction.press(PointOption.point(tapPoint.getX(), tapPoint.getY()))
                    .release()
                    .perform();
            ExtentManager.getTest().log(Status.PASS, "Tap action performed successfully.");

            // Closing the bar
            touchAction.press(PointOption.point(tapPoint.getX(), tapPoint.getY()))
                    .release()
                    .perform();


// Multitouch
            int startX2 = 157; // Example start X coordinate
            int startY2 = 1048; // Example start Y coordinate
            int endX2 = 1100; // Example end X coordinate
            int endY2 = 926; // Example end Y coordinate

            // Perform the swipe action
            touchAction.press(PointOption.point(startX2, startY2))
                    .moveTo(PointOption.point(endX2, endY2))
                    .release()
                    .perform();
            Thread.sleep(3000);

            ExtentManager.getTest().log(Status.PASS, "Swipe and multi touch action performed successfully.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");

            driver.quit();
            ExtentManager.endTest();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");

            ExtentManager.getTest().log(Status.FAIL, "Test failed due to: " + e.getMessage());
            ExtentManager.getTest().fail(e);

            driver.quit();
            ExtentManager.endTest();

            e.printStackTrace();
        }

    }
}