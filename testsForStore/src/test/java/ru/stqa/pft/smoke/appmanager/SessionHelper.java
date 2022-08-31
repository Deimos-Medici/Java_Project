package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String email, String password) {
        type(By.name("email"), email);
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public boolean lookForPresent(String locator){
        return isElementPresent(By.xpath(locator));
    }

    public void registration(String lastname, String telephone, String password){
        long now = System.currentTimeMillis();
        String email = String.format("user%s@testmail.test", now);
        String firstname = String.format("user%s", now);

        type(By.name("firstname"), firstname);
        type(By.name("lastname"), lastname);
        type(By.name("email"), email);
        type(By.name("telephone"), telephone);
        type(By.name("password"), password);
        type(By.name("confirm"), password);
        click(By.name("agree"));
        click(By.xpath("//input[@value='Continue']"));
    }

}



