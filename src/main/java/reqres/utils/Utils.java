package reqres.utils;


import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reqres.helpers.issueandcomments.CreateComment;
import reqres.helpers.issueandcomments.CreateIssue;
import reqres.helpers.user.CreateUser;
import reqres.helpers.user.UpdateUser;

public class Utils {
    Response res = null;
    public static String path;
    static Logger log = Logger.getLogger(Utils.class);
    static String url;
    private static String auth= "Bearer ef5f17031142449f50937be1f0701998154047e3";

    public static void setBaseURI(){
        log.info("setting base url");
        //RestAssured.baseURI = "https://api.github.com/repos";
        RestAssured.baseURI = "https://api.github.com";
        //RestAssured.baseURI = "https://reqres.in/api/users";
    }

    public static String getBaseURI(){
        return RestAssured.baseURI;
    }

    public static void resetBaseURI(){
        RestAssured.baseURI = null;
    }

    public static void setBasePath(String basePath){
        RestAssured.basePath = basePath;
    }

    public static String getBasePath(){
        return RestAssured.basePath;
    }

    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    public static void setContentType(ContentType type){
        log.info("setting content type");

        RestAssured.given().contentType(type);
    }

    public static Response getUserReponse(String id){
        log.info("calling get");

        return get(id);
    }

    public static Response getUserReponseWithAuth(){
        log.info("calling get");

        return given().header("Authorization", auth ).get();
    }

    public static Response getUserPostReponse(ContentType type,CreateUser user){
        log.info("calling getUserPostReponse");

        return given().contentType(type).body(user).post();

    }

    public static Response getIssuePostResponse(ContentType type,CreateIssue issue){
        log.info("calling getIssuePostReponse");

        return given().header("Authorization", auth).contentType(type).body(issue).post();

    }

    public static Response getCommentPostResponse(ContentType type,CreateComment comment){
        log.info("calling getCommentPostReponse");

        return given().header("Authorization", auth).contentType(type).body(comment).post();

    }

    public static Response getCommentPatchResponse(ContentType type,CreateComment newcomment){
        log.info("calling getCommentPatchresponse");
        return given().header("Authorization",auth).contentType(type).body(newcomment).patch();
    }

    public static Response getUserPutReponse(ContentType type,UpdateUser user){
        log.info("calling getUserPutReponse");

        return given().contentType(type).body(user).put();
    }

    public static Response getUserDeleteReponse(String id){
        log.info("calling delete");

        return delete(id);
    }

    public static Response getCommentDeleteReponse(){
        log.info("calling delete");

        return given().header("Authorization",auth).delete();
    }


}