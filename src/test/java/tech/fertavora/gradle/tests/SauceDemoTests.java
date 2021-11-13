package tech.fertavora.gradle.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import tech.fertavora.pageobjects.saucedemo.HomePage;
import tech.fertavora.pageobjects.saucedemo.LoginPage;

@Epic("SauceDemo Tests")
@Feature("User Authentication")
public class SauceDemoTests extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;

    @Story("Successfull authentication")
    @TmsLink("TC-1")
    @Issue("BUG-1")
    @Link(name = "STORY-1", type="myLink")
    @Test(description = "User can succesfully sign in and lands in homepage.", enabled = false)
    public void test() {
        loginPage = new LoginPage(driver);

        loginPage.getTo()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickButtonLogin();

        homePage = new HomePage(driver);
        String actualFirstLinkText = homePage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, "Sauce Labs Backpacks");
    }

    @Test
    public void homePageTest(){
        homePage = new HomePage(driver);
        homePage.getTo();
        try{
            Thread.sleep(3000);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }


}
