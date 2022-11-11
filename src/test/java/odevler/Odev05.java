package odevler;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPatchPojo;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev05 extends ReqresBaseUrl {

    /*

    //Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.

       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test //Map ile
    public void odev05a() {

        //Set the Url
        spec.pathParams("first","users","second",2);

        //Set the Expected Data
        ReqresTestData obj = new ReqresTestData();
       Map<String,String> expectedData = obj.expectedDataMethod("neo",null);
        System.out.println("expectedData = " + expectedData);

        //Send  GET Request and Get the Response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
       response.prettyPrint();

        //Do Assertion
        Map<String,String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));


    }


    @Test  //Pojo ile
    public void odev05b() {

        //Set the Url
        spec.pathParams("first","users","second",2);

        //Set the Expected Data
        ReqresPatchPojo expectedData = new ReqresPatchPojo("neo");
        System.out.println("expectedData = " + expectedData);

        //Send  GET Request and Get the Response
      Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        ReqresPatchPojo actualData = response.as(ReqresPatchPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());

    }
}
