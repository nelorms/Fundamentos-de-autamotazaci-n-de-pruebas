package selenium;

import PageObjects.BaseClass;
import PageObjects.HeaderPage;
import PageObjects.LoginPage;
import PageObjects.RegisterPage;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestAccount  extends BaseClass {

    @Description("Validate test login was successful")
    @Test(description = "Test Login Success")
    @Parameters({"username", "password"})
    public void Test_Login_Successful(){
        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String username = "juan.piedra@ucreativa.com";
        String password = "asdf";

        //Go To Login Page
        headerPage.clickOnMyAccount();
        headerPage.clickOnLoginButton();

        /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.finsElements(By.linkText("Wish List"));
         */

        //Llenar formulario
        loginPage.EnterEmail(username);
        loginPage.EnterPassword(password);
        loginPage.ClickSubmitButton();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());

    }
    @Description("Validate the the login is working with non valid credentials")
    @Test(description = "Test Login Not Success")
    public void Test_Login_Unsuccessful(){

        LoginPage loginPage = new LoginPage(driver);
        String username = "juan.piedra@ucreativa.com";
        String password = "ghjk";
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        loginPage.GoTo();
        loginPage.login(username, password);

        //Llenar formulario
        loginPage.login(username, password);

        WebElement alertMessage = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());

    }
    @Test
    public void Test_Create_New_Account(){
        String firstName = "Juan";
        String lastName = "Piedra";
        String email = "juan@piedra.com";
        String telephone = "11111";
        String password = "asdf";
        String expectedMessage = "Your Account Has Been Created!";


        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.GoTo();
        registerPage.FillForm("Juan", "Piedra", "juan@piedra.com", "11111", "asdf");

        Assert.assertEquals(registerPage.GetConfirmationMessage(), expectedMessage);
    }
}
