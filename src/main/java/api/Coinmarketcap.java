package api;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;

/**
 * Created by egorvas on 16.02.2018.
 */
public class Coinmarketcap {

    protected static Response get(String endPoint, Map<String,Object> params){
        return rest()
                .headers(getDefaultHeaders())
                .params(params)
                .get(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }

    protected static Response get(String endPoint){
        return rest()
                .headers(getDefaultHeaders())
                .get(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }

    protected static Response get(String endPoint, Headers headers, Map<String,Object> params){
        return rest()
                .headers(headers)
                .params(params)
                .get(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }

    protected static Response get(String endPoint,Headers headers){
        return rest()
                .headers(headers)
                .get(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }

    protected static Response post(String endPoint, Map<String,Object> body){
        return rest()
                .headers(getDefaultHeaders())
                .body(body)
                .post(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }


    protected static Response post(String endPoint) {
        return rest()
                .headers(getDefaultHeaders())
                .post(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }


    protected static Response post(String endPoint,Headers headers,Map<String,Object> body){
        return rest()
                .headers(headers)
                .body(body)
                .post(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }


    protected static Response post(String endPoint,Headers headers){
        return rest()
                .headers(headers)
                .post(SystemConstants.COINMARKETCAP_API_URL + endPoint);
    }


    protected static Headers getDefaultHeaders() {
        return new Headers(
                new Header("Content-Type","application/json; charset=UTF-8"));

    }

}
