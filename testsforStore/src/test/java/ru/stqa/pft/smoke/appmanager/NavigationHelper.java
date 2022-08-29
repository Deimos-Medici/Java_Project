package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void loginBottom() {
        wd.findElement(By.xpath("//div[@id='top-links']/ul/li[2]/a/span[2]")).click();
        wd.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
    }

}
