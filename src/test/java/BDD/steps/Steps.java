package BDD.steps;

import base.BaseTests;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.ProductPage;
import pages.ShoppingCartPage;

import java.util.HashMap;

public class Steps extends BaseTests {

    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;


    @Given("I am in the home page")
    public void I_am_in_the_home_page(){
        setUp();
    }

    @When("I log in to the website")
    public void I_log_in_to_the_website() {
        HashMap<String, String> loginCredentials = getLoginCredentials();
        homePage.clickLoginButton().completeLogin(loginCredentials.get("email"), loginCredentials.get("password"));
    }

    @When("I choose product and add product to cart")
    public void I_choose_product_and_add_product_to_cart() {
        productPage = homePage.addThirdProductToCart();
    }

    @When("Choose product options")
    public void Choose_product_options(){
        productPage.chooseSizeAndColor("XL", "Gray");
        productPage.clickAddToCart();
    }

    @Then("I should be able to complete checkout")
    public void i_should_be_able_to_complete_checkout() {
        shoppingCartPage = productPage.goToShoppingCart();
        checkoutPage = shoppingCartPage.proceedToCheckout();
        chooseAddress();
        checkoutPage.selectShippingMethod();
        checkoutPage.clickNext();
    }

    @Then("place order")
    public void place_order() {
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getPurchaseCompleteMessage(),
                "Thank you for your purchase!",
                "Purchase complete successfully message didn't appear");
        tearDown();
    }

    private void chooseAddress(){
        if(!checkoutPage.isAddressSaved()){
            HashMap<String, String> addressData = getAddressesData().get(0);
            checkoutPage.fillAddressData(addressData);
        }
    }
}
