package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver(getChromeoptions());
        goHome();
        homePage = new HomePage(driver);
    }

    private void goHome(){
        String websiteURL = "https://magento.softwaretestingboard.com/";
        driver.get(websiteURL);
    }

    private ChromeOptions getChromeoptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        return chromeOptions;
    }

//    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
