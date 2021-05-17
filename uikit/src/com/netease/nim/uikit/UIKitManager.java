package com.netease.nim.uikit;

import com.netease.nim.uikit.interfaces.CallVideoListener;
import com.netease.nim.uikit.interfaces.Fasonginterface;
import com.netease.nim.uikit.interfaces.Hongdianinterface;
import com.netease.nim.uikit.interfaces.IntentUserInfoDetailListener;
import com.netease.nim.uikit.interfaces.OnSendGiftsListener;
import com.netease.nim.uikit.interfaces.OnShowSendGiftsAnimationListener;
import com.netease.nim.uikit.interfaces.RecentContactsHeardListener;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/23 13:25
 * @Version:
 */
public class UIKitManager {
    public Fasonginterface fasonginterface;
    public Hongdianinterface hongdianinterface;

    /**
     * 视频通话
     */
    public CallVideoListener callVideoListener;

    /**
     * IM跳转个人详情
     */
    public IntentUserInfoDetailListener detailListener;

    /**
     * 消息上面三个title点击
     */
    public RecentContactsHeardListener heardListener;

    public OnSendGiftsListener sendGiftsListener;

    public OnShowSendGiftsAnimationListener giftsAnimationListener;

    public void setGiftsAnimationListener(OnShowSendGiftsAnimationListener giftsAnimationListener) {
        this.giftsAnimationListener = giftsAnimationListener;
    }

    public void setSendGiftsListener(OnSendGiftsListener sendGiftsListener) {
        this.sendGiftsListener = sendGiftsListener;
    }

    public void setHeardListener(RecentContactsHeardListener heardListener) {
        this.heardListener = heardListener;
    }

    public void setDetailListener(IntentUserInfoDetailListener detailListener) {
        this.detailListener = detailListener;

    }

    public void setCallVideoListener(CallVideoListener callVideoListener) {
        this.callVideoListener = callVideoListener;
    }

    public void setFasonginterface(Fasonginterface fasonginterface) {
        this.fasonginterface = fasonginterface;
    }

    public void setHongdianinterface(Hongdianinterface hongdianinterface) {
        this.hongdianinterface = hongdianinterface;
    }

    public static UIKitManager getInstance() {
        return ModuleManagerHolder.instance;
    }

    private UIKitManager() {

    }


    private static class ModuleManagerHolder {
        public static UIKitManager instance = new UIKitManager();
    }

}
