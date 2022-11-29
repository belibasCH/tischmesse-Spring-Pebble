package com.example.Tischmesse.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SectorAdminPage {

    private static final String BASE_URL = "http://localhost:";

    public static SectorAdminPage create(WebDriver driver, int port) {
        return create(driver, port, "/sectors");
    }

    public static SectorAdminPage create(WebDriver driver, int port, String path) {
        driver.get(BASE_URL + port + path);
        return PageFactory.initElements(driver, SectorAdminPage.class);
    }

    @FindBy(css = ".sector-card")
    private List<WebElement> allSectors;

    @FindBy(css = "#sectorName")
    private WebElement createSectorInputField;

    @FindBy(css = "#sector-add-button")
    private WebElement createSectorSubmitButton;

    public List<WebElement> getAllSectors(){ return allSectors;}

    public WebElement getInputField(){ return createSectorInputField; }

    public WebElement getSubmitButton(){ return createSectorSubmitButton; }

}
