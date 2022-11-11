package get_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Pojo extends JsonplaceholderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    //ObjectMapper + Pojo = En İyi Yöntem
    @Test
    public void get14Pojo() {

        //Set the url
        spec.pathParams("first","todos", "second", 198);

        //Set the Expected Data
        JsonplaceholderPojo expectedData = new JsonplaceholderPojo(10,"quis eius est sint explicabo",true);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //Do Assertion
       JsonplaceholderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),JsonplaceholderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
       assertEquals(expectedData.getUserId(), actualData.getUserId());
       assertEquals(expectedData.getTitle(), actualData.getTitle());
       assertEquals(expectedData.getCompleted(), actualData.getCompleted());




    }
}
