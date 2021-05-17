package com.netease.nim.uikit.interfaces;

import android.app.Activity;
import android.content.Context;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/23 13:34
 * @Version:
 */
public interface IntentUserInfoDetailListener {
    /**
     *
     * @param activity
     * @param userID
     */
    void onIntentUserInfoListener(Context activity,String userID);
}
