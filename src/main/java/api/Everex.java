package api;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;

/**
 * Created by egorvas on 16.02.2018.
 */
public class Everex {

    protected static Response post(Map<String,Object> params){

        return rest()
                .headers(getDefaultHeaders())
                .body(params)
                .post(SystemConstants.EVEREX_API_URL);
    }

    protected static Headers getDefaultHeaders() {
        return new Headers(
                new Header("content-type","application/json"));

    }

}
