package reqres.utils;


import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reqres.helpers.user.CreateUser;
import reqres.helpers.user.UpdateUser;

public class Utils {
    Response res = null;
    public static String path;
    static Logger log = Logger.getLogger(Utils.class);


    public static void setBaseURI(){
        log.info("setting base url");
        RestAssured.baseURI = "https://reqres.in/api/users";
    }

    public static void resetBaseURI(){
        RestAssured.baseURI = null;
    }

    public static void setBasePath(String basePath){
        RestAssured.basePath = basePath;
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

    public static Response getUserPostReponse(ContentType type,CreateUser user){
        log.info("calling getUserPostReponse");

        return given().contentType(type).body(user).post();

    }

    public static Response getUserPutReponse(ContentType type,UpdateUser user){
        log.info("calling getUserPutReponse");

        return given().contentType(type).body(user).put();
    }

    public static Response getUserDeleteReponse(String id){
        log.info("calling delete");

        return delete(id);
    }


}