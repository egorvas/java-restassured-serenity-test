package api;


import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;

/**
 * Created by egorvas on 16.02.2018.
 */
public class Ethplorer {

    protected static Response get(String endPoint, Map<String,Object> params){
        Response originalResponse = rest()
                .headers(getDefaultHeaders())
                .params(params)
                .get(SystemConstants.ETHPLORER_API_URL + endPoint);
        Response newResponse = new ResponseBuilder().clone(originalResponse).setContentType(ContentType.JSON)
                .build();
        return  newResponse;
    }

    protected static Headers getDefaultHeaders() {
        return new Headers(
                new Header("User-Agent", ""),
                new Header("Content-Type","application/json"));

    }

}
