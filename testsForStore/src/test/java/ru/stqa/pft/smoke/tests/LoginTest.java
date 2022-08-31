package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LoginTest extends TestBase{

    @Test
    public void testLogin() {
        app.goTo().loginBottom();
        app.session().login(app.getProperty("web.email"), app.getProperty("web.password"));
        String locator = "//div[@id='content']/ul[2]/li[2]/a";
        assertTrue(app.session().lookForPresent(locator));
    }

    @Test
    public void testLogout() {
        //app.goTo().loginBottom();
       // app.session().login(app.getProperty("web.email"), app.getProperty("web.password"));
        app.goTo().logoutBottom();
        String locator = "//div[@id='content']/h1";
        assertTrue(app.session().lookForPresent(locator));
    }

}
