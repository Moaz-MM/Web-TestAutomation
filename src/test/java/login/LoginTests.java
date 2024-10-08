package login;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import java.util.HashMap;

public class LoginTests extends BaseTests {

    @Description("Given I try to login, When I enter valid data, Then I should be able complete login successfully")
    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 1)
    public void loginSuccessfully(){
        HashMap<String, String> loginCredentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(loginCredentials.get("email"));
        loginPage.setPassword(loginCredentials.get("password"));
        loginPage.clickLoginButton();
        boolean loginSuccessful = loginPage.getWelcomeMessage().contains("Welcome");
        Assert.assertTrue(loginSuccessful, "Login is not successful");
        homePage.logOut();
        homePage.clickLogo();
    }

    @Description("Given I try to login, When I enter invalid data, Then error message should appear")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void loginWithInvalidEmail(){
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail("fake@email.com");
        loginPage.setPassword("123456");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.", "Login is not correct error message didn't appear");
        homePage.clickLogo();
    }

    @Description("Given I try to login, When I enter empty fields, Then error message should appear")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void loginWithEmptyFields(){
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail("");
        loginPage.setPassword("");
        loginPage.clickLoginButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getEmailErrorMessage(), "This is a required field.", "Email is required error message didn't appear");
        softAssert.assertEquals(loginPage.getPasswordErrorMessage(), "This is a required field.", "Password is required error message didn't appear");
        homePage.clickLogo();
    }

    @Description("Given I'm signed in, When click logout, Then I should be able to logout successfully")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void logout(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        homePage.logOut();
        Assert.assertEquals(loginPage.getLogoutMessage(), "You are signed out", "Sign out success message didn't appear");
    }
}
