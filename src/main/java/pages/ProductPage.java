package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    WebDriver driver;
    By addToCartButton = By.id("product-addtocart-button");
    By productName = By.className("base");
    By productAddedToCartMessage = By.xpath("//div[@data-ui-id='message-success']/div");
    By noticeMessage = By.xpath("//div[@data-ui-id='message-notice']/div");
    By sizeIsRequiredText = By.id("super_attribute[143]-error");
    By colorIsRequiredText = By.id("super_attribute[93]-error");
    By quantityField = By.id("qty");
    By shoppingCartButton = By.xpath("//div[@data-ui-id='message-success']/div/a");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void choseSize(String size){
        String sizeXpath = "//*[@option-label='" + size + "']";
        By sizeButton = By.xpath(sizeXpath);
        driver.findElement(sizeButton).click();
    }

    public void choseColor(String color){
        String colorXpath = "//*[@option-label='" + color + "']";
        By colorButton = By.xpath(colorXpath);
        driver.findElement(colorButton).click();
    }

    public void choseQuantity(int quantity){
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
    }

    public void clickAddToCart(){
        driver.findElement(addToCartButton).click();
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }

    public String getAddedToCartMessage(){
        try{
            return driver.findElement(productAddedToCartMessage).getText();
        } catch (Exception e){
            return "Product added to cart message didn't appear";
        }
    }

    public String getNoticeMessage(){
        try {
            return driver.findElement(noticeMessage).getText();
        } catch (Exception e){
            return "Notice message didn't appear";
        }
    }

    public ShoppingCartPage goToShoppingCart(){
        driver.findElement(shoppingCartButton).click();
        return new ShoppingCartPage(driver);
    }
}
