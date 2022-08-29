package ru.stqa.pft.smoke.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    public final Properties properties;
    private final String browser;
    private SearchHelper searchHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private WebDriver wd;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

        if (wd == null){
            if (Objects.equals(browser, BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.EDGE)){
                wd = new EdgeDriver();
            }
            navigationHelper = new NavigationHelper(wd);
            sessionHelper = new SessionHelper(wd);
            searchHelper = new SearchHelper(wd);
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
    }

    public void stop() {
        if (wd != null){
            wd.quit();
        }
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SearchHelper search() {
        return searchHelper;
    }

    public SessionHelper newSession() {
        return sessionHelper;
    }

}
