package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;

public class AddToCartTests extends TestBase{

    @Test
    public void testFastAddToCartWithoutLogin() throws InterruptedException {
        app.buy().addToCartFast();
        Thread.sleep(1000);
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

    @Test
    public void testNormalAddToCartWithoutLogin(){
        app.goTo().goToItem();
        app.buy().addToCartNormal();
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

    @Test
    public void testFastAddToCartWithLogin() throws InterruptedException {
        app.goTo().loginBottom();
        app.session().login(app.getProperty("web.email"), app.getProperty("web.password"));
        app.goTo().mainPage();
        app.buy().addToCartFast();
        Thread.sleep(1000);
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

    @Test
    public void testNormalAddToCartWithLogin(){
        app.goTo().loginBottom();
        app.session().login(app.getProperty("web.email"), app.getProperty("web.password"));
        app.goTo().mainPage();
        app.goTo().goToItem();
        app.buy().addToCartNormal();
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

}
