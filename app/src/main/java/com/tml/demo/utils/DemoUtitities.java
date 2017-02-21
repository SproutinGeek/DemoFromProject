package com.tml.demo.utils;

import android.graphics.Rect;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Arun Pillai on 2/9/2017.
 */

public class DemoUtitities {

    public static boolean isViewInScreeRect(View viewToCheck, ScrollView scrollView) {
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        return !(!viewToCheck.getLocalVisibleRect(scrollBounds)
                || scrollBounds.height() < viewToCheck.getHeight());
    }
}
