package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewContactInfo;

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

    int max = after.stream().max((o1, o2) -> Integer.compare((o1.getId()), o2.getId())).get().getId();
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
