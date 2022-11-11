package get_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
          I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 200 todos
       And
           "quis eius est sint explicabo" should be one of the todos title
       And
           2, 7, and 9 should be among the userIds
     */


    /*
    verilen
        https://jsonplaceholder.typicode.com/todos
    Ne zaman
   URL'ye bir GET isteği gönderiyorum
 Ve
     Kabul türü "application/json"
 O zamanlar
     HTTP Durum Kodu 200 olmalıdır
 Ve
     Yanıt formatı "application/json" olmalıdır
 Ve
     200 todo olmalı
 Ve
     "quis eius est sint explicabo" yapılacaklar başlığından biri olmalı
 Ve
     2, 7 ve 9 kullanıcı kimlikleri arasında olmalıdır
     */
    @Test
    public void get01(){

        // 1. Set The Url

        spec.pathParam("first","todos");

        // 2. Set The expected Data (Put, Patch, Post)

        // 3. Send The request And get The Response
        Response response=given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200),
                        "title",hasItem("quis eius est sint explicabo"),
                        "userId",hasItems(2,7,9));



    }


}
