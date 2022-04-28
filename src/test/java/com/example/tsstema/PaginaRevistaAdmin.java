package com.example.tsstema;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.List;

public class PaginaRevistaAdmin extends BaseTest{

    @BeforeTest
    public void goToRevista() {

        webDriver.manage().window().maximize();
        webDriver.get("https://www.scoalaluceafarul.ro/revista/index.php");
    }

    @Test
    public void testLogin() throws InterruptedException {
        WebElement butonLogin = webDriver.findElement(By.xpath("//*[@id=\"navi\"]/ul/li[6]/a"));
        butonLogin.click();

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/revista/admin/login.php", currentUrl);

        WebElement inputUsername = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/div[1]/input"));
        WebElement inputParola = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/input"));

        inputUsername.sendKeys("petcu.steliana");
        inputParola.sendKeys("19luceafarul99");

        WebElement butonLogare = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/button"));
        butonLogare.click();
        Thread.sleep(6000);
        String revistaAdminLoggedIn = webDriver.getCurrentUrl();
        Assert.assertEquals(revistaAdminLoggedIn, "https://www.scoalaluceafarul.ro/revista/admin/index.php");

    }

    @Test
    public void testAdaugarePostare() throws InterruptedException {
        WebElement butonLogin = webDriver.findElement(By.xpath("//*[@id=\"navi\"]/ul/li[6]/a"));
        butonLogin.click();

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/revista/admin/login.php", currentUrl);

        WebElement inputUsername = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/div[1]/input"));
        WebElement inputParola = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/input"));

        inputUsername.sendKeys("petcu.steliana");
        inputParola.sendKeys("19luceafarul99");

        WebElement butonLogare = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/button"));
        butonLogare.click();
        Thread.sleep(1000);

        webDriver.get("https://www.scoalaluceafarul.ro/revista/admin/new-post.php");
        String paginaPostareNoua = webDriver.getCurrentUrl();
        Assert.assertEquals(paginaPostareNoua, "https://www.scoalaluceafarul.ro/revista/admin/new-post.php");

        WebElement inputTitlu = webDriver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div[3]/div[2]/form/div/div[1]/div/div/div[1]/div/input"));
        inputTitlu.sendKeys("Test TSS!");

        webDriver.switchTo().frame("mce_0_ifr");
        WebElement inputDescriere = webDriver.findElement(By.id("tinymce"));
        inputDescriere.sendKeys("Lorem ipsum");

        webDriver.switchTo().parentFrame();
        webDriver.switchTo().frame("mce_2_ifr");
        WebElement inputContinut = webDriver.findElement(By.id("tinymce"));
        inputContinut.sendKeys("<3");
        Thread.sleep(3000);

        webDriver.switchTo().parentFrame();
        Thread.sleep(3000);
        WebElement inputFisier = webDriver.findElement(By.cssSelector("input[name='image']"));
        inputFisier.sendKeys("D:/porto_54_990x660.jpg");

        WebElement butonAdaugaPostare = webDriver.findElement(By.xpath("//div[@class='form-group']//button[@type='submit' and @value='Submit']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(butonAdaugaPostare).click().perform();

        webDriver.get("http://www.scoalaluceafarul.ro/revista/index.php");

        WebElement titluPostare = webDriver.findElement(By.xpath("//*[@id=\"liber\"]/div[1]/div/div[1]/a[1]"));
        Assert.assertEquals(titluPostare.getText(), "Test TSS!");
        WebElement descrierePostare = webDriver.findElement(By.xpath("//*[@id=\"liber\"]/div[1]/div/div[1]/p[2]"));
        Assert.assertEquals(descrierePostare.getText(), "Lorem ipsum");


//        WebElement pozaUploadata = webDriver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div/div[1]/div[1]/img"));
//        System.out.println(pozaUploadata.getAttribute("src"));
    }


//
//    @Test
//    public void testLogout(){
//        WebElement profileName = webDriver.findElement(By.xpath("/html/body/header/nav/div/ul/li/a/span"));
//        String expectedMessage = this.usernameTest;
//
//        Assert.assertEquals(profileName.getText(), expectedMessage);
//        profileName.click();
//
//        WebElement logoutButton = webDriver.findElement(By.xpath("/html/body/header/nav/div/ul/li/ul/li[2]/div[2]/a"));
//        logoutButton.click();
//
//        String currentUrl = webDriver.getCurrentUrl();
//        Assert.assertEquals("https://www.scoalaluceafarul.ro/grading/system/login.php", currentUrl);
//    }
//
//    @Test
//    public void selectSemestru() throws InterruptedException {
//        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/notaelev.php");
//        Thread.sleep(6000);
//
//        WebElement semSelect = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]"));
//        Select selectObject = new Select(semSelect);
//        List<WebElement> allAvailableOptions = selectObject.getOptions();
//        List<String> newList = new LinkedList<>();
//
//        for (WebElement element: allAvailableOptions){
//            newList.add(element.getText());
//        }
//
//        List<String> expectedSemValues = Arrays.asList("-- Selecteaza semestrul --", "1", "2");
//        Assert.assertTrue(expectedSemValues.equals(newList));
//
//    }
//    //
//    @Test
//    public void displayNote() throws InterruptedException {
//        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/notaelev.php");
//
//        WebElement semSelect = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]"));
//        semSelect.click();
//
//        WebElement sem1 = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]/option[2]"));
//        sem1.click();
//
//        WebElement searchButton = webDriver.findElement(By.xpath("/html/body/div[3]/aside[2]/section[2]/div/div/div[1]/div/form/div/button"));
//        searchButton.click();
//
//        List<WebElement> tableHeader = webDriver.findElements(By.xpath("//*[@id=\"table\"]/thead/tr/th"));
//        Assert.assertEquals(tableHeader.get(1).getText(), "Materie:");
//        Assert.assertEquals(tableHeader.get(2).getText(), "Nota 1:");
//        Assert.assertEquals(tableHeader.get(3).getText(), "Nota 2:");
//        // many more fields
//
//        List<WebElement> person1 = webDriver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td"));
//        System.out.println("$ " + person1.get(9).getText());
//
//        WebElement casetaMentiune = webDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[11]/label"));
//        Integer medieFinala = Integer.parseInt(person1.get(9).getText());
//        if (medieFinala >= 5) {
//            Assert.assertEquals(casetaMentiune.getText(), "Promovat");
//        } else {
//            Assert.assertEquals(casetaMentiune.getText(), medieFinala.toString());
//        }
//
//    }
//    @Test
//    public void testSearchBox(){
//        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/profesori.php");
//        WebElement search = webDriver.findElement(By.xpath("/html/body/div[3]/aside[2]/section[2]/div/div/div[2]/div/div[1]/div[2]/div/label/input"));
//
//        search.sendKeys("ina");
//        List<WebElement> tableBody = webDriver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr/td[3]"));
//        for( WebElement elem: tableBody ){
//            System.out.println("**"+ elem.getText());
//            Assert.assertEquals(elem.getText().contains("ina"), true);
//        }
//
//    }
//
//    // test if the "Previous" button is disabled if we are on the first page
//    @Test
//    public void testPreviousButton(){
//        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaelev/profesori.php");
//        WebElement firstPageButton = webDriver.findElement(By.xpath("//*[@id=\"table_paginate\"]/ul/li[2]/a"));
//
//        firstPageButton.click();
//        WebElement previousButton = webDriver.findElement(By.className("disabled"));
//        Assert.assertEquals(previousButton.getText(), "Previous");
//    }

}
