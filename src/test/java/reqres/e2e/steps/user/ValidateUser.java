package reqres.e2e.steps.user;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.from;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.testng.Assert;

import reqres.helpers.HelperTestMethods;
import reqres.helpers.World;
import reqres.helpers.user.CreateUser;
import reqres.helpers.user.UpdateUser;
import reqres.utils.Utils;

public class ValidateUser {

    HelperTestMethods htm = new HelperTestMethods();
    World createUserWorld = new World();
    World updateUserWorld = new World();
    World getUserWorld = new World();
    // Utils util = new Utils();
    CreateUser cre = new CreateUser();
    UpdateUser upd = new UpdateUser();
    ObjectMapper mp = new ObjectMapper();
    static String id = "";
    Response res;
    static Logger log = Logger.getLogger(ValidateUser.class);

    @Given("^the user has POST api of create user$")
    public void the_user_has_POST_api_of_create_user() throws Throwable {
        Utils.setBaseURI();

    }

    @When("^the user enters the data \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_user_enters_the_data_and(String name, String job) throws Throwable {
        cre.setName(name);
        cre.setJob(job);
        mp.convertValue(cre, CreateUser.class);
    }

    @When("^the user hits the POST api$")
    public void the_user_hits_the_POST_api() throws Throwable {
        Response res = Utils.getUserPostReponse(ContentType.JSON, cre);
        createUserWorld.setResponse(res);
        log.info("create User response is:   " + createUserWorld.getResponseBody());
        // System.out.println("create User response is:   " + createUserWorld.getResponseBody());
    }

    @Then("^the user should be created$")
    public void the_user_should_be_created() throws Throwable {
        try {
            htm.checkStatusIs201(createUserWorld.getResponse());
            Assert.assertTrue(createUserWorld.getResponseBody().contains("name"));
            Assert.assertTrue(createUserWorld.getResponseBody().contains("job"));
            Assert.assertTrue(createUserWorld.getResponseBody().contains("id"));
            Assert.assertTrue(createUserWorld.getResponseBody().contains("createdAt"));
            ValidateUser.id = from(createUserWorld.getResponseBody()).getString("id");
        } catch (Exception e) {
            log.error("User creation FAILED");
        }

    }

    @Given("^the user has GET api of user$")
    public void the_user_has_GET_api_of_user() throws Throwable {
        Utils.setBaseURI();
        Utils.setContentType(ContentType.JSON);
    }

    @When("^the user hits the GET api with valid id$")
    public void the_user_hits_the_GET_api_with_valid_id() throws Throwable {
        log.info("Created user id is-- " + ValidateUser.id);
        Response res = Utils.getUserReponse(ValidateUser.id);

        log.info("Response of get api is--- " + res.asString());
        getUserWorld.setResponse(res);
        
    }

    @Then("^the user should be validated$")
    public void the_user_should_be_validated() {
        try {
            htm.checkStatusIs200(getUserWorld.getResponse());
            Assert.assertTrue(getUserWorld.getResponseBody().contains("id"));
            Assert.assertTrue(getUserWorld.getResponseBody().contains("email"));
            Assert.assertTrue(getUserWorld.getResponseBody().contains("first_name"));
            Assert.assertTrue(getUserWorld.getResponseBody().contains("last_name"));
            Assert.assertEquals(from(createUserWorld.getResponseBody()).getString("id"), ValidateUser.id, "id matched");
            // ValidateUser.id = from(createUserWorld.getResponseBody()).getString("id");
        } catch (Exception e) {
            log.error("FAILED to fetch a user details");
        }
    }

    @Given("^the user has PUT api of a user with valid id$")
    public void the_user_has_PUT_api_of_a_user_with_valid_id() throws Throwable {
        Utils.setBaseURI();
        Utils.setContentType(ContentType.JSON);
    }

    @When("^user enters the updated data \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_the_updated_data_and(String name, String job) throws Throwable {
        upd.setName(name);
        upd.setJob(job);
        mp.convertValue(upd, UpdateUser.class);
    }

    @When("^the user hits the PUT api$")
    public void the_user_hits_the_PUT_api() throws Throwable {
        Utils.setBasePath(id);
        updateUserWorld.setResponse(Utils.getUserPutReponse(ContentType.JSON, upd));
    }

    @Then("^the user should be updated$")
    public void the_user_should_be_updated() throws Throwable {
        try {
            htm.checkStatusIs200(updateUserWorld.getResponse());
            Assert.assertTrue(updateUserWorld.getResponseBody().contains("name"));
            Assert.assertTrue(updateUserWorld.getResponseBody().contains("job"));
            Assert.assertTrue(updateUserWorld.getResponseBody().contains("updatedAt"));
            // Assert.assertEquals(from(updateUserWorld.getResponseBody()).getString("id"),
            // ValidateUser.id, "id matched");
            log.info("updated name is--" + from(updateUserWorld.getResponseBody()).getString("name"));
            log.info("updated job is--" + from(updateUserWorld.getResponseBody()).getString("job"));

            Assert.assertEquals(from(updateUserWorld.getResponseBody()).getString("name"), upd.getName(),
                    "Name is updated");
            Assert.assertEquals(from(updateUserWorld.getResponseBody()).getString("job"), upd.getJob(),
                    "Job is updated");
        } catch (Exception e) {
            log.error("FAILED to UPDATE a user details");
        }
    }

    @Given("^the user has DELETE api with valid id$")
    public void the_user_has_DELETE_api_with_valid_id() throws Throwable {
        Utils.setBaseURI();
    }

    @When("^the user hits the DELETE api$")
    public void the_user_hits_the_DELETE_api() throws Throwable {
        res = Utils.getUserDeleteReponse(ValidateUser.id);

    }

    @Then("^the user should gets deleted$")
    public void the_user_should_gets_deleted() throws Throwable {
        try {
            htm.checkStatusIs204(res);
        } catch (Exception e) {
            log.info("FAILED to DLETE a user ");
        }
    }
}