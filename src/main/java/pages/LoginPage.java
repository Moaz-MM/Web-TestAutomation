package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final By emailField = By.id("email");
    private final By passwordField = By.id("pass");
    private final By loginButton = By.id("send2");
    private final By forgotPasswordButton = By.linkText("Forgot Your Password?");
    private final By welcomeMessage = By.className("logged-in");
    private final By loginErrorMessage = By.xpath("//div[@data-ui-id='message-error']/div");
    private final By emailErrorMessage = By.id("email-error");
    private final By passwordErrorMessage = By.id("pass-error");
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(welcomeMessage, "Welcome"));
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
