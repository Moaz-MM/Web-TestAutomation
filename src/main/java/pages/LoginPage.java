package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    By emailField = By.id("email");
    By passwordField = By.id("pass");
    By loginButton = By.id("send2");
    By forgotPasswordButton = By.linkText("Forgot Your Password?");
    By welcomeMessage = By.className("logged-in");
    By loginErrorMessage = By.xpath("//div[@data-ui-id='message-error']/div");
    By emailErrorMessage = By.id("email-error");
    By passwordErrorMessage = By.id("pass-error");
//    By myAccountHeader = By.className("base");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public String getWelcomeMessage(){
        try{
            return driver.findElement(welcomeMessage).getText();
        } catch (Exception e){
            return "Login not successful";
        }
    }

    public String getLoginErrorMessage(){
        return driver.findElement(loginErrorMessage).getText();
    }

    public String getEmailErrorMessage(){
        return driver.findElement(emailErrorMessage).getText();
    }

    public String getPasswordErrorMessage(){
        return driver.findElement(passwordErrorMessage).getText();
    }

    public ForgotPasswordPage clickForgotPasswordButton(){
        driver.findElement(forgotPasswordButton).click();
        return new ForgotPasswordPage(driver);
    }
}
