package reqres.apitests.get;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import reqres.helpers.HelperTestMethods;
import reqres.helpers.World;
import reqres.helpers.repo.GetARepoDetailOwnerResponse;
import reqres.helpers.repo.GetARepoDetailPermissionResponse;
import reqres.helpers.repo.GetUserAllReposResponse;
import reqres.utils.Utils;

public class ValidateGetUserAllRepos {

    Response response;
    HelperTestMethods htm = new HelperTestMethods();
    static Logger log = Logger.getLogger(ValidateGetUserAllRepos.class);
    World world = new World();
    ObjectMapper mp = new ObjectMapper();

    // Get A repo
    // deails---------------------------------------------------------------
    @Given("^User has a GET Api which will lists all repos for a user \"([^\"]*)\"$")
    public void user_has_a_GET_Api_which_will_lists_all_repos_for_a_user(String basepath) throws Throwable {
        log.info("-------------------------------------------------");
        Utils.setBaseURI();
        Utils.setBasePath(basepath);
        log.info("user hit the get all comments api : " + Utils.getBaseURI() + Utils.getBasePath());

    }

    @When("^User hits the GET Api to get all repos$")
    public void user_hits_the_GET_Api_to_get_all_repos() throws Throwable {
        response = Utils.getUserReponseWithAuth();
        world.setResponse(response);
    }

    @Then("^all the repositories should gets fetched$")
    public void all_the_repositories_should_gets_fetched() throws Throwable {

        htm.checkStatusIs200(response);
        log.info("data----------------");
        log.info(world.getResponseBody());
        GetARepoDetailOwnerResponse owner;
        GetARepoDetailPermissionResponse permission;
       
        try {
            List<GetUserAllReposResponse> lists = response.jsonPath().getList("", GetUserAllReposResponse.class);
            log.info("response list size-----" + lists.size());
            for(int i=0;i<lists.size();i++){
             owner =mp.convertValue(lists.get(i).getOwner(), GetARepoDetailOwnerResponse.class);
             permission = mp.convertValue(lists.get(i).getPermissions(), GetARepoDetailPermissionResponse.class) ;
             log.info("Repo "+(i+1)+ " name: " +lists.get(i).getName());
             log.info("Admin's permissions : " +i+ " : " +permission.getAdmin());
             log.info("---------------------------");
            }

        } catch (Exception e) {
            log.info("failed to parsed JSON response: " + e);
        }


    }

}
