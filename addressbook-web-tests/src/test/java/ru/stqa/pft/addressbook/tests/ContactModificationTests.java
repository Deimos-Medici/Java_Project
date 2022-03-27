package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactModificationTests extends TestBase {

    @Test
    public void testAddEdit() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().submitEditContact();
        app.getContactHelper().fillContactForm(new NewContactInfo("Sasha", "Morgan2", "Volga street", "89583487547", "workmail@gmail.com"));
        app.getContactHelper().submitUpdate();
        app.getContactHelper().returnToHomePage();
    }
}
