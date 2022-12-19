package com.example.Tischmesse.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ExhibitorAdminPage {

    private static final String BASE_URL = "http://localhost:";

    public static ExhibitorAdminPage create(WebDriver driver, int port) {
        return create(driver, port, "/exhibitor");
    }

    public static ExhibitorAdminPage create(WebDriver driver, int port, String path) {
        driver.get(BASE_URL + port + path);
        return PageFactory.initElements(driver, ExhibitorAdminPage.class);
    }

    @FindBy(css = ".aussteller-card")
    private List<WebElement> allExhibitors;

    public List<WebElement> getAllExhibitors(){ return allExhibitors;}

}
