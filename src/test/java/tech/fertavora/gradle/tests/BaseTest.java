package tech.fertavora.gradle.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private static final String BROWSER_NAME = "chrome";
    public static final String SELENIUM_HOST = "http://localhost:4444/wd/hub";
    private static final String ARGUMENT_START_MAXIMIZED = "start-maximized";
    private static final String ARGUMENT_HEADLESS = "--headless";

    protected WebDriver driver;

    @BeforeSuite
    public void startBrowser() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(ARGUMENT_START_MAXIMIZED);
        chromeOptions.addArguments(ARGUMENT_HEADLESS);
        caps.setCapability("browserName", BROWSER_NAME);
        driver = new RemoteWebDriver(new URL(SELENIUM_HOST), chromeOptions);
    }

}