package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchHelper extends HelperBase {

    public SearchHelper(WebDriver wd) {
        super(wd);
    }

    public void SimpleSearch(String item){
        SearchLine();
        type(By.name("search"), item);
        SearchButton();
    }

    public void SearchLine(){
        wd.findElement(By.name("search")).click();
    }

    public void SearchButton(){
        wd.findElement(By.xpath("//div[@id='search']/span/button/i")).click();
    }
}
