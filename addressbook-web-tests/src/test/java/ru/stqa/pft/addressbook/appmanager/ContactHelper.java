package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewAddInfo;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void returnToHomePage() {

        click(By.linkText("home page"));
    }

    public void submitNewAdd() {

        click(By.xpath("//input[21]"));
    }

    public void fillAddForm(NewAddInfo newAddInfo) {
        type(By.name("firstname"), newAddInfo.name());
        type(By.name("lastname"), newAddInfo.lastname());
        type(By.name("address"), newAddInfo.address());
        type(By.name("mobile"), newAddInfo.phone());
        type(By.name("email"), newAddInfo.mail());
    }

    public void selectAdd() {

        click(By.name("selected[]"));
    }

    public void deleteAdd() {
        click(By.xpath("//input[@value='Delete']"));
    }
}
