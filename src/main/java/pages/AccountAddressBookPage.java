package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountAddressBookPage {

    private final WebDriver driver;
    private final By companyField = By.id("company");
    private final By phoneField = By.id("telephone");
    private final By streetAddress1 = By.id("street_1");
    private final By streetAddress2 = By.id("street_2");
    private final By streetAddress3 = By.id("street_3");
    private final By cityField = By.id("city");
    private final By stateProvinceField = By.id("region");
    private final By zipPostalCodeField = By.id("zip");
    private final By countrySelect = By.id("country");
    private final By saveAddressButton = By.xpath("//button[@title='Save Address']");
    private final By addressSavedSuccessMessage = By.xpath("//div[@data-ui-id='message-success']/div");
    private final By addNewAddressButton = By.xpath("//button[@title='Add New Address']");
    private final By additionalAddress1EditButton = By.xpath("//table[@id='additional-addresses-table']//tr[1]//a[1]");
    private final By additionalAddress1DeleteButton = By.xpath("//table[@id='additional-addresses-table']//tr[1]//a[2]");
    private final By additionalAddressConfirmDeletionButton = By.xpath("//footer/button[2]");

    public AccountAddressBookPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setCompany(String company){
        driver.findElement(companyField).sendKeys(company);
    }

    public void setPhone(String phone){
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void setStreetAddress1(String address){
        driver.findElement(streetAddress1).sendKeys(address);
    }

    public void setStreetAddress2(String address){
        driver.findElement(streetAddress2).sendKeys(address);
    }

    public void setStreetAddress3(String address){
        driver.findElement(streetAddress3).sendKeys(address);
    }

    public void setCity(String city){
        driver.findElement(cityField).sendKeys(city);
    }

    public void setStateProvince(String stateProvince){
        driver.findElement(stateProvinceField).sendKeys(stateProvince);
    }

    public void setZipPostalCode(String zipPostalCode){
        driver.findElement(zipPostalCodeField).sendKeys(zipPostalCode);
    }

    public void selectCountry(String country){
        Select countryList = new Select(driver.findElement(countrySelect));
        countryList.selectByVisibleText(country);
    }

    public void clickSaveAddressButton(){
        driver.findElement(saveAddressButton).click();
    }

    public String getAddressSavedSuccessMessage(){
        try {
            return driver.findElement(addressSavedSuccessMessage).getText();
        } catch (Exception e){
            return "Address wasn't saved successfully";
        }
    }

    public void clickAddNewAddressButton(){
        driver.findElement(addNewAddressButton).click();
    }

    public void clickAdditionalAddress1EditButton(){
        driver.findElement(additionalAddress1EditButton).click();
    }

    public void changeAddressUsage(String usage){
        By defaultUsageCheck = By.id(usage);
        driver.findElement(defaultUsageCheck).click();
    }

    public void clickAdditionalAddress1DeleteButton(){
        driver.findElement(additionalAddress1DeleteButton).click();
    }

    public void clickConfirmAdditionalAddressDeletion(){
        driver.findElement(additionalAddressConfirmDeletionButton).click();
    }
}
