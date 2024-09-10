package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    WebDriver driver;
    By addToCartButton = By.id("product-addtocart-button");
    By productName = By.className("base");
    By productAddedToCartMessage = By.xpath("//div[@data-ui-id='message-success']/div");

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
}
