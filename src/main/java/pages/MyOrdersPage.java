package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyOrdersPage {

    private final WebDriver driver;
    private final By lastOrderNumber = By.xpath("//*[@id='my-orders-table']//td[1]");

    public MyOrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLastOrderNumber(){
        try {
            return driver.findElement(lastOrderNumber).getText();
        }catch (Exception e){
            return "Can't find last order number";
        }
    }
}
