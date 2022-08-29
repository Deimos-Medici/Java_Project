package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LoginTest extends TestBase{
    @Test
    public void testLogin() {
        app.goTo().loginBottom();
        assertTrue(app.newSession().login(app.getProperty("web.adminEmail"), app.getProperty("web.adminPassword")));
    }
}
