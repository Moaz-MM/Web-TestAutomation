package search;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTests extends BaseTests {

    @Description("Given I'm in home page, When I try to search an existing product, Then the product should appear on the top of search result")
    @Story("Search")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, dataProvider = "validSearchTerms")
    public void validSearch(String searchTerm){
        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertEquals(searchPage.getFirstSearchResult(), searchTerm, "Searched item is not the first result");
    }

    @Description("Given I'm in home page, When I try to search with invalid search terms, Then warning that no results found should appear")
    @Story("Search")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2, dataProvider = "invalidSearchTerms")
    public void invalidSearch(String searchTerm){
        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertEquals(searchPage.getNoResultMessage(), "Your search returned no results.", "No result found message didn't appear");
    }

    @Description("Given I'm in home page, When I try to search with empty search field, Then search button should be disabled")
    @Story("Search")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 3)
    public void emptySearch(){
        homePage.search("");
        Assert.assertFalse(homePage.isSearchButtonClickable(), "Search button is clickable when it should be disabled because search bar is empty");
    }

    @Description("Given I'm in search result page, When I sort result by price descending, Then items should appear sorted accordingly")
    @Story("Search")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4)
    public void sortByPriceDescending(){
        SearchPage searchPage = homePage.search("jacket");
        searchPage.sortByPriceDesc();
        Assert.assertTrue(searchPage.isPricesSortedDesc(), "Products aren't sorted desc by prices");
    }

    @Description("Given I'm in search result page, When I sort result by price ascending, Then items should appear sorted accordingly")
    @Story("Search")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 5)
    public void sortByPriceAscending(){
        SearchPage searchPage = homePage.search("jacket");
        searchPage.sortByPriceAsc();
        Assert.assertTrue(searchPage.isPricesSortedAsc(), "Products aren't sorted asc by prices");
    }

    @DataProvider
    public String[] validSearchTerms(){
        String[] searches = new String[4];
        searches[0] = "Montana Wind Jacket";
        searches[1] = "Balboa Persistence Tee";
        searches[2] = "Frankie Sweatshirt";
        searches[3] = "Affirm Water Bottle";
        return searches;
    }
    @DataProvider
    public String[] invalidSearchTerms(){
        String[] searches = new String[4];
        searches[0] = "@#$%";
        searches[1] = "iphone12";
        searches[2] = "<h1>hello</h1>";
        searches[3] = "<script>alert('XSS');</script>";
        return searches;
    }
}
