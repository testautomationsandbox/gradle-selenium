package tech.fertavora.pageobjects.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tech.fertavora.pageobjects.common.BasePage;

import java.util.ArrayList;
import java.util.List;

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
    private final By allProductPrices = By.cssSelector("div[class=\"inventory_item_price\"]");
    private final By linkShoppingCart = By.cssSelector("a[class=\"shopping_cart_link\"]");
    private final By selectSorting = By.cssSelector("[data-test=\"product_sort_container\"]");

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

    @Step("Test runner gets the product link by index {0}")
    private WebElement getProductLinkByIndex(int linkIndex){
        waitForVisibility(allProductLinks);
        return driver.findElements(allProductLinks).get(linkIndex);
    }

    @Step("Test runner gets the product link text by index {0}")
    public String getLinkTextByIndex(int linkIndex){
        return getProductLinkByIndex(linkIndex).getText();
    }

    @Step("Test runner gets list of products links texts")
    public List<String> getAllProductLinks(){
        return convertWebElementsToStrings(waitForAllVisibility(allProductLinks));
    }

    @Step("Test runner gets list of products prices")
    public List<Double> getAllProductPrices(){
        return convertStringsToDoubles(convertWebElementsToStrings(waitForAllVisibility(allProductPrices)));
    }

    private List<Double> convertStringsToDoubles(List<String> strings){
        List<Double> doubles = new ArrayList<>();
        for(String string : strings) {
            doubles.add(Double.parseDouble(string.replace("$", "")));
        }
        return doubles;
    }

    @Step("Users clicks the backpack 'Add to cart' button")
    public void clickButtonAddBackpack(){
        waitForClickable(buttonAddBackpack).click();
    }

    @Step("Users clicks the backpack title")
    public ProductDetailsPage clickLinkBackpack(){
        clickLink("Sauce Labs Backpack");
        return new ProductDetailsPage(driver);
    }

    @Step("Users clicks the backpack image")
    public ProductDetailsPage clickImageBackpack(){
        waitForClickable(imageLinkBackpack).click();
        return new ProductDetailsPage(driver);
    }

    @Step("Test runner gets the amount of products in cart")
    public String getProductsAmountInCart(){
        return driver.findElement(linkShoppingCart).getText();
    }

    public String getProductsAmountInCartFromLocalStorage(){
        return getKeyValueFromLocalStorage("cart-contents");
    }

    @Step("User selects {0} from sorting drop down")
    public void selectSorting(String option){
        Select sorting = new Select(waitForVisibility(selectSorting));
        sorting.selectByVisibleText(option);
    }
}
