package com.example.Tischmesse.integrationTests;

import com.example.Tischmesse.pages.ExhibitorAdminPage;
import com.example.Tischmesse.pages.ExhibitorFormPage;
import com.example.Tischmesse.pages.SectorAdminPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FillOutFormTest {

    @Value("${local.server.port}")
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();


    @Test
    public void clickFillOutSectorFormWithValidEntry(){
        var page = SectorAdminPage.create(driver, port);
        int startSectorCount = page.getAllSectors().size();
        page.getInputField().sendKeys("neueBranche");
        page.getSubmitButton().click();
        int allSectors = page.getAllSectors().size();
        assertTrue(startSectorCount < allSectors);
    }

    @Test
    public void clickFillOutSectorFormWithInvalidEntry(){
        var page = SectorAdminPage.create(driver, port);
        int startSectorCount = page.getAllSectors().size();
        page.getInputField().sendKeys("thisWordIsClearlyToLongToBeEnteredIntoATextFieldorNot");
        page.getSubmitButton().click();
        int allSectors = page.getAllSectors().size();
        assertTrue(startSectorCount == allSectors);
    }

    @Test
    public void testFillOutExhibitorFormWithValidEntry(){
        var pageExhibitorList = ExhibitorAdminPage.create(driver, port);
        int startExhibitorCount = pageExhibitorList.getAllExhibitors().size();
        var pageForm = ExhibitorFormPage.create(driver, port);
        pageForm.getCompanyNameInputField().sendKeys("Elias AG");
//        pageForm.getEmailInputField().sendKeys("elias@gmail.com");
//        pageForm.getTelefonNrInputField().sendKeys("0791112223548");
//        pageForm.getPlzInputField().sendKeys("5000");
//        pageForm.getLocationInputField().sendKeys("Aarau");
//        pageForm.getAddressInputField().sendKeys("Bahnhofstrasse 5");
//        pageForm.getUrlInputField().sendKeys("www.google.ch");
//        pageForm.getBeschreibungInputField().sendKeys("Nothing to add");
        pageForm.getFormEntryButton().click();
        var pageExhibitorListAfter = ExhibitorAdminPage.create(driver, port);
        int allExhibitors = pageExhibitorList.getAllExhibitors().size();
        assertTrue(startExhibitorCount < allExhibitors);
    }

    @Test
    public void testFillOutExhibitorFormWithValidExhibitorName(){
        var pageExhibitorList = ExhibitorAdminPage.create(driver, port);
        int startExhibitorCount = pageExhibitorList.getAllExhibitors().size();
        var pageForm = ExhibitorFormPage.create(driver, port);
        pageForm.getCompanyNameInputField().sendKeys("Elias AG");
        pageForm.getFormEntryButton().click();
        var pageExhibitorListAfter = ExhibitorAdminPage.create(driver, port);
        int allExhibitors = pageExhibitorList.getAllExhibitors().size();
        assertTrue(startExhibitorCount < allExhibitors);
    }

    @Test
    public void testFillOutExhibitorFormWithEmptyExhibitorName(){
        var pageExhibitorList = ExhibitorAdminPage.create(driver, port);
        int startExhibitorCount = pageExhibitorList.getAllExhibitors().size();
        var pageForm = ExhibitorFormPage.create(driver, port);
        pageForm.getCompanyNameInputField().sendKeys("");
        pageForm.getFormEntryButton().click();
        var pageExhibitorListAfter = ExhibitorAdminPage.create(driver, port);
        int allExhibitors = pageExhibitorList.getAllExhibitors().size();
        assertTrue(startExhibitorCount == allExhibitors);
    }
}
