package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By loginButton = By.linkText("Sign In");
    private By registerButton = By.linkText("Create an Account");
    private By changeButton = By.xpath("button[@class='action switch']");
    private By signoutButton = By.xpath("//li[@class='authorization-link']/a");

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

    public HomePage signout(){
        driver.findElement(changeButton).click();
        driver.findElement(signoutButton).click();
        return this;
    }
}
