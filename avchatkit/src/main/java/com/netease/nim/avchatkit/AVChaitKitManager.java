package com.netease.nim.avchatkit;

import com.netease.nim.avchatkit.interfaces.AVChatKitListener;
import com.netease.nim.avchatkit.interfaces.AVChatKitReleaseVideoListener;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/25 20:59
 * @Version:
 */
public class AVChaitKitManager {


    public AVChatKitListener kitListener;

    public void setKitListener(AVChatKitListener kitListener) {
        this.kitListener = kitListener;
    }

    public static AVChaitKitManager getInstance() {
        return ModuleManagerHolder.instance;
    }


    public AVChatKitReleaseVideoListener releaseVideoListener;

    public void setReleaseVideoListener(AVChatKitReleaseVideoListener releaseVideoListener) {
        this.releaseVideoListener = releaseVideoListener;
    }

    private AVChaitKitManager() {

    }


    private static class ModuleManagerHolder {
        public static AVChaitKitManager instance = new AVChaitKitManager();
    }




}
