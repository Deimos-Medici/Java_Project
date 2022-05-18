package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void changePassword(String user){
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.linkText(user));
        click(By.cssSelector("input[value='—бросить пароль']"));
    }


    public void login() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        String username = app.getProperty("web.adminLogin");
        String password = app.getProperty("web.adminPassword");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }
    public void testChangePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public int count() {
        return getElementCount();
    }

}
