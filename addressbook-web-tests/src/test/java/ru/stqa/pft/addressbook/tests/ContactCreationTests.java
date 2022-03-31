package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().createContact(new NewContactInfo("Sasha3", "Morgan", "moscow", "892494944", "@house.com"));
  }


}
