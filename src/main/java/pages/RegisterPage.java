package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    By firstNameField = By.id("firstname");
    By lastNameField = By.id("lastname");
    By emailField = By.id("email_address");
    By passwordField = By.id("password");
    By confirmPasswordField = By.id("password-confirmation");
    By registrationSubmissionButton = By.xpath("//button[@title='Create an Account']");
    By successfullRegistrationMessage = By.xpath("//div[@data-ui-id]/div");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(String firstname, String lastname, String email, String password, String confirmPassword){
        setFirstName(firstname);
        setLastName(lastname);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
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

    public String getSuccessfullRegistrationMessage(){
        return driver.findElement(successfullRegistrationMessage).getText();
    }
}
