package com.tml.demo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Application class is the starting point of the android application.
 * Created by 1021354 on 19-06-2015.
 */
public class DemoApplication extends Application {

    private static SharedPreferences sharedPreferences;
    private static DemoApplication demoApplication;

    /**
     * Get the application context instance in {@link DemoApplication}
     *
     * @return
     */
    public static DemoApplication getKycApplicationContext() {
        return demoApplication;
    }

    /**
     * Singleton GetSharedPreferences
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        demoApplication = (DemoApplication) getApplicationContext();
    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     *//*
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(demoApplication);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
            // mTracker.enableAdvertisingIdCollection(true);
        }
        return mTracker;
    }*/

}
