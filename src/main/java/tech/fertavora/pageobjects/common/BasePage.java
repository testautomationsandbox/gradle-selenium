package tech.fertavora.pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends LoadableComponent {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected void waitFor(ExpectedCondition expectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(expectedCondition);
    }
}
