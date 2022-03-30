package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactEdit() {
        app.getNavigationHelper().gotoHome();
        if (app.getContactHelper().isTheseContact()){
            app.getContactHelper().createContact(new NewContactInfo("Sasha3", "Morgan", null, null, null));
        }
        app.getContactHelper().submitEditContact();
        app.getContactHelper().fillContactForm(new NewContactInfo("Sasha", "Morgan2", "Volga street", "89583487547", "workmail@gmail.com"));
        app.getContactHelper().submitSaveContact();
        app.getContactHelper().returnToHomePage();
    }
}
