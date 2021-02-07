package tech.fertavora.gradle.tests;

import com.google.common.io.Files;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestExample {
    private static WebDriver driver;

    @BeforeSuite
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
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

    //todo is this needed when using allure or reportportal?
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
