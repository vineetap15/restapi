package reqres.apitests.get;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.testng.Assert;

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

public class ValidateGetARepoDetail {

    Response response;
    HelperTestMethods htm = new HelperTestMethods();
    static Logger log = Logger.getLogger(ValidateGetARepoDetail.class);
    World world = new World();
    ObjectMapper mp = new ObjectMapper();

    // Get A repo
    // deails---------------------------------------------------------------
    @Given("^User has a GET Api which will fetch a repository detail \"([^\"]*)\"$")
    public void user_has_a_GET_Api_which_will_fetch_a_repository_detail(String basepath) throws Throwable {
        log.info("-------------------------------------------------");
        Utils.setBaseURI();
        Utils.setBasePath(basepath);

    }

    @When("^User hits the GET Api to fetch a repo details$")
    public void user_hits_the_GET_Api_to_fetch_a_repo_details() throws Throwable {
        response = Utils.getUserReponseWithAuth();
        world.setResponse(response);
    }

    @Then("^the repo details should be fetched and verify \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_repo_details_should_be_fetched(String name, String login, String admin_per, String push_per,
            String pull_per) throws Throwable {

        htm.checkStatusIs200(response);
        log.info("data----------------");
        log.info(world.getResponseBody());
        GetARepoDetailOwnerResponse owner;
        GetARepoDetailPermissionResponse permission;

        try {
            log.info("---------------------------");
            GetUserAllReposResponse lists = mp.convertValue(response.jsonPath().get(""), GetUserAllReposResponse.class);

            owner = mp.convertValue(lists.getOwner(), GetARepoDetailOwnerResponse.class);
            permission = mp.convertValue(lists.getPermissions(), GetARepoDetailPermissionResponse.class);

            log.info("Repo  name: " + lists.getName());
            log.info("Login  name: " + owner.getLogin());
            log.info("Admin's permissions : " + permission.getAdmin());
            log.info("Push permissions : " + permission.getPush());
            log.info("Pull permissions : " + permission.getPull());

            Assert.assertEquals(name, lists.getName());
            Assert.assertEquals(login, owner.getLogin());
            Assert.assertEquals(admin_per, Boolean.toString(permission.getAdmin()));
            Assert.assertEquals(admin_per, Boolean.toString(permission.getAdmin()));
            Assert.assertEquals(push_per, Boolean.toString(permission.getPush()));
            Assert.assertEquals(pull_per, Boolean.toString(permission.getPull()));

        } catch (Exception e) {
            log.info("failed to parsed JSON response: " + e);
        }

    }

}
