package com.netease.nim.avchatkit.interfaces;

import android.app.Activity;
import android.content.Context;

import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.avchatkit.bean.AVChatVideoBean;
import com.netease.nim.avchatkit.controll.AVChatController;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.opensource.svgaplayer.SVGAImageView;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/25 21:02
 * @Version:
 */
public interface AVChatKitListener {

    //拒绝接听
    void testAVChatKitListener(Context context, String userid, AVChatController avChatController, AVChatData avChatData );


    void jieting(Context context, String userid, AVChatController avChatController,boolean isInReceiveing,boolean shouldEnableToggle,boolean canSwitchCamera,AVChatData avChatData);

    void guaduan(Context context,String account,AVChatController avChatController,boolean isReleasedVideo);


    void showVideoDialog(Context context, String account, SVGAImageView svgaImageView, long currentChatId);

    void sendVideoGiftsMessage(Context context, String sessionId, GiftsAttachmentBean bean, String count, SVGAImageView svgaImageView, String url);

    /**
     * 获取user的type
     * @return
     */
    String getUserType();

    void  getConsumerDetails(AVChatActivity context,int exitCode,boolean isSelf);


}
