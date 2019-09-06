package reqres.e2e.steps.issueAndComments;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.from;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.testng.Assert;

import reqres.helpers.HelperTestMethods;
import reqres.helpers.World;
import reqres.helpers.issueandcomments.CreateComment;
import reqres.helpers.issueandcomments.CreateIssue;
import reqres.helpers.issueandcomments.GetCommentsResponse;
import reqres.helpers.issueandcomments.GetCommentsResponseUser;
import reqres.helpers.user.CreateUser;
import reqres.helpers.user.UpdateUser;
import reqres.utils.Utils;

public class ValidateComments {
    static String issueTitle;
    static String issueBody;
    static String commentBody;
    Response response;
    
    HelperTestMethods htm = new HelperTestMethods();
    CreateComment comment = new CreateComment();
    CreateIssue issue =  new CreateIssue();
    static Logger log = Logger.getLogger(ValidateComments.class);
    World world = new World();
    static int issueid;
    static int commentid;
    static String newComment;

// Create Issue-----------------------------------------------------------

    @Given("^the user has POST api of create issue$")
    public void the_user_has_POST_api_of_create_issue() throws Throwable {
        
        Utils.setBaseURI();
        Utils.setBasePath("vineetap15/restapi/issues");
        log.info("create Issue URL is: " +Utils.getBaseURI()+Utils.getBasePath());
        log.info("-------------------------------------------------");


    }
    
    @When("^the user enters the title \"([^\"]*)\"$")
    public void the_user_enters_the_title(String title) throws Throwable {
        issue.setTitle(title);
        ValidateComments.issueTitle = title;
        log.info("Entered issue title is:  " +title);
    }
    
    @When("^the user enters the  body \"([^\"]*)\"$")
    public void the_user_enters_the_body(String body) throws Throwable {
        issue.setBody(body);
        ValidateComments.issueBody = body;
        log.info("Entered issue body message is:  " +body);

    }
    
    @When("^the user enters the assignees$")
    public void the_user_enters_the_assignees(List<String> assignees) throws Throwable {
        issue.setAssignees(assignees);

        log.info("Entered assignees are:  " +assignees);


    }
   
    
    @When("^the user enters the labels$")
    public void the_user_enters_the(List<String> labels) throws Throwable {
        issue.setLabels(labels);
        log.info("Entered labels are:  " +labels);

    }
    
    @When("^the user hits the POST api for issue creation$")
    public void the_user_hits_the_POST_api_for_issue_creation() throws Throwable {
        response = Utils.getIssuePostResponse(ContentType.JSON, issue);
        world.setResponse(response);
        
    }
    
    @Then("^the issue should be created$")
    public void the_issue_should_be_created() throws Throwable {
        htm.checkStatusIs201(world.getResponse());
        log.info("issue successfully created. Response data: " +world.getResponseBody());
        Assert.assertTrue(world.getResponseBody().contains("id"));
        Assert.assertTrue(world.getResponseBody().contains("created_at"));
        Assert.assertTrue(world.getResponseBody().contains("updated_at"));

        ValidateComments.issueid = Integer.parseInt(from(world.getResponseBody()).getString("number"));
        log.info("issue body : " +from(world.getResponseBody()).get("body"));
        log.info("issue tite : " +from(world.getResponseBody()).get("title"));

        Assert.assertEquals(from(world.getResponseBody()).get("body"), ValidateComments.issueBody);
        Assert.assertEquals(from(world.getResponseBody()).get("title"), ValidateComments.issueTitle);

    }
    
   // Create Comments----------------------------------------------------------- 
    @Given("^the user has POST api of create a comments$")
    public void the_user_has_POST_api_of_create_a_comments() throws Throwable {
        log.info("-------------------------------------------------");

        Utils.setBaseURI();
        String basepath ="/vineetap15/restapi/issues/"+ValidateComments.issueid+"/comments";
        Utils.setBasePath(basepath);
        log.info("create comments URL is: " +Utils.getBaseURI()+Utils.getBasePath());
    }
    
    @When("^the user enter the comment body \"([^\"]*)\"$")
    public void the_user_enter_the_comment_body(String body) throws Throwable {
        comment.setBody(body);;
        ValidateComments.commentBody = body;
        log.info("my comment is : " +body);

    }
    
    @When("^the user hits the POSt api for comments creation$")
    public void the_user_hits_the_POSt_api_for_comments_creation() throws Throwable {
        response = Utils.getCommentPostResponse(ContentType.JSON, comment);
        world.setResponse(response);
        log.info("create comment status code is : " +response.getStatusCode());
    }
    
    @Then("^the comment should be created$")
    public void the_comment_should_be_created() throws Throwable {
        htm.checkStatusIs201(world.getResponse());
        log.info("comment successfully created. Response data: " +world.getResponseBody());
        Assert.assertTrue(world.getResponseBody().contains("id"));
        Assert.assertTrue(world.getResponseBody().contains("created_at"));
        Assert.assertTrue(world.getResponseBody().contains("updated_at"));
        Assert.assertTrue(world.getResponseBody().contains("body"));
        //ValidateComments.issueid = Integer.parseInt(from(world.getResponseBody()).getString("id"));

        Assert.assertEquals(from(world.getResponseBody()).getString("body"), commentBody);
        ValidateComments.commentid = from(world.getResponseBody()).get("id");
        //Assert.assertEquals(from(world.getResponseBody()).get("title"), issueTitle);
    }

   // Edit Comments----------------------------------------------------------- 

    @Given("^the user has a PATCH api to edit a comment$")
    public void the_user_has_a_PATCH_api_to_edit_a_comment() throws Throwable {
        Utils.getBaseURI();
        String basepath = "/vineetap15/restapi/issues/comments/"+ValidateComments.commentid;
        log.info("The comment id is ----" +ValidateComments.commentid);
        Utils.setBasePath(basepath);
        log.info("edit comment url is:  " +Utils.getBaseURI()+Utils.getBasePath());
    }
    
    @When("^user edited the comment \"([^\"]*)\"$")
    public void user_edited_the_comment(String newComment) throws Throwable {
        comment.setBody(newComment);
        ValidateComments.newComment = newComment;
    }
    
    @When("^user hit the PATCH api$")
    public void user_hit_the_PATCH_api() throws Throwable {
        response = Utils.getCommentPatchResponse(ContentType.JSON, comment);
        world.setResponse(response);
    }
    
    @Then("^user is able to edit the comment successfully$")
    public void user_is_able_to_edit_the_comment_successfully() throws Throwable {
        log.info("Comment "+ValidateComments.commentid+" has been updated with text : "+ValidateComments.newComment);
        htm.checkStatusIs200(world.getResponse());
        Assert.assertEquals(from(world.getResponseBody()).get("body"), ValidateComments.newComment);
    }

  // Get All comments-----------------------------------------------------------  
    @Given("^the user has GET api to read all the comments to an issue$")
    public void the_user_has_GET_api_to_read_all_the_comments_to_an_issue() throws Throwable {
        log.info("-------------------------------------------------");
        Utils.setBaseURI();
        

    }
    
    @When("^the user hits the GET api to read all the comments to an issue$")
    public void the_user_hits_the_GET_api_to_read_all_the_comments_to_an_issue() throws Throwable {
        String basepath ="vineetap15/restapi/issues/"+ValidateComments.issueid+"/comments";
        //String basepath ="https://api.github.com/repos/vineetap15/restapi/issues/14/comments";
        log.info("user hit the get all comments api : " +Utils.getBaseURI()+Utils.getBasePath());
         Utils.setBasePath(basepath);
        response = Utils.getUserReponseWithAuth();
        world.setResponse(response);
    }
    
    @Then("^user can view all the comments$")
    public void user_can_view_all_the_comments() throws Throwable {
        log.info("Comments details for an issue:  " +world.getResponseBody());
        ///log.info(world.getResponseBody());
        htm.checkStatusIs200(response);

        
        
        //List<String> commentslist = new ArrayList<String>();
        //List<String> commentslist  = response.;

      //  int size = commentslist.size();
        // log.info("SIZE of response is:  " +size);

        // for(int i=0;i<size;i++){
        //     //String commentdetail;
        //     // String username,created_at,updated_at,commentsBody,author_association;
        //     // Map<String,String> commentdetail = new HashMap<String,String>();

        //     // GetCommentsResponseUser u = response.as(GetCommentsResponseUser.class);
        //     // GetCommentsResponse g = response.as(GetCommentsResponse.class);
        //     // mp.convertValue(world.getResponseBody(),GetCommentsResponse.class);

        //     Map<Integer,String> mp = new HashMap<Integer,String>();
        //     mp.put(i,commentslist.get(i));


        //     commentid = Integer.parseInt(from(mp.get(i)).getString("id"));
        //     String created_at = from(mp.get(i)).getString("created_at");
        //     String updated_at = from(mp.get(i)).getString("updated_at");
        //     String author_association = from(mp.get(i)).getString("author_association");
        //     String body = from(mp.get(i)).getString("body");

        //     log.info("comment id-------" +commentid);
        //     log.info("created_at-------" +created_at);
        //     log.info("updated_at-------" +updated_at);
        //     log.info("author_association-------" +author_association);
        //     log.info("body-------" +body);
            

        //     Map<String,String> userdetails = response.jsonPath().getMap("user[0]");

        //     Assert.assertTrue(world.getResponseBody().contains("id"));
        //     Assert.assertTrue(world.getResponseBody().contains("user.login"));
        //     Assert.assertTrue(world.getResponseBody().contains("created_at"));
        //     Assert.assertTrue(world.getResponseBody().contains("updated_at"));
        //     Assert.assertTrue(world.getResponseBody().contains("author_association"));
        //     Assert.assertTrue(world.getResponseBody().contains("body"));
            
        }

   //Deleting comments---------------------------------------------

        @Given("^the user has a DELETE api to delete a comment$")
public void the_user_has_a_DELETE_api_to_delete_a_comment() throws Throwable {
    String basepath = "/vineetap15/restapi/issues/comments/"+ValidateComments.commentid; 
    Utils.setBaseURI();
    Utils.setBasePath(basepath);
    log.info("Delete URL :  " +Utils.getBaseURI()+Utils.getBasePath());

}

@When("^user hit the DELETE api for comment deletion$")
public void user_hit_the_DELETE_api_for_comment_deletion() throws Throwable {
    world.setResponse(Utils.getCommentDeleteReponse());
}

@Then("^user is able to delete the comment successfully$")
public void user_is_able_to_delete_the_comment_successfully() throws Throwable {
    htm.checkStatusIs204(world.getResponse());
    log.info("Comment "+ValidateComments.commentid+" has been deleted");
}
        
    } 
