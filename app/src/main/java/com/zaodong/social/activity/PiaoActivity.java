package com.zaodong.social.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nim.uikit.common.ui.dialog.AlertDialog;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.zaodong.social.R;
import com.zaodong.social.bean.Piaoliaobean;
import com.zaodong.social.bean.Piaowenbean;
import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yxbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IPiaowenpresenter;
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.IYxpresenter;
import com.zaodong.social.presenter.Piaowenpresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.Yxpresenter;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Piaoview;
import com.zaodong.social.view.Telephoneview;
import com.zaodong.social.view.Yxview;

public class PiaoActivity extends AppCompatActivity implements View.OnClickListener, Piaoview, Telephoneview, Yxview {

    private LinearLayout mPiaoBaiBack;
    private RelativeLayout mPiaoBackBack;
    private ImageButton mPiaoBack;
    private ImageView mPiaoDian;
    private RelativeLayout mPiaoHeiBack;
    private ImageView mPiaoPingzi;
    private ImmersionBar immersionBar;
    private IPiaowenpresenter piaowenpresenter;
    private String title;
    private Piaoliaobean.DataBean data;
    private String b="0";
    private int i;
    private ITelephonepresenter telephonepresenter;
    private IYxpresenter yxpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piao);
        initView();
        telephonepresenter=new Telephonepresenter(this);
        piaowenpresenter=new Piaowenpresenter(this);
        piaowenpresenter.loadData(Sputils.getInstance().getuser_id());
        yxpresenter=new Yxpresenter(this);
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar!=null){
            immersionBar.destroy();
        }
    }
    private void initView() {
        mPiaoBaiBack = findViewById(R.id.mPiao_bai_back);
        mPiaoBaiBack.setOnClickListener(this);
        mPiaoBackBack = findViewById(R.id.mPiao_back_back);
        mPiaoBackBack.setOnClickListener(this);
        mPiaoBack = findViewById(R.id.mPiao_back);
        mPiaoBack.setOnClickListener(this);
        mPiaoDian = findViewById(R.id.mPiao_dian);
        mPiaoDian.setOnClickListener(this);
        mPiaoHeiBack = findViewById(R.id.mPiao_hei_back);
        mPiaoHeiBack.setOnClickListener(this);
        mPiaoPingzi = findViewById(R.id.mPiao_pingzi);
        mPiaoPingzi.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mPiao_dian:
                mPiaoBaiBack.setVisibility(View.GONE);
                mPiaoHeiBack.setVisibility(View.VISIBLE);
                break;
            case R.id.mPiao_pingzi:
                b=Sputils.getInstance().getTiao();
                if (b.length()>0){
                    i = Integer.parseInt(b);
                }else {
                    i=0;
                }
                Sputils.getInstance().setTiao(i+1+"");
                int i1 = Integer.parseInt(Sputils.getInstance().getTiao());
                int i2 = Integer.parseInt("3");
                if (i1>=i2){
                    Toast.makeText(this, "今日免费次数已用完，开通vip额外获得5次", Toast.LENGTH_SHORT).show();
                    Start1();
                    popupWindow11.showAtLocation(popview1.findViewById(R.id.mKai_wai), Gravity.CENTER | Gravity.CENTER, 0, 0);
                }else {
                    piaowenpresenter.loadDataliao(Sputils.getInstance().getuser_id());
                }
                break;
            case R.id.mPiao_back:
                finish();
                break;
        }
    }

    @Override
    public void showDatapiao(Piaowenbean piaowenbean) {
        title=piaowenbean.getData().getTitle();
    }

    @Override
    public void showDataliao(Piaoliaobean piaoliaobean) {
        data= piaoliaobean.getData();
        Start(title);
        popupWindow1.showAtLocation(popview.findViewById(R.id.mPiao_icon), Gravity.CENTER | Gravity.CENTER, 0, 0);
    }

    private View popview;
    private PopupWindow popupWindow1=new PopupWindow();
    private ImageView mPiao_icon;
    private ImageView mPiao_girl;
    private TextView mPiao_name,mPiao_qian,mPiao_wenan,mPiao_liao,mPiao_rengdiao;
    //调用相册 相机
    private void Start(String title) {
        popview = LayoutInflater.from(this).inflate(R.layout.piao_item, null);
        mPiao_icon=popview.findViewById(R.id.mPiao_icon);
        RoundedCorners roundedCorners = new RoundedCorners(25);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);//不做内存缓存
        Glide.with(this)
                .asBitmap()
                .load(data.getAvatar())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .apply(coverRequestOptions)
                .into(mPiao_icon);
        mPiao_girl=popview.findViewById(R.id.mPiao_girl);
        mPiao_qian=popview.findViewById(R.id.mPiao_qian);
        mPiao_qian.setText(data.getLabel()+"");
        mPiao_wenan=popview.findViewById(R.id.mPiao_zhaohu);
        mPiao_wenan.setText(title);
        mPiao_liao=popview.findViewById(R.id.mPiao_liao);
        mPiao_rengdiao=popview.findViewById(R.id.mPiao_rengdiao);
        mPiao_name=popview.findViewById(R.id.mPiao_name);
        mPiao_name.setText(data.getNickname()+"");
        popupWindow1 = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);
        mPiao_liao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yxpresenter.loadData(data.getYx_accid());
                popupWindow1.dismiss();
            }
        });
        mPiao_rengdiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                piaowenpresenter.loadData(Sputils.getInstance().getuser_id());
                mPiaoHeiBack.setVisibility(View.GONE);
                mPiaoBaiBack.setVisibility(View.VISIBLE);
                popupWindow1.dismiss();
            }
        });
    }


    private View popview1;
    private PopupWindow popupWindow11=new PopupWindow();
    private TextView mKai_liji;
    private RelativeLayout mKai_wai;
    //调用相册 相机
    private void Start1() {
        popview1 = LayoutInflater.from(this).inflate(R.layout.kai_vip_item, null);
        mKai_liji=popview1.findViewById(R.id.mKai_liji);
        mKai_wai=popview1.findViewById(R.id.mKai_wai);
        popupWindow11 = new PopupWindow(popview1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow11.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow11.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow11.setTouchable(true);
        popupWindow11.setFocusable(true);
        mKai_wai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow11.dismiss();
            }
        });
        mKai_liji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PiaoActivity.this, VipActivity.class);
                startActivity(intent);
                popupWindow11.dismiss();
            }
        });
    }


    @Override
    public void showDataStart(Telebeanstart telebeanstart) {
        if (telebeanstart.getCode()==2000){
            AVChatKit.outgoingCall(this, data.getYx_accid(), UserInfoHelper.getUserDisplayName(data.getYx_accid()), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
        }else {
            Toast.makeText(this, telebeanstart.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }
    private AlertDialog mAlertDialog;
    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {
        if (yzmfbean.getMsg().contains("余额不足")){
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
    public void showdata(Yxbean yxbean) {
        Sputils.getInstance().setCallvideo(yxbean.getData().getCallvideo());
        telephonepresenter.showDataStart(Sputils.getInstance().getuser_id(),data.getUser_id()+"");
        piaowenpresenter.loadData(Sputils.getInstance().getuser_id());
    }
}