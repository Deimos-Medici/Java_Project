package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.testng.SkipException;

import java.io.IOException;


public class TestBase {



    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    //  public boolean isIssueOpen(int issueId) throws IOException {

    //    JsonElement parsed = new  JsonParser().parse(json);
      //  return parsed.getAsJsonObject().get("issues").getAsJsonObject().get(0).getAsJsonObject().get("state_name").getAsString();;
      //  IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
      //  return !issue.getStatus().getName().equals("resolved");
   // }



  //  public void skipIfNotFixed(int issueId) throws IOException {
       // if (isIssueOpen(issueId)) {
     //       throw new SkipException("Ignored because of issue " + issueId);
     //   }
  //  }

}
