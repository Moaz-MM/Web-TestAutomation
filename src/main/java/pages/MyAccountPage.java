package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {

    private final WebDriver driver;
    private final By editContactInfoButton = By.linkText("Edit");
    private final By changePasswordButton = By.linkText("Change Password");
//    private final By accountInfoSavedMessage = By.xpath("//div[@data-ui-id='message-success']/div");
    private final By addressBookButton = By.linkText("Address Book");
    private final By myOrdersButton = By.linkText("My Orders");

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public AccountInformationPage clickEditContactInfoButton(){
        driver.findElement(editContactInfoButton).click();
        return new AccountInformationPage(driver);
    }

    public AccountInformationPage clickChangePasswordButton(){
        driver.findElement(changePasswordButton).click();
        return new AccountInformationPage(driver);
    }

    public AccountAddressBookPage clickAddressBookButton(){
        driver.findElement(addressBookButton).click();
        return new AccountAddressBookPage(driver);
    }

    public MyOrdersPage clickMyOrdersButton(){
        driver.findElement(myOrdersButton).click();
        return new MyOrdersPage(driver);
    }
}
