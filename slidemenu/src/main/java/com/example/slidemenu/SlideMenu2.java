package com.example.slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by laiji on 2017/7/15.
 */

public class SlideMenu2 extends ViewGroup {

    private float startX;
    private float startY;
    private int mMenuW;
    private View mMenu;
    private View mCOntent;
    private int mMenuH;
    private int mContW;
    private int mContH;
    private Scroller mScroller;
    private int scrollX;
    private float mStartInterceptX;
    private float mStartInterceptY;

    public SlideMenu2(Context context) {
        super(context);
    }

    public SlideMenu2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mMenu.layout(-mMenuW, 0, 0, mMenuH);

        mCOntent.layout(0, 0, mContW, mContH);
    }

    float mSumX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                float dx = endX - startX;
                float dy = endY - startY;
                scrollBy((int) -dx, 0);
                scrollX = getScrollX();
                if (scrollX > 0) {
                    scrollTo(0, 0);
                }
                if (scrollX < -mMenuW) {
                    scrollTo(-mMenuW, 0);
                }
                mSumX += dx;
                startX = endX;
                startY = endY;
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                scrollX = getScrollX();
                if (scrollX < -mMenuW / 2) {
                    mScroller.startScroll(scrollX, 0, -mMenuW - scrollX, 0, 500);
                } else {
                    mScroller.startScroll(scrollX, 0, -scrollX, 0, 500);
                }
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            scrollTo(currX, currY);
            invalidate();//申请重绘
        }

    }

    float mSumInterceptX;
    float mSumInterceptY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartInterceptX = ev.getX();
                mStartInterceptY = ev.getY();
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();

                float dx = moveX - mStartInterceptX;
                float dy = moveY - mStartInterceptY;
                mSumInterceptX+=dx;
                mSumInterceptY+=dy;
                if(Math.abs(mSumInterceptX)> ViewConfiguration.getTouchSlop()&&Math.abs(mSumInterceptX)>Math.abs(mSumInterceptY)){
                    return true;
                }
                mStartInterceptX = moveX;
                mStartInterceptY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                mSumInterceptX = 0;
                mSumInterceptY = 0;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mMenu = getChildAt(0);
        mCOntent = getChildAt(1);
        mMenuW = mMenu.getMeasuredWidth();
        mMenuH = mMenu.getMeasuredHeight();
        mContW = mCOntent.getMeasuredWidth();
        mContH = mCOntent.getMeasuredHeight();

    }

}
