package ru.stqa.pft.playwright.tests;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.*;

import java.util.Properties;
import static org.hamcrest.CoreMatchers.equalTo;


public class SimpleLoginTest extends TestBase{

    @Test
    public static void testLogin(){

        app.goTo().groupPage();
        int sizeBefore = app.group().count();
        app.goTo().newGroupCreation(app.properties.getProperty("group.name"), app.properties.getProperty("group.header"), app.properties.getProperty("group.footer"));

        MatcherAssert.assertThat(app.group().count(), equalTo(sizeBefore +1));

        //Locator head = addressbook.locator("span[class=\"group\"]");
        //assertThat(head).hasText("play1");
        }
}
