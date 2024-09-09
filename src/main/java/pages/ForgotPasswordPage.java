package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    public WebDriver driver;
    By emailField = By.id("email_address");
    By resetPasswordButton = By.xpath("//button[@class='action submit primary']");
    By resetPasswordMessage = By.xpath("//div[@data-ui-id]/div");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickResetPasswordButton(){
        driver.findElement(resetPasswordButton).click();
    }

    public String getResetPasswordMessage(){
        return driver.findElement(resetPasswordMessage).getText();
    }
}
