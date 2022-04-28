package com.example.tsstema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageMobile extends BaseTest {

    @Test
    public void openHomepageMobile(){
        goToHomePageMobile();
    }

    @Test
    public void checkCVButton(){
        goToHomePageMobile();
//        WebElement butonCatalogVirtual = webDriver.findElement(By.xpath("//div[1]/div[2]/div/div/div/a"));
//        Assert.assertTrue(butonCatalogVirtual.isDisplayed());

    }

}
