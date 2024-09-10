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
    private By signoutButton = By.xpath("//li[@class='authorization-link']/a");
    private By cartIcon = By.className("showcart");
    private By editAndViewCartButton = By.className("viewcart");
    private By emptyCartText = By.xpath("//*[@id='minicart-content-wrapper']/div[2]/strong");
    private By addToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
    private By listOfProducts = By.className("product-items");
    private By thirdProductBlock = By.xpath("//*[contains(@class, 'product-items')]/li[3]");
    private By thirdProductAddToCartButton = By.xpath("//*[contains(@class, 'product-items')]/li[3]//button");
    private By proceedToCheckoutButton = By.id("top-cart-btn-checkout");

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

        actions.moveToElement(driver.findElement(thirdProductBlock)).
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

    public void clickCartIcon(){
        driver.findElement(cartIcon).click();
    }

    public ShoppingCartPage viewAndEditCart(){
        driver.findElement(editAndViewCartButton).click();
        return new ShoppingCartPage(driver);
    }

    public CheckoutPage clickProceedToCheckout(){
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }

    public String getEmptyCartText(){
        try{
            return driver.findElement(emptyCartText).getText();
        }catch (Exception e){
            return "Empty cart message didn't appear";
        }
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

    public void chooseCategory(String genericCategory, String clothesCategory, String clothesName){
        //TODO
    }
}
