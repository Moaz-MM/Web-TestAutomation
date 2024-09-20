package filter_products;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FilterProductsPage;
import pages.ProductPage;

public class FilterProductsTests extends BaseTests {

    @Test(priority = 1)
    public void filterBySizeAndColor(){
        FilterProductsPage filterProductsPage = homePage.choseMenJackets();
        String size = "XL";     String color = "Red";
        filterProductsPage.selectSizeAndColor(size, color);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(filterProductsPage.isAllProductsSizeSelected(size), "Products size wasn't selected correctly");
        softAssert.assertTrue(filterProductsPage.isAllProductsColorSelected(color), "Products color wasn't selected correctly");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void sortByPriceDescending(){
        FilterProductsPage filterProductsPage = homePage.choseMenJackets();
        filterProductsPage.sortByPriceDesc();
        Assert.assertTrue(filterProductsPage.isPricesSortedDesc(), "Products aren't sorted desc by prices");
    }

    @Test(priority = 3)
    public void sortByPriceAscending(){
        FilterProductsPage filterProductsPage = homePage.choseMenJackets();
        filterProductsPage.sortByPriceAsc();
        Assert.assertTrue(filterProductsPage.isPricesSortedAsc(), "Products aren't sorted asc by prices");
    }

    @Test
    public void addProductToCartAfterChoosingOptions(){
        FilterProductsPage filterProductsPage = homePage.choseMenJackets();
        String size = "XL";     String color = "Red";
        filterProductsPage.selectSizeAndColor(size, color);
        String productName = filterProductsPage.addFirstProductToCart();
        Assert.assertEquals(filterProductsPage.getProductAddedToCartMessage(),
                "You added " + productName + " to your shopping cart.",
                "Product added to cart message didn't appear");
    }

    @Test
    public void addProductToCartWithoutChoosingOptions(){
        FilterProductsPage filterProductsPage = homePage.choseMenJackets();
        ProductPage productPage = filterProductsPage.addFirstProductToCartWithoutOptions();
        Assert.assertEquals(productPage.getNoticeMessage(),
                "You need to choose options for your item.",
                "Notice message that user needs to choose options for the product didn't appear");
    }
}
