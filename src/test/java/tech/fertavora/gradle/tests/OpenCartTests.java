package tech.fertavora.gradle.tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import tech.fertavora.pageobjects.opencart.HomePage;

public class OpenCartTests extends BaseTest {
    private HomePage homePage;

    @AfterSuite
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
        String actualText = homePage.getHomeLogoText();
        assertText(actualText, "Your Store");
    }

    @Attachment
    private byte[] takeScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Assert text")
    public void assertText(String actual, String expected){
        Assert.assertEquals(actual, expected);
    }
}
