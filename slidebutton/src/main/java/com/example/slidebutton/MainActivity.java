package com.example.slidebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private SlideButton sbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        sbt = (SlideButton) findViewById(R.id.sbt);
        sbt.setOnslideListener(new SlideButton.OnslideListener() {
            @Override
            public void slide(boolean isOpen) {
                tv1.setText("自动升级已经"+(isOpen?"开启":"关闭"));
            }
        });
    }

}
