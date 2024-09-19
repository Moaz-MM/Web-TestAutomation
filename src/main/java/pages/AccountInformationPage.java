package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountInformationPage {

    private final WebDriver driver;
    private final By changeEmailCheckBox = By.id("change-email");
//    private final By changePasswordCheckBox = By.id("change-password");
    private final By newEmailField = By.id("email");
    private final By emailErrorMessage = By.id("email-error");
    private final By currentPasswordField = By.id("current-password");
    private final By newPasswordField = By.id("password");
    private final By newPasswordErrorMessage = By.id("password-error");
    private final By confirmNewPasswordErrorMessage = By.id("password-confirmation-error");
    private final By confirmNewPasswordField = By.id("password-confirmation");
    private final By saveButton = By.xpath("//button[@title='Save']");
    private final By accountInfoSavedMessage = By.xpath("//div[@data-ui-id='message-success']/div");
    private final By accountInfoSavedErrorMessage = By.xpath("//div[@data-ui-id='message-error']/div");

    public AccountInformationPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkChangeEmail(){
        driver.findElement(changeEmailCheckBox).click();
    }

    public void setNewEmail(String email){
        driver.findElement(newEmailField).clear();
        driver.findElement(newEmailField).sendKeys(email);
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
    public void clickSave(){
        driver.findElement(saveButton).click();
    }

    public String getEmailErrorMessage(){
        try{
            return driver.findElement(emailErrorMessage).getText();
        } catch (Exception e){
            return "Invalid email error message didn't appear";
        }
    }

    public String getNewPasswordErrorMessage(){
        try {
            return driver.findElement(newPasswordErrorMessage).getText();
        } catch (Exception e){
            return "New password error message didn't appear";
        }
    }

    public String getConfirmNewPasswordErrorMessage(){
        try {
            return driver.findElement(confirmNewPasswordErrorMessage).getText();
        } catch (Exception e){
            return "Confirm new password error message didn't appear";
        }
    }

    public String getInfoSavedMessage(){
        try{
            return driver.findElement(accountInfoSavedMessage).getText();
        } catch(Exception e){
            return "Changed wasn't saved successfully";
        }
    }

    public String getInfoSavedErrorMessage(){
        try{
            return driver.findElement(accountInfoSavedErrorMessage).getText();
        } catch(Exception e){
            return "Email already exists error message didn't appear";
        }
    }
}
