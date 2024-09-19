package register;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

import java.util.HashMap;

public class RegisterTests extends BaseTests {

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

    @Test
    public void registerWithExistingEmail(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        HashMap<String, String> registerCredentials = getLoginCredentials();
        registerPage.register(registerCredentials);
        Assert.assertTrue(registerPage.getExistingEmailErrorMessage().contains("There is already an account with this email address."),
                "Error message of the email already exists didn't appear");
        homePage.clickLogo();
    }

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

    @Test
    public void registerWithInvalidEmail(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        registerPage.register("", "", "moaz.com", "", "");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Please enter a valid email address (Ex: johndoe@domain.com).", "Invalid email error message didn't appear");
        homePage.clickLogo();
    }

    @Test
    public void registerWithPasswordAndConfirmPasswordNotMatching(){
        RegisterPage registerPage = homePage.clickRegisterButton();
        registerPage.register("", "", "", "123", "1234");
        homePage.clickLogo();
    }
}
