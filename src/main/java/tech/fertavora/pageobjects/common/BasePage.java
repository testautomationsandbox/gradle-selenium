package tech.fertavora.pageobjects.common;

import com.google.common.collect.Comparators;
import com.google.common.collect.Ordering;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public List<WebElement> waitForAllVisibility(By by){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public WebElement waitForClickable(By by){
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForClickable(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
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

    public void clickLink(String linkText){
        waitForClickable(driver.findElement(By.linkText(linkText))).click();
    }

    public boolean areStringsSorted(List<String> listOfStrings) {
        return Comparators.isInOrder(listOfStrings, Comparator.<String> naturalOrder());
    }

    public boolean areStringsReversedSorted(List<String> listOfStrings) {
        return Comparators.isInOrder(listOfStrings, Comparator.<String> reverseOrder());
    }

    public boolean areDoublesSorted(List<Double> listOfDoubles) {
        return Comparators.isInOrder(listOfDoubles, Comparator.<Double> naturalOrder());
    }

    public boolean areDoublesReversedSorted(List<Double> listOfDoubles) {
        return Comparators.isInOrder(listOfDoubles, Comparator.<Double> reverseOrder());
    }

    protected List<String> convertWebElementsToStrings(List<WebElement> webElements){
        List<String> titles = new ArrayList<>();

        for(WebElement webElement : webElements){
            titles.add(webElement.getText());
        }
        return titles;
    }
}
