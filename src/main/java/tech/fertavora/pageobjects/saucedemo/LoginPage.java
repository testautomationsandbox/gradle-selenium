package tech.fertavora.pageobjects.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class LoginPage extends BasePage {
    private final By inputUsername = By.cssSelector("[data-test=\"username\"]");
    private final By inputPassword = By.cssSelector("[data-test=\"password\"]");
    private final By buttonLogin = By.cssSelector("[data-test=\"login-button\"]");
    private final By textErrorMessage = By.cssSelector("[data-test=\"error\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Test runner loads the log in page")
    public LoginPage getTo(){
        driver.get(System.getenv("ENV_BASE_URL"));
        return this;
    }

    @Step("The user signs into the app as {0}")
    public void signIn(String username, String password){
        this.enterUsername(username)
            .enterPassword(password)
            .clickButtonLogin();
    }

    @Step("Test runner gets the error message text")
    public String getErrorMessage(){
        return waitForVisibility(textErrorMessage).getText();
    }

    private LoginPage enterUsername(String username) {
        waitForVisibility(inputUsername).sendKeys(username);
        return this;
    }

    private LoginPage enterPassword(String password) {
        waitForVisibility(inputPassword).sendKeys(password);
        return this;
    }

    private void clickButtonLogin(){
        waitForVisibility(buttonLogin).click();
    }
}
