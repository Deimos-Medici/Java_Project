package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void mainPage(){
        wd.findElement(By.linkText("Your Store")).click();
    }

    public void goToItem(){
        wd.findElement(By.xpath("//div[@id='content']/div[2]/div/div/div/a")).click();
    }

    public void loginBottom() {
        dropMyAccountMenu();
        wd.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
    }

    public void dropMyAccountMenu() {
        wd.findElement(By.xpath("//div[@id='top-links']/ul/li[2]/a/span[2]")).click();
    }

    public void registrationBottom() {
        dropMyAccountMenu();
        wd.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
    }

    public void logoutBottom() {
        dropMyAccountMenu();
        wd.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    public void goToShoppingCart(){
        wd.findElement(By.id("cart-total")).click();
    }

}
