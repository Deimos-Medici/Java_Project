package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactInfo;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactEdit() {
        app.getNavigationHelper().gotoHome();
        if (app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new NewContactInfo("Sasha3", "Morgan", null, null, null));
        }
        List<NewContactInfo> before = app.getContactHelper().getContactList();
        app.getContactHelper().submitEditContact(before.size() - 1);
        NewContactInfo contact = new NewContactInfo(before.get(before.size() - 1).getId(), "Sasha1", "Morgan1", "Volga street", "89583487547", "workmail@gmail.com");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitSaveContact();
        app.getContactHelper().returnToHomePage();
        List<NewContactInfo> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
