package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactEdit() {
        app.getNavigationHelper().gotoHome();
        int before = app.getGroupHelper().getElementCount();
        if (app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new NewContactInfo("Sasha3", "Morgan", null, null, null));
        }
        app.getContactHelper().submitEditContact(before - 1);
        app.getContactHelper().fillContactForm(new NewContactInfo("Sasha27", "Morgan3", "Volga street", "89583487547", "workmail@gmail.com"));
        app.getContactHelper().submitSaveContact();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
