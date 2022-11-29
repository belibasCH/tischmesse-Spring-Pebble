package com.example.Tischmesse.e2eTests;

import com.example.Tischmesse.e2eTests.pages.ExhibitorPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ExhibitorAdminIT {

    @Value("${local.server.port}")
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();

    @Test
    public void testExhibitorsAreShownInAdminInterface(){
        var page = ExhibitorPage.create(driver, port);
        assertFalse(page.getAllExhibitors().isEmpty());
    }
}
