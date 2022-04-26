package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).toList().iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupModification(GroupData group) {
        app.goTo().GroupPage();
        if (app.group().all().size() == 0){
            app.group().create(group);
        }
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData modificationGroup = new GroupData()
                .withId(modifiedGroup.getId()).withName( "test3modi").withHeader( "test3").withFooter( "test4");
        app.group().modify(modificationGroup);
        assertThat(app.group().count(), CoreMatchers.equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(modificationGroup)));
    }


}
