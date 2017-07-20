package com.example.slidebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by laiji on 2017/7/14.
 */

public class SlideButton extends View {

    private Bitmap mBg;
    private Bitmap mBtn;
    private int mBgWidth;
    private int mBgHeight;
    private Paint paint;
    private float startX;
    private float startY;

    public SlideButton(Context context) {
        super(context);
    }

    public SlideButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        mBtn = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        mBgWidth = mBg.getWidth();
        mBgHeight = mBg.getHeight();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBg,0,0,paint);
        int i =mBgWidth-mBtn.getWidth();
        if(mSumDx>i){
            mSumDx=i;
        }
        if(mSumDx<0)
        {
            mSumDx=0;
        }
        canvas.drawBitmap(mBtn,mSumDx+0,0,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        if(modeW==MeasureSpec.AT_MOST)
        {
            sizeW=mBgWidth;
        }
        if(modeH==MeasureSpec.AT_MOST)
        {
            sizeH=mBgHeight;
        }
        setMeasuredDimension(sizeW,sizeH);
    }
    float mSumDx;
    boolean mIsUp;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                float dx = endX- startX;
                float dy = endY - startY;
                mSumDx+=dx;
                startX =event.getX();
                startY =event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                int i = mBgWidth-mBtn.getWidth();
                if(mSumDx>i/2)
                {
                    mSumDx=i;
                   if(!mIsUp)
                   {
                       mIsUp=true;
                       Log.e("xmg", "onTouchEvent: "+"将文本改为开启");
                        if(mOnslideListener!=null)
                        {
                            mOnslideListener.slide(mIsUp);
                        }
                   }
                }
               else{
                    mSumDx=0;
                    if(mIsUp)
                    { mIsUp=false;
                        Log.e("xmg", "onTouchEvent: "+"将文本改为关闭");

                        if(mOnslideListener!=null){
                            mOnslideListener.slide(mIsUp);
                        }
                    }
                }

                invalidate();
                break;
        }
        return true;
    }

    public interface OnslideListener{
        void slide(boolean isOpen);
    }
    private OnslideListener mOnslideListener;
    public void setOnslideListener(OnslideListener onslideListener)
    {
        this.mOnslideListener=onslideListener;
    }
}
