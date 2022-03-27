package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testAddDeletion() throws Exception {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.alert();
        app.getNavigationHelper().gotoHome();
    }
}
