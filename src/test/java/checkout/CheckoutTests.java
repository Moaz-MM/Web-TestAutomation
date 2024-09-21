package checkout;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.HashMap;

public class CheckoutTests extends BaseTests {

    private String lastOrderNumber = "";

    @Test(priority = 1)
    public void checkoutAndPlaceOrderSuccessfully(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        homePage.scrollToProducts();
        ProductPage productPage = homePage.addThirdProductToCart();
        productPage.chooseSize("L");
        productPage.chooseColor("Gray");
        productPage.clickAddToCart();
        Assert.assertEquals(productPage.getAddedToCartMessage(),
                "You added " + productPage.getProductName() + " to your shopping cart.",
                "Product added to cart message didn't appear");
        ShoppingCartPage shoppingCartPage = productPage.goToShoppingCart();
        CheckoutPage checkoutPage = shoppingCartPage.proceedToCheckout();
        if(!checkoutPage.isAddressSaved()){
        HashMap<String, String> addressData = getAddressesData().get(0);
        checkoutPage.fillAddressData(addressData);
        }
        checkoutPage.selectShippingMethod();
        checkoutPage.clickNext();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getPurchaseCompleteMessage(),
                "Thank you for your purchase!",
                "Purchase complete successfully message didn't appear");
        lastOrderNumber = checkoutPage.getOrderNumber();
        homePage.logOut();
    }

    @Test(priority = 2)
    public void verifyOrderIsAddedToMyOrders(){
        HashMap<String, String> credentials = getLoginCredentials();
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setEmail(credentials.get("email"));
        loginPage.setPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = homePage.goToMyAccountPage();
        MyOrdersPage myOrdersPage = myAccountPage.clickMyOrdersButton();
        Assert.assertEquals(myOrdersPage.getLastOrderNumber(), lastOrderNumber, "Last order number doesn't match");
    }

}
