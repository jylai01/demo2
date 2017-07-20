package com.example.slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by laiji on 2017/7/15.
 */

public class SlideMenu extends ViewGroup {

    private float startX;
    private float startY;
    private int mMenuW;
    private View mMenu;
    private View mCOntent;
    private int mMenuH;
    private int mContW;
    private int mContH;

    public SlideMenu(Context context) {
        super(context);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(mSumX<0){
            mSumX=0;
        }
        if(mSumX>mMenuW)
        {
            mSumX=mMenuW;
        }
        mMenu.layout(-mMenuW+(int)mSumX,0,0+(int)mSumX,mMenuH);
        mCOntent.layout((int)mSumX,0,mContW+(int)mSumX,mContH);
    }
    float mSumX;
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
                float dx = endX - startX;
                float dy = endY - startY;
                mSumX+=dx;
                startX=endX;
                startY=endY;
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                if(mSumX>mMenuW/2){
                    mSumX=mMenuW;
                }
                else {
                    mSumX=0;
                }
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        mMenu = getChildAt(0);
        mCOntent = getChildAt(1);
        mMenuW = mMenu.getMeasuredWidth();
        mMenuH = mMenu.getMeasuredHeight();
        mContW = mCOntent.getMeasuredWidth();
        mContH = mCOntent.getMeasuredHeight();
        
    }

}
