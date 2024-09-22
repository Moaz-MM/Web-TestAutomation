package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    private final By logo = By.className("logo");
    private final By registerButton = By.linkText("Create an Account");
    private final By loginButton = By.linkText("Sign In");
    private final By searchInput = By.id("search");
    private final By searchButton = By.xpath("//button[@title='Search']");
    private final By changeButton = By.className("switch");
    private final By myAccountButton = By.linkText("My Account");
    private final By myWishListButton = By.linkText("My Wish List");
    private final By signoutButton = By.linkText("Sign Out");
//    private final By cartIcon = By.className("showcart");
//    private final By addToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
    private final By listOfProducts = By.className("product-items");
    private final By thirdProductBlock = By.xpath("//li[@class='product-item'][3]");
    private final By thirdProductAddToCartButton = By.xpath("//li[@class='product-item'][3]//button");
    private final By fourthProductBlock = By.xpath("//li[@class='product-item'][4]");
    private final By fourthProductName = By.xpath("//li[@class='product-item'][4]//a[@class='product-item-link']");
    private final By fourthProductLSize = By.xpath("//li[@class='product-item'][4]//div[@option-id='169']");
    private final By fourthProductBlackColor = By.xpath("//li[@class='product-item'][4]//div[@option-id='49']");
    private final By fourthProductAddToWishlistButton = By.xpath("//li[@class='product-item'][4]//a[@title='Add to Wish List']");
    private final By menCategoryButton = By.id("ui-id-5");
    private final By menTopsButton = By.id("ui-id-17");
    private final By menJacketsButton = By.id("ui-id-19");

    public HomePage(WebDriver driver){
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickLogo(){
        driver.findElement(logo).click();
    }

    public void scrollToProducts(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        String script = "arguments[0].scrollIntoView();";
        jsExecutor.executeScript(script, driver.findElement(listOfProducts));
    }

    public ProductPage addThirdProductToCart(){
        actions.
                moveToElement(driver.findElement(thirdProductBlock)).
                moveToElement(driver.findElement(thirdProductAddToCartButton)).click().perform();
        return new ProductPage(driver);
    }

    public String getFourthProductName(){
        return driver.findElement(fourthProductName).getText();
    }

    public void selectOptionsForFourthProduct(){
        actions.moveToElement(driver.findElement(fourthProductLSize)).click().
                moveToElement(driver.findElement(fourthProductBlackColor)).click().
                perform();
    }

    public MyWishListPage addFourthProductToWishlist(){
        actions.
                moveToElement(driver.findElement(fourthProductBlock)).
                moveToElement(driver.findElement(fourthProductAddToWishlistButton)).click().perform();
        return new MyWishListPage(driver);
    }

    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public RegisterPage clickRegisterButton(){
        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }

    public MyAccountPage goToMyAccountPage(){
        wait.until(ExpectedConditions.elementToBeClickable(changeButton));
        driver.findElement(changeButton).click();
        driver.findElement(myAccountButton).click();
        return new MyAccountPage(driver);
    }

    public MyWishListPage goToMyWishListPage(){
        driver.findElement(changeButton).click();
        driver.findElement(myWishListButton).click();
        return new MyWishListPage(driver);
    }

    public void logOut(){
        driver.findElement(changeButton).click();
        driver.findElement(signoutButton).click();
    }

    public SearchPage search(String searchString){
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(searchString + Keys.ENTER);
        return new SearchPage(driver);
    }

    public boolean isSearchButtonClickable(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        }catch (Exception e){}
        return driver.findElement(searchButton).isEnabled();
    }

    public FilterProductsPage chooseMenJackets(){
        actions.
                moveToElement(driver.findElement(menCategoryButton)).
                moveToElement(driver.findElement(menTopsButton)).
                moveToElement(driver.findElement(menJacketsButton)).
                click().perform();
        return new FilterProductsPage(driver);
    }
}
