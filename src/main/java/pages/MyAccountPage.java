package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {

    WebDriver driver;
    private By changePasswordButton = By.linkText("Change Password");
    private By currentPasswordField = By.id("current-password");
    private By newPasswordField = By.id("password");
    private By confirmNewPasswordField = By.id("password-confirmation");
    private By saveButton = By.xpath("//button[@title='Save']");
    private By accountInfoSavedMessage = By.xpath("//div[@data-ui-id='message-success']/div");

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickChangePasswordButton(){
        driver.findElement(changePasswordButton).click();
    }

    public void setCurrentPassword(String currentPassword){
        driver.findElement(currentPasswordField).sendKeys(currentPassword);
    }
    //TODO update password in login data
    public void setNewPassword(String newPassword){
        driver.findElement(newPasswordField).sendKeys(newPassword);
    }
    public void setConfirmNewPassword(String confirmPassword){
        driver.findElement(confirmNewPasswordField).sendKeys(confirmPassword);
    }
    public void saveChanges(){
        driver.findElement(saveButton).click();
    }

    public String getInfoSavedMessage(){
        return driver.findElement(accountInfoSavedMessage).getText();
    }
}
