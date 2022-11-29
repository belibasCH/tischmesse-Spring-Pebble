package com.example.Tischmesse.e2eTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class ExhibitorPublicPage {

    private static final String BASE_URL = "http://localhost:";

    public static ExhibitorPublicPage create(WebDriver driver, int port) {
        return create(driver, port, "/");
    }

    public static ExhibitorPublicPage create(WebDriver driver, int port, String path) {
        driver.get(BASE_URL + port + path);
        return PageFactory.initElements(driver, ExhibitorPublicPage.class);
    }

    @FindBy(css = "article")
    private List<WebElement> allPublicExhibitors;

    public List<WebElement> getAllPublicExhibitors(){ return allPublicExhibitors;}
}
