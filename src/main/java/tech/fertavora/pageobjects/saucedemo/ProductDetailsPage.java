package tech.fertavora.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class ProductDetailsPage extends BasePage {

    private final By itemTitle = By.cssSelector("div.inventory_details_name ");

    public ProductDetailsPage(WebDriver driver){
        super(driver);
    }

    public String getItemTitle(){
        return waitForVisibility(itemTitle).getText();
    }
}
