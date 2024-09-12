package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FilterProductsPage {

    private WebDriver driver;
    private Actions actions;
    private By sizeTab = By.xpath("//*[@id='narrow-by-list']/div[2]");
    private By colorTab = By.xpath("//*[@id='narrow-by-list']/div[3]");
    private By firstProductBlock = By.xpath("//ol[@class = 'products list items product-items']/li[@class = 'item product product-item'][1]");
    private By firstProductName = By.xpath("//ol[@class = 'products list items product-items']/li[@class = 'item product product-item'][1]//strong[contains(@class, product-item-name)]");
    private By getFirstProductAddToCart = By.xpath("//ol[@class = 'products list items product-items']/li[@class = 'item product product-item'][1]//button");
    private By productAddedToCartMessage = By.xpath("//div[@data-ui-id=\"message-success\"]/div");

    public FilterProductsPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void selectSize(String size){
        String sizeXPath = "//div[@option-label='" + size + "' and @tabindex='-1']";
        By chosenSizeButton = By.xpath(sizeXPath);
        driver.findElement(sizeTab).click();
        driver.findElement(chosenSizeButton).click();
    }

    public void selectColor(String color){
        String colorXPath = "//div[@option-label='" + color + "' and @tabindex='-1']";
        By chosenColorButton = By.xpath(colorXPath);
        driver.findElement(colorTab).click();
        driver.findElement(chosenColorButton).click();
    }

    public String addFirstProductToCart(){
        actions.
                moveToElement(driver.findElement(firstProductBlock)).
                moveToElement(driver.findElement(getFirstProductAddToCart)).
                click().perform();
        return driver.findElement(firstProductName).getText();
    }

    public String getProductAddedToCartMessage(){
        try{
            return driver.findElement(productAddedToCartMessage).getText();
        } catch (Exception e){
            return "Product added to cart message didn't appear";
        }
    }
}
