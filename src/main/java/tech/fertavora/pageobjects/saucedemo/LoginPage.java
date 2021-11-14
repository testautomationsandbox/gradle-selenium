package tech.fertavora.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class LoginPage extends BasePage {
    private final By inputUsername = By.cssSelector("[data-test=\"username\"]");
    private final By inputPassword = By.cssSelector("[data-test=\"password\"]");
    private final By buttonLogin = By.cssSelector("[data-test=\"login-button\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage getTo(){
        driver.get(System.getenv("ENV_BASE_URL"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        waitForVisibility(inputUsername).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        waitForVisibility(inputPassword).sendKeys(password);
        return this;
    }

    public void clickButtonLogin(){
        waitForVisibility(buttonLogin).click();
    }
}
