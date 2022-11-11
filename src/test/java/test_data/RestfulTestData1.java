package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData1 {
    public Map<String, Object> expectedDataBookingdatesMethod(String checkin,String checkout){
        Map<String,Object> expectedDataBookingdates=new HashMap<>();  //once inner map yapildi
        expectedDataBookingdates.put("checkin",checkin);
        expectedDataBookingdates.put("checkout",checkout);

        return expectedDataBookingdates;


    }
    public Map<String,Object>expectedDataAllMethod(String firstname,String lastname,Integer totalprice,Map<String,Object> bookingdates,String additionalneeds){
        Map<String,Object> expectedDataAll=new HashMap<>();
        expectedDataAll.put("firstname",firstname);
        expectedDataAll.put("lastname",lastname);
        expectedDataAll.put("totalprice",totalprice);
        expectedDataAll.put("bookingdates",bookingdates);
        expectedDataAll.put("additionalneeds",additionalneeds);

        return expectedDataAll;
    }
}
