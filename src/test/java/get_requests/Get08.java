package get_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JsonPlaceHolderTestData;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get08<JsonplaceholderTestData> extends JsonplaceholderBaseUrl {

    //De-Serialization: Json datayı Java objesine çevirme
    //Serialization: Java objesini Json formatına çevirme.
    //De-Serialization: Iki türlü yapacağız.
    //Gson: Google tarafından üretilmiştir.
    //Object Mapper: Daha popüler...

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion

    @Test
    public void get08() {

//Set the Url
        spec.pathParams("first", "todos", "second", 2);

//Set The Expected Data ==> Payload

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);


//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.getStatusCode());

    }



    //Dinamik yöntem
    @Test
    public void get08b() {

//Set the Url
        spec.pathParams("first", "todos", "second", 2);

//Set The Expected Data ==> Payload
        JsonPlaceHolderTestData objJsonPlcHldr = new JsonPlaceHolderTestData();

        Map<String, Object> expectedData = objJsonPlcHldr.expectedDataMethod(1, "quis ut nam facilis et officia qui", false);
        System.out.println(expectedData);


//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());


    }


        @Test

        public void get08c() {
            spec.pathParams("first","todos","second",2);
            Response response=given().spec(spec).when().get("/{first}/{second}");
            response.then().statusCode(200).header("Via",equalTo("1.1 vegur")).header("Server",equalTo("cloudflare"));

            //1.yol body ile
            response.then().statusCode(200).header("Via",equalTo("1.1 vegur")).header("Server",equalTo("cloudflare")).
                    body("userId",equalTo(1),
                            "title",equalTo("quis ut nam facilis et officia qui"),
                            "completed",equalTo(false));

            //2.yol json ile
            JsonPath json =response.jsonPath();
            SoftAssert softAssert= new SoftAssert();
            softAssert.assertEquals(json.getInt("userId"),1);
            softAssert.assertEquals(json.getString("title"),"quis ut nam facilis et officia qui");
            softAssert.assertEquals(json.getBoolean("completed"),false);

            softAssert.assertAll();

            //3.yol map ile
            Map<String,Object> expectedData=new HashMap<>();
            expectedData.put("userId",1);
            expectedData.put("title","quis ut nam facilis et officia qui");
            expectedData.put("completed",false);
            System.out.println("expected data ="+expectedData);

            Map<String,Object> actualData=response.as(HashMap.class);//De-Serialization
            System.out.println("actual data ="+actualData);
            assertEquals(expectedData.get("userId"),actualData.get("userId"));
            assertEquals(expectedData.get("title"),actualData.get("title"));
            assertEquals(expectedData.get("completed"),actualData.get("completed"));

            //4.yol map dinamik yontem(66 -70 satirdaki gibi ekleme yapmadik parametre gonderip methodu kullandik)
            JsonPlaceHolderTestData objJsonPlcHldr = new JsonPlaceHolderTestData();
            Map<String,Object> expectedData2=objJsonPlcHldr.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
            Map<String,Object> actualData2=response.as(HashMap.class);
            assertEquals(expectedData2.get("userId"),actualData2.get("userId"));
            assertEquals(expectedData2.get("title"),actualData2.get("title"));
            assertEquals(expectedData2.get("completed"),actualData2.get("completed"));

    }

}
