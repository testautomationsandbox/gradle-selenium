package tech.fertavora.pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public static final int TIME_OUT_IN_SECONDS = 2;
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    public WebElement waitForVisibility(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void setCookie(WebDriver driver, String name, String value){
        Cookie cookie = new Cookie(name, value, "/");
        driver.manage().addCookie(cookie);
    }
}
