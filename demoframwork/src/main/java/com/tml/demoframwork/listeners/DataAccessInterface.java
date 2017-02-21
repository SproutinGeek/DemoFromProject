package com.tml.demoframwork.listeners;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;


import org.json.JSONException;


/**
 * Interface used to access the database from the AsyncTask
 */
public interface DataAccessInterface {
    /**
     * Call Database function
     */
    @WorkerThread
    void callDataAccess() throws JSONException;

    /**
     * After getting result in the on post execute give the listener to the UI
     */
    @UiThread
    void dataAccessCallBack(Exception exception);

}
