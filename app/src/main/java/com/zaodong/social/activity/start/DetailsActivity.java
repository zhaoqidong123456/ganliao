package com.zaodong.social.activity.start;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.netease.nim.uikit.common.ui.dialog.AlertDialog;
import com.zaodong.social.R;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.adapter.GiftAdapter;
import com.zaodong.social.adapter.MyPagerAdapter;
import com.zaodong.social.bean.Attenbean;
import com.zaodong.social.bean.Detailsbean;
import com.zaodong.social.bean.Giftbean;
import com.zaodong.social.bean.Mybean;
import com.zaodong.social.bean.SendGiftbean;
import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.fragment.main.details.ImageFragment;
import com.zaodong.social.fragment.main.details.IntroFragment;
import com.zaodong.social.fragment.main.details.VideotwoFragment;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.fans.Attenpresenter;
import com.zaodong.social.presenter.fans.IAttenpresenter;
import com.zaodong.social.presenter.login.IMypresenter;
import com.zaodong.social.presenter.login.Mypresenter;
import com.zaodong.social.presenter.start.Detailspresenter;
import com.zaodong.social.presenter.start.IDetailspresenter;
import com.zaodong.social.presenter.vip.Giftpresenter;
import com.zaodong.social.presenter.vip.IGiftpresenter;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.utils.LoadDialogUtils;
import com.zaodong.social.utils.ModifyTabLayout;
import com.zaodong.social.utils.MyLoder;
import com.zaodong.social.view.Attenview;
import com.zaodong.social.view.Detailsview;
import com.zaodong.social.view.Giftview;
import com.zaodong.social.view.Myview;
import com.zaodong.social.view.Telephoneview;
import com.gyf.barlibrary.ImmersionBar;
import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.uikit.extension.GiftsAttachment;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zaodong.social.base.MyApplication;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, Detailsview, Attenview, Giftview, Telephoneview, Myview {


    public static final String DETAIL_ID = "DetailsActivity_detailId";

    private Banner mDetails_banner;
    private ImageButton mDetails_back;
    private TextView mDetails_state;
    private TextView mDetails_name;
    private ModifyTabLayout mDetails_tablayout;
    private ViewPager mDetails_viewpager;
    private ImageView mDetails_guanzhu;
    private ImageView mDetails_chat;
    private ImageView mDetails_gift;
    private ImageView mDetails_phone;
    private IDetailspresenter detailspresenter;
    private TextView mDetails_lever;
    private TextView mDetails_guan_counts;
    private ImmersionBar immersionBar;
    private TextView mDetails_piece;
    private int a;
    private IAttenpresenter attenpresenter;
    private IGiftpresenter giftpresenter;
    private Dialog dialog;
    private LinearLayout mDetails_zhubo;
    private String contactId;
    private SVGAImageView mSVGAKninghtood;
    private ITelephonepresenter telephonepresenter;
    private IMypresenter mypresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mypresenter=new Mypresenter(this);
        telephonepresenter=new Telephonepresenter(this);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(DETAIL_ID);
        immersionBar = ImmersionBar.with(this);
        immersionBar.init();
        dialog = LoadDialogUtils.createLoadingDialog(this, "正在加载...");
        detailspresenter = new Detailspresenter(this);
        attenpresenter = new Attenpresenter(this);
        giftpresenter = new Giftpresenter(this);
        Sputils.getInstance().setau_id(stringExtra);
        detailspresenter.loadData(Sputils.getInstance().getuser_id(), stringExtra);
        initView();
    }

    private void initView() {
        mSVGAKninghtood = findViewById(R.id.mSVGAKninghtood);
        mDetails_banner = (Banner) findViewById(R.id.mDetails_banner);
        mDetails_back = (ImageButton) findViewById(R.id.mDetails_back);
        mDetails_state = (TextView) findViewById(R.id.mDetails_state);
        mDetails_name = (TextView) findViewById(R.id.mDetails_name);
        mDetails_tablayout = (ModifyTabLayout) findViewById(R.id.mDetails_tablayout);
        mDetails_viewpager = (ViewPager) findViewById(R.id.mDetails_viewpager);
        mDetails_guanzhu = (ImageView) findViewById(R.id.mDetails_guanzhu);
        mDetails_guanzhu.setOnClickListener(this);
        mDetails_chat = (ImageView) findViewById(R.id.mDetails_chat);
        mDetails_chat.setOnClickListener(this);
        mDetails_gift = (ImageView) findViewById(R.id.mDetails_gift);
        mDetails_gift.setOnClickListener(this);
        mDetails_phone = (ImageView) findViewById(R.id.mDetails_phone);
        mDetails_phone.setOnClickListener(this);
        mDetails_back.setOnClickListener(this);
        mDetails_lever = (TextView) findViewById(R.id.mDetails_lever);
        mDetails_lever.setOnClickListener(this);
        mDetails_guan_counts = (TextView) findViewById(R.id.mDetails_guan_counts);
        mDetails_guan_counts.setOnClickListener(this);
        mDetails_piece = (TextView) findViewById(R.id.mDetails_piece);
        mDetails_piece.setOnClickListener(this);
        initData();
        mDetails_zhubo = findViewById(R.id.mDetails_niu);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) {
            immersionBar.init();
        }
    }

    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> list_Title;

    private void initData() {
        mDetails_tablayout.setOnClickListener(this);
        mDetails_tablayout.setViewHeight(dp2px(35));
        mDetails_tablayout.setBottomLineWidth(dp2px(10));
        mDetails_tablayout.setBottomLineHeight(dp2px(3));
        mDetails_tablayout.setBottomLineHeightBgResId(R.color.color_EF709D);
        mDetails_tablayout.setItemInnerPaddingLeft(dp2px(6));
        mDetails_tablayout.setItemInnerPaddingRight(dp2px(6));
        mDetails_tablayout.setmTextColorSelect(ContextCompat.getColor(MyApplication.getContext(), R.color.color_14805E));
        mDetails_tablayout.setmTextColorUnSelect(ContextCompat.getColor(MyApplication.getContext(), R.color.color_666666));
        mDetails_tablayout.setTextSize(16);
        mDetails_viewpager.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new IntroFragment());
        fragmentList.add(new VideotwoFragment());
        fragmentList.add(new ImageFragment());
        list_Title.add("介绍");
        list_Title.add("视频");
        list_Title.add("图片");
        mDetails_viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), MyApplication.getContext(), fragmentList, list_Title));
        mDetails_tablayout.setupWithViewPager(mDetails_viewpager);//此方法就是让tablayout和ViewPager联动
    }

    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    String data11;
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.mDetails_back:
                finish();
                break;
            case R.id.mDetails_guanzhu:
                if (a == 1) {
                    a = 2;
                    attenpresenter.loadData(Sputils.getInstance().getuser_id(), Sputils.getInstance().getau_id(), "2");
                    mDetails_guanzhu.setImageResource(R.mipmap.details_follow);
                } else if (a == 2) {
                    a = 1;
                    attenpresenter.loadData(Sputils.getInstance().getuser_id(), Sputils.getInstance().getau_id(), "1");
                    mDetails_guanzhu.setImageResource(R.mipmap.guanzhu);
                }
                break;
            case R.id.mDetails_gift:
                if (Sputils.getInstance().gettype().contains("2")){
                    Toast.makeText(this, "主播不可以赠送哟～", Toast.LENGTH_SHORT).show();
                }else if (Sputils.getInstance().getuser_id().equals(user_id)){
                    Toast.makeText(this, "不可以给自己发送礼物", Toast.LENGTH_SHORT).show();
                }else {
                    dialog = LoadDialogUtils.createLoadingDialog(this, "");
                    giftpresenter.loadData(Sputils.getInstance().getuser_id());
                }
                break;
            case R.id.mDetails_chat:
                if (Sputils.getInstance().getuser_id().equals(user_id)){
                    Toast.makeText(this, "不可以和自己聊天", Toast.LENGTH_SHORT).show();
                }else {
                    NimUIKit.startP2PSession(this, contactId);
                }
                break;
            case R.id.mDetails_phone:
                if (PackageManager.PERMISSION_GRANTED ==   ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                    if (Sputils.getInstance().getuser_id().equals(user_id)){
                        Toast.makeText(this, "不能和自己视频哟～", Toast.LENGTH_SHORT).show();
                    }else {
                        telephonepresenter.showDataStart(Sputils.getInstance().getuser_id(),user_id);
                    }
                }else{
                    //提示用户开户权限 申请权限
                    String[] perms = {"android.permission.CAMERA","android.permission.MODIFY_AUDIO_SETTINGS","android.permission.RECORD_AUDIO"};
                    ActivityCompat.requestPermissions(this,perms, 100);
                }
                break;
        }
    }
    @Override
    public void showDataDetails(Detailsbean detailsbean){
        Sputils.getInstance().setCallvideo(detailsbean.getData().getVideoimages().get(0).getUrl());
        a = detailsbean.getData().getIs_follow();
        user_id = detailsbean.getData().getUser_id() + "";
        contactId = detailsbean.getData().getYx_accid();
        String userName = UserInfoHelper.getUserName(contactId);
        if (detailsbean.getData().getOnline().contains("1")) {
            mDetails_state.setBackgroundResource(R.drawable.kongxian);
            mDetails_state.setText("空闲");
        } else if (detailsbean.getData().getOnline().contains("2")){
            mDetails_state.setBackgroundResource(R.drawable.buzaixian);
            mDetails_state.setText("忙碌");
        }else {
            mDetails_state.setBackgroundResource(R.drawable.jiushi_yuan);
            mDetails_state.setText("离线");
        }
        mDetails_name.setText(detailsbean.getData().getNickname() + "");
        mDetails_lever.setText(detailsbean.getData().getLevel() + "");
        mDetails_guan_counts.setText(detailsbean.getData().getFollow() + "关注" + "·" + detailsbean.getData().getFans() + "粉丝");
        ArrayList<String> banner_image = new ArrayList<>();
        for (int i = 0; i < detailsbean.getData().getBackgroundimages().size(); i++) {
            banner_image.add(detailsbean.getData().getBackgroundimages().get(i));
        }
        banner_image.add(detailsbean.getData().getAvatar());
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mDetails_banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器，图片加载器在下方
        mDetails_banner.setImageLoader(new MyLoder());
        //设置图片网址或地址的集合
        mDetails_banner.setImages(banner_image);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mDetails_banner.setBannerAnimation(Transformer.Default);
        mDetails_banner.setDelayTime(1500);
        //设置是否为自动轮播，默认是“是”。
        mDetails_banner.isAutoPlay(true);
        mDetails_banner.setIndicatorGravity(BannerConfig.CENTER)
                //必须最后调用的方法，启动轮播图。
                .start();
        mDetails_piece.setText(detailsbean.getData().getCallwriting() + "");
        EventBus.getDefault().post(new BusEvent(10087, data, new Object()));
        if (detailsbean.getData().getIs_follow() == 1) {
            mDetails_guanzhu.setImageResource(R.mipmap.guanzhu);
        } else {
            mDetails_guanzhu.setImageResource(R.mipmap.details_follow);
        }
        LoadDialogUtils.closeDialog(dialog);
        if (Sputils.getInstance().gettype().contains("2")||Sputils.getInstance().getuser_id().contains(user_id)){
            mDetails_zhubo.setVisibility(View.GONE);
        } else {
            mDetails_zhubo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDataDetailsf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    String data;

    @Override
    public void showatt(Attenbean attenbean) {
        EventBus.getDefault().post(new BusEvent(10099, data11, new Object()));
        Toast.makeText(this, attenbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showattf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    private View view;
    private PopupWindow popupWindow1 = new PopupWindow();
    private RecyclerView mGift_recy;
    private TextView mGift_yu, mGift_chong, mGift_zeng, mGift_wai;
    private EditText mGift_edit;
    private ArrayList<GiftsAttachmentBean> gift_array = new ArrayList<>();
    private GiftAdapter giftAdapter;

    //调用相册 相机
    private void Start() {
        view = LayoutInflater.from(this).inflate(R.layout.gift_item, null);
        mGift_chong = view.findViewById(R.id.mGift_chong);
        mGift_yu = view.findViewById(R.id.mGift_yu);
        mGift_yu.setText(Sputils.getInstance().getMoney());
        mGift_zeng = view.findViewById(R.id.mGift_zeng);
        mGift_wai = view.findViewById(R.id.mGift_wai);
        mGift_edit = view.findViewById(R.id.mGift_edit);
        mGift_recy = view.findViewById(R.id.mGift_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        giftAdapter = new GiftAdapter(gift_array, this);
        mGift_recy.setAdapter(giftAdapter);
        giftAdapter.notifyDataSetChanged();
        mGift_recy.setLayoutManager(gridLayoutManager);
        mGift_yu = view.findViewById(R.id.mGift_yu);
        mGift_chong = view.findViewById(R.id.mGift_chong);
        mGift_zeng = view.findViewById(R.id.mGift_zeng);
        mGift_edit = view.findViewById(R.id.mGift_edit);
        popupWindow1 = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);
        giftAdapter.setOnItemListener(new GiftAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos) {
                giftAdapter.setDefSelect(pos);
                id = gift_array.get(pos).getId();
                price = gift_array.get(pos).getPrice();
                //Log.e("gift", "选中" + pos);
                a1 = pos;
            }
        });
        mGift_chong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
                Intent intent = new Intent(DetailsActivity.this, MymoneyActivity.class);
                startActivity(intent);
            }
        });
        mGift_wai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
            }
        });
        mGift_zeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
                if (id == 0) {
                    Toast.makeText(MyApplication.getContext(), "请选择礼物", Toast.LENGTH_SHORT).show();
                } else {
                    giftpresenter.loadDatazeng(Sputils.getInstance().getuser_id(), user_id, "", id + "", price, mGift_edit.getText().toString(), "2", "", user_id);
                }
            }
        });
    }

    private int a1;
    private String user_id;
    private String price;
    private int id = 0;

    @Override
    public void showData(Giftbean giftbean) {
        gift_array.clear();
        gift_array.addAll(giftbean.getData());
        Start();
        popupWindow1.showAtLocation(view.findViewById(R.id.mGift_recy), Gravity.CENTER | Gravity.CENTER, 0, 0);
        LoadDialogUtils.closeDialog(dialog);

    }
    @Override
    public void showDataf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showDatasend(SendGiftbean sendGiftbean){
        mypresenter.loadData(Sputils.getInstance().getuser_id());
        if (sendGiftbean.getCode()==2000){
            GiftsAttachment giftsAttachment = new GiftsAttachment();
            GiftsAttachmentBean bean = new GiftsAttachmentBean();
            if (gift_array != null && gift_array.size() > 0) {
                bean = gift_array.get(a1);
            }
            bean.setUmber(Integer.parseInt(mGift_edit.getText().toString()));
            giftsAttachment.setGiftsAttachmentBean(bean);
            IMMessage stickerMessage = MessageBuilder.createCustomMessage(contactId,SessionTypeEnum.P2P, "礼物", giftsAttachment, new CustomMessageConfig());
            NIMClient.getService(MsgService.class).sendMessage(stickerMessage,true);

            //UIKitManager.getInstance().sendGiftsListener.sendGiftsMessage(container,sessionId,bean,mGift_edit.getText().toString());
            Toast.makeText(this, "赠送成功", Toast.LENGTH_SHORT).show();
            mSVGAKninghtood.setVisibility(View.VISIBLE);
            try {
                new SVGAParser(this).decodeFromURL(new URL(gift_array.get(a1).getEffectfile()), new SVGAParser.ParseCompletion() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        if (mSVGAKninghtood != null) {
                            mSVGAKninghtood.setVideoItem(videoItem);
                            mSVGAKninghtood.stepToFrame(0, true);
                        }
                    }
                    @Override
                    public void onError() {
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, sendGiftbean.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void showDatafsend(Yzmfbean yzmfbean) {
        if (yzmfbean.getMsg().contains("余额不")){
            if (mAlertDialog == null) {
                mAlertDialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.yue_item)
                        .fullWidth()
                        .setOnClickListener(R.id.mYu_buzu, v1 -> {
                            mAlertDialog.dismiss();
                            Intent intent = new Intent();
                            intent.setClass(this, MymoneyActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .create();
            }
            mAlertDialog.show();
        }else {
            Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void showDataStart(Telebeanstart telebeanstart) {
        if (telebeanstart.getCode()==2000){
            String userName = UserInfoHelper.getUserName(contactId);
            Log.e("jdhsgfbvdfh",userName);
            AVChatKit.outgoingCall(this, contactId, userName, AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
        }else {
            Toast.makeText(this, telebeanstart.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }
    private AlertDialog mAlertDialog;
    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {
        if (yzmfbean.getMsg().contains("余额不")){
            if (mAlertDialog == null) {
                mAlertDialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.yue_item)
                        .fullWidth()
                        .setOnClickListener(R.id.mYu_buzu, v1 -> {
                            mAlertDialog.dismiss();
                            Intent intent = new Intent();
                            intent.setClass(this, MymoneyActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .create();
            }
            mAlertDialog.show();
        }else {
            Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
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
    public void showDatamy(Mybean mybean) {
        Sputils.getInstance().setMoney(mybean.getData().getMoney());
    }
    @Override
    public void showDatamyf(Yzmfbean yzmfbean) {

    }
}
