package register;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

import java.util.HashMap;

public class RegisterTests extends BaseTests {

    @Description("Given I'm in the registration form, When I enter valid registration data, Then I should be able to register successfully")
    @Story("Registration")
    @Severity(SeverityLevel.BLOCKER)
    @Test(invocationCount = 3)
    public void registerSuccessfully(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        HashMap<String, String> registerCredentials = getRegisterCredentials();
        registerPage.register(registerCredentials);
        Assert.assertEquals(registerPage.getSuccessfulRegistrationMessage(), "Thank you for registering with Main Website Store.",
                "Successful registration message didn't show");
        addLoginCredential(registerCredentials);
        homePage.logOut();
        homePage.clickLogo();
    }

    @Description("Given I'm in the registration form, When I enter an already registered email, Then error message should appear")
    @Story("Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registerWithExistingEmail(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        HashMap<String, String> registerCredentials = getLoginCredentials();
        registerPage.register(registerCredentials);
        Assert.assertTrue(registerPage.getExistingEmailErrorMessage().contains("There is already an account with this email address."),
                "Error message of the email already exists didn't appear");
        homePage.clickLogo();
    }

    @Description("Given I'm in the registration form, When I enter empty fields, Then error message should appear")
    @Story("Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registerWithEmptyFields(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        registerPage.register("", "", "", "", "");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registerPage.getFirstNameErrorMessage(), "This is a required field.", "Firstname is required error message didn't appear");
        softAssert.assertEquals(registerPage.getLastNameErrorMessage(), "This is a required field.", "Lastname is required error message didn't appear");
        softAssert.assertEquals(registerPage.getEmailErrorMessage(), "This is a required field.", "Email is required error message didn't appear");
        softAssert.assertEquals(registerPage.getPasswordErrorMessage(), "This is a required field.", "Password is required error message didn't appear");
        softAssert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "This is a required field.", "Confirm password is required error message didn't appear");
        softAssert.assertAll();
        homePage.clickLogo();
    }

    @Description("Given I'm in the registration form, When I enter invalid registration data, Then error message should appear")
    @Story("Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registerWithInvalidEmail(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        registerPage.register("Moaz", "Mohammed", "moaz.com", "mo123456_", "mo123456_");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Please enter a valid email address (Ex: johndoe@domain.com).", "Invalid email error message didn't appear");
        homePage.clickLogo();
    }

    @Description("Given I'm in the registration form, When I enter confirmation password not matching the password, Then error message should appear")
    @Story("Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void registerWithPasswordAndConfirmPasswordNotMatching(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        registerPage.register("", "", "", "123", "1234");
        homePage.clickLogo();
    }
}
