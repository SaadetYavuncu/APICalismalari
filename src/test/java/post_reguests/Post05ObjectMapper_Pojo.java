package post_reguests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;
import utils.ObjectMapperUtils;

import java.util.jar.JarEntry;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonplaceholderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like  {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post05ObjectMapper() {

        //Set the Url
        spec.pathParam("first","todos");

        //Set the Expected Data
        JsonplaceholderPojo expectedData = new JsonplaceholderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //Set the Request ang Get  the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();


        //Do Assertion

       JsonplaceholderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonplaceholderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }
}
