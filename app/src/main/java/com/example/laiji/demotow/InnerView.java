package com.example.laiji.demotow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by laiji on 2017/7/14.
 */

public class InnerView extends View{
    public InnerView(Context context) {
        super(context);
    }

    public InnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtils.getLog(event,"onTouchEvent:InnerView");
        return super.onTouchEvent(event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchUtils.getLog(ev,"dispatchTouchEvent: 在InnerView中");
        return super.dispatchTouchEvent(ev);
    }
}
