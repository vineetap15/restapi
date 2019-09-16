package reqres.apitests.delete;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import reqres.helpers.HelperTestMethods;
import reqres.helpers.World;
import reqres.utils.Utils;

public class DeleteRepo {

    Response response;
    HelperTestMethods htm = new HelperTestMethods();
    static Logger log = Logger.getLogger(DeleteRepo.class);
    World world = new World();
    ObjectMapper mp = new ObjectMapper();

    @Given("^User has a DELETE Api to delete a repository \"([^\"]*)\"$")
public void user_has_a_DELETE_Api_to_delete_a_repository(String basepath) throws Throwable {
    log.info("setting base path");
        Utils.setBaseURI();
        Utils.setBasePath(basepath);
}

@When("^User hits the DELETE Api to delete a repository$")
public void user_hits_the_DELETE_Api_to_delete_a_repository() throws Throwable {
    response = Utils.getDeleteReponse();
}

@Then("^the repo should gets deleted$")
public void the_repo_should_gets_deleted() throws Throwable {
    htm.checkStatusIs204(response);
    log.info("the repo successfully deleted");
}

}