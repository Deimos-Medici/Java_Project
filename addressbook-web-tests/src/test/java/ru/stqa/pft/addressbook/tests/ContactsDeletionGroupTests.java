package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
            ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan").withAddress("London")
                    .withFirstMail("@mail.ru");
            app.contact().create(contact);
        }

    }

    @Test
    public void testDeleteContactFromGroup() {
        GroupData group = findGroup();
        ContactData contact = findContactWithGroup(group);
        Groups contactsGroupBefore = contact.getGroups();
        app.contact().deleteContactFromGroup(contact, group);
        Groups contactsGroupAfter = app.db().contactById(contact.getId()).getGroups();
        assertThat(contactsGroupAfter, equalTo(
                contactsGroupBefore.without(app.db().groupById(group.getId()))));

    }

    private ContactData findContactWithGroup(GroupData group) {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().contains(group)) {
                return contact;
            }
            app.contact().addContactInGroup(contact, group);
        }
        return contacts.iterator().next();
    }

    private GroupData findGroup() {
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if (group.getContacts().size() > 0) {
                return group;
            }
        }
        return groups.iterator().next();
    }
}