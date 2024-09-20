package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FilterProductsPage {

    private final WebDriver driver;
    private final Actions actions;
    private final By sizeTab = By.xpath("//*[@id='narrow-by-list']/div[2]");
    private final By colorTab = By.xpath("//*[@id='narrow-by-list']/div[3]");
    private final By firstProductBlock = By.xpath("//ol[@class = 'products list items product-items']/li[@class = 'item product product-item'][1]");
    private final By firstProductName = By.xpath("//ol[@class = 'products list items product-items']/li[@class = 'item product product-item'][1]//strong[contains(@class, product-item-name)]");
    private final By getFirstProductAddToCart = By.xpath("//ol[@class = 'products list items product-items']/li[@class = 'item product product-item'][1]//button");
    private final By productAddedToCartMessage = By.xpath("//div[@data-ui-id='message-success']/div");
    private final By allProductsPrices = By.xpath("//ol[contains(@class, 'products')]/li//span[@class='price']");
    private final By sortBySelect = By.id("sorter");
    private final By sortAscButton = By.xpath("//a[@title='Set Ascending Direction']");
    private final By sortDescButton = By.xpath("//a[@title='Set Descending Direction']");

    public FilterProductsPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void selectSizeAndColor(String size, String color){
        selectSize(size);
        selectColor(color);
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

    public ProductPage addFirstProductToCartWithoutOptions(){
        actions.
                moveToElement(driver.findElement(firstProductBlock)).
                moveToElement(driver.findElement(getFirstProductAddToCart)).
                click().perform();
        return new ProductPage(driver);
    }

    public String getProductAddedToCartMessage(){
        try{
            return driver.findElement(productAddedToCartMessage).getText();
        } catch (Exception e){
            return "Product added to cart message didn't appear";
        }
    }

    public boolean isAllProductsSizeSelected(String size){
        By productsSizeButton = By.xpath("//div[@option-label='" + size + "']");
        List<WebElement> productsSizeElements = driver.findElements(productsSizeButton);
        for (WebElement productsSizeElement : productsSizeElements) {
            if (!productsSizeElement.getAttribute("aria-checked").equals("true"))
                return false;
        }
        return true;
    }
    public boolean isAllProductsColorSelected(String color){
        By productsColorButton = By.xpath("//div[@option-label='" + color + "']");
        List<WebElement> productsColorElements = driver.findElements(productsColorButton);
        for (WebElement productsColorElement : productsColorElements) {
            if (!productsColorElement.getAttribute("aria-checked").equals("true"))
                return false;
        }
        return true;
    }

    public void sortByPriceDesc(){
        Select sortByList = new Select(driver.findElement(sortBySelect));
        sortByList.selectByVisibleText("Price");
        driver.findElement(sortDescButton).click();
    }

    public void sortByPriceAsc(){
        Select sortByList = new Select(driver.findElement(sortBySelect));
        sortByList.selectByVisibleText("Price");
//        driver.findElement(sortAscButton).click();
    }

    public double[] getAllProductsPrices(){
        List<WebElement> priceTagsElements =  driver.findElements(allProductsPrices);
        double[] prices = new double[priceTagsElements.size()];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Double.parseDouble(priceTagsElements.get(i).getText().substring(1));
        }
        return prices;
    }

    public boolean isPricesSortedDesc(){
        double[] prices = getAllProductsPrices();
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPricesSortedAsc(){
        double[] prices = getAllProductsPrices();
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] > prices[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
