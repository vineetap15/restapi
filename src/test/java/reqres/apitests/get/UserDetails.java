package reqres.apitests.get;

import static io.restassured.path.json.JsonPath.from;

import java.util.Map;

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

    @Given("^the user has GET api of user$")
    public void the_user_has_GET_api_of_user() throws Throwable {
        Utils.setBaseURI();
        Utils.setContentType(ContentType.JSON);

    }

    @When("^the user hits the GET api with valid id \"([^\"]*)\"$")
    public void the_user_hits_the_GET_api_with_valid_id(String id) throws Throwable {
        res = Utils.getUserReponse(id);
    }

    @Then("^the user should be validated$")
    public void the_user_should_be_validated() throws Throwable {
        try {
            HelperTestMethods htm = new HelperTestMethods();
            htm.checkStatusIs200(res);
        } catch (Exception e) {
            System.out.println("GET is failed");
        }

        World world = new World();
        world.setResponse(res);
        System.out.println("User details are:  ");
        System.out.println(res.asString());
        Map data = from(world.getResponseBody()).getMap("data");
        System.out.println("data is - " + data.toString());
        System.out.println("id is - " + data.get("id"));
        System.out.println("email is - " + data.get("email"));
        System.out.println("firstname is - " + data.get("first_name"));
        System.out.println("lastname is - " + data.get("last_name"));
        System.out.println("avatar is - " + data.get("avatar"));

        Assert.assertTrue(world.getResponseBody().contains("id"));
        Assert.assertTrue(world.getResponseBody().contains("email"));
        Assert.assertTrue(world.getResponseBody().contains("first_name"));
        Assert.assertTrue(world.getResponseBody().contains("last_name"));
        Assert.assertTrue(world.getResponseBody().contains("avatar"));

    }
}
