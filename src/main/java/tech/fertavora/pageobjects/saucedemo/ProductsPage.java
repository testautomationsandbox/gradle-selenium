package tech.fertavora.pageobjects.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.fertavora.pageobjects.common.BasePage;

public class ProductsPage extends BasePage {

    private final By buttonAddBackpack = By.cssSelector("[data-test=\"add-to-cart-sauce-labs-backpack\"]");
    private final By buttonAddBoltTShirt = By.cssSelector("[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    private final By buttonAddOnesie = By.cssSelector("[data-test=\"add-to-cart-sauce-labs-onesie\"]");
    private final By buttonAddBikeLight = By.cssSelector("[data-test=\"add-to-cart-sauce-labs-bike-light\"]");
    private final By buttonAddJacket = By.cssSelector("[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]");
    private final By buttonAddAllThingsTShirt = By.cssSelector("[data-test=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]");

    private final By imageLinkBackpack = By.id("item_4_img_link");
    private final By imageLinkBoltTShirt = By.id("item_1_img_link");
    private final By imageLinkOnesie = By.id("item_2_img_link");
    private final By imageLinkBikeLight = By.id("item_0_img_link");
    private final By imageLinkJacket = By.id("item_5_img_link");
    private final By imageLinkAllThingsTShirt = By.id("item_3_img_link");

    private final By allProductLinks = By.cssSelector("div[class=\"inventory_item_label\"] > a");
    private final By linkBackpack = By.linkText("Sauce Labs Backpack");
    private final By linkBikeLight = By.linkText("Sauce Labs Bike Light");
    private final By linkBoltTShirt = By.linkText("Sauce Labs Bolt T-Shirt");
    private final By linkJacket = By.linkText("Sauce Labs Fleece Jacket");
    private final By linkOnesie = By.linkText("Sauce Labs Onesie");
    private final By linkAllThingsTShirt = By.linkText("Test.allTheThings() T-Shirt (Red)");

    public ProductsPage(WebDriver driver){
        super(driver);
    }

    @Step("Test runner loads the Products Page")
    public ProductsPage getTo(){
        driver.get(System.getenv("ENV_BASE_URL"));
        setCookie(driver,"session-username", "standard_user");
        driver.get(System.getenv("ENV_BASE_URL")  + "/inventory.html");
        return this;
    }

    @Step("Test runner gets the product link with text {0}")
    private WebElement getProductLinkByIndex(int linkIndex){
        waitForVisibility(allProductLinks);
        return driver.findElements(allProductLinks).get(linkIndex);
    }

    @Step("Test runner gets the link text by index {0}")
    public String getLinkTextByIndex(int linkIndex){
        return getProductLinkByIndex(linkIndex).getText();
    }
}
