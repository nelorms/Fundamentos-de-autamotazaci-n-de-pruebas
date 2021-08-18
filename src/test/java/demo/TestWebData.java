package demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWebData {

    @Test(dataProvider = "numeroProvider")
    public void Suma(int numero){
        Assert.assertTrue(numero > 0);
        System.out.println("Numero: + numero");
    }

    @DataProvider(name = "numeroProvider")
    public Object[][] methodNumeroProvider(){
        return new Object[][]{
                {5}, {6}
        };
    }
}
