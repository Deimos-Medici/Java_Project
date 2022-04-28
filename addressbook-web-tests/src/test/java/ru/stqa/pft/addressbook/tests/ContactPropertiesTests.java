package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPropertiesTests extends TestBase{

    @Test
    public void testContactProperties(){
        app.contact().Home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getFirstMail(), equalTo(mergeMails(contactInfoFromEditForm)));
    }

    private <T> String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPropertiesTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return  phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

    private <T> String mergeMails(ContactData contact) {
        return Arrays.asList(contact.getFirstMail(), contact.getSecondMail(), contact.getThirdMail())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}
