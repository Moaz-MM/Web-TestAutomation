package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchPage {

    private final WebDriver driver;
    private final By firstSearchResult = By.className("product-item-link");
    private final By noResultMessage = By.xpath("//div[@class='message notice']/div");
    private final By allProductsPrices = By.xpath("//span[@class='price']");
    private final By sortBySelect = By.id("sorter");
    private final By sortAscButton = By.xpath("//a[@title='Set Ascending Direction']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstSearchResult(){
        return driver.findElement(firstSearchResult).getText();
    }

    public String getNoResultMessage(){
        try {
            return driver.findElement(noResultMessage).getText();
        } catch (Exception e){
            return "No result found message didn't appear";
        }
    }

    public void sortByPriceDesc(){
        Select sortByList = new Select(driver.findElement(sortBySelect));
        sortByList.selectByVisibleText("Price");
    }

    public void sortByPriceAsc(){
        Select sortByList = new Select(driver.findElement(sortBySelect));
        sortByList.selectByVisibleText("Price");
        driver.findElement(sortAscButton).click();
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
