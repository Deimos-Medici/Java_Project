package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeletionGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            GroupData group = new GroupData().withName("groupInBefore").withHeader("test321").withFooter("test123");
            app.group().create(group);
        }

        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/ZHCat.jpg");
            ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan").withPhoto(String.valueOf(photo)).withAddress("London")
                    .withFirstMail("@mail.ru").withSecondMail("@gmail.com").withThirdMail("@yandex.com")
                    .withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525");
            app.contact().create(contact);
            Contacts contact1 = app.db().contacts();
            GroupData OneGroup = app.db().groups().iterator().next();
            app.contact().addGroup(contact1, OneGroup);
        }
        Contacts contact1 = app.db().contacts();
        GroupData OneGroup = app.db().groups().iterator().next();
        app.contact().addGroup(contact1, OneGroup);
        ContactData newContact = app.db().contacts().iterator().next();
        app.contact().selectContactById(newContact.getId());
        app.contact().submitAddGroup();
        app.contact().Home();
    }

    @Test
    public void testDeleteContactFromGroup() {
        app.contact().Home();
        Contacts before = app.db().contacts();
        ContactData addContact = before.iterator().next();
        Groups contactsGroupBefore = addContact.getGroups();
        app.contact().deleteContactFromGroup(addContact);
        ContactData contactWithGroup = app.db().contacts().iterator().next();
        Groups contactsGroupAfter = contactWithGroup.getGroups();

        assertThat(contactsGroupAfter.size(), equalTo(contactsGroupBefore.size() - 1));
    }

}
