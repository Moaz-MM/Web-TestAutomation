package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;
    private By firstSearchResult = By.className("product-item-link");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstSearchResult(){
        return driver.findElement(firstSearchResult).getText();
    }
}
