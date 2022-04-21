package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.contact().Home();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/ZHCat.jpg");
    ContactData contact = new ContactData().withName("Sasha1").withLastname("Morgan222").withPhoto(photo).withAddress("London").
            withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525").withAllMails("@mail.com");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    app.contact().Home();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("Sasha1'").withLastname("Morgan222").withAddress("London").withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525").withAllMails("@mail.com");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before));
  }

}
