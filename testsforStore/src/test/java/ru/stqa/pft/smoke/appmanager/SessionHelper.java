package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public boolean login(String email, String password) {
        type(By.name("email"), email);
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
        return isElementPresent(By.xpath("//div[@id='content']/ul[2]/li[2]/a"));
    }

}



