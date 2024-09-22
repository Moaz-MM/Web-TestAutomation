package wishlist;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyWishListPage;

import java.util.HashMap;

public class WishlistTests extends BaseTests {

    @Description("Given I'm interacting with a product, When I add it to wishlist, Then it should be added and success message should appear with a link to the wishlist page")
    @Story("Wishlist")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 1)
    public void addProductToWishList(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        homePage.clickLogo();
        homePage.scrollToProducts();
        String productName = homePage.getFourthProductName();
        MyWishListPage myWishListPage = homePage.addFourthProductToWishlist();
        Assert.assertEquals(myWishListPage.getSuccessMessage(), productName + " has been added to your Wish List. Click here to continue shopping.", "Product added to wishlist success message didn't appear");
        homePage.logOut();
    }

    @Description("Given I'm in wishlist page and product options are not selected, When I click add all to cart, Then error message should appear stating I need to select product options")
    @Story("Wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void addAllToCartWithoutSelectingProductOptions(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        homePage.clickLogo();
        homePage.scrollToProducts();
        String productName = homePage.getFourthProductName();
        MyWishListPage myWishListPage = homePage.addFourthProductToWishlist();
        Assert.assertEquals(myWishListPage.getSuccessMessage(), productName + " has been added to your Wish List. Click here to continue shopping.", "Product added to wishlist success message didn't appear");
        myWishListPage.clickAddAllToCartButton();
        Assert.assertEquals(myWishListPage.getErrorMessage(), "You need to choose options for your item for \"" + productName + "\".");
        homePage.logOut();
    }

    @Description("Given I'm in wishlist page, When I delete an item, Then it should be deleted successfully")
    @Story("Wishlist")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void deleteProductFromWishList(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        MyWishListPage myWishListPage = homePage.goToMyWishListPage();
        String productName = myWishListPage.getFirstProductName();
        myWishListPage.deleteFirstProduct();
        Assert.assertEquals(myWishListPage.getSuccessMessage(), productName + " has been removed from your Wish List.", "Product successfully deleted from wishlist message didn't appear");
        homePage.logOut();
    }

    @Description("Given I'm in my wishlist page and item options were previously selected, When I click add all to cart, Then it should be added to cart successfully")
    @Story("Wishlist")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 4)
    public void addAllToCartAfterSelectingProductOptions(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        homePage.clickLogo();
        homePage.scrollToProducts();
        String productName = homePage.getFourthProductName();
        homePage.selectOptionsForFourthProduct();
        MyWishListPage myWishListPage = homePage.addFourthProductToWishlist();
        Assert.assertEquals(myWishListPage.getSuccessMessage(), productName + " has been added to your Wish List. Click here to continue shopping.", "Product added to wishlist success message didn't appear");
        myWishListPage.clickAddAllToCartButton();
        Assert.assertEquals(myWishListPage.getSuccessMessage(), "You added "+ productName + " to your shopping cart.", "All added to cart success message didn't appear");
        homePage.logOut();
    }
}