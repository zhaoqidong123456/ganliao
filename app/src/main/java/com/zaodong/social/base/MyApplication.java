package com.zaodong.social.base;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;
import com.zaodong.social.impl.AVChatKitImpl;
import com.zaodong.social.impl.UIKitImpl;
import com.zaodong.social.model.Sputils;
import com.netease.nim.avchatkit.AVChaitKitManager;
import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.bean.AVChatVideoBean;
import com.netease.nim.avchatkit.interfaces.IExtendMessage;
import com.netease.nim.demo.NimApplication;
import com.netease.nim.uikit.UIKitManager;
public class MyApplication extends NimApplication {
    public static AppCompatActivity context;
    public static Context getContext() {
        return context;
    }
    public static AppCompatActivity context(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //UIKit的交互
        AVChatKit.setExtendMessage(new IExtendMessage() {
            @Override
            public AVChatVideoBean videoBean() {
                AVChatVideoBean videoBean = new AVChatVideoBean();
                videoBean.setAvatar(Sputils.getInstance().getImage());//头像
                videoBean.setType(Sputils.getInstance().gettype());
                videoBean.setYx_split("ganliao");
                videoBean.setUser_id(Sputils.getInstance().getuser_id());
                videoBean.setMoney(Sputils.getInstance().getMoney());
                videoBean.setCallprice(Sputils.getInstance().getCallprice());
                videoBean.setNickname(Sputils.getInstance().getnickname()+"");
                videoBean.setCallvideo(Sputils.getInstance().getCallvideo());
                return videoBean;
            }
        });
        UIKitManager.getInstance().setCallVideoListener(UIKitImpl.getInstance());
        UIKitManager.getInstance().setDetailListener(UIKitImpl.getInstance());
        UIKitManager.getInstance().setHeardListener(UIKitImpl.getInstance());
        UIKitManager.getInstance().setSendGiftsListener(UIKitImpl.getInstance());
        UIKitManager.getInstance().setGiftsAnimationListener(UIKitImpl.getInstance());
        AVChaitKitManager.getInstance().setKitListener(new AVChatKitImpl());
        UIKitManager.getInstance().setFasonginterface(UIKitImpl.getInstance());
        UIKitManager.getInstance().setHongdianinterface(UIKitImpl.getInstance());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

}
