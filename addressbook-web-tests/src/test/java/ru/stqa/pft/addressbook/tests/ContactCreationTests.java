package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillContactForm(new NewContactInfo("Sasha3", "Morgan", "Volga street", "89583487547", "workmail@gmail.com"));
    app.getContactHelper().submitEnter();
    app.getContactHelper().returnToHomePage();
  }


}
