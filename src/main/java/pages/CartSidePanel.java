package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartSidePanel {

    WebDriver driver;
    private By emptyCartText = By.xpath("//*[@id='minicart-content-wrapper']/div[2]/strong");
    private By editAndViewCartButton = By.className("viewcart");
    private By proceedToCheckoutButton = By.id("top-cart-btn-checkout");

    public CartSidePanel(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmptyCartText(){
        try{
            return driver.findElement(emptyCartText).getText();
        }catch (Exception e){
            return "Empty cart message didn't appear";
        }
    }

    public ShoppingCartPage viewAndEditCart(){
        driver.findElement(editAndViewCartButton).click();
        return new ShoppingCartPage(driver);
    }

    public CheckoutPage clickProceedToCheckout(){
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }
}
