package ru.stqa.pft.playwright.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.playwright.appmanager.ApplicationManager;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
