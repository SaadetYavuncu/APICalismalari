package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

    public Map<String,String> expectedDataMethod(String name,String job) {


        Map<String, String> expectedDataMap = new HashMap<>();

        if(name!=null) {
            expectedDataMap.put("name", name);
        }
        if(job!=null) {

            expectedDataMap.put("job", job);
        }



        return expectedDataMap;


    }


}


/*
{
  "name": "morpheus",
  "job": "leader",
  "id": "496",
  "createdAt": "2022-10-04T15:18:56.372Z"
       }
 */