package tech.fertavora.gradle.tests.saucedemo;

import org.testng.annotations.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

public class ProductsTests extends BaseTest {
    private ProductsPage productsPage;

    @Test(description = "User adds a product to cart from Products Page.")
    public void homePageTest(){
        productsPage = new ProductsPage(driver);
        productsPage.getTo()
                .clickButtonAddBackpack();

        String actualAmountProductsInCart = productsPage.getProductsAmountInCart();
        String actualCartAmountInLocalStorage = productsPage.getProductsAmountInCartFromLocalStorage();
        assertText(actualAmountProductsInCart, "1");
        assertText(actualCartAmountInLocalStorage, "[4]");
    }
}
