package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class CheckoutPage {

    private final WebDriver driver;
    private final By addressForm = By.id("co-shipping-form");
    private final By companyField = By.name("company");
    private final By streetAddress1 = By.name("street[0]");
    private final By streetAddress2 = By.name("street[1]");
    private final By streetAddress3 = By.name("street[2]");
    private final By cityField = By.name("city");
    private final By stateProvinceField = By.name("region");
    private final By zipPostalCodeField = By.name("postcode");
    private final By countryField = By.name("country_id");
    private final By phoneField = By.name("telephone");
    private final By shippingMethodButton = By.xpath("//input[@value='flatrate_flatrate']");
//    private final By shippingMethodButton = By.xpath("//table//input");
    private final By nextButton = By.xpath("//button[@data-role='opc-continue']");
    private final By billingAddressDetails = By.xpath("//div[@class='billing-address-details']");
    private final By placeOrderButton = By.xpath("//button[@title='Place Order']");
    private final By purchaseCompleteMessage = By.xpath("//span[@data-ui-id='page-title-wrapper']");
    private final By orderNumber = By.className("order-number");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillAddressData(HashMap<String, String> addressData){
        driver.findElement(companyField).sendKeys(addressData.get("company"));
        driver.findElement(streetAddress1).sendKeys(addressData.get("street1"));
        driver.findElement(streetAddress2).sendKeys(addressData.get("street2"));
        driver.findElement(streetAddress3).sendKeys(addressData.get("street3"));
        driver.findElement(cityField).sendKeys(addressData.get("city"));
        driver.findElement(zipPostalCodeField).sendKeys(addressData.get("zipcode"));
        new Select(driver.findElement(countryField)).selectByVisibleText(addressData.get("country"));
        driver.findElement(stateProvinceField).sendKeys(addressData.get("state"));
        driver.findElement(phoneField).sendKeys(addressData.get("phone"));
    }

    public void selectShippingMethod(){
        driver.findElement(shippingMethodButton).click();
    }

    public void clickNext(){
        driver.findElement(nextButton).click();
    }

    public void clickPlaceOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressDetails));
        driver.findElement(placeOrderButton).click();
    }

    public String getPurchaseCompleteMessage(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(purchaseCompleteMessage, "Thank"));
            return driver.findElement(purchaseCompleteMessage).getText();
        }catch (Exception e){
            return "Purchase complete message didn't appear";
        }
    }

    public String getOrderNumber(){
        try {
            return driver.findElement(orderNumber).getText();
        }catch (Exception e){
            return "Order number didn't appear";
        }
    }

    public boolean isAddressSaved(){
        return !driver.findElement(addressForm).isDisplayed();
    }
}
