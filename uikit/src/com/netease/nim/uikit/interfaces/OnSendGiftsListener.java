package com.netease.nim.uikit.interfaces;


import android.content.Context;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.business.session.module.Container;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/26 21:27
 * @Version:
 */
public interface OnSendGiftsListener {
    /**
     *
     * @param container
     * @param userID 自己的ID
     * @param sessionId 给谁发消息的ID 网易云信的ID
     */
    void showGiftsDialog(Container container, String userID, String sessionId);

    void cleanDialogGiftsData();

    void sendGiftsMessage(Container container, String sessionId, GiftsAttachmentBean bean, String count);
}
