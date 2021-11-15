package tech.fertavora.gradle.tests.saucedemo;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

@Epic("SauceDemo Tests")
@Feature("User Authentication")
public class LoginTests extends BaseTest {
    private ProductsPage productsPage;

    @Story("Successfull authentication")
    @TmsLink("TC-1")
    @Issue("BUG-1")
    @Link(name = "STORY-1", type="myLink")
    @Test(description = "User can succesfully sign in and lands in homepage.")
    public void successfulLogin() {
        loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickButtonLogin();

        productsPage = new ProductsPage(driver);
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, "Sauce Labs Backpack");
    }

    @Test(description = "User gets error message on invalid login.")
    public void invalidLogin(){
        loginPage.enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickButtonLogin();

        String actualErrorMessage = loginPage.getErrorMessage();
        assertText(actualErrorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "User takes to long to login.")
    public void slowLogin(){
        loginPage.enterUsername("performance_glitch_user")
                .enterPassword("secret_sauce")
                .clickButtonLogin();

        productsPage = new ProductsPage(driver);
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, "Sauce Labs Backpack");
    }

}