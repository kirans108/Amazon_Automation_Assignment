package com.test;

import com.selenium.base.BaseClass;
import com.selenium.pages.SearchProduct;
import org.testng.annotations.*;

public class searchProductTest extends BaseClass {
    SearchProduct searchProduct;
    @BeforeMethod
    public void initialise(){
        setUp();
        searchProduct=new SearchProduct(driver);
    }
    @Test(priority = 0)
    public void searchProperty() throws InterruptedException {
        searchProduct.searchProduct();
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
