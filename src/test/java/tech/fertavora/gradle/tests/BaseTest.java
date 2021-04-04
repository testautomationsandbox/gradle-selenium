package tech.fertavora.gradle.tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SRC_TEST_RESOURCES_CHROMEDRIVER = "/src/test/resources/chromedriver";
    private static final String ARGUMENT_START_MAXIMIZED = "start-maximized";

    protected WebDriver driver;

    @BeforeSuite
    @Step("Browser start")
    public void startBrowser() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER, USER_DIR + SRC_TEST_RESOURCES_CHROMEDRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(ARGUMENT_START_MAXIMIZED);
        driver = new ChromeDriver(options);
    }
}
