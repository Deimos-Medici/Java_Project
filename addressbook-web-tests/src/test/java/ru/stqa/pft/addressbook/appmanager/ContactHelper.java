package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.NewContactInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToHomePage() {

        click(By.linkText("home page"));
    }

    public void submitEnter() {

        click(By.xpath("//input[21]"));
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(NewContactInfo newContactInfo) {
        type(By.name("firstname"), newContactInfo.getName());
        type(By.name("lastname"), newContactInfo.getLastname());
        type(By.name("address"), newContactInfo.getAddress());
        type(By.name("mobile"), newContactInfo.getPhone());
        type(By.name("email"), newContactInfo.getMail());
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitEditContact(int indexEdit) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(indexEdit).click();
    }

    public void submitSaveContact() {
        click(By.name("update"));
    }

    public void createContact(NewContactInfo contact) {
        gotoAddNewContact();
        fillContactForm(contact);
        submitEnter();
        returnToHomePage();
    }

    public boolean isThereContact() {
        return isThereElement();
    }

    public int getContactCount() {
        return getElementCount();
    }


    public List<NewContactInfo> getContactList() {
        List<NewContactInfo> contacts = new ArrayList<NewContactInfo>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            NewContactInfo contact = new NewContactInfo(id, name, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
