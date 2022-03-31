package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactInfo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new NewContactInfo("Sasha20", "Morgan", "moscow", "892494944", "@house.com"));
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before +1);
  }


}
