package odevler;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev02 extends AutomationExerciseBaseUrl {

    /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/



    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion


    @Test
    public void odev02() {

        //Set the Url
        spec.pathParams("first","api","second","brandsList");

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).when().get("/{first}/{second}");
        //response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

       //Do Assertion
        assertEquals(200, response.getStatusCode());
        assertEquals("text/html; charset=utf-8",response.getContentType());
        //System.out.println("response.getContentType() = " + response.getContentType());
        assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        List<String> HMList = jsonPath.getList("brands.findAll{it.brand=='H&M'}.brand");
        System.out.println("HMList = " + HMList);

        List<String> PoloList= jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("PoloList = " + PoloList);

        assertEquals(HMList.size(),PoloList.size());

    }
}
