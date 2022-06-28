package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsAddGroupTests extends TestBase{

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
    public void testAddContactFromGroup() {
        GroupData group = findGroup();
        ContactData contact = findContactWithoutGroup(group);
        Groups contactsGroupBefore = contact.getGroups();
        app.contact().Home();
        app.contact().addContactInGroup(contact, group);
        Groups contactsGroupAfter = app.db().contactById(contact.getId()).getGroups();
        assertThat(contactsGroupAfter, equalTo(
                contactsGroupBefore.withAdded(group.withId(contactsGroupAfter.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    public GroupData findGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
            for (GroupData group : groups) {
                if (group.getContacts().size() < contacts.size()){
                    return group;
                }
            }
            app.goTo().GroupPage();
            GroupData group = new GroupData().withName("groupInFindGroup").withHeader("test321").withFooter("test123");
            app.group().create(group);
            Groups newGroupsList = app.db().groups();
            return app.db().groupById(newGroupsList.stream().mapToInt((m) -> m.getId()).max().getAsInt());
    }

    private ContactData findContactWithoutGroup(GroupData group) {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (!contact.getGroups().contains(group)){
                return contact;
            }
        }
        ContactData contact = new ContactData().withFirstName("Sasha").withLastname("Morgan").withAddress("London")
                .withFirstMail("@mail.ru");
        app.contact().create(contact);
        Contacts newContactList = app.db().contacts();
        return app.db().contactById(newContactList.stream().mapToInt((m) -> m.getId()).max().getAsInt());
    }

}
