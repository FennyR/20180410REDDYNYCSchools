package reddy.com.nycschools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mreddy on 4/10/2018.
 */

public class SchoolDetailsActivity extends AppCompatActivity {

    private SchoolDetailsActivity schoolDetailsActivity;
    private SchoolBean schoolBean;
    private TextView dbn;
    private TextView noOfTests;
    private TextView schoolName;
    private TextView mathScore;
    private TextView readingScore;
    private TextView writingScore;
    private TextView schoolDetailsHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_data);
        schoolDetailsActivity = SchoolDetailsActivity.this;

        /*
        * fetch all the data that has been sent through Bundle from Intent.
        * */
        Bundle extras = getIntent().getExtras();

        /*
        * Identify the excat data we need from the bundle with the help of the tag name.
        * */
        schoolBean = (SchoolBean) extras.get("SCHOOL");

        //initialize the views
        dbn = (TextView) findViewById(R.id.dbn);
        schoolName = (TextView) findViewById(R.id.schoolName);
        noOfTests = (TextView) findViewById(R.id.noOfTests);
        mathScore = (TextView) findViewById(R.id.mathScore);
        readingScore = (TextView) findViewById(R.id.readingScore);
        writingScore = (TextView) findViewById(R.id.writingScore);
        schoolDetailsHeader = (TextView) findViewById(R.id.schoolDetailsHeader);

        schoolDetailsHeader.setVisibility(View.VISIBLE);

        /*
        * Now, with the available data, we try to assign values to the views.
        * */
        showSchoolData(schoolBean);

    }


    private void showSchoolData(SchoolBean school) {
        schoolName.setText(school.getSchoolName());
        dbn.setText("dbn: ");
        dbn.append(school.getDbn());
        noOfTests.setText(getResources().getString(R.string.no_of_tests));
        noOfTests.append(school.getNoOfTestsTaken());
        mathScore.setText(getResources().getString(R.string.math_Score));
        mathScore.append(school.getMathAvgScore());
        readingScore.setText(getResources().getString(R.string.reading_Score));
        readingScore.append(school.getReadingAvgScore());
        writingScore.setText(getResources().getString(R.string.reading_Score));
        writingScore.append(school.getWritingAvgScore());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
