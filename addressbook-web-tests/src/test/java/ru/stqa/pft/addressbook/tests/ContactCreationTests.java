package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.contact().Home();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withName("Sasha1").withLastname("Morgan222").withAddress("London").withPhone("89358946").withMail("@mail.com");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    assertEquals(before, after);

  }
}
