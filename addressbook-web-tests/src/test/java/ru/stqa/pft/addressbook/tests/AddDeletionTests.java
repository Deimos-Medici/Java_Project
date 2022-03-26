package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class AddDeletionTests extends TestBase {

    @Test
    public void testAddDeletion() throws Exception {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectAdd();
        app.getContactHelper().deleteAdd();
        app.alert();
        app.getNavigationHelper().gotoHome();
    }
}
