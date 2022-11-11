package get_requests;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresBaseUrl {

     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200   1)Durum kodu 200
        2)Print all pantone_values   2)Tüm pantone_değerlerini yazdır
        3)Print all ids greater than 3 on the console   3)Konsolda 3'ten büyük tüm kimlikleri yazdır 3'ten büyük 3 kimlik olduğunu iddia et
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
           4)Kimlikleri 3'ten küçük olan tüm adları konsolda yazdır kimlikleri 3'ten küçük olan isimler 2'dir
        */

    @Test
    public void get06() {

        //Set the Url
        spec.pathParam("first", "unknown");

        //Set the Expected Data

        //Send  GET Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //response.then().body("data",hasSize(6));

        //Do Assertion
        //1)Status code is 200   1)Durum kodu 200
        assertEquals(200, response.getStatusCode());

        //2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        System.out.println("jsonPath.getList(data.pantone_value) = " + jsonPath.getList("data.pantone_value"));

       /*
        List<Integer> list = jsonPath.getList("data.pantone_value");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". data : " + list.get(i));
        }
        List<Integer> idler = jsonPath.getList("data.id");

        for (int i = 0; i < idler.size(); i++) {
            if (idler.get(i) > 3) {
                System.out.println(i + ".id:" + idler.get(i));
            }
}
*/

        //3)Print all ids greater than 3 on the console
        //System.out.println(jsonPath.getList("data.id"));
        jsonPath.getList("data.findAll{it.id>3}.id");
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids: " +ids);

        //Assert that there are 3 ids greater than 3
        assertEquals(3,ids.size());

        //4)Print all names whose ids are less than 3 on the console
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names: " +names);

        //Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());



    }

}
