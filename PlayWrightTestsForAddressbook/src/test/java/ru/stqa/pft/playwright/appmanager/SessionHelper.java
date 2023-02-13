package ru.stqa.pft.playwright.appmanager;

import static ru.stqa.pft.playwright.appmanager.ApplicationManager.addressbook;

public class SessionHelper {

    public void login(String username, String password) {
        addressbook.navigate("http://localhost/addressbook/");
        addressbook.locator("input[name=\"user\"]").fill(username);
        addressbook.locator("input[name=\"pass\"]").fill(password);
        addressbook.locator("input[value=\"Login\"]").click();
    }

}
