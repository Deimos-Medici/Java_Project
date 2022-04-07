package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactInfo;

import java.util.Collections;
import java.util.Comparator;
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
        NewContactInfo contact = new NewContactInfo(before.get(before.size() - 1).getId(), "Sasha22", "Morgan1", "Volga street", "89583487547", "workmail@gmail.com");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitSaveContact();
        app.getContactHelper().returnToHomePage();
        List<NewContactInfo> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super NewContactInfo> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
