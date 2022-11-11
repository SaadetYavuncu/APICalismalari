package get_requests;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {

      /*
      Given
          https://gorest.co.in/public/v1/users
      When
          User send GET Request
      Then
          The value of "pagination limit" is 10
      And
          The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
      And
          The number of users should  be 10
      And
          We have at least one "active" status
      And
          Fr. Paramartha Bandopadhyay, Pres. Amarnath Dhawan and Sujata Chaturvedi are among the users
      And
          The female users are less than or equals to male users
   */

    /*
    Verilen https:gorest.co.inpublicv1users
     Kullanıcı GET İsteği Gönderdiğinde
     O Zaman "sayfalama limiti" değeri 10'dur
     Ve "geçerli bağlantı" "https:gorest.co.inpublicv1users?page=1" olmalıdır
     ve Kullanıcı sayısı olmalıdır 10 ol Ve en az bir "aktif" durumumuz var
    Ve Niranjan Gupta, Samir Namboothiri ve Gandharva Kaul kullanıcılar arasında
    Ve Kadın kullanıcılar erkek kullanıcılara eşit veya daha az
     */

    @Test
    public void get11() {


        //1. Set The URL
        spec.pathParam("first","users");

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 4. Do Assertion
        response.then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data",hasSize(10),
                        "data.status",hasItem("active"),
                        "data.name",hasItems("Navin Panicker", "Pres. Amarnath Dhawan" ,"Sujata Chaturvedi"));

        //Kadın ve Erkek sayılarını karşılastıralım.
        //1.Yol
       List<String> genders = response.jsonPath().getList("data.gender");
        System.out.println(genders);
       int numFemale =0;
       int male =0;

        for (String w: genders) {
            if(w.equalsIgnoreCase("numFemale")){
                numFemale++;

            }
        }
        assertTrue(numFemale<=genders.size()-numFemale);


        //2. Yol Kadın ve Erkek sayılarını Groovy ile bulalım
        List<String> femaleNames = response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        System.out.println("femaleNames = " + femaleNames);

        List<String> maleNames = response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println("maleNames = " + maleNames);

        assertTrue(femaleNames.size()<=maleNames.size());

        double erkek = response.jsonPath().getList("data.gender").stream().filter(t-> t.toString().equals("male")).count();
        System.out.println("erkek = " + erkek);

        /*
        for (String w:genders) {
        if(w.equals("numFemale")){
            numFemale++;
        }else male++;

    }
    System.out.println("numFemale = " + numFemale);
    System.out.println("male = " + male);
         */

       /* JsonPath json=response.jsonPath();
        Assert.assertTrue(json.getList("data.findAll{it.gender='numFemale'}").size()<=json.getList("data.findAll{it.gender='male'}").size());
        */
    }

}
