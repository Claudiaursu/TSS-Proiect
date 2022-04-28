package com.example.tsstema;

import com.codeborne.selenide.commands.As;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProfilElev extends BaseTest{

    private String usernameTest = "Ciumatu Nectaria Elena";

    public void insertUsername(WebElement usernameField, String username){
        usernameField.sendKeys(username);
    }
    public void insertPassword(WebElement parolaField, String password){
        parolaField.sendKeys(password);
    }
    public void clickLogin(WebElement loginButton){
        loginButton.click();
    }

    @BeforeTest
    public void testProfile() throws InterruptedException {
        goToCatalogLoginFullScreen();
        WebElement parolaField = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[2]/input"));
        WebElement usernameField = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[1]/input"));
        WebElement loginButton = webDriver.findElement(By.name("btn_login"));
        WebElement forgotPasswordButton = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/form/div[3]/button[1]"));

        this.insertUsername(usernameField, "ciumatu.a.nectaria");
        this.insertPassword(parolaField, "elev");
        Thread.sleep(3000);
        this.clickLogin(loginButton);
    }

    @Test
    public void testLoginPage(){
        WebElement welcomeText = webDriver.findElement(By.xpath("/html/body/div[3]/aside[1]/section/div/div/h4"));
        String expectedMessage = new String("Bine ai venit, "+ this.usernameTest);
        Assert.assertEquals(expectedMessage, welcomeText.getText());
    }

    @Test
    public void testLogout(){
        WebElement profileName = webDriver.findElement(By.xpath("/html/body/header/nav/div/ul/li/a/span"));
        String expectedMessage = this.usernameTest;

        Assert.assertEquals(profileName.getText(), expectedMessage);
        profileName.click();

        WebElement logoutButton = webDriver.findElement(By.xpath("/html/body/header/nav/div/ul/li/ul/li[2]/div[2]/a"));
        logoutButton.click();

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/grading/system/login.php", currentUrl);
    }

    @Test
    public void selectSemestru() throws InterruptedException {
        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/notaelev.php");
        Thread.sleep(6000);

        WebElement semSelect = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]"));
        Select selectObject = new Select(semSelect);
        List<WebElement> allAvailableOptions = selectObject.getOptions();
        List<String> newList = new LinkedList<>();

        for (WebElement element: allAvailableOptions){
            newList.add(element.getText());
        }

        List<String> expectedSemValues = Arrays.asList("-- Selecteaza semestrul --", "1", "2");
        Assert.assertTrue(expectedSemValues.equals(newList));

    }
//
    @Test
    public void displayNote() throws InterruptedException {
        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/notaelev.php");

        WebElement semSelect = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]"));
        semSelect.click();

        WebElement sem1 = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]/option[2]"));
        sem1.click();

        WebElement searchButton = webDriver.findElement(By.xpath("/html/body/div[3]/aside[2]/section[2]/div/div/div[1]/div/form/div/button"));
        searchButton.click();

        List<WebElement> tableHeader = webDriver.findElements(By.xpath("//*[@id=\"table\"]/thead/tr/th"));
        Assert.assertEquals(tableHeader.get(1).getText(), "Materie:");
        Assert.assertEquals(tableHeader.get(2).getText(), "Nota 1:");
        Assert.assertEquals(tableHeader.get(3).getText(), "Nota 2:");
        // many more fields

        List<WebElement> person1 = webDriver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td"));
        System.out.println("$ " + person1.get(9).getText());

        WebElement casetaMentiune = webDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[11]/label"));
        Integer medieFinala = Integer.parseInt(person1.get(9).getText());
        if (medieFinala >= 5) {
            Assert.assertEquals(casetaMentiune.getText(), "Promovat");
        } else {
            Assert.assertEquals(casetaMentiune.getText(), medieFinala.toString());
        }

    }
        @Test
        public void testSearchBox(){
            webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/profesori.php");
            WebElement search = webDriver.findElement(By.xpath("/html/body/div[3]/aside[2]/section[2]/div/div/div[2]/div/div[1]/div[2]/div/label/input"));

            search.sendKeys("ina");
            List<WebElement> tableBody = webDriver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr/td[3]"));
            for( WebElement elem: tableBody ){
                System.out.println("**"+ elem.getText());
                Assert.assertEquals(elem.getText().contains("ina"), true);
            }
        }
        // test if the "Previous" button is disabled if we are on the first page
        @Test
        public void testPreviousButton(){
            webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/profesori.php");
            WebElement firstPageButton = webDriver.findElement(By.xpath("//*[@id=\"table_paginate\"]/ul/li[2]/a"));

            firstPageButton.click();
            WebElement previousButton = webDriver.findElement(By.className("disabled"));
            Assert.assertEquals(previousButton.getText(), "Previous");
        }

}
