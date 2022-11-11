package get_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */


    /*
    Given
            https://dummy.restapiexample.com/api/v1/employees

    When
        User sends Get request

    Then
        i) Status code is 200
    And
        ii) There are 24 employees
    And
       iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        iv) The greatest age is 66
    And
         v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
        vi) Total salary of all employees is 6,644,770

  */

    @Test
    public void get16() {
        spec.pathParam("first","employees");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

       //i) Status code is 200
       //ii) There are 24 employees
       //iii) "Tiger Nixon" and "Garrett Winters" are among the employees

        response.then().assertThat().statusCode(200).body("data.id",hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon","Garrett Winters"));

        //iv) The greatest age is 66
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("Sorted Ages = " + ages);
        System.out.println(ages.get(ages.size() - 1));
        assertEquals(66,(int)(ages.get(ages.size() - 1)));

        //v) The name of the lowest age is "Tatyana Fitzpatrick"

        String lowestAgedEmployee = response.jsonPath().getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name"); //grommy
        System.out.println("lowestAgedEmployee = " + lowestAgedEmployee);

        assertEquals("[Tatyana Fitzpatrick]",lowestAgedEmployee);

        //vi) Total salary of all employees is 6,644,770
       List<Integer> salaries = response.jsonPath().getList("data.employee_salary");
       //1.Yol
        int sum1 = 0;
        for (int w : salaries) {
            sum1 +=w;

        }
        System.out.println("sum1 = " + sum1);

        assertEquals(6644770,sum1);

        //2.Yol
        int sum2 = salaries.stream().reduce(0,(t,u)->t+u);
        System.out.println("sum2 = " + sum2);
        assertEquals(6644770,sum2);
        int sum3 = salaries.stream().reduce(Integer::sum).get();
        System.out.println("sum3 = " + sum3);
        assertEquals(6644770,sum3);
        int sum4 = salaries.stream().reduce(0, Math::addExact);
        System.out.println("sum4 = " + sum4);
        assertEquals(6644770,sum4);
        int sum5 = salaries.stream().reduce(0, Integer::sum);
        System.out.println("sum5 = " + sum5);
        assertEquals(6644770,sum5);


    }
}
