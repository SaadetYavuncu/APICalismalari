package odevler;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev01 extends AutomationExerciseBaseUrl {

        /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "text/html; charset=utf-8"
    And
        Status Line should be HTTP/1.1 200 OK
    And
         There must be 12 Women, 9 Men, 13 Kids usertype in products
      */

        //set the Url
        //Set the Expected Data
        //send the request and Get the Response
        //Do assert


   /* @Test
    public void odev01() {

        //set the Url
        spec.pathParams("first", "api", "second", "productsList");

        //Set the Expected Data

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        //Do assert
        //SoftAssert softAssert = new SoftAssert();


        //HTTP Status Code should be 200
        //Content Type should be "text/html; charset=utf-8"
        //Status Line should be HTTP/1.1 200 OK
        response.then().
                statusCode(200).
                contentType(ContentType.HTML).statusLine("HTTP/1.1 200 OK");


        //There must be 12 Women, 9 Men, 13 Kids usertype in products
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = response.jsonPath();
        //jsonPath.prettyPrint();
        List<String> women = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("women = " + women);
        softAssert.assertEquals(12, women.size(),"Women sayisi 12'ye esit degil");

        List<String> men = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("men = " + men);
        softAssert.assertEquals(9, men.size(),"Men sayisi 9'a esit degil");

        List<String> kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("kids = " + kids);
        softAssert.assertEquals(13, kids.size(),"Kids saysi 13'e esit degil");

        softAssert.assertAll();
        */




         @Test
    public void h01() {
        //Set the Url
        spec.pathParams("first","api","second","productsList");
        //Send The Request and Get The Response
        Response response = given().spec(spec).get("/{first}/{second}");
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
        //Do Assertion
        //Then
        //    HTTP Status Code should be 200
        assertEquals(200,response.statusCode());
        //And
        //    Content Type should be "text/html; charset=utf-8"
        assertEquals("text/html; charset=utf-8",response.getContentType());
        //And
        //    Status Line should be HTTP/1.1 200 OK
        assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        //And
        //     There must be 12 Women, 9 Men, 13 Kids usertype in products
        List<String> categories =  jsonPath.getList("products.category.usertype.usertype");
        int countWomen = 0;
        int countMen = 0;
        int countKids = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals("Women")) countWomen++;
            if (categories.get(i).equals("Men")) countMen++;
            if (categories.get(i).equals("Kids")) countKids++;
        }
        System.out.println("countWomen = " + countWomen);
        System.out.println("countMen = " + countMen);
        System.out.println("countKids = " + countKids);
        assertEquals(12,countWomen);
        assertEquals(9,countMen);
        assertEquals(13,countKids);





    }




}