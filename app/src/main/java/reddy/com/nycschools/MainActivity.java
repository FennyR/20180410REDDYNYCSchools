package reddy.com.nycschools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener {

    private MainActivity mainActivity;
    private RecyclerView schoolRecyclerView;
    private SchoolRecyclerAdapter schoolRecyclerAdapter;
    private ArrayList<SchoolBean> schoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = MainActivity.this;

        schoolRecyclerView = (RecyclerView) findViewById(R.id.schoolRecyclerView);

        /*
            always get the Bundle object from Viewless fragment.
            On the initial load, the bundle will be null. So get the school data by making a service call.
            When the orientation is changed, we retain the data by saving it in ViewLess Fragment in onSaveInstance method.
            This saves us form making another service call to the sevrice when the device orientation is chnaged.
         */
        savedInstanceState = RetainedFragment.getInstance(mainActivity.getSupportFragmentManager()).popData();

        if (savedInstanceState == null) {
           /*
           * get data form the Server
           * */
            fetchSchoolDataFromServer();
        } else {
            /*
            * load the data which is already saved in Bundle
            * */
            schoolList = savedInstanceState.getParcelableArrayList("SCHOOL_LIST");

            /*
            * making sure we render RecyclerView only if the appropriate data is available.
            * */
            if(schoolList != null) {
                plotRecyclerView(schoolList);
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
    }

    private void fetchSchoolDataFromServer() {

        /*
        * Instead of implemeenting Async task with in the Activity, I am creating a seperate Service layer, whihc will hav all async calls
        * and the respective Server calls inorder to get data.
        * Once we get data, from server, we implemented the TaskDelegate in onPostExecute of AsyncTask. So the control comes to TaskDelegate method
        * from Async task's onPostExecute.
        * */

        GetSchoolsDataAsyncTask getSchoolsDataAsyncTask = new GetSchoolsDataAsyncTask(mainActivity);

        getSchoolsDataAsyncTask.setDelegate(new TaskDelegate() {
            @Override
            public void taskComplete(Object object) {
                List<SchoolBean> schoolDataArray = (ArrayList<SchoolBean>) object;
                if (schoolDataArray != null && !schoolDataArray.isEmpty()) {
                    schoolList = (ArrayList<SchoolBean>) schoolDataArray;
                    plotRecyclerView(schoolList);
                } else {
                    /* show an alert in case of any deviation from normal behaviour.*/
                    CommonUtils.ShowDialog(ProjectConstants.TECHNICAL_ERROR_CODE, ProjectConstants.TECHNICAL_ISSUE_TITLE, ProjectConstants.UNEXPECTED_ERROR, mainActivity);
                }
            }
        });

        getSchoolsDataAsyncTask.execute();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("SCHOOL_LIST", schoolList);

        RetainedFragment.getInstance(mainActivity.getSupportFragmentManager()).pushData((Bundle) outState.clone(), false);
        outState.clear();
    }

    private void plotRecyclerView(List<SchoolBean> schoolData) {
        /*
        * initialize Recycler View adapter class.
         */
        schoolRecyclerAdapter = new SchoolRecyclerAdapter(MainActivity.this, schoolData);
        schoolRecyclerView.setAdapter(schoolRecyclerAdapter);
        schoolRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        /*
        * This is to add a divider to each item when rendering in Recycler view.
        * */
        schoolRecyclerView.addItemDecoration(new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL));
        schoolRecyclerAdapter.setClickListener(mainActivity);
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    /*
    *
    * this method handles all the click events of Recycler View.
    * i.e when ever any item is selected, we get the position of the item selected and fetch the respective "School Object" from that position from the available school list.
    * */

    @Override
    public void onClick(View view, int position) {
        showSelectedSchooldetails(position);
    }

    private void showSelectedSchooldetails(int position) {

      /*
      * Trigger an intent to another activity to show the selected School details.
      * Pass School object to the new activity by attaching it to Intent.
      * */
        SchoolBean school = schoolList.get(position);
        Intent schoolIntent = new Intent(mainActivity, SchoolDetailsActivity.class);
        schoolIntent.putExtra("SCHOOL", (Serializable) school);
        startActivity(schoolIntent);
    }
}
