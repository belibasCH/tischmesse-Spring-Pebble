package com.example.Tischmesse.integrationTests;

import com.example.Tischmesse.pages.ExhibitorAdminPage;
import com.example.Tischmesse.pages.ExhibitorFormPage;
import com.example.Tischmesse.pages.SectorAdminPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    properties = {"tischmesse.administrator_password=secret"})
@AutoConfigureTestDatabase
public class FillOutFormTest {

    @Value("${local.server.port}")
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();


    @Test
    public void clickFillOutSectorFormWithValidEntry(){
        login();
        var page = SectorAdminPage.create(driver, port);
        int startSectorCount = page.getAllSectors().size();
        page.getInputField().sendKeys("neue Branche");
        page.getSubmitButton().click();
        int allSectors = page.getAllSectors().size();
        assertTrue(startSectorCount < allSectors);
    }

    @Test
    public void clickFillOutSectorFormWithInvalidEntry(){
        login();
        var page = SectorAdminPage.create(driver, port);
        int startSectorCount = page.getAllSectors().size();
        page.getInputField().sendKeys("thisWordIsClearlyToLongToBeEnteredIntoATextFieldorNot");
        page.getSubmitButton().click();
        int allSectors = page.getAllSectors().size();
        assertTrue(startSectorCount == allSectors);
    }

    @Test
    public void testFillOutExhibitorFormWithValidExhibitorName(){
        login();
        var pageExhibitorList = ExhibitorAdminPage.create(driver, port);
        int startExhibitorCount = pageExhibitorList.getAllExhibitors().size();
        var pageForm = ExhibitorFormPage.create(driver, port);
        pageForm.getCompanyNameInputField().sendKeys("Elias AG");
        pageForm.getFormEntryButton().click();
        var pageExhibitorListAfter = ExhibitorAdminPage.create(driver, port);
        int allExhibitors = pageExhibitorListAfter.getAllExhibitors().size();
        assertTrue(startExhibitorCount < allExhibitors);
    }

    @Test
    public void testFillOutExhibitorFormWithEmptyExhibitorName(){
        login();
        var pageExhibitorList = ExhibitorAdminPage.create(driver, port);
        int startExhibitorCount = pageExhibitorList.getAllExhibitors().size();
        var pageForm = ExhibitorFormPage.create(driver, port);
        pageForm.getCompanyNameInputField().sendKeys("");
        pageForm.getFormEntryButton().click();
        var pageExhibitorListAfter = ExhibitorAdminPage.create(driver, port);
        int allExhibitors = pageExhibitorListAfter.getAllExhibitors().size();
        assertEquals(startExhibitorCount, allExhibitors);
    }

    private void login(){
        driver.navigate().to("http://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("administrator");
        driver.findElement(By.id("password")).sendKeys("secret");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }
}
