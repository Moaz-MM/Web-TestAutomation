package forgot_password;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;

import java.util.HashMap;
import java.util.List;

public class ForgotPasswordTests extends BaseTests {

    @Description("Given I'm in the forgot password page, When I enter my email, Then a message stating that email was sent to the email should appear")
    @Story("Forgot Password")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "allLoginCredentials")
    public void resetWithValidEmail(String email){
        ForgotPasswordPage forgotPasswordPage = homePage.clickLoginButton().clickForgotPasswordButton();
//        forgotPasswordPage.setEmail("mo@za.com");
        forgotPasswordPage.setEmail(email);
        forgotPasswordPage.clickResetPasswordButton();
        Assert.assertEquals(forgotPasswordPage.getResetPasswordMessage(), "If there is an account associated with " + email + " you will receive an email with a link to reset your password.", "forgot password message error");
//        Assert.assertEquals(forgotPasswordPage.getResetPasswordMessage(), "If there is an account associated with mo@za.com you will receive an email with a link to reset your password.", "forgot password message error");
    }

    @Description("Given I'm in the forgot password page, When I enter invalid email, Then error message should appear")
    @Story("Forgot Password")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void resetWithInvalidEmail(){
        ForgotPasswordPage forgotPasswordPage = homePage.clickLoginButton().clickForgotPasswordButton();
        forgotPasswordPage.setEmail("fake.com");
        forgotPasswordPage.clickResetPasswordButton();
        Assert.assertEquals(forgotPasswordPage.getEmailErrorMessage(), "Please enter a valid email address (Ex: johndoe@domain.com).", "Invalid email error message didn't appear");
    }

    @Description("Given I'm in the forgot password page, When I enter empty fields, Then error message should appear")
    @Story("Forgot Password")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void resetWithEmptyEmailField(){
        ForgotPasswordPage forgotPasswordPage = homePage.clickLoginButton().clickForgotPasswordButton();
        forgotPasswordPage.clickResetPasswordButton();
        Assert.assertEquals(forgotPasswordPage.getEmailErrorMessage(), "This is a required field.", "Email field is required error message didn't appear");
    }

    @DataProvider
    private String[] allLoginCredentials(){
        List<HashMap<String, String>> list = getAllLoginCredentials();
        String[] data = new String[list.size()];
        for (int i = 0; i < list.size(); i++)   data[i] = list.get(i).get("email");
        return data;
    }
}
