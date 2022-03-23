package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NewAddInfo;

public class AddNewCreationTests extends TestBase {

  @Test
  public void testAddNewCreation() throws Exception {
    app.gotoAddNew();
    app.fillAddForm(new NewAddInfo("Sasha", "Morgan", "Volga street", "89583487547", "workmail@gmail.com"));
    app.submitNewAdd();
    app.returnToHomePage();
  }


}
