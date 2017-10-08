package step_definitions;


import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.EventPage;
import pageobjects.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class Hooks{
    public static WebDriver driver;
    protected MainPage mainPage;
    protected EventPage eventPage;







    @Given("^I want to use the enviroment (.*)$")
    public void openBrowser(String enviroment) throws MalformedURLException {

//instantiate a new browser based on the choice of enviroment

        switch (enviroment) {
            case "Desktop":

                // set the path to the chromedriver
                String Chrome = new String("src/test/resources/chromedriver.exe");
                // set the webdriver
                System.setProperty("webdriver.chrome.driver",Chrome);
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                // set the waiting time
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                // delete coockies before testing
                driver.manage().deleteAllCookies();
                // set the browser window in maximal window size
                driver.manage().window().maximize();
                break;

            case "Android":

                // Create object of  DesiredCapabilities class and specify android platform
                DesiredCapabilities capabilities=DesiredCapabilities.android();
                capabilities.setCapability("no",true);
                capabilities.setCapability("newCommandTimeout", 100000);
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("appPackage", "com.android.chrome");

                // set the capability to execute test in chrome browser
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);

                // set the capability to execute our test in Android Platform
                capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);

                // we need to define platform name
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");

                // Set the device name as well (you can give any name)
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"SAMSUNG-SM-G859A");

                // set the android version as well
                capabilities.setCapability(MobileCapabilityType.VERSION,"4.4.4");

                // Create object of URL class and specify the appium server address
                URL url= new URL("http://127.0.0.1:1235/wd/hub");

                // Create object of  AndroidDriver class and pass the url and capability that we created
                WebDriver driver = new AndroidDriver(url, capabilities);
                break;
        }

        //pass the created webdriwer to the page object classes
        mainPage = new MainPage(driver);
        eventPage = new EventPage(driver);

        }


    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
       // when  the scenario failes then the script will automatically create log and screenshot to give as many clues as possible
        if(scenario.isFailed()) {
        try {
        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        }
        // close the browser after testing
        driver.quit();
    }
    
}