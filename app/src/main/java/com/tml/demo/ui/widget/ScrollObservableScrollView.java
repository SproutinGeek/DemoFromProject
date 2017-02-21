package com.tml.demo.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


public class ScrollObservableScrollView extends ScrollView {

    private OnScrollChangedListener mOnScrollViewListener;

    public ScrollObservableScrollView(Context context) {
        super(context);
    }

    public ScrollObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScrollObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        mOnScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener mOnScrollViewListener) {
        this.mOnScrollViewListener = mOnScrollViewListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollObservableScrollView v, int l, int t, int oldl, int oldt);
    }
}
