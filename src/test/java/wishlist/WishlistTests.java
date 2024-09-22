package wishlist;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyWishListPage;

import java.util.HashMap;

public class WishlistTests extends BaseTests {

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