package odevler;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev03 extends ReqresBaseUrl {


    /*

     //Map ve Pojo Class ile ayrı ayrı yapınız.

     Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/




    @Test //Map ile
    public void odev03a() {

        //Set the Url
        spec.pathParam("first", "users");

        //Set the Expected Data
        ReqresTestData obj = new ReqresTestData();
        Map<String, String> expectedData = obj.expectedDataMethod("morpheus", "leader");
        System.out.println("expectedData = " + expectedData);



        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        //1.yol MAP
        Map<String, String> actualData = response.as(HashMap.class); //De-Serialization
        System.out.println("actualData = " + actualData);

        //assertEquals(expectedData.get("createdAt"), actualData.get("createdAt"));
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));

    }


        @Test //Pojo ile
        public void odev03b() {


           //Set the Url
            spec.pathParam("first", "users");

            //Set the Expected Data
            ReqresPojo expectedData1 = new ReqresPojo("morpheus", "leader");
            System.out.println("expectedData1 = " + expectedData1);

            //Send the request and get the response
            Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData1).post("/{first}");
            response.prettyPrint();

            //Do Assertion
            //2.Yol POJO
            ReqresPojo actualData1 = response.as(ReqresPojo.class);
            System.out.println("actualData1 = " + actualData1);

            assertEquals(201, response.getStatusCode());
            assertEquals(expectedData1.getName(), actualData1.getName());
            assertEquals(expectedData1.getJob(), actualData1.getJob());


    }
}

