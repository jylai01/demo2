package com.example.laiji.demotow;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by laiji on 2017/7/14.
 */

public class TouchUtils {
    public static void getLog(MotionEvent event, String where) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v("xmg",where+"is ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.v("xmg",where+"is ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("xmg",where+"is ACTION_MOVE");
                break;
        }
    }
}
