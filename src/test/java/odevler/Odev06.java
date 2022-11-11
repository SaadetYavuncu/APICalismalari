package odevler;

import base_url.SwaggerBaseUrl;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.junit.Test;

import pojos.SwaggerDeletePojo;
import pojos.SwaggerPojo;
import utils.ObjectMapperUtils;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev06 extends SwaggerBaseUrl {

    /*
    https://petstore.swagger.io/ documantation adresini kullanarak
    'user' oluşturan ve
    oluşsan bu user'ı silen bir otomasyon kodu yazınız.
    */


    @Test
    public void postTest06() {

        //Set the Url
        spec.pathParams("first","v2","second","user");

        //Set the Expected Data
        SwaggerPojo expectedData = new SwaggerPojo("saadet123","saadet","yavuncu","saadet@gmail","1234","55555");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        SwaggerDeletePojo actualData = response.as(SwaggerDeletePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.getStatusCode());
        assertEquals("unknown", actualData.getType());

    }

    @Test
    public void deleteTest06() {
        //Set the Url
        spec.pathParams("first","v2","second","user","third","saadet123");

        //Set the Expected Data
        SwaggerDeletePojo expectedDeleteData = new SwaggerDeletePojo(200,"unknown","saadet123");
        System.out.println("expectedDeleteData = " + expectedDeleteData);

        //Send the Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}/{third}");
       response.prettyPrint();

       //Do Assertion
        SwaggerDeletePojo actualDeleteData =response.as(SwaggerDeletePojo.class);
        System.out.println("actualDeleteData"+actualDeleteData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDeleteData.getCode(),actualDeleteData.getCode());
        assertEquals(expectedDeleteData.getType(), actualDeleteData.getType());
        assertEquals(expectedDeleteData.getMessage(), actualDeleteData.getMessage());


    }
}



