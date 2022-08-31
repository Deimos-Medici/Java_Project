package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;

public class SimpleSearchTest extends TestBase {

    @Test
    public void testSimpleSearch() {
        app.search().SimpleSearch(app.getProperty("web.searchQuestion"));
        String locator = "//div[@id='content']/p[2]";
        assertFalse(app.session().lookForPresent(locator));
    }
}
