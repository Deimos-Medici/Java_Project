package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuyHelper extends HelperBase{

    public BuyHelper(WebDriver wd) {
        super(wd);
    }

    public void addToCartFast(){
        wd.findElement(By.cssSelector("button[onclick*='cart.add('43');']")).click();
    }
    public void addToCartNormal(){
        wd.findElement(By.name("button-cart")).click();
    }
}
