package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

    @Test
    public void testContactPhones(){
        app.contact().Home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));

    }

    private <T> String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getFirstMail(), contact.getSecondMail(), contact.getThirdMail())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}
