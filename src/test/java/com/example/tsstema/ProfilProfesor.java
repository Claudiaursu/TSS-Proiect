package com.example.tsstema;

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

public class ProfilProfesor extends BaseTest{

    private String usernameTest = "Oprea Cristina";

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

        this.insertUsername(usernameField, "oprea.cristina");
        this.insertPassword(parolaField, "Cristina68");
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
        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaprof/notaprof.php");
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

    @Test
    public void displayNote() throws InterruptedException {
        webDriver.get("https://www.scoalaluceafarul.ro/grading/system/pages/notaprof/notaprof.php");

        WebElement semSelect = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]"));
        semSelect.click();

        WebElement sem1 = webDriver.findElement(By.xpath("//*[@id=\"ddl_sem\"]/option[2]"));
        sem1.click();

        WebElement clasaSelect = webDriver.findElement(By.xpath("//*[@id=\"ddl_yl\"]"));
        semSelect.click();

        WebElement clasa6B = webDriver.findElement(By.xpath("//*[@id=\"ddl_yl\"]/option[2]"));
        clasa6B.click();

        WebElement searchButton = webDriver.findElement(By.xpath("/html/body/div[3]/aside[2]/section[2]/div/div/div[1]/div/form/div/div/div[3]/button"));
        searchButton.click();

        List<WebElement> tableHeader = webDriver.findElements(By.xpath("//*[@id=\"table\"]/thead/tr/th"));
        Assert.assertEquals(tableHeader.get(1).getText(), "Nume:");
        Assert.assertEquals(tableHeader.get(2).getText(), "Prenume:");
        Assert.assertEquals(tableHeader.get(3).getText(), "Nota 1:");
        // many more fields

        List<WebElement> person1 = webDriver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td"));
        int sumaNote = 0;
        int nrNote = 0;

        for(int i = 3; i <= 8; i++){
            if( !person1.get(i).getText().isBlank() ){
                System.out.println("$ "+ person1.get(i).getText());
                sumaNote += Integer.parseInt(person1.get(i).getText());
                nrNote += 1;
            }
        }

        double medie = Math.ceil( (double) sumaNote/nrNote);
        Assert.assertEquals(medie, Double.parseDouble(person1.get(10).getText()));

        WebElement editButton = webDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[12]/button"));
        editButton.click();

        WebElement inputNota = webDriver.findElement(By.xpath("//*[@id=\"editModal3000\"]/div/div/div[2]/div/div/div[2]/input"));

        new WebDriverWait(webDriver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editModal3000\"]/div/div/div[2]/div/div/div[2]/input"))).click();

        inputNota.clear();
        inputNota.sendKeys("6");
        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"editModal3000\"]/div/div/div[3]/div/input[2]"));

        WebElement salveazaButon = webDriver.findElement(By.xpath("//*[@id=\"editModal3000\"]/div/div/div[3]/div/button"));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", submitButton);

        salveazaButon.click();
        Thread.sleep(5000);

        List<WebElement> note = webDriver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td"));
        System.out.println(note.get(3).getText());
        Assert.assertEquals(note.get(3).getText(), "6");

    }



}
