



import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FireTv {

    public static DesiredCapabilities getCaps() {
        DesiredCapabilities Capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("deviceName", "Amazon Fire TV Stick");
        ltOptions.put("platformVersion", "7");
        ltOptions.put("platformName", "fireos");
        ltOptions.put("isRealMobile", true);
        ltOptions.put("build", "firetv");
        ltOptions.put("video", true);
        ltOptions.put("app", "");
        ltOptions.put("network", false);
        //   ltOptions.put("geoLocation", "RU");
        ltOptions.put("devicelog", true);
        ltOptions.put("visual", true);
        ltOptions.put("w3c", true);
        ltOptions.put("automationName","UIAutomator2");
        ltOptions.put("appiumVersion","latest");
        Capabilities.setCapability("lt:options", ltOptions);
        return Capabilities;
    }

    public static void runTest() {
        String USERNAME = "jayak";
        String ACCESS_KEY = "b4JOibh8UrnB8Ce2eL2VgHtc9P91eu4xCSc2IdXA7nibD30lX1";
        String gridUrl = "mobile-hub.lambdatest.com/wd/hub";

        DesiredCapabilities desiredCapabilities = getCaps();
        String url = "http://" + USERNAME + ":" + ACCESS_KEY + "@" + gridUrl;

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
            System.out.println("Initiating remote driver on platform: " + desiredCapabilities.getCapability("deviceName") + " browser: " + " version: " + desiredCapabilities.getCapability("platformVersion"));

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.navigate().back();
            Thread.sleep(100);
            System.out.println("back button clicked");

            WebElement el1 = (WebElement) ((RemoteWebDriver) driver).findElementById("com.amazon.tv.launcher:id/profile_icon");
            el1.click();
            System.out.println("profile button clicked");
            Thread.sleep(100);

            System.out.println("right button3 clicked");
            WebElement el2 = (WebElement) ((RemoteWebDriver) driver).findElementByXPath("//android.view.ViewGroup[@content-desc=\"Live\"]/android.widget.ImageView[2]");
            el2.click();
            Thread.sleep(1000);
            System.out.println("live tv button clicked");
            WebElement el3 =(WebElement) ((RemoteWebDriver) driver).findElementByXPath("//android.widget.ImageView[@content-desc=\"SonyLIV\"]");
            el3.click();
            System.out.println("Sonylive button clicked");
            Thread.sleep(1000);
            WebElement el4 = (WebElement) ((RemoteWebDriver) driver).findElementByXPath("//android.widget.ImageButton[@content-desc=\"Download You own it\"]");
            el4.click();
            Thread.sleep(1000);
            System.out.println("Download started button clicked");

            ((RemoteWebDriver) driver).executeScript("lambda-status=passed");
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void main(String[] args) {
        runTest();
    }
}