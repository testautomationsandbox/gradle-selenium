package tech.fertavora.pageobjects.opencart;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tech.fertavora.pageobjects.common.BasePage;

public class HomePage extends BasePage {

    public static final String ENV_BASE_URL = System.getenv("ENV_BASE_URL");
    private By slideshow = By.id("slideshow0");
    private By homeLogoLink = By.cssSelector("#logo > h1 > a");

    public HomePage(WebDriver driver){
        super(driver);
    }

    @Override
    @Step("Load homepage")
    protected void load() {
        driver.get(ENV_BASE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            waitFor(ExpectedConditions.visibilityOfAllElementsLocatedBy(slideshow));
        } catch(Exception error) {
            throw new Error("HomePage not loaded!");
        }
    }

    @Step("Get Home Logo text")
    public String getHomeLogoText(){
        return driver.findElement(homeLogoLink).getText();
    }
}
