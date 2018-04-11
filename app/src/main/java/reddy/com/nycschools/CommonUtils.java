package reddy.com.nycschools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mreddy on 4/10/2018.
 */

public class CommonUtils {


    /*
    * All methods which are commonly used and independent of another component are defined here.
    *
    * */


    /*
    * check for Network availability.
    * */
    public static boolean isNetworkAvailable(Activity activity) {
        if (activity != null) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /*
    * show Progress dialog
    * */

    public static ProgressDialog showProgressDialog(Activity activity,
                                                    String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        //progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }


    /*
    * show alert Dialog
    * */
    public static void ShowDialog(final int errorCode, final String title, final String message, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                if (errorCode != 0) {
                    if (errorCode == ProjectConstants.NO_NETWORK_ERROR_CODE) {
                        alertDialog.setTitle(ProjectConstants.NO_NETWORK_TITLE);
                    } else if (errorCode == ProjectConstants.TECHNICAL_ERROR_CODE) {
                        alertDialog.setTitle(ProjectConstants.TECHNICAL_ISSUE_TITLE);
                    }
                }
                alertDialog.setMessage(message);
                alertDialog.setCancelable(false);
                alertDialog.setButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // here you can add functions
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }


    /*
    * check for null/ empty values of strings.
    * */
    public static boolean isNullOrEmpty(String value) {
        if (value == null)
            return true;
        else if (value != null && value.trim().length() == 0)
            return true;
        return false;
    }

}
