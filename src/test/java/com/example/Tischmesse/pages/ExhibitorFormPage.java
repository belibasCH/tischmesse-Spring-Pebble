package com.example.Tischmesse.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExhibitorFormPage {

    private static final String BASE_URL = "http://localhost:";

    public static ExhibitorFormPage create(WebDriver driver, int port) {
        return create(driver, port, "/exhibitor/add");
    }

    public static ExhibitorFormPage create(WebDriver driver, int port, String path) {
        driver.get(BASE_URL + port + path);
        return PageFactory.initElements(driver, ExhibitorFormPage.class);
    }

    @FindBy(css = "#companyName")
    private WebElement companyName;

    @FindBy(css = "#email")
    private WebElement email;

    @FindBy(css = "#telefonNr")
    private WebElement telefonNr;

    @FindBy(css = "#plz")
    private WebElement plz;

    @FindBy(css = "#location")
    private WebElement location;

    @FindBy(css = "#address")
    private WebElement address;

    @FindBy(css = "#url")
    private WebElement url;

    @FindBy(css = "#beschreibung")
    private WebElement beschreibung;

    @FindBy(css = "#submit-Button-Form")
    private WebElement formEntryButton;



    public WebElement getCompanyNameInputField(){ return companyName;}

    public WebElement getEmailInputField(){ return email;}

    public WebElement getTelefonNrInputField(){ return telefonNr;}

    public WebElement getPlzInputField(){ return plz;}

    public WebElement getLocationInputField(){ return location;}

    public WebElement getAddressInputField(){ return address;}

    public WebElement getUrlInputField(){ return url;}

    public WebElement getBeschreibungInputField(){ return beschreibung;}

    public WebElement getFormEntryButton(){ return formEntryButton;}
}
