package reqres.apitests.get;

import static io.restassured.path.json.JsonPath.from;

import java.util.Map;

import org.apache.log4j.Logger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import reqres.helpers.HelperTestMethods;
import reqres.helpers.World;
import reqres.utils.Utils;

public class UserDetails {
    Response res = null;
    static Logger log = Logger.getLogger(UserDetails.class);


    // @Given("^the user has GET api of user$")
    // public void the_user_has_GET_api_of_user() throws Throwable {
    //     Utils.setBaseURI();
    //     Utils.setContentType(ContentType.JSON);

    // }

    @When("^the user hits the GET api with valid id \"([^\"]*)\"$")
    public void the_user_hits_the_GET_api_with_valid_id(String id) throws Throwable {
        res = Utils.getUserReponse(id);
        log.info("user details are:   " +res.asString());
    }

    @Then("^the user should be fetched$")
    public void the_user_should_be_validated() throws Throwable {
        try {
            HelperTestMethods htm = new HelperTestMethods();
            htm.checkStatusIs200(res);
        } catch (Exception e) {
            log.error("GET is failed");
        }

        World world = new World();
        world.setResponse(res);
        log.info("User details are:  " + res.asString());
        Map data = from(world.getResponseBody()).getMap("data");
        log.info("data is - " + data.toString());
        log.info("id is - " + data.get("id"));
        log.info("email is - " + data.get("email"));
        log.info("firstname is - " + data.get("first_name"));
        log.info("lastname is - " + data.get("last_name"));
        log.info("avatar is - " + data.get("avatar"));

        Assert.assertTrue(world.getResponseBody().contains("id"));
        Assert.assertTrue(world.getResponseBody().contains("email"));
        Assert.assertTrue(world.getResponseBody().contains("first_name"));
        Assert.assertTrue(world.getResponseBody().contains("last_name"));
        Assert.assertTrue(world.getResponseBody().contains("avatar"));

    }
}
