package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;
    Actions actions;
    private By logo = By.className("logo");
    private By registerButton = By.linkText("Create an Account");
    private By loginButton = By.linkText("Sign In");
    private By searchInput = By.id("search");
    private By changeButton = By.className("switch");
    private By myAccountButton = By.linkText("My Account");
    private By myWishListButton = By.linkText("My Wish List");
    private By signoutButton = By.linkText("Sign Out");
    private By cartIcon = By.className("showcart");
    private By addToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
    private By listOfProducts = By.className("product-items");
    private By thirdProductBlock = By.xpath("//*[contains(@class, 'product-items')]/li[3]");
    private By thirdProductAddToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
    private By menCategoryButton = By.id("ui-id-5");
    private By menTopsButton = By.id("ui-id-17");
    private By menJacketsButton = By.id("ui-id-19");

    public HomePage(WebDriver driver){
        this.driver = driver;
        actions = new Actions(driver);
    }

//    public HomePage clickLogo(){
//        driver.findElement(logo).click();
//        return this;
//    }

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

    public void addProductToCart(){
        driver.findElement(addToCartButton).click();
    }

    public void signout(){
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
