package ru.stqa.pft.playwright.appmanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

    public Properties properties;

    public static Browser chrome;
    public static Playwright playwright;
    public static Page addressbook;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;



    public void init() throws IOException {

        properties = new Properties();

        playwright = Playwright.create();
        chrome = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

        String target = System.getProperty("target","local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

        groupHelper = new GroupHelper();
        addressbook = chrome.newPage();
        sessionHelper = new SessionHelper();
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public void stop() {
        playwright.close();
    }

    public GroupHelper goTo() {
        return groupHelper;
    }

}
