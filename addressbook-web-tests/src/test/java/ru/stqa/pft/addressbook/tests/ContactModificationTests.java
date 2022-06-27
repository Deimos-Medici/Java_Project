package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan").withAddress("London")
                    .withFirstMail("@mail.ru");
            app.contact().create(contact);

        }

    }

    @Test
    public void testContactEdit() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Misha").withLastname("Morgan1").withAddress("Volga street")
                .withHomePhone("893564646").withMobilePhone("2425555").withWorkPhone("3257777725")
                .withFirstMail("firstmodimail").withSecondMail("secondmail").withThirdMail("@thirdmail.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
