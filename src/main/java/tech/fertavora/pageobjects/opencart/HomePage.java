package tech.fertavora.pageobjects.opencart;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tech.fertavora.pageobjects.common.BasePage;

public class HomePage extends BasePage {

    private By slideshow = By.id("slideshow0");

    public HomePage(WebDriver driver){
        super(driver);
    }

    @Override
    @Step("Load homepage")
    protected void load() {
        driver.get(System.getenv("ENV_BASE_URL"));
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            waitFor(ExpectedConditions.visibilityOfAllElementsLocatedBy(slideshow));
        } catch(Exception error) {
            throw new Error("HomePage not loaded!");
        }
    }
}
