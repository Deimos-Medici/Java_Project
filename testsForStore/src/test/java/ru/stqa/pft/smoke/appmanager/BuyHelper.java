package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;


public class BuyHelper extends HelperBase{

    public BuyHelper(WebDriver wd) {
        super(wd);
    }

    public void addToCartFast(){
        int cart = Integer.parseInt(wd.findElement(By.id("cart-total")).getText().split(" ")[0]);
        if (cart != 0){
            goToMiniCart();
            cleanCart();
        }
        wd.findElement(By.xpath("//div[@id='content']/div[2]/div/div/div[3]/button")).click();
    }
    public void addToCartNormal(){
        wd.findElement(By.id("button-cart")).click();
    }

    private void goToMiniCart(){
        wd.findElement(By.id("cart-total")).click();
    }

    public void cleanCart(){
        wd.findElement(By.xpath("//div[@id='cart']/ul/li/table/tbody/tr/td[5]/button/i")).click();
    }
}
