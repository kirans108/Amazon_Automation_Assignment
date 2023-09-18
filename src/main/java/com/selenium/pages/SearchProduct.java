package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SearchProduct {
    WebDriver driver;
    @CacheLookup
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchBar;
    @CacheLookup
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement searchButton;
    @CacheLookup
    @FindBy(xpath = "(//span[@class='a-size-medium a-color-base a-text-normal'])[3]")
    WebElement productOne;
    @CacheLookup
    @FindBy(xpath = "//span[@id='productTitle']")
    WebElement searchedProduct;
    @CacheLookup
    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    WebElement addToCart;

    public SearchProduct(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void searchProduct() throws InterruptedException {
        searchBar.sendKeys("Iphone");
        searchButton.click();
        List<WebElement> listOfProducts=driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
        System.out.println(listOfProducts.size());

        for (WebElement webElement : listOfProducts) {
            String name = webElement.getText();
            System.out.println(name);
        }
        productOne.click();
        Thread.sleep(2000);
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String productName=searchedProduct.getText();
                Assert.assertEquals(productName,"Apple iPhone 14 Pro (128 GB) - Deep Purple");
            }
        }
        addToCart.click();
    }
}
