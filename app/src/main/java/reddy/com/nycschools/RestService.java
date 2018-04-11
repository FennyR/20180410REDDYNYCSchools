package reddy.com.nycschools;

import java.util.List;

/**
 * Created by mreddy on 4/10/2018.
 */

/*
* This is the service layer. All Calls to the server will be implemented here in this layer.
* */


public class RestService {


    public static List<SchoolBean> getSchoolData() throws Exception {

        // Making a request to url and getting response
        String url = "https://data.cityofnewyork.us/resource/734v-jeq5.json";
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String result = sh.makeServiceCall(url);

        /*
        *  after receiving response form the Server, we send this raw data to parser  inorder to get formatted data in the way we desire.
        *
        * */

        return SchoolBeanDataParser.parse(result);

    }


}
