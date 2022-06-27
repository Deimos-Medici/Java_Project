package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;


public class TestBase {

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

      public boolean isIssueOpen(int issueId) throws IOException {

        if (bagReportsCondition(issueId).equals("Closed") || bagReportsCondition(issueId).equals("Resolved")){
           return false;
          }
        return true;
    }

    private String bagReportsCondition(int issueId) {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
