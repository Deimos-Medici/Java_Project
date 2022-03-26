package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewAddInfo;

public class AddEditTests extends TestBase {

    @Test
    public void testAddEdit() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().submitEditAdd();
        app.getContactHelper().fillAddForm(new NewAddInfo("Sasha", "Morgan2", "Volga street", "89583487547", "workmail@gmail.com"));
        app.getContactHelper().submitUpdate();
        app.getContactHelper().returnToHomePage();
    }
}
