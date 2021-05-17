package com.netease.nim.avchatkit.interfaces;

import com.netease.nim.avchatkit.bean.AVChatVideoBean;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 5/6/21 10:49 AM
 * @Version:
 */
public abstract class IExtendMessage {

    /**
     * 视频得时候透传的
     * @return
     */
    public abstract AVChatVideoBean videoBean();

}
