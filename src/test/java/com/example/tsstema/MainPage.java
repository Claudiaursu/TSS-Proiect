package com.example.tsstema;

import com.codeborne.selenide.SelenideElement;
import com.sun.tools.jconsole.JConsoleContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BaseTest{


    @Test
    public void openHomepage(){
        goToHomePageFullScreen();
    }

    @Test
    public void logoImage(){
        goToHomePageFullScreen();
        WebElement logoImage = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/a[1]"));
        Assert.assertTrue(logoImage.isDisplayed());
        logoImage.click();

        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/index.php", currentUrl);
    }

    @Test
    public void logoText(){
        goToHomePageFullScreen();
        WebElement logo = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/nav/a[2]"));
        String logoText = logo.getText();
        Assert.assertEquals("Școala Gimnazială\n\"LUCEAFĂRUL\"", logoText);
    }


    @Test
    public void acasaButton(){
        goToHomePageFullScreen();
        WebElement acasaButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/a[1]"));
        acasaButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/index.php", currentUrl);
    }


    @Test
    public void avizierButton(){
        goToHomePageFullScreen();
        WebElement avizierButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/a[2]"));
        avizierButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/avizier.php", currentUrl);
    }

    @Test
    public void eleviDropdown(){
        goToHomePageFullScreen();
        WebElement eleviButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/div/a"));

        Actions builder = new Actions(webDriver);
        builder.moveToElement(eleviButton).click().build().perform();

        List<WebElement> dropdownOptions = webDriver.findElements(By.xpath("//*[@id=\"main_nav\"]/div/div/div"));
        List<String> expectedOptions = Arrays.asList("Științe exacte", "Lecturiada", "Arte", "Consiliere", "Elevi");
        List<String> dropDownList = Arrays.asList(dropdownOptions.get(0).getText().split("\n"));

        for (int i = 0; i < dropdownOptions.size(); i++) {
            System.out.println(expectedOptions.get(i) + " - " +dropDownList.get(i) );
            Assert.assertEquals(expectedOptions.get(i), dropDownList.get(i));
        }
    }


    @Test
    public void despreNoiButton(){
        goToHomePageFullScreen();
        WebElement despreNoiButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/a[3]"));
        despreNoiButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/despre-noi.php", currentUrl);
    }

    @Test
    public void personalButton(){
        goToHomePageFullScreen();
        WebElement personalButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/a[4]"));
        personalButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/personal.php", currentUrl);
    }

    @Test
    public void ofertaEducationalaButton(){
        goToHomePageFullScreen();
        WebElement ofertaEducationalaButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/a[5]"));
        ofertaEducationalaButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/oferta.php", currentUrl);
    }

    @Test
    public void contactButton(){
        goToHomePageFullScreen();
        WebElement contactButton = webDriver.findElement(By.xpath("//*[@id=\"main_nav\"]/div/a[6]"));
        contactButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.scoalaluceafarul.ro/contact.php", currentUrl);
    }



    @Test
    public void checkCVButton(){
        goToHomePageFullScreen();
        WebElement butonCatalogVirtual = webDriver.findElement(By.xpath("//div[1]/div[2]/div/div/div/a"));
        Assert.assertTrue(butonCatalogVirtual.isDisplayed());
    }

}
