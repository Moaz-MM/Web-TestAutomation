package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {

    private final WebDriver driver;
    private final By proceedToCheckoutButton = By.xpath("//button[@data-role='proceed-to-checkout']");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage proceedToCheckout(){
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }
}
