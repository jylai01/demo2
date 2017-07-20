package com.example.laiji.demotow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtils.getLog(event,"onTouchEvent:MainActivity");

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchUtils.getLog(ev,"dispatchTouchEvent:MainActivity");
        return super.dispatchTouchEvent(ev);
    }
}
