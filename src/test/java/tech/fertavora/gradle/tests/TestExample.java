package tech.fertavora.gradle.tests;

import com.google.common.io.Files;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestExample {
    public static final String SELENIUM_HOST = "http://localhost:4444/wd/hub";
    private static WebDriver driver;

    @BeforeSuite
    public void startBrowser() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "chrome");
        driver = new RemoteWebDriver(new URL(SELENIUM_HOST), caps);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void closeBrowser(){
        this.takeScreenshot();
        driver.quit();
    }

    @Test
    public void test() {
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("play station 5");
        driver.findElement(By.id("search_button_homepage")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".results--sidebar")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".results--main")));
        Assert.assertTrue(true);
    }

    private void takeScreenshot(){
        var camera = (TakesScreenshot)driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot taken: " + screenshot.getAbsolutePath());
        try {
            Files.move(screenshot, new File("resources/screenshots/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
