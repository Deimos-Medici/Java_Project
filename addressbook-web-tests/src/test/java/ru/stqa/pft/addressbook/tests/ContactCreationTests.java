package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))){
      String xml = "";
      String line = reader.readLine();
      while (line != null){
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      xstream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).toList().iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).toList().iterator();
    }
  }


  @Test
  public void ensurePreconditions(GroupData group) {
    if (app.db().groups().size() == 0){
      app.group().create(group);
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    app.contact().Home();
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    contact.inGroup(groups.iterator().next());
    GroupData group = new GroupData().withName(contact.getGroups().iterator().next().getName());
    app.contact().create(group, contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }



  @Test(enabled = false)
  public void testBadContactCreation() {
    app.contact().Home();
    Contacts before = app.contact().all();
    Groups groups = app.db().groups();
    ContactData contact = new ContactData().withFirstName("Sasha1'").withLastname("Morgan222").withAddress("London")
            .withFirstMail("@mail.ru").withSecondMail("@gmail.com").withFirstMail("@yandex.com")
            .withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525").inGroup(groups.iterator().next());
    GroupData groupData = new GroupData().withName(contact.getGroups().iterator().next().getName());
    app.contact().create(groupData, contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before));
  }

}
