package com.example.Tischmesse.e2eTests;

import com.example.Tischmesse.pages.SectorAdminPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    properties = {"tischmesse.administrator_password=secret"})
@AutoConfigureTestDatabase
public class SectorAdminIT {

    @Value("${local.server.port}")
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();

    @Test
    public void testSectorsAreShownInAdminInterface(){
        login();
        var page = SectorAdminPage.create(driver, port);
        assertFalse(page.getAllSectors().isEmpty());
    }

    private void login(){
        driver.navigate().to("http://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("administrator");
        driver.findElement(By.id("password")).sendKeys("secret");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }
}
