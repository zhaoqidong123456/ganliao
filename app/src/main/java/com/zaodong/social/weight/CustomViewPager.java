package com.zaodong.social.weight;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

import com.netease.nim.avchatkit.common.log.LogUtil;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/04/27 22:26
 * @Version:
 */
public class CustomViewPager extends ViewPager {

    /**
     * 控制页面是否可以左右滑动
     */
    private boolean mSlidenabled = true;

    /**
     * @param context
     */
    public CustomViewPager(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否可以滑动
     */
    public final void setSlideEnabled(boolean enabled) {
        mSlidenabled = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        if (!mSlidenabled) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setNotLeftSlip(boolean notLeftSlip) {
        this.notLeftSlip = notLeftSlip;
    }

    /**
     * 禁止向左滑
     */
    private boolean notLeftSlip = false;
    private int whichFragment = 0;

    public void setWhichFragment(int whichFragment) {
        this.whichFragment = whichFragment;
    }


    private float x;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float mLastMotionX = ev.getX() - x;
                if (!notLeftSlip)
                    return super.dispatchTouchEvent(ev);
                if (mLastMotionX < 80) {
                    LogUtil.e("CustomViewPager", "ACTION_MOVE <0:" + mLastMotionX);
                    return false;
                }
                LogUtil.e("CustomViewPager", "ACTION_MOVE >0:" + mLastMotionX);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mSlidenabled) {
            return false;
        }
        try {
            return super.onTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mSlidenabled;
    }

}
