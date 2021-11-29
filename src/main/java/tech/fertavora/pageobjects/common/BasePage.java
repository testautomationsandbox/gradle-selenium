package tech.fertavora.pageobjects.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public static final long TIME_OUT_IN_SECONDS = 2;
    protected WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    public WebElement waitForVisibility(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForClickable(By by){
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void setCookie(WebDriver driver, String name, String value){
        Cookie cookie = new Cookie(name, value, "/");
        driver.manage().addCookie(cookie);
    }

    protected String getKeyValueFromLocalStorage(String key){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        return (String) javascriptExecutor.executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }
}
