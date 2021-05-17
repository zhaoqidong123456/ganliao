package com.netease.nim.uikit.interfaces;

import android.content.Context;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.opensource.svgaplayer.SVGAImageView;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/27 14:33
 * @Version:
 */
public interface OnShowSendGiftsAnimationListener {


    /**
     * 展示礼物动画
     *
     * @param context
     * @param message
     * @param svgaImageView
     */
    void onShowGiftsAnimationListener(Context context, IMMessage message, SVGAImageView svgaImageView);
    void onShowGiftsAnimationListener(Context context, SVGAImageView svgaImageView,String url);
}
