package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class SimpleSearchTest extends TestBase {
    @Test
    public void testSimpleSearch() {
        assertFalse(app.search().SimpleSearch(app.getProperty("web.searchQuestion")));
    }
}
