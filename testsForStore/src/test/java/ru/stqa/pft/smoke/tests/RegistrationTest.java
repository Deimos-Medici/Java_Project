package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase {

    @Test
    public void testRegistration(){
        app.goTo().registrationBottom();
        app.session().registration(app.getProperty("web.lastname"),
                app.getProperty("web.telephone"),
                app.getProperty("web.password"));
        String locator = "//div[@id='content']/h1";
        assertTrue(app.session().lookForPresent(locator));

    }

}
