package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;

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
            File photo = new File("src/test/resources/ZHCat.jpg");
            ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan222").withPhoto(String.valueOf(photo)).withAddress("London")
                    .withFirstMail("@mail.ru").withSecondMail("@gmail.com").withThirdMail("@yandex.com")
                    .withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525");
            app.contact().create(contact);

        }

    }

    @Test
    public void testAddContactFromGroup() {
        app.contact().Home();
        Groups groups = app.db().groups(); //получение списка групп
        Contacts contact1 = app.db().contacts(); //получение списка контактов
        GroupData OneGroup = groups.iterator().next(); //получение группы
        app.contact().addGroup(contact1, OneGroup); //проверка списка контактов
        Contacts contacts2 = app.db().contacts();
        if(contacts2.size() != contact1.size()){ //кейс если создан новый контакт
            ContactData newContact = app.db().contacts().iterator().next(); //Поиск только что созданного контакта. Как найти тот самый? Как взять максимальный Id?
            Groups contactsGroupBefore1 = newContact.getGroups();
            app.contact().selectContactById(newContact.getId());
            app.contact().submitAddGroup();
            app.contact().Home();
            ContactData contactWithGroup = app.db().contacts().iterator().next();
            Groups contactsGroupAfter = contactWithGroup.getGroups();
            assertThat(contactsGroupAfter, equalTo(
                    contactsGroupBefore1.withAdded(OneGroup.withId(contactsGroupAfter.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

        } else {
            ContactData addContact = app.db().contacts().iterator().next();
            Groups contactsGroupBefore = addContact.getGroups();
        //    app.contact().selectContactById(addContact.getId());
        //    app.contact().submitAddGroup();
        //    app.contact().Home();
            ContactData contactWithGroup = app.db().contacts().iterator().next();
            Groups contactsGroupAfter = contactWithGroup.getGroups();
            assertThat(contactsGroupAfter, equalTo( //сравнение при кейсе без создания нового контакта
                    contactsGroupBefore.withAdded(OneGroup.withId(contactsGroupAfter.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        }
        //assertThat(contactsGroupAfter.size(), equalTo(contactsGroupBefore.size() + 1));



    }
}
