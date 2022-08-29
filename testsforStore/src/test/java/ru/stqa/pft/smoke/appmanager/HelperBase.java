package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;
    protected ApplicationManager app;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }

        }
    }
    public void click(By locator) {
        wd.findElement(locator).click();
    }


    protected void attach(By locator, File file) {
        if (file != null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isThereElement() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getElementCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
