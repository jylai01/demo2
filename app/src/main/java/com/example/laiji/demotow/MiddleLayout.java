package com.example.laiji.demotow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by laiji on 2017/7/14.
 */

public class MiddleLayout extends FrameLayout {
    public MiddleLayout(@NonNull Context context) {
        super(context);
    }

    public MiddleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtils.getLog(event,"onTouchEvent:MiddleLayout");
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchUtils.getLog(ev,"dispatchTouchEvent:MiddleLayout");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        TouchUtils.getLog(ev,"onInterceptTouchEvent:MiddleLayout");
        return super.onInterceptTouchEvent(ev);
    }
}
