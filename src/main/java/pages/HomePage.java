package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By loginButton = By.linkText("Sign In");
    private By registerButton = By.linkText("Create an Account");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public RegisterPage clickRegisterButton(){
        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }
}
