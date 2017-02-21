package com.tml.demoframwork.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.tml.demoframwork.listeners.DataAccessInterface;

import org.json.JSONException;


/**
 * For all the Database queries use this async task.
 * Created by 1021354 on 17-07-2015.
 */
public class DataAccessAsyncTask extends AsyncTask<Void, Void, Void> {

    private DataAccessInterface dataAccessInterface;
    private Context mContext;
    private Exception exception;

    public DataAccessAsyncTask(Context mContext, @NonNull DataAccessInterface dataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
        this.mContext = mContext;
    }

    @Override
    @MainThread
    protected void onPreExecute() {
        super.onPreExecute();
        exception = null;
    }

    @Override
    @UiThread
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dataAccessInterface.dataAccessCallBack(exception);

    }

    @Override
    @WorkerThread
    protected Void doInBackground(Void... params) {
        try {
            dataAccessInterface.callDataAccess();
        } catch (JSONException e) {
            exception = e;
        }
        return null;
    }


}
