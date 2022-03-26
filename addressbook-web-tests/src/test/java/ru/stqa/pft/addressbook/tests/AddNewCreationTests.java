package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewAddInfo;

public class AddNewCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillAddForm(new NewAddInfo("Sasha3", "Morgan", "Volga street", "89583487547", "workmail@gmail.com"));
    app.getContactHelper().submitNewAdd();
    app.getContactHelper().returnToHomePage();
  }


}
