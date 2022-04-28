package com.example.tsstema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginCatalog extends BaseTest {

    public void insertUsername(WebElement usernameField, String username){
        usernameField.sendKeys(username);
    }
    public void insertPassword(WebElement parolaField, String password){
        parolaField.sendKeys(password);
    }

    public void clickLogin(WebElement loginButton){
        loginButton.click();
    }

    @Test
    public void Login() throws InterruptedException {
        goToCatalogLoginFullScreen();

        WebElement parolaField = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[2]/input"));
        WebElement usernameField = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[1]/input"));
        WebElement loginButton = webDriver.findElement(By.name("btn_login"));
        WebElement forgotPasswordButton = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[3]/button[1]"));

        this.insertUsername(usernameField, "titu.maria");
        this.insertPassword(parolaField, "Marimar.64");
        Thread.sleep(3000);
        this.clickLogin(loginButton);

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/grading/system/pages/dashboard/dashboard.php", currentUrl);

    }

    @Test
    public void failedLogin() throws InterruptedException {
        goToCatalogLoginFullScreen();

        WebElement parolaField = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[2]/input"));
        WebElement usernameField = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[1]/input"));
        WebElement loginButton = webDriver.findElement(By.name("btn_login"));
        WebElement forgotPasswordButton = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[3]/button[1]"));

        this.insertUsername(usernameField, "test_fail");
        this.insertPassword(parolaField, "test");
        Thread.sleep(3000);
        this.clickLogin(loginButton);

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/grading/system/pages/invalid.php", currentUrl);
    }

}

