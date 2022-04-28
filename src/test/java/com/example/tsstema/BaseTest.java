package com.example.tsstema;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
public class BaseTest {
    public Actions action;

    public WebDriver webDriver;
    // /html/body/div[3]/aside[1]/section/div/div/h4

    @BeforeTest
    public void init() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        action = new Actions(webDriver);
    }

    public void goToHomePageFullScreen(){
        webDriver.manage().window().maximize();
        webDriver.get("https://www.scoalaluceafarul.ro");
    }

    public void goToCatalogLoginFullScreen(){
        webDriver.manage().window().maximize();
        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/login.php");
    }

    public void goToHomePageMobile(){
        webDriver.manage().window().setSize(new Dimension(350, 700));
        webDriver.get("https://www.scoalaluceafarul.ro");
    }

    @AfterTest
    public void tearDown(){
        webDriver.close();
    }

//    oid foo(String a, Integer... b) {
//        Integer b1 = b.length > 0 ? b[0] : 0;
//        Integer b2 = b.length > 1 ? b[1] : 0;
//        //...
//    }
//
//    foo("a");
//    foo("a", 1, 2);

//    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", "F:\\drivers\\chromedriver.exe");
//        //create chrome instance
//        webDriver = new ChromeDriver();
//        driver.get("http://www.facebook.com/");
//        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a"));
//        element.sendKeys("abc@gmail.com");
//        WebElement element = driver.findElement(By.xpath("//input[@name='pass']"));
//        element.sendKeys("abbaas");
//        WebElement button = driver.findElement(By.xpath("//input[@name='login']"));
//        button.click();
//    }
}
