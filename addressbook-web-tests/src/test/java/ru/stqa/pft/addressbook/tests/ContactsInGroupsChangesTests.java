package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsInGroupsChangesTests extends TestBase{

    @Test
    public void testDeleteContactFromGroup(ContactData contact) {
        app.contact().Home();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.contact().deleteContactFromGroup(contact);

    }

}
