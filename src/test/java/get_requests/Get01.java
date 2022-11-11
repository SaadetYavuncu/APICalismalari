package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1)Postman, manuel API testleri için kullandik,
    2)Otomasyon testleri için de Rest Assured Library kullanacağız.
    3)Otomasyon testlerimizi yaparken asagıdaki adımlari izliyoruz;
        a)Gereksimleri anlamak,
        b)Test Case yaziyoruz
            i)Test Case yaziminda "Gherkin" dilini kullanacagiz.Bizler yazılım diline hakim olsak da,karsımızdaki
            kisiler hakim olmayabilir ama Gherkin ile yazılan testleri anlamak ta zorluk çekmeyeceklerdir.
            Gherkin dilinde kullanacağımız keywordler;

            -Given : On kosullar,
            -When : Yapılacak aksiyonlar için (get(), put(),post(),patch() ve delete())
            -Then : Istek yaptıktan (request gonderdikten sonra) dogrulama
            -And : Coklu islemlerde kullanacagız.

         c)Test Kodlarımızı Yazmaya Baslayacagiz

            i)Set the URL,
            ii)Set the expected Data (beklenen datanın olusturulması, Post, Put, Patch)
            iii)Type code to send request (Talep göndermek için kod yazımı)
            iv)Do Assertion (dogrulama yapmak)
     */

    /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
 */

    @Test
    public void get01() {
        // i) Set the URL,
        String url = "http://restful-booker.herokuapp.com/booking/101";

        //ii)Set the expected Data (beklenen datanın olusturulması, Post, Put, Patch)
        //Bizden post,put ya da patch istenmediği için bu case de kullanmayacagiz.

        //iii)Type code to send request (Talep göndermek için kod yazımı)
        Response response = given().when().get(url);
        response.prettyPrint();

       //iv)Do Assertion (dogrulama yapmak)

        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //Status Code konsola yazdiralim
        System.out.println("Status Code: " + response.getStatusCode());

        //Content type konsola yazdiralim
        System.out.println("Content Type: " + response.getContentType());

        //Status Line konsola yazdiralim
        System.out.println("Status Line : " + response.getStatusLine());

        //Header konsola yazdiralim
        System.out.println("Header : " + response.getHeader("Server"));

        //Headers konsola yazdiralim
        System.out.println("Headers : " + response.getHeaders());

        //Time konsola yazdiralim
        System.out.println("Time : " + response.getTime());




    }
}
