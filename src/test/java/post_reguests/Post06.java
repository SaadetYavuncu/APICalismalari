package post_reguests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {

   /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    Given
    https://dummy.restapiexample.com/api/v1
    And {
       "employee_name": "Tom Hanks",
       "employee_salary": 111111,
       "employee_age": 23,
       "profile_image": "Perfect image",
       "id": 4891
     }
     When
   User send POST Request to the Url
    Then
    Status code is 200
    And
    response body is like {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
    */


    @Test
    public void post06() {

        //Set the url
        spec.pathParam("first","create");

        //Set the Expected Data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        System.out.println("expectedData = " + expectedData);

        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
         response.prettyPrint();

         //Do Assertion
       DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
        //assertEquals("Successfully! Record has been added.",actualData.getMessage());  ==>Hard Codding




        /*
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(actualData.getData().getEmployee_name(),expectedData.getEmployee_name(),"Employee_name uyusmadi");
        softAssert.assertEquals(actualData.getData().getEmployee_salary(),expectedData.getEmployee_salary(),"Employee_salary uyusmadi");
        softAssert.assertEquals(actualData.getData().getEmployee_age(),expectedData.getEmployee_age(),"Employee_age uyusmadi");
        softAssert.assertEquals(actualData.getData().getProfile_image(),expectedData.getProfile_image(),"Profile_image uyusmadi");
        softAssert.assertAll();
         */

    }


}
