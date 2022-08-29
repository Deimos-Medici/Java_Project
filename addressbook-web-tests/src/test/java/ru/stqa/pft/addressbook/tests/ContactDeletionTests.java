package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan").withAddress("London")
                    .withFirstMail("@mail.ru");
            app.contact().create(contact);

        }

    }

    @Test
    public void testContactDeletion() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
