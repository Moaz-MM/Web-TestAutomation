package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MyWishListPage {

    private final WebDriver driver;
    private final Actions actions;
    private final By successMessage = By.xpath("//div[@data-ui-id='message-success']/div");
    private final By addAllToCartButton = By.xpath("//button[@title='Add All to Cart']");
    private final By errorMessage = By.xpath("//div[@data-ui-id='message-error']/div");
    private final By firstProductBlock = By.xpath("//ol[@class='product-items']/li[@data-row]");
    private final By firstProductName = By.xpath("//ol[@class='product-items']/li[@data-row]//a[@class='product-item-link']");
    private final By firstProductDeleteButton = By.xpath("//ol[@class='product-items']/li[@data-row]//a[@data-role='remove']");

    public MyWishListPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public String getSuccessMessage(){
        try{
            return driver.findElement(successMessage).getText();
        } catch (Exception e){
            return driver.findElement(errorMessage).getText();
        }
    }

    public void clickAddAllToCartButton(){
        driver.findElement(addAllToCartButton).click();
    }

    public String getErrorMessage(){
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e){
            return "You need to choose options error message didn't appear";
        }
    }

    public String getFirstProductName(){
        try {
            return driver.findElement(firstProductName).getText();
        } catch (Exception e){
            return "No product was found";
        }
    }

    public void deleteFirstProduct(){
        actions.moveToElement(driver.findElement(firstProductBlock)).
                moveToElement(driver.findElement(firstProductDeleteButton)).
                click().perform();
    }

}
