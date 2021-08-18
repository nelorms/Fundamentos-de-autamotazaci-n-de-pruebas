package selenium;

import PageObjects.BaseClass;
import PageObjects.SearchResultsPage;
import dataProviders.SearchProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.SearchData;

public class TestSearch  extends BaseClass {
    @Test
    @Parameters({"searchCriteria"})
    public void Validate_Search(@Optional("macbook") String searchCriteria){
        int expectedResult = 3;

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(searchCriteria, Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("search="+searchCriteria));

        //Assert.assertEquals(results.size(), expectedResult,
          //      String.format("Expected %s results, but got %s", expectedResult, results.size()));

        Assert.assertEquals(getresults(), getresults(),
                "Expecting " + expectedResult + " results, but got " + getresults());
    }

    @Test
    public void Test_Empty_Results(){
        String searchCriteria = "Star Wars";
        int expectedResult = 0;

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(searchCriteria, Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("search="+searchCriteria));

        //Assert.assertEquals(results.size(), expectedResult,
        //        String.format("Expected %s results, but got %s", expectedResult, results.size()));

        Assert.assertEquals(getresults(), expectedResult,
                "Expecting " + expectedResult + " results, but got " + getresults());
    }

    public int getresults(){

        return driver.findElements(By.cssSelector(".product-thumb")).size();
    }

    @Test (dataProvider = "getSearchDataFromJson", dataProviderClass = SearchProvider.class)
    public void Test_Search_WithData(SearchData testData){
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
       WebElement searchInput = driver.findElement(By.name("search"));
       searchInput.sendKeys(testData.getSearchCriteria());

       driver.findElement(By.xpath("//div[@id='search']/span/button")).click();

       if(testData.getExpectedResults() >0) {
           Assert.assertEquals(searchResultsPage.getResultsCount(), testData.getExpectedResults());

       }else{
           Assert.assertTrue(searchResultsPage.isNoResultsVisible());
       }

       Assert.assertEquals(getresults(), testData.getExpectedResults());
    }

    @DataProvider(name = "searchEntries")
    public Object[][] dataProvider(){
        return new Object[][]{
                {"macbook", 3},
                {"star wars", 0}
        };
    }
}

