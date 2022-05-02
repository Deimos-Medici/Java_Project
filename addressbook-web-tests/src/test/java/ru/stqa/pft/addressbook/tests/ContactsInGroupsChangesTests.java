package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.json.TypeToken;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsInGroupsChangesTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
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

    @Test(dataProvider = "validGroupsFromJson")
    public void ensurePreconditions(GroupData group) {
        if (app.db().groups().size() == 0){
            app.goTo().GroupPage();
            app.group().create(group);
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testAddContactFromGroup(ContactData contact) {
        app.contact().Home();
        if (app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
            contact.inGroup(groups.iterator().next());
            GroupData groupData = new GroupData().withName(contact.getGroups().iterator().next().getName());
            app.contact().create(groupData, contact);
        }

        Groups groups = app.db().groups();
        contact.inGroup(groups.iterator().next());
        ContactData addContact = app.db().contacts().iterator().next();
        Groups contactsGroupBefore = addContact.getGroups();
        app.contact().selectContactById(addContact.getId());
        app.contact().addGroup(contact);
        ContactData contactWithGroup = app.db().contacts().iterator().next();
        Groups contactsGroupAfter = contactWithGroup.getGroups();
        assertThat(contactsGroupAfter.size(), equalTo(contactsGroupBefore.size() + 1));

    }

    @Test(dataProvider = "validContactsFromJson")
    public void testDeleteContactFromGroup(ContactData contact) {
        app.contact().Home();
        if (app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
            contact.inGroup(groups.iterator().next());
            GroupData groupData = new GroupData().withName(contact.getGroups().iterator().next().getName());
            app.contact().create(groupData, contact);
        }

        Groups groups = app.db().groups();
        contact.inGroup(groups.iterator().next());
        GroupData group = new GroupData().withName(contact.getGroups().iterator().next().getName());
        ContactData addContact = app.db().contacts().iterator().next();
        Groups contactsGroupBefore = addContact.getGroups();
        app.contact().goToGroupPage(contact);
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContactFromGroup(group, deletedContact);
        ContactData contactWithGroup = app.db().contacts().iterator().next();
        Groups contactsGroupAfter = contactWithGroup.getGroups();
        assertThat(contactsGroupAfter.size(), equalTo(contactsGroupBefore.size() - 1));

    }

}
