package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

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

    @Test(dataProvider = "validContactsFromJson")
    public void testContactEdit(ContactData contact) {
        app.contact().Home();
        if (app.contact().all().size() == 0){
            app.contact().create(contact);
        }
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData modificationContact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Sasha22").withLastname("Morgan1").withPhoto("src/test/resources/ZHCat2.jpg").withAddress("Volga street")
                .withHomePhone("893564646").withMobilePhone("2425555").withWorkPhone("3257777725")
                .withFirstMail("firstmodimail").withSecondMail("secondmail").withThirdMail("@thirdmail.com");
        app.contact().modify(modificationContact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modificationContact)));
    }

}
