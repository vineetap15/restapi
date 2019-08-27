package reqres.helpers;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class World{
    Response res =null;
    JsonPath jsonPathValue =null; 

    public void setResponse(Response response){
        this.res = response;
    }

    public Response getResponse(){
        return res;
    }

    public String getResponseBody() throws Exception{

        return res.getBody().asString();
    }
     
    public int getResponseStatusCode(){

        return res.getStatusCode();
    }

    public String getParamValue(Response response,String str){
        jsonPathValue = response.jsonPath();
        return jsonPathValue.get(str);
    }

    public Map getParamList(Response response, String str){
        return response.jsonPath().getMap(str);
    }

}