package reqres.apitests.post;

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
import reqres.helpers.repo.CreateRepo;
import reqres.helpers.repo.CreateRepoResponse;
import reqres.helpers.repo.GetARepoDetailOwnerResponse;
import reqres.helpers.repo.GetARepoDetailPermissionResponse;
import reqres.utils.Utils;

public class ValidateCreateRepo {
    CreateRepo crepo = new CreateRepo();
    ObjectMapper mp = new ObjectMapper();
    Response response =null;
    static Logger log = Logger.getLogger(ValidateCreateRepo.class);
    World world = new World();
    HelperTestMethods htm = new HelperTestMethods(); 
    static String reponame,desc,homepage;
    boolean pri,has_issues,has_projects,has_wiki;

   
@Given("^User has a POST Api to create a repository \"([^\"]*)\"$")
public void user_has_a_POST_Api_to_create_a_repository(String basepath) throws Throwable {
Utils.setBaseURI();
Utils.setBasePath(basepath);
    
}

@When("^user enter the \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\"$")
public void user_enter_the_and(String reponame, String desc, String homepage, String pri, String has_issues, String has_projects, String has_wiki) throws Throwable {

crepo.setName(reponame);
crepo.setDescription(desc);
crepo.setHomepage(homepage);
crepo.setPrivate(Boolean.parseBoolean(pri));
crepo.setHasIssues(Boolean.parseBoolean(has_issues));
crepo.setHasProjects(Boolean.parseBoolean(has_projects));
crepo.setHasWiki(Boolean.parseBoolean(has_wiki));
mp.convertValue(crepo, CreateRepo.class);
}

@When("^User hits the POST Api to create a repository$")
public void user_hits_the_POST_Api_to_create_a_repository() throws Throwable {
    response = Utils.getRepoPostResponse(ContentType.JSON, crepo);
    world.setResponse(response);
    log.info("repo is created successfully: ");
    log.info(world.getResponseBody());
}

@Then("^the repo should gets created$")
public void the_repo_should_gets_created() throws Throwable {
   htm.checkStatusIs201(response);
   CreateRepoResponse cr = new CreateRepoResponse();
   GetARepoDetailOwnerResponse owner = new GetARepoDetailOwnerResponse();
   GetARepoDetailPermissionResponse permision = new GetARepoDetailPermissionResponse();
   try {
       cr = mp.convertValue(response.jsonPath().get(""), CreateRepoResponse.class);
       owner = mp.convertValue(cr.getOwner(), GetARepoDetailOwnerResponse.class);
       permision = mp.convertValue(cr.getPermissions(),GetARepoDetailPermissionResponse.class);
   } catch (Exception e) {
       log.info("Unable to map the response objects");
   }

   Assert.assertEquals(crepo.getName(), cr.getName());
   Assert.assertEquals(crepo.getDescription(), cr.getDescription());
   Assert.assertEquals(crepo.getHomepage(), cr.getHomepage());
   Assert.assertEquals(crepo.getPrivate(), cr.getPrivate());
   Assert.assertEquals(crepo.getHasIssues(), cr.getHasIssues());
   Assert.assertEquals(crepo.getHasProjects(), cr.getHasProjects());
   Assert.assertEquals(crepo.getHasWiki(), cr.getHasWiki());

   
   //String reponame, String desc, String homepage, 
   //String pri, String has_issues, String has_projects, String has_wiki

}

}