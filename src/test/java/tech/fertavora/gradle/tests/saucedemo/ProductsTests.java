package tech.fertavora.gradle.tests.saucedemo;

import org.testng.annotations.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

public class ProductsTests extends BaseTest {
    private ProductsPage productsPage;

    @Test(enabled = false)
    public void homePageTest(){
        productsPage = new ProductsPage(driver);
        productsPage.getTo();
        try{
            Thread.sleep(3000);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
