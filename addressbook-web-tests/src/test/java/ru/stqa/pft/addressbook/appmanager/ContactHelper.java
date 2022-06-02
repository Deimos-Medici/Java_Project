package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.awt.*;
import java.io.File;
import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToHomePage() {

        click(By.linkText("home page"));
    }

    public void Home() {
        click(By.linkText("home"));
    }

    public void submitEnter() {

        click(By.xpath("//input[21]"));
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.getFirstname());
        type(By.name("lastname"), contact.getLastname());
        //attach(By.name("photo"), new File(contact.getPhoto()));
        type(By.name("address"), contact.getAddress());
        type(By.name("home"), contact.getHomePhone());
        type(By.name("mobile"), contact.getMobilePhone());
        type(By.name("work"), contact.getWorkPhone());
        type(By.name("email"), contact.getFirstMail());
        type(By.name("email2"), contact.getSecondMail());
        type(By.name("email3"), contact.getThirdMail());

        if (contact.getGroups().size() > 0) {
            new Select(wd.findElement(By.name("new_group")))
                    .selectByVisibleText(contact.getGroups().iterator().next().getName());
        }
    }


    private void SelectGroup() {
        wd.findElement(By.name("new_group")).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }


    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitEditContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitAddGroup() {
        wd.findElement(By.name("add")).click();
    }



    public void alert(){
        wd.switchTo().alert().accept();
    }

    public void submitSaveContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        gotoAddNewContact();
        fillContactForm(contact);
        submitEnter();
        returnToHomePage();
        contactCache = null;
    }

    public void addGroup(Contacts contacts, GroupData group){
        ContactData findcontact = findContactWithoutGroup(contacts);
        if (findcontact != null){
            selectContactById(findcontact.getId());
            new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
            wd.findElement(By.name("add")).click();;
            Home();
        } else {
            ContactData contact = new ContactData().withFirstName("Sasha1").withLastname("Morgan222").withAddress("London")
                    .withFirstMail("@mail.ru").withSecondMail("@gmail.com").withThirdMail("@yandex.com")
                    .withHomePhone("89358946").withMobilePhone("2424245").withWorkPhone("3255525");
            create(contact);
            new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
        }
    }


    public ContactData findContactWithoutGroup(Contacts contacts){
        for (ContactData contact : contacts){
            if (contact.getGroups().size() == 0){
                return contact;
            }
        }
    return null;
    }


    public void deleteContactFromGroup(ContactData contact) {
        new Select(wd.findElement(By.name("group")))
                .selectByVisibleText(contact.getGroups().iterator().next().getName());
        selectContactById(contact.getId());
        submitRemove();
        Home();
    }

    private void submitRemove() {
        click(By.name("remove"));
    }

    public void modify(ContactData contact) {
        submitEditContactById(contact.getId());
        fillContactForm(contact);
        submitSaveContact();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        alert();
        contactCache = null;
        Home();
    }


    public boolean isThereContact() {
        return isThereElement();
    }

    public int count() {
        return getElementCount();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache !=null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allMails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(name).withLastname(lastname).withAddress(address)
                    .withHomePhone(allPhones).withFirstMail(allMails));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        submitEditContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String firstMail = wd.findElement(By.name("email")).getAttribute("value");
        String secondMail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdMail = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withFirstMail(firstMail).withSecondMail(secondMail).withThirdMail(thirdMail);
    }

    public void selectNewContact(Contacts contacts2) {

    }
}
