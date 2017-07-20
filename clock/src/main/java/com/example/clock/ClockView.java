package com.example.clock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by laiji on 2017/7/15.
 */

public class ClockView extends View {
    private Bitmap mBmp_dial;
    private Bitmap mBmp_hour;
    private Bitmap mBmp_minute;
    private Bitmap mBmp_second;
    private Bitmap mBmp_center;
    private int mDialWidth;
    private int mDialHeight;
    private Calendar mCalendar;
    private Paint paint;
    private Thread mThread;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBmp_dial = BitmapFactory.decodeResource(getResources(), R.drawable.clock_dial);
        mBmp_hour = BitmapFactory.decodeResource(getResources(), R.drawable.hour_hand);
        mBmp_minute = BitmapFactory.decodeResource(getResources(), R.drawable.minute_hand);
        mBmp_second = BitmapFactory.decodeResource(getResources(), R.drawable.sec_hand);
        mBmp_center = BitmapFactory.decodeResource(getResources(), R.drawable.hand_center);
        mDialWidth = mBmp_dial.getWidth();
        mDialHeight = mBmp_dial.getHeight();
        paint = new Paint();
        mCalendar = Calendar.getInstance();
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mIsattach){
                    SystemClock.sleep(1000);
                    postInvalidate();
                }
            }
        });
    }
    boolean mIsattach;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mIsattach=true;
        mThread.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIsattach=false;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST){
            widthSize = mDialWidth;
        }
        if(heightMode==MeasureSpec.AT_MOST){
            heightSize = mDialHeight;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        int hour = mCalendar.get(Calendar.HOUR);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if(measuredWidth<mDialWidth||measuredHeight<mDialHeight){
            float scaleW = measuredWidth*1f / mDialWidth;    //小数
            float scaleH = measuredHeight*1f / mDialHeight;
            //避免缩放时变形,都要使用同一个比例来进行缩放
            float min = Math.min(scaleW, scaleH);
            canvas.scale(min,min,measuredWidth/2,measuredHeight/2);
        }

        canvas.drawBitmap(mBmp_dial,measuredWidth/2-mBmp_dial.getWidth()/2,
                measuredHeight/2-mBmp_dial.getHeight()/2,paint);

        float hourAngle = hour / 12f * 360+minute/60f*30;
                canvas.rotate(hourAngle,measuredWidth/2,measuredHeight/2);

        float minuteAngle = minute/60f*360;
                canvas.rotate(minuteAngle,measuredWidth/2,measuredHeight/2);
        float secondAngle = second/60f*360;
                canvas.rotate(secondAngle,measuredWidth/2,measuredHeight/2);
        canvas.drawBitmap(mBmp_second,measuredWidth/2-mBmp_second.getWidth()/2
                                ,measuredHeight/2-mBmp_second.getHeight()/2-25,paint);
        canvas.drawBitmap(mBmp_hour,measuredWidth/2-mBmp_hour.getWidth()/2
                ,measuredHeight/2-mBmp_hour.getHeight()/2-25,paint);


//        canvas.save();
//        float hourAngle = hour / 12f * 360+minute/60f*30;
//        canvas.rotate(hourAngle,measuredWidth/2,measuredHeight/2);
//        canvas.drawBitmap(mBmp_hour,measuredWidth/2-mBmp_hour.getWidth()/2
//                ,measuredHeight/2-mBmp_hour.getHeight()/2-25,paint);
//        canvas.restore();
//
//        canvas.save();
//        float minuteAngle = minute/60f*360;
//        canvas.rotate(minuteAngle,measuredWidth/2,measuredHeight/2);
//        canvas.drawBitmap(mBmp_minute,measuredWidth/2-mBmp_minute.getWidth()/2
//                ,measuredHeight/2-mBmp_minute.getHeight()/2-25,paint);
//        canvas.restore();
//
//       canvas.save();
//        float secondAngle = second/60f*360;
//        canvas.rotate(secondAngle,measuredWidth/2,measuredHeight/2);
//        canvas.drawBitmap(mBmp_second,measuredWidth/2-mBmp_second.getWidth()/2
//                ,measuredHeight/2-mBmp_second.getHeight()/2-25,paint);
//        canvas.restore();

        canvas.drawBitmap(mBmp_center,measuredWidth/2-mBmp_center.getWidth()/2
                ,measuredHeight/2-mBmp_center.getHeight()/2,paint);
    }
}
