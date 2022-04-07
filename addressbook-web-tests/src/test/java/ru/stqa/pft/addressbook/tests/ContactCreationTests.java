package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactInfo;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.getNavigationHelper().gotoHome();
    List<NewContactInfo> before = app.getContactHelper().getContactList();
    NewContactInfo contact = new NewContactInfo("Sasha", "Morgan222", "London", "89358946", "@mail.com");
    app.getContactHelper().createContact(contact);
    List<NewContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    Comparator<? super NewContactInfo> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
