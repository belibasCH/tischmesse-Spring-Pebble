package com.example.Tischmesse.e2eTests;

import com.example.Tischmesse.e2eTests.pages.ExhibitorPublicPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ExhibitorPublicIT {

    @Value("${local.server.port}")
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();

    @Test
    public void testExhibitorsAreShownOnPublicPage() {
        var page = ExhibitorPublicPage.create(driver, port);
        assertFalse(page.getAllPublicExhibitors().isEmpty());
    }
}