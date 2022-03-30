package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewContactInfo;

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

    public void fillContactForm(NewContactInfo newContactInfo) {
        type(By.name("firstname"), newContactInfo.name());
        type(By.name("lastname"), newContactInfo.lastname());
        type(By.name("address"), newContactInfo.address());
        type(By.name("mobile"), newContactInfo.phone());
        type(By.name("email"), newContactInfo.mail());
    }

    public void selectContact() {

        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitEditContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitSaveContact() {
        click(By.name("update"));
    }

    public void createContact(NewContactInfo contact) {
        fillContactForm(contact);
        submitEnter();
        returnToHomePage();
    }

    public boolean isTheseContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
