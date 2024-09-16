package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class RegisterPage {

    private WebDriver driver;
    By firstNameField = By.id("firstname");
    By lastNameField = By.id("lastname");
    By emailField = By.id("email_address");
    By passwordField = By.id("password");
    By confirmPasswordField = By.id("password-confirmation");
    By registrationSubmissionButton = By.xpath("//button[@title='Create an Account']");
    By successfulRegistrationMessage = By.xpath("//div[@data-ui-id]/div");
    By existingEmailErrorMessage = By.xpath("//div[@data-ui-id='message-error']/div");
    By firstNameErrorMessage = By.id("firstname-error");
    By lastNameErrorMessage = By.id("lastname-error");
    By emailFieldErrorMessage = By.id("email_address-error");
    By passwordErrorMessage = By.id("password-error");
    By confirmPasswordErrorMessage = By.id("password-confirmation-error");



    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(String firstName, String lastName, String email, String password, String confirmPassword){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        clickRegistrationSubmissionButton();
    }

    public void register(HashMap<String, String> registerCredentials){
        setFirstName(registerCredentials.get("firstName"));
        setLastName(registerCredentials.get("lastName"));
        setEmail(registerCredentials.get("email"));
        setPassword(registerCredentials.get("password"));
        setConfirmPassword(registerCredentials.get("password"));
        clickRegistrationSubmissionButton();
    }

    public void setFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword){
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickRegistrationSubmissionButton(){
        driver.findElement(registrationSubmissionButton).click();
    }

    public String getSuccessfulRegistrationMessage(){
        return driver.findElement(successfulRegistrationMessage).getText();
    }

    public String getExistingEmailErrorMessage(){
        return driver.findElement(existingEmailErrorMessage).getText();
    }

    public String getFirstNameErrorMessage(){
        return driver.findElement(firstNameErrorMessage).getText();
    }

    public String getLastNameErrorMessage(){
        return driver.findElement(lastNameErrorMessage).getText();
    }

    public String getEmailErrorMessage(){
        return driver.findElement(emailFieldErrorMessage).getText();
    }

    public String getPasswordErrorMessage(){
        return driver.findElement(passwordErrorMessage).getText();
    }

    public String getConfirmPasswordErrorMessage() {
        return driver.findElement(confirmPasswordErrorMessage).getText();
    }
}
