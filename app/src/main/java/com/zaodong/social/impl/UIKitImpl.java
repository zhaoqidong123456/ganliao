package com.zaodong.social.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ui.dialog.AlertDialog;
import com.netease.nim.uikit.interfaces.Hongdianinterface;
import com.zaodong.social.R;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.activity.PiaoActivity;
import com.zaodong.social.activity.TelephoneActivity;
import com.zaodong.social.activity.VipActivity;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.bean.Peibean;
import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yxbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IPeipresenter;
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.IYxpresenter;
import com.zaodong.social.presenter.Peipresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.Yxpresenter;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.view.Peiview;
import com.zaodong.social.view.Telephoneview;
import com.zaodong.social.view.Yxview;
import com.zaodong.social.weight.GiftDialog;
import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.demo.main.activity.MsgMigrationActivity;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.extension.GiftsAttachment;
import com.netease.nim.uikit.business.session.module.Container;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.util.string.StringUtil;
import com.netease.nim.uikit.interfaces.CallVideoListener;
import com.netease.nim.uikit.interfaces.Fasonginterface;
import com.netease.nim.uikit.interfaces.IntentUserInfoDetailListener;
import com.netease.nim.uikit.interfaces.OnSendGiftsListener;
import com.netease.nim.uikit.interfaces.OnShowSendGiftsAnimationListener;
import com.netease.nim.uikit.interfaces.RecentContactsHeardListener;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/23 13:39
 * @Version:
 */
public class UIKitImpl implements CallVideoListener,
        IntentUserInfoDetailListener,
        RecentContactsHeardListener,
        Yxview,
        OnSendGiftsListener,
        OnShowSendGiftsAnimationListener, Telephoneview, Fasonginterface, Peiview, Hongdianinterface {


    private Container container1;
    private IYxpresenter yxpresenter;
    private Context context;
    private ITelephonepresenter telephonepresenter;
    private String a;
    private String account1;
    private String displayName1;
    private String type;
    private EditText editText1;
    private boolean isRobotSession1;
    private View sendMessageButtonInInputBar;
    private IPeipresenter peipresenter;
    private String b="0";
    private int i;
    private AlertDialog mAlertDialog;

    @Override
    public void Sendtext(EditText editText, Container container,boolean isRobotSession, View view) {
        container1=container;
        editText1=editText;
        isRobotSession1=isRobotSession;
        sendMessageButtonInInputBar=view;
        peipresenter=new Peipresenter(this);
        peipresenter.showData(Sputils.getInstance().getuser_id());

    }
    private void restoreText(boolean clearText) {
        if (clearText) {
            editText1.setText("");
        }

        checkSendButtonEnable(editText1);
    }


    /**
     * 显示发送或更多
     *
     * @param editText
     */
    private void checkSendButtonEnable(EditText editText) {
        if (isRobotSession1) {
            return;
        }
        String textMessage = editText.getText().toString();
        if (!TextUtils.isEmpty(StringUtil.removeBlanks(textMessage)) && editText.hasFocus()) {
            sendMessageButtonInInputBar.setVisibility(View.VISIBLE);
        } else {
            sendMessageButtonInInputBar.setVisibility(View.GONE);
        }
    }
    protected IMMessage createTextMessage(String text) {
        return MessageBuilder.createTextMessage(container1.account, container1.sessionType, text);
    }
    @Override
    public void showData(Peibean peibean) {
        if (Sputils.getInstance().getvip_u().contains("2") || Sputils.getInstance().getvip_u().contains("3")||Sputils.getInstance().gettype().contains("2")||Sputils.getInstance().gettype().contains("3")){
            String text = editText1.getText().toString();
            IMMessage textMessage = createTextMessage(text);
            if (container1.proxy.sendMessage(textMessage)) {
                restoreText(true);
            }
        } else {
            b=Sputils.getInstance().getTiao();
            if (b.length()>0){
                i = Integer.parseInt(b);
            }else {
                i=0;
            }
            Sputils.getInstance().setTiao(i+1+"");
        int i1 = Integer.parseInt(Sputils.getInstance().getTiao());
        int i2 = Integer.parseInt(peibean.getData().getChat_number());
        if (i1>=i2){
            if (mAlertDialog == null) {
                mAlertDialog = new AlertDialog.Builder(container1.activity)
                        .setContentView(R.layout.kaitong_vip)
                        .fullWidth()
                        .setOnClickListener(R.id.mKai_liji, v1 -> {
                            mAlertDialog.dismiss();
                            Intent intent = new Intent();
                            intent.setClass(container1.activity, VipActivity.class);
                            container1.activity.startActivity(intent);
                        }).create();
            }
            mAlertDialog.show();
                Toast.makeText(container1.activity, "今日私聊次数已用完，开通VIP享受无限畅聊", Toast.LENGTH_SHORT).show();
            } else {
                String text = editText1.getText().toString();
                IMMessage textMessage = createTextMessage(text);
                if (container1.proxy.sendMessage(textMessage)) {
                    restoreText(true);
                }
            }
        }
    }
    private String data="";
    @Override
    public void bug() {
        EventBus.getDefault().post(new BusEvent(100, data, new Object()));
    }

    private static class ConfImplHolder {
        public static UIKitImpl instance = new UIKitImpl();
    }

    private UIKitImpl() {
    }
    public static UIKitImpl getInstance() {
        return ConfImplHolder.instance;
    }


    public void release() {
        yxpresenter = null;
        context = null;
        telephonepresenter = null;
        a = null;
        account1 = null;
        displayName1 = null;
        type = null;
    }

    @Override
    public void onCallVideoListener(Context activity, String account, String displayName) {
        context = activity;
        a = "video";
        account1 = account;
        displayName1 = displayName;
        telephonepresenter = new Telephonepresenter(this);
        yxpresenter = new Yxpresenter(this);
        yxpresenter.loadData(account);
//        AVChatKit.outgoingCall(activity, account, UserInfoHelper.getUserDisplayName(displayName), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
    }

    @Override
    public void testExportMsg(Activity activity) {
        activity.startActivity(new Intent(activity, MsgMigrationActivity.class));
    }

    @Override
    public void onIntentUserInfoListener(Context activity, String userID) {
        a = "details";
        context = activity;
        yxpresenter = new Yxpresenter(this);
        yxpresenter.loadData(userID);
    }

    @Override
    public void onRecentContactsRecordListener(Context context) {
        //ToastHelper.showToast(context, "跳转到记录页");
        Intent intent = new Intent(context, TelephoneActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onRecentContactsNewsListener(Context context) {
        Intent intent = new Intent(context, PiaoActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void onRecentContactsKefuListener(Context context) {
        NimUIKit.startP2PSession(context, "uans90321609143164");
    }

    @Override
    public void showdata(Yxbean yxbean) {
        type = yxbean.getData().getType();
        Sputils.getInstance().setCallvideo(yxbean.getData().getCallvideo());
        if (a.contains("details")) {
            Sputils.getInstance().setau_id(yxbean.getData().getUser_id() + "");
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(DetailsActivity.DETAIL_ID, yxbean.getData().getUser_id() + "");
            context.startActivity(intent);
        } else {
            if (yxbean.getData().getType().contains("2")) {
                telephonepresenter.showDataStart(Sputils.getInstance().getuser_id(), yxbean.getData().getUser_id() + "");
            } else {
                telephonepresenter.showDataStart(yxbean.getData().getUser_id() + "", Sputils.getInstance().getuser_id());
            }
        }
    }
    @Override
    public void showGiftsDialog(Container container, String userID, String sessionId) {
        // TODO: 2021/4/26 userId 是平台id  目前暂时hardcode
        GiftDialog.getInstance(container, container.account, sessionId).loadData();
    }
    @Override
    public void cleanDialogGiftsData() {
        GiftDialog.cleanAllData();
    }

    @Override
    public void sendGiftsMessage(Container container, String sessionId, GiftsAttachmentBean bean, String count) {
        GiftsAttachment giftsAttachment = new GiftsAttachment();
        bean.setUmber(Integer.parseInt(count));
        giftsAttachment.setGiftsAttachmentBean(bean);
        IMMessage stickerMessage = MessageBuilder.createCustomMessage(sessionId, SessionTypeEnum.P2P, "礼物", giftsAttachment, new CustomMessageConfig());
        container.proxy.sendMessage(stickerMessage);
    }

    @Override
    public void onShowGiftsAnimationListener(Context context, IMMessage message, @NotNull SVGAImageView svgaImageView) {
        svgaImageView.setVisibility(View.VISIBLE);
        MsgAttachment attachment = message.getAttachment();
        if (attachment instanceof GiftsAttachment) {
            GiftsAttachment giftsAttachment = (GiftsAttachment) attachment;
            GiftsAttachmentBean giftsAttachmentBean = giftsAttachment.getGiftsAttachmentBean();
            SVGAParser svgaParser = new SVGAParser(context);
            try {
                svgaParser.decodeFromURL(new URL(giftsAttachmentBean.getEffectfile()), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        svgaImageView.setVideoItem(svgaVideoEntity);
                        svgaImageView.stepToFrame(0, true);
                    }
                    @Override
                    public void onError() {
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void showDataStart(@NotNull Telebeanstart telebeanstart) {
        if (telebeanstart.getCode() == 2000) {
            if (Sputils.getInstance().gettype().contains("1") && type.contains("2")) {
                AVChatKit.outgoingCall(context, account1, UserInfoHelper.getUserDisplayName(displayName1), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
            } else if (Sputils.getInstance().gettype().contains("2") && type.contains("1")) {
                AVChatKit.outgoingCall(context, account1, UserInfoHelper.getUserDisplayName(displayName1), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
            } else if (Sputils.getInstance().gettype().contains("1") && type.contains("1")) {
                Toast.makeText(context, "用户与用户不能通话", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "主播与主播不能通话", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, telebeanstart.getMsg() + "", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {
        if (type.contains("1")) {
            AVChatKit.outgoingCall(context, account1, UserInfoHelper.getUserDisplayName(displayName1), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
        } else {
            if (yzmfbean.getMsg().contains("余额不足")) {
                Toast.makeText(context, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MymoneyActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showDatafalse(Telebeanfalse telebeanstart) {

    }

    @Override
    public void showDatafalsef(Yzmfbean yzmfbean) {

    }

    @Override
    public void showDatatrue(Telebeantrue telebeanstart) {

    }

    @Override
    public void showDatatruef(Yzmfbean yzmfbean) {

    }

    @Override
    public void showDataup(Telebeanup telebeanup) {

    }

    @Override
    public void showDataupf(Yzmfbean yzmfbean) {
    }

    @Override
    public void onShowGiftsAnimationListener(Context context, SVGAImageView svgaImageView, String url) {
        svgaImageView.setVisibility(View.VISIBLE);
        SVGAParser svgaParser = new SVGAParser(context);
        try {
            svgaParser.decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                    svgaImageView.setVideoItem(svgaVideoEntity);
                    svgaImageView.stepToFrame(0, true);
                }

                @Override
                public void onError() {
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
