package com.tml.demoframwork.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tml.demoframwork.listeners.DataAccessInterface;
import com.tml.demoframwork.task.DataAccessAsyncTask;


/**
 * Created by 1021354 on 22-06-2015.
 * Base Business logic class.
 */
public abstract class BaseBL {
    protected Context mContext;
    protected DataAccessAsyncTask dataAccessAsyncTask;

    /**
     * Context is the one mandatory thing for creating the bl implementations
     *
     * @param mContext for getting the context
     */
    public BaseBL(@NonNull Context mContext) {
        this.mContext = mContext;
    }


}
