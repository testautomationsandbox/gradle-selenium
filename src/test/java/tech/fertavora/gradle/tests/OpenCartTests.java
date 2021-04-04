package tech.fertavora.gradle.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import tech.fertavora.pageobjects.opencart.HomePage;

import java.util.function.Supplier;

public class OpenCartTests extends BaseTest {
    private HomePage homePage;

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
        homePage = new HomePage(driver);
        homePage.get();
        Assert.assertTrue(true);
    }

    private void takeScreenshot(){
        System.out.println("Taking screenshot.."); //todo remove this
        var camera = (TakesScreenshot)driver;
        byte[] screenshot = camera.getScreenshotAs(OutputType.BYTES);
        Supplier<byte[]> supplier = () -> screenshot;
        Allure.addByteAttachmentAsync("name", "image/png", supplier);
    }
}
