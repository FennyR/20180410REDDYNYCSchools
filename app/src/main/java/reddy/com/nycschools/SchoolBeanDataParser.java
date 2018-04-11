package reddy.com.nycschools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mreddy on 4/10/2018.
 */

public class SchoolBeanDataParser {

    /*
    * this method returns the list of schools information.
    * If the result from server is empty, we show an alert to user notifying him that Server did not send data.
    *
    * */

    public static List<SchoolBean> parse(String response) throws Exception {
        List<SchoolBean> schoolResultList = null;
        if (!CommonUtils.isNullOrEmpty(response)) {
            SchoolBean schoolBeanObj = null;
            JSONArray jsonArray = new JSONArray(response);

            /*
            * check if the JsonArray really has elements in it.
            * */
            if (jsonArray != null && jsonArray.length() > 0) {

                schoolResultList = new ArrayList<>();
                for (int k = 0; k < jsonArray.length(); k++) {
                    schoolBeanObj = new SchoolBean();

                    /*
                    * parse every tag form the Server response and assign it to the SchoolBEna object.
                    * */
                    JSONObject jsonObject = jsonArray.getJSONObject(k);
                    schoolBeanObj.setDbn(jsonObject.getString("dbn"));
                    schoolBeanObj.setNoOfTestsTaken(jsonObject.getString("num_of_sat_test_takers"));
                    schoolBeanObj.setSchoolName(jsonObject.getString("school_name"));
                    schoolBeanObj.setReadingAvgScore(jsonObject.getString("sat_critical_reading_avg_score"));
                    schoolBeanObj.setWritingAvgScore(jsonObject.getString("sat_writing_avg_score"));
                    schoolBeanObj.setMathAvgScore(jsonObject.getString("sat_math_avg_score"));

                    /*
                    * add every SchoolBena object to the list as we return this list to "View controller".
                    * */

                    schoolResultList.add(schoolBeanObj);
                }

            } else {
                throw new Exception("Empty data from Server !!!");
            }
        }
        return schoolResultList;
    }

}
