package reqres.helpers;

import org.apache.log4j.Logger;
import org.testng.Assert;

import io.restassured.response.Response;

public class HelperTestMethods {

    static Logger log = Logger.getLogger(HelperTestMethods.class);


    public void checkStatusIs200(Response response) throws Exception {
        log.info("checking for response code");
        Assert.assertEquals(response.getStatusCode(), 200,"Status check FAILED");

    }

    public void checkStatusIs201(Response response) throws Exception {
        log.info("checking for response code");
        Assert.assertEquals(response.getStatusCode(), 201, "Status check FAILED");

    }

    public void checkStatusIs202(Response response) throws Exception {
        log.info("checking for response code");
        Assert.assertEquals(response.getStatusCode(), 202, "Status check FAILED");

    }

    public void checkStatusIs204(Response response) throws Exception {
        log.info("checking for response code");
        Assert.assertEquals(response.getStatusCode(), 204, "Status check FAILED");

    }

    public void checkStatusIs400(Response response) throws Exception {
        log.info("checking for response code");
        Assert.assertEquals(response.getStatusCode(), 400, "Status check FAILED");

    }

    public void checkStatusIs404(Response response) throws Exception {
        log.info("checking for response code");
        Assert.assertEquals(response.getStatusCode(), 404, "Status check FAILED");
    }

    public void checkStatusIs500(Response response) throws Exception {
        
        log.info("checking for response code");

        Assert.assertEquals(response.getStatusCode(), 500, "Status check FAILED");

    }
}