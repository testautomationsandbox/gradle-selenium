package tech.fertavora.gradle.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private static final String ARGUMENT_START_MAXIMIZED = "start-maximized";
    private static final String ARGUMENT_HEADLESS = "--headless";
    private static final String ARGUMENT_WINDOWS_SIZE = "--window-size=1920,1080";

    protected static WebDriver driver;

    @Step("Browser is opened.")
    @BeforeClass
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                ARGUMENT_WINDOWS_SIZE,
                ARGUMENT_START_MAXIMIZED,
                ARGUMENT_HEADLESS
        );
        driver = new ChromeDriver(chromeOptions);
    }

    @Step("Browser is closed.")
    @AfterClass
    protected void closeBrowser(){
        driver.quit();
    }

    @AfterMethod
    protected void attachScreenshot(){
        takeScreenshot();
    }

    @Attachment
    protected byte[] takeScreenshot(){
        var camera = (TakesScreenshot)driver;
        return camera.getScreenshotAs(OutputType.BYTES);
    }

    @Step("Assert text")
    protected void assertText(String actual, String expected){
        Assert.assertEquals(actual, expected);
    }

}