package reqres.apitests.get;

import static io.restassured.path.json.JsonPath.from;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reqres.helpers.HelperTestMethods;
import reqres.helpers.World;
import reqres.helpers.issueandcomments.CreateComment;
import reqres.helpers.issueandcomments.CreateIssue;
import reqres.helpers.issueandcomments.GetCommentsResponse;
import reqres.helpers.issueandcomments.GetCommentsResponseUser;
import reqres.utils.Utils;

public class GetARepoDetail {
    static String issueTitle;
    static String issueBody;
    static String commentBody;
    Response response;
    
    HelperTestMethods htm = new HelperTestMethods();
    CreateComment comment = new CreateComment();
    CreateIssue issue =  new CreateIssue();
    static Logger log = Logger.getLogger(GetARepoDetail.class);
    World world = new World();
    static int issueid;
    static int commentid;
    static String newComment;
    ObjectMapper mp = new ObjectMapper();


  // Get A repo deails---------------------------------------------------------------    
    @Given("^User has a GET Api which will fetch a repository detail \"([^\"]*)\"$")
public void the_user_has_GET_api_which_will_fetch_a_repository_detail(String basepath) throws Throwable {
    log.info("-------------------------------------------------");
        Utils.setBaseURI();
        Utils.setBasePath(basepath);
        log.info("user hit the get all comments api : " +Utils.getBaseURI()+Utils.getBasePath());
         
        
    }

    @When("^User hits the GET Api to fetch a repo details$")
public void the_user_hits_the_GET_api_to_fetch_a_repo_detail() throws Throwable {
    response = Utils.getUserReponseWithAuth();
        world.setResponse(response);
}
    
    @Then("^the repo details should be fetched and verify \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_repo_details_should_be_fetched(String name,String login, String admin_per, String push_per, String pull_per) throws Throwable {
      
        htm.checkStatusIs200(response);
        log.info("data----------------");
        log.info(world.getResponseBody());

    //     List<String> body = response.jsonPath().get("body");
    //     List<String> userlist = response.jsonPath().get("user.login");
    //     List<String> id= response.jsonPath().get("id");
    //     List<String> author_association= response.jsonPath().get("author_association");
        
    //     log.info("below details are parsed: ");

    //     log.info("Expected body is ---- " +body);
    //     log.info("-------------------------");
    //     log.info("-------------------------");
    //    // log.info("Actual body is ---- " +ValidateComments.newComment);
    //     log.info("-------------------------");
    //     log.info("-------------------------");
    //     log.info("userlist is ---- " +userlist);
    //     log.info("-------------------------");
    //     log.info("-------------------------");
    //     log.info("id is ---- " +id);
    //     log.info("-------------------------");
    //     log.info("-------------------------");
    //     log.info("author_association is ---- " +author_association);

    //     List<GetCommentsResponse> l = response.jsonPath().getList("",GetCommentsResponse.class);
    //    GetCommentsResponseUser user = mp.convertValue(l.get(0).getUser(), GetCommentsResponseUser.class);
    //     log.info("login details ----"+user.getLogin());
    //     log.info("List size----" +l.size());

    //      for(int i=0;i< l.size();i++){
    //          log.info("body " +l.get(i).getBody());
    //          log.info("user " +l.get(i).getUser());
    //      }
    //     // GetCommentsResponse gr =  mp.convertValue(response.ge, toValueType)
        

        
    //     // Assert.assertTrue(id.contains(ValidateComments.commentid));
    //     // Assert.assertTrue(body.contains(ValidateComments.newComment));
    //     // Assert.assertTrue(author_association.contains("OWNER"));
    //     // Assert.assertTrue(userlist.contains("vineetap15"));

    //     // Assert.assertTrue(world.getResponseBody().contains("created_at"));
         

        }
        
    } 
