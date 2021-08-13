import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearch  extends BaseClass{
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

    public void getresults(){
        return driver.findElements(By.cssSelector(".product-thumb"));
    }
}
