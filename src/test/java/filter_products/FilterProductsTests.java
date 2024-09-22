package filter_products;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FilterProductsPage;
import pages.ProductPage;

public class FilterProductsTests extends BaseTests {

    @Description("Given I'm shopping products, When I choose color and size, Then items should be filtered accordingly and options selected for all results")
    @Story("Filter Products")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void filterBySizeAndColor(){
        FilterProductsPage filterProductsPage = homePage.chooseMenJackets();
        String size = "XL";     String color = "Red";
        filterProductsPage.selectSizeAndColor(size, color);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(filterProductsPage.isAllProductsSizeSelected(size), "Products size wasn't selected correctly");
        softAssert.assertTrue(filterProductsPage.isAllProductsColorSelected(color), "Products color wasn't selected correctly");
        softAssert.assertAll();
    }

    @Description("Given I'm shopping products, When I sort by price descending, Then items should be sorted accordingly")
    @Story("Filter Products")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void sortByPriceDescending(){
        FilterProductsPage filterProductsPage = homePage.chooseMenJackets();
        filterProductsPage.sortByPriceDesc();
        Assert.assertTrue(filterProductsPage.isPricesSortedDesc(), "Products aren't sorted desc by prices");
    }

    @Description("Given I'm shopping products, When I sort by price ascending, Then items should be sorted accordingly")
    @Story("Filter Products")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void sortByPriceAscending(){
        FilterProductsPage filterProductsPage = homePage.chooseMenJackets();
        filterProductsPage.sortByPriceAsc();
        Assert.assertTrue(filterProductsPage.isPricesSortedAsc(), "Products aren't sorted asc by prices");
    }

    @Description("Given I'm shopping products and chose their options, When I add a product to cart, Then it should be added successfully to cart")
    @Story("Filter Products")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void addProductToCartAfterChoosingOptions(){
        FilterProductsPage filterProductsPage = homePage.chooseMenJackets();
        String size = "XL";     String color = "Red";
        filterProductsPage.selectSizeAndColor(size, color);
        String productName = filterProductsPage.addFirstProductToCart();
        Assert.assertEquals(filterProductsPage.getProductAddedToCartMessage(),
                "You added " + productName + " to your shopping cart.",
                "Product added to cart message didn't appear");
    }

    @Description("Given I'm shopping products and didn't choose their options, When I add a product to cart, Then it should be added successfully to cart")
    @Story("Filter Products")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void addProductToCartWithoutChoosingOptions(){
        FilterProductsPage filterProductsPage = homePage.chooseMenJackets();
        ProductPage productPage = filterProductsPage.addFirstProductToCartWithoutOptions();
        Assert.assertEquals(productPage.getNoticeMessage(),
                "You need to choose options for your item.",
                "Notice message that user needs to choose options for the product didn't appear");
    }
}
