package tech.fertavora.pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends LoadableComponent {
    public static final int TIME_OUT_IN_SECONDS = 2;
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected void waitFor(ExpectedCondition expectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.until(expectedCondition);
    }
}
