package reddy.com.nycschools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * Created by mreddy on 4/10/2018.
 */

/*
     View less fragment to save data on orientation changes & tackle transcationTooLarge exception
     This frgament is mainly intented to store data in bundle and access this data in change of orientation, trnafer data between activities or fragments.
     Here in this project, I used this frgament for saving data on orientation change scenario.
*/

public class RetainedFragment extends Fragment {

    private static final String TAG = "RetainedFragment";
    private Bundle mInstanceBundle = null;

    public RetainedFragment() {
        super();
        // this specifies fragment will not be destroyed on configuration change eg.on orientation change
        setRetainInstance(true);
    }

    public static final RetainedFragment getInstance(FragmentManager fragmentManager) {
        RetainedFragment out = (RetainedFragment) fragmentManager.findFragmentByTag(TAG);

        if (out == null) {
            out = new RetainedFragment();
            fragmentManager.beginTransaction().add(out, TAG).commit();
        }
        return out;
    }

    /**
     * Stores bundle state
     *
     * @param instanceState
     * @return
     */
    public RetainedFragment pushData(Bundle instanceState, boolean clearPreviousData) {
        if (this.mInstanceBundle == null) {
            this.mInstanceBundle = instanceState;
        } else {
            if (clearPreviousData) {
                this.mInstanceBundle.clear();
            }
            this.mInstanceBundle.putAll(instanceState);
        }
        return this;
    }

    /**
     * Retrieves savedBundleState
     *
     * @return
     */
    public Bundle popData() {
        Bundle out = this.mInstanceBundle;
        this.mInstanceBundle = null;
        return out;
    }

}
