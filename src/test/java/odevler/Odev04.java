package odevler;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev04 extends ReqresBaseUrl {


    /*

   // Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.


        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/


    @Test //Map ile
    public void odev04a() {

        //set the Url
        spec.pathParams("first","api","second",2);

        //Set the Expected Data
        ReqresTestData obj = new ReqresTestData();
        Map<String,String> expectedData = obj.expectedDataMethod("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        //send the request and Get the Response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
       response.prettyPrint();

        //Do assert
       Map<String,String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));
    }


    @Test  //Pojo ile
    public void odev04b() {

        //set the Url
        spec.pathParams("first","api","second",2);

        //Set the Expected Data
        ReqresPojo expectedData = new ReqresPojo("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        //send the request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //Do assert
        ReqresPojo actualData =response.as(ReqresPojo.class);  //Gson  ile oluyor! as methodu
        System.out.println("actualData = " + actualData);

       ReqresPojo actualData1 = ObjectMapperUtils.convertJsonToJava(response.asString(),ReqresPojo.class);
        System.out.println("actualData1 = " + actualData1);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());


    }
}
