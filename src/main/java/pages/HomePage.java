package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
    private final By changeButton = By.className("switch");
    private final By myAccountButton = By.linkText("My Account");
    private final By myWishListButton = By.linkText("My Wish List");
    private final By signoutButton = By.linkText("Sign Out");
    private final By cartIcon = By.className("showcart");
//    private final By addToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
    private final By listOfProducts = By.className("product-items");
    private final By thirdProductBlock = By.xpath("//*[contains(@class, 'product-items')]/li[3]");
    private final By thirdProductAddToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
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

    public CartSidePanel clickCartIcon(){
        driver.findElement(cartIcon).click();
        return new CartSidePanel(driver);
    }

//    public void addProductToCart(){
//        driver.findElement(addToCartButton).click();
//    }

    public void logOut(){
        driver.findElement(changeButton).click();
        driver.findElement(signoutButton).click();
    }

    public SearchPage search(String searchString){
        driver.findElement(searchInput).sendKeys(searchString + Keys.ENTER);
        return new SearchPage(driver);
    }

    public FilterProductsPage choseMenJackets(){
        actions.
                moveToElement(driver.findElement(menCategoryButton)).
                moveToElement(driver.findElement(menTopsButton)).
                moveToElement(driver.findElement(menJacketsButton)).
                click().perform();
        return new FilterProductsPage(driver);
    }
}
