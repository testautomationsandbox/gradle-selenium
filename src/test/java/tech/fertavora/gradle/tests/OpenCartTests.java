package tech.fertavora.gradle.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tech.fertavora.pageobjects.opencart.HomePage;

import java.util.function.Supplier;

public class OpenCartTests {
    private static WebDriver driver;
    private HomePage homePage;

    @BeforeSuite
    @Step("Browser start")
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        homePage = new HomePage(driver);
    }

    @AfterSuite
    @Step("Browser close")
    public void closeBrowser(){
        driver.quit();
    }

    @AfterTest
    public void attachScreenshot(){
        takeScreenshot();
    }

    @Test()
    @Description("First test ever")
    public void test() {
        // to change the test name in allure report
//        AllureLifecycle lifecycle = Allure.getLifecycle();
//        lifecycle.updateTestCase(testResult -> testResult.setName("CHANGED_NAME"));
        // END

        homePage.get();
        Assert.assertTrue(true);
    }

    private void takeScreenshot(){
        System.out.println("Taking screenshot..");
        var camera = (TakesScreenshot)driver;
        byte[] screenshot = camera.getScreenshotAs(OutputType.BYTES);
        Supplier<byte[]> supplier = () -> screenshot;
        Allure.addByteAttachmentAsync("name", "image/png", supplier);
    }
}
