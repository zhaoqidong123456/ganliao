package com.netease.nim.uikit.interfaces;

import android.app.Activity;
import android.content.Context;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/23 13:34
 * @Version:
 */
public interface CallVideoListener {


    /**
     * 发起音视频通话呼叫
     *
     * @param context     上下文
     * @param account     被叫方账号
     * @param displayName 被叫方显示名称
     */
    void onCallVideoListener(Context context, String account, String displayName);


    void testExportMsg(Activity activity);
}
