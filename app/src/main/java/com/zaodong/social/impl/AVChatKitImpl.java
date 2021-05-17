package com.zaodong.social.impl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.netease.nim.avchatkit.AVChaitKitManager;
import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.uikit.common.ui.dialog.AlertDialog;
import com.zaodong.social.bean.Endbean;
import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yxbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.Endpresenter;
import com.zaodong.social.presenter.IEndpresenter;
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.IYxpresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.Yxpresenter;
import com.zaodong.social.view.Endview;
import com.zaodong.social.view.Telephoneview;
import com.zaodong.social.view.Yxview;
import com.zaodong.social.weight.VideoGiftDialog;
import com.netease.nim.avchatkit.constant.AVChatExitCode;
import com.netease.nim.avchatkit.controll.AVChatController;
import com.netease.nim.avchatkit.interfaces.AVChatKitListener;
import com.netease.nim.avchatkit.module.AVChatControllerCallback;
import com.netease.nim.uikit.extension.GiftsAttachment;
import com.netease.nim.uikit.UIKitManager;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.opensource.svgaplayer.SVGAImageView;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/25 21:12
 * @Version:
 */
public class AVChatKitImpl implements AVChatKitListener, Telephoneview, Yxview, Endview {
    private Context context1;
    private ITelephonepresenter telephonepresenter;
    private IYxpresenter yxpresenter;
    private AVChatController avChatController1;
    private AVChatData avChatData1;
    private String a;
    private long chatId;
    private IEndpresenter endpresenter;

    //接听
    private boolean isInReceiveing1;
    private boolean shouldEnableToggle1;
    private boolean canSwitchCamera1;

    //挂断
    private boolean isReleasedVideo1;
    private Context context2;

    //拒绝
    @Override
    public void testAVChatKitListener(Context context, String userid, AVChatController avChatController, AVChatData avChatData) {
        a="jujue";
        telephonepresenter=new Telephonepresenter(this);
        context1=context;
        avChatController1=avChatController;
        avChatData1=avChatData;
        yxpresenter=new Yxpresenter(this);
        yxpresenter.loadData(userid);
    }
    //接听
    @Override
    public void jieting(Context context, String userid, AVChatController avChatController,boolean isInReceiveing,boolean shouldEnableToggle,boolean canSwitchCamera,AVChatData avChatData) {
        a="jieting";
        telephonepresenter=new Telephonepresenter(this);
        avChatData1=avChatData;
        chatId = avChatData1.getChatId();
        context1=context;
        avChatController1=avChatController;
        isInReceiveing1=isInReceiveing;
        shouldEnableToggle1=shouldEnableToggle;
        canSwitchCamera1=canSwitchCamera;
        yxpresenter=new Yxpresenter(this);
        yxpresenter.loadData(userid);
    }

    //挂断
    @Override
    public void guaduan(Context context,String account,AVChatController avChatController,boolean isReleasedVideo){
        a="kaishi";
        context1=context;
        avChatController1=avChatController;
        telephonepresenter=new Telephonepresenter(this);
        yxpresenter=new Yxpresenter(this);
        yxpresenter.loadData(account);

    }

    private void closeSession() {
//        ((Activity) context1).finish();
    }

    @Override
    public void showDataStart(Telebeanstart telebeanstart) {

    }

    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {

    }


    @Override
    public void showDatafalse(Telebeanfalse telebeanstart){
        if (telebeanstart.getCode()==2000){
            Toast.makeText(context1, "通话已拒绝", Toast.LENGTH_SHORT).show();
            avChatController1.hangUp(AVChatExitCode.REJECT);
            closeSession();
        }else {
            Toast.makeText(context1, telebeanstart.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void showDatafalsef(Yzmfbean yzmfbean) {
        Toast.makeText(context1, yzmfbean.getMsg(), Toast.LENGTH_SHORT).show();
        avChatController1.hangUp(AVChatExitCode.HANGUP);
        closeSession();
    }

    @Override
    public void showDatatrue(Telebeantrue telebeanstart) {
        isInReceiveing1 = true;
        //showNotify(com.netease.nim.avchatkit.R.string.avchat_connecting);
        shouldEnableToggle1 = true;
        avChatController1.receive(AVChatType.VIDEO, new AVChatControllerCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                isInReceiveing1 = false;
                canSwitchCamera1 = true;
                long currentChatId = AVChatManager.getInstance().getCurrentChatId();
                Sputils.getInstance().setCurrentChatId(currentChatId+"");
            }
            @Override
            public void onFailed(int code, String errorMsg) {
                isInReceiveing1 = false;
                closeSession();
                //long currentChatId = AVChatManager.getInstance().getCurrentChatId();
               // Log.e("cu2",currentChatId+"");
            }
        });
        //long currentChatId = AVChatManager.getInstance().getCurrentChatId();
        //Log.e("cu3",currentChatId+"");
    }
    @Override
    public void showDatatruef(Yzmfbean yzmfbean) {
        Toast.makeText(context1, yzmfbean.getMsg(), Toast.LENGTH_SHORT).show();
        releaseVideo();
        avChatController1.hangUp(AVChatExitCode.HANGUP);
        closeSession();
    }

    @Override
    public void showDataup(Telebeanup telebeanup) {
//        releaseVideo();
        avChatController1.hangUp(AVChatExitCode.HANGUP);
//        closeSession();
//        AVChatManager.getInstance().stopVideoPreview();
//        AVChatManager.getInstance().disableVideo();

    }

    public void releaseVideo() {
        if (isReleasedVideo1) {
            return;
        }
        isReleasedVideo1 = true;
        AVChatManager.getInstance().stopVideoPreview();
        AVChatManager.getInstance().disableVideo();
    }
    @Override
    public void showDataupf(Yzmfbean yzmfbean) {
        Toast.makeText(context1, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
        releaseVideo();
        avChatController1.hangUp(AVChatExitCode.HANGUP);
        closeSession();
    }
    @Override
    public void showdata(Yxbean yxbean) {
        if (a.contains("jujue")){
            if (yxbean.getData().getType().contains("2")){
                telephonepresenter.showDatafalse(Sputils.getInstance().getuser_id()+"",yxbean.getData().getUser_id()+"");
            }else {
                telephonepresenter.showDatafalse(yxbean.getData().getUser_id()+"",Sputils.getInstance().getuser_id()+"");
            }
        }else if (a.contains("jieting")){
            Log.e("wahahha",Sputils.getInstance().getuser_id()+"    "+chatId+"   "+yxbean.getData().getUser_id()+"");
            if (yxbean.getData().getType().contains("2")){
                telephonepresenter.showDatatrue(Sputils.getInstance().getuser_id()+"",yxbean.getData().getUser_id()+"",chatId+"");
            }else {
                telephonepresenter.showDatatrue(yxbean.getData().getUser_id()+"",Sputils.getInstance().getuser_id()+"",chatId+"");
            }
        }else if (a.contains("kaishi")){
            if (yxbean.getData().getType().contains("2")){
                telephonepresenter.showDataup(Sputils.getInstance().getuser_id()+"",yxbean.getData().getUser_id()+"");
            }else {
                telephonepresenter.showDataup(yxbean.getData().getUser_id()+"",Sputils.getInstance().getuser_id()+"");
            }
        }
    }

    @Override
    public void showVideoDialog(Context context, String account, SVGAImageView svgaImageView,long currentChatId) {
        VideoGiftDialog.getInstance(context, account, account,svgaImageView,currentChatId);
    }

    @Override
    public void sendVideoGiftsMessage(Context context, String sessionId, GiftsAttachmentBean bean, String count, SVGAImageView svgaImageView, String url) {
        GiftsAttachment giftsAttachment = new GiftsAttachment();
        bean.setUmber(Integer.parseInt(count));
        giftsAttachment.setGiftsAttachmentBean(bean);
        IMMessage stickerMessage = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, "礼物", giftsAttachment, new CustomMessageConfig());
        NIMClient.getService(MsgService.class).sendMessage(stickerMessage,true).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("=======", "onSuccess: ");
                UIKitManager.getInstance().giftsAnimationListener.onShowGiftsAnimationListener(context,svgaImageView,url);
            }
            @Override
            public void onFailed(int i) {
                Log.e("=======", "onFailed: ");
            }
            @Override
            public void onException(Throwable throwable) {
                Log.e("=======", "onException: ");

            }
        });
    }

    @Override
    public String getUserType() {
        return Sputils.getInstance().gettype();
    }
    private AlertDialog mAlertDialog = null;

    @Override
    public void getConsumerDetails(AVChatActivity context, int exitCode, boolean isSelf) {
        context2=context;
        if (Sputils.getInstance().getCurrentChatId().length()<5){
            ((Activity) context2).finish();
        }else {
            endpresenter=new Endpresenter(this);
            endpresenter.loadData(Sputils.getInstance().getCurrentChatId()+"");
        }
    }
    @Override
    public void showData(Endbean endbean) {
        //在这获取通话的详情 然后弹窗
        if (mAlertDialog != null) {
            mAlertDialog = null;
        }
        mAlertDialog = new AlertDialog.Builder(context2)
                .setContentView(com.netease.nim.avchatkit.R.layout.dialog_chat_video_consumer_detail)
                .fullWidth()
                .setCancelable(false)
                .setText(com.netease.nim.avchatkit.R.id.time, "通话时长："+endbean.getData().getDuration()+"")
                .setText(com.netease.nim.avchatkit.R.id.consumption, "本次消费："+endbean.getData().getTotal_consumption()+"钻石")
                .setText(com.netease.nim.avchatkit.R.id.gift_consumption, "礼物消费："+endbean.getData().getGift_consumption()+"钻石")
                .setText(com.netease.nim.avchatkit.R.id.time_consumption, "计时消费："+endbean.getData().getDuration_consumption()+"钻石")
                .setOnClickListener(com.netease.nim.avchatkit.R.id.quit, v1 ->{
                    Sputils.getInstance().setCurrentChatId("");
                    mAlertDialog.dismiss();
                    ((Activity) context2).finish();
                })
                .create();
        mAlertDialog.show();
    }
}
