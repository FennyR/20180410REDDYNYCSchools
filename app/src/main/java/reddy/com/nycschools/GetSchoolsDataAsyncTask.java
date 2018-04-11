package reddy.com.nycschools;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.util.List;


/**
 * Created by mreddy on 4/10/2018.
 */

public class GetSchoolsDataAsyncTask extends AsyncTask<Void, Void,List<SchoolBean>> {

    private ProgressDialog progressDialog;
    private Activity activity;
    private TaskDelegate taskDelegate;
    private int errorCode;
    private String errorMsg;

    public GetSchoolsDataAsyncTask(Activity activity) {
        this.activity = activity;
    }

    public void setDelegate(TaskDelegate taskDelegate) {
        this.taskDelegate = taskDelegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress bar
        progressDialog = CommonUtils.showProgressDialog(activity, "Loading", "downloading school data ..");

        //Check for internet connection.
        if (!CommonUtils.isNetworkAvailable(activity)) {
            progressDialog.dismiss();
            progressDialog = null;
            CommonUtils.ShowDialog(ProjectConstants.NO_NETWORK_ERROR_CODE, ProjectConstants.NO_NETWORK_TITLE, ProjectConstants.NO_NETWORK_MSG, activity);
            cancel(true);
        }
    }


    @Override
    protected List<SchoolBean> doInBackground(Void... arg0) {

        /*
            make the Service call here in the Async task.

         */
        List<SchoolBean> schoolDataResult = null;
        if(!isCancelled()){
            try{
                schoolDataResult = RestService.getSchoolData();
            } catch (Exception e) {
                errorCode = ProjectConstants.TECHNICAL_ERROR_CODE;
                errorMsg = ProjectConstants.UNEXPECTED_ERROR;
                e.printStackTrace();
            }

        }
        return schoolDataResult;

    }


    @TargetApi(17)
    protected void onPostExecute(List<SchoolBean> schoolData) {
        //NOTE: Trigger async task, click on home and rotate the  activity is killed but progress dialog is try to dismiss and throwing "PhoneWindow not attached to window manager" exception.
        //The below solution is for only API > 17. But this problem is still there in PII < 17
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity != null && activity.isDestroyed()) {
                // or call isFinishing() if min sdk version < 17
                return;
            }
        }

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        /*
        *   When the Async tasks work is done, we would like to send the return data to this method in order to perform further actions.
        *   I implemented this interface in order to maintain transperancy between UI layer and service layer.
        *   SO, now my UI and Service layers are totally independent of each other.
        *
        * */
        if (taskDelegate != null) {
            taskDelegate.taskComplete(schoolData);
        }

    }

}
