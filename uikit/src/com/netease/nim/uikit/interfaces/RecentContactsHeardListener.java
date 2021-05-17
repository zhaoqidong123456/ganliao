package com.netease.nim.uikit.interfaces;

import android.content.Context;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/26 10:09
 * @Version:
 */
public interface RecentContactsHeardListener {

    /**
     * 记录点击
     */
    void onRecentContactsRecordListener(Context context);

    /**
     *消息通知点击
     */
    void onRecentContactsNewsListener(Context context);

    /**
     * 客服点击
     */
    void onRecentContactsKefuListener(Context context);
}
