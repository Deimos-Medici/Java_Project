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

public class ContactsInGroupsChangesTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        File photo = new File("src/test/resources/ZHCat.jpg");
        ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan222").withPhoto(String.valueOf(photo)).withAddress("London")
                .withFirstMail("@mail.ru").withSecondMail("@gmail.com").withThirdMail("@yandex.com")
                .withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525");
        if (app.db().contacts().size() == 0){
            app.contact().create(contact);
        }
        if (app.db().groups().size() == 0){
            app.goTo().GroupPage();
            GroupData group = new GroupData().withName("test123").withHeader("test321").withFooter("test123");
            app.group().create(group);
        }
    }

    @Test
    public void testAddContactFromGroup() {
        app.contact().Home();
        Groups groups = app.db().groups();
        ContactData addContact = app.db().contacts().iterator().next();
        Groups contactsGroupBefore = addContact.getGroups();
        GroupData OneGroup = groups.iterator().next();
        app.contact().addGroup(addContact, OneGroup);
        ContactData contactWithGroup = app.db().contacts().iterator().next();
        Groups contactsGroupAfter = contactWithGroup.getGroups();
        assertThat(contactsGroupAfter.size(), equalTo(contactsGroupBefore.size() + 1));

        assertThat(contactsGroupAfter, equalTo(
                contactsGroupBefore.withAdded(OneGroup.withId(contactsGroupAfter.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));


    }

    @Test
    public void testDeleteContactFromGroup() {
        app.contact().Home();
        Groups groups = app.db().groups();
        ContactData addContact = app.db().contacts().iterator().next();
        Groups contactsGroupBefore = addContact.getGroups();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContactFromGroup(deletedContact);
        ContactData contactWithGroup = app.db().contacts().iterator().next();
        Groups contactsGroupAfter = contactWithGroup.getGroups();
        assertThat(contactsGroupAfter.size(), equalTo(contactsGroupBefore.size() - 1));

        assertThat(contactsGroupAfter, equalTo(before.without(deletedContact)));
    }

}
