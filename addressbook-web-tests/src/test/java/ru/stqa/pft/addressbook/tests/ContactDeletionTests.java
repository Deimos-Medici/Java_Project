package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoHome();
        if (app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new NewContactInfo("Sasha3", "Morgan", null, null, null));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.alert();
        app.getNavigationHelper().gotoHome();
    }
}
