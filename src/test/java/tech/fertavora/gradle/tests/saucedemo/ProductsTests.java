package tech.fertavora.gradle.tests.saucedemo;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductDetailsPage;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

public class ProductsTests extends BaseTest {
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;

    @BeforeMethod
    public void productsPage(){
        productsPage = new ProductsPage(driver);
        productsPage.getTo();
    }

    @Test(description = "User adds a product to cart from Products Page.")
    public void addProductToCartFromProductsPage(){
        productsPage.clickButtonAddBackpack();

        String actualAmountProductsInCart = productsPage.getProductsAmountInCart();
        String actualCartAmountInLocalStorage = productsPage.getProductsAmountInCartFromLocalStorage();
        assertText(actualAmountProductsInCart, "1");
        assertText(actualCartAmountInLocalStorage, "[4]");
    }

    @Test(description = "User goes to Product Details by clicking Product title")
    public void goToProductDetailsByTitleClick(){
        productDetailsPage = productsPage.clickLinkBackpack();
        String actualItemTitle = productDetailsPage.getItemTitle();
        assertText(actualItemTitle, "Sauce Labs Backpack");
    }

    @Test(description = "User goes to Product Details by clicking Product iamge")
    public void goToProductDetailsByImageClick(){
        productDetailsPage = productsPage.clickImageBackpack();
        String actualItemTitle = productDetailsPage.getItemTitle();
        assertText(actualItemTitle, "Sauce Labs Backpack");
    }

    //todo add the sorting tests
}
