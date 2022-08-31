package ru.stqa.pft.smoke.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;

public class AddToCartTests extends TestBase{

    @Test
    public void testFastAddToCartWithoutLogin(){
        app.buy().addToCartFast();
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

    @Test(enabled = false)
    public void testNormalAddToCartWithoutLogin(){
        app.goTo().goToItem();
        app.buy().addToCartNormal();
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

    @Test(enabled = false)
    public void testFastAddToCartWithLogin(){
        app.goTo().loginBottom();
        app.session().login(app.getProperty("web.email"), app.getProperty("web.password"));
        app.goTo().mainPage();
        app.buy().addToCartFast();
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

    @Test(enabled = false)
    public void testNormalAddToCartWithLogin(){
        app.goTo().mainPage();
        app.goTo().goToItem();
        app.buy().addToCartNormal();
        app.goTo().goToShoppingCart();
        String locator = "//div[@id='content']/p";
        assertFalse(app.session().lookForPresent(locator));
    }

}
