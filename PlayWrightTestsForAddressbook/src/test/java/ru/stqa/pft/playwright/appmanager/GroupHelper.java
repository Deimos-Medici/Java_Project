package ru.stqa.pft.playwright.appmanager;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static ru.stqa.pft.playwright.appmanager.ApplicationManager.addressbook;

public class GroupHelper {

    public void groupPage(){
        addressbook.locator("a[href=\"group.php\"]").click();
    }

    public void returnGroupPage(){
        addressbook.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("group page")).click();
    }

    public void newGroupAdd(){
        addressbook.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New group")).first().click();
    }

    public void newGroupCreateButton(){
        addressbook.locator("input[name=\"submit\"]").click();
    }

    public int count() {
        return getElementCount();
    }

    public int getElementCount() {
        return addressbook.locator("span[class=\"group\"]").count();
    }


    public void newGroupCreation(String name, String header, String footer){
        newGroupAdd();
        addressbook.locator("input[name=\"group_name\"]").fill(name);
        addressbook.locator("textarea[name=\"group_header\"]").fill(header);
        addressbook.locator("textarea[name=\"group_footer\"]").fill(footer);
        newGroupCreateButton();
        returnGroupPage();
    }
}
