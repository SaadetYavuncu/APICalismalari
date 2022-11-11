package post_reguests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {

/*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */


    @Test
    public void post03Pojo() {


        //Set the url
        spec.pathParam("first","todos");

        //Set the Expected Data
        JsonplaceholderPojo expectedData = new JsonplaceholderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        //response.prettyPrint();

        //Do Assertion
        JsonplaceholderPojo actualData = response.as(JsonplaceholderPojo.class);
        System.out.println("actualData = " + actualData);

            /*
             @JsonIgnoreProperties
             JSON verisinde gözükmesi istenmeyen anahtarlar belirtilir.
             */

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());

    }
}
