package com.zaodong.social.fragment.main;


import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.activity.ImageActivity;
import com.zaodong.social.activity.MyfansActivity;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.activity.PhotosActivity;
import com.zaodong.social.activity.VipActivity;
import com.zaodong.social.activity.login.SettingActivity;
import com.zaodong.social.activity.presonal.ShouruActivity;
import com.zaodong.social.activity.presonal.StateActivity;
import com.zaodong.social.bean.Mybean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.login.IMypresenter;
import com.zaodong.social.presenter.login.Mypresenter;
import com.zaodong.social.view.Myview;
import com.netease.nim.uikit.api.NimUIKit;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener, Myview {
    private View rootView;
    private TextView mMy_name;
    private TextView mMy_id;
    private TextView mMy_copy;
    private TextView mMy_bianji;
    private LinearLayout mMy_boda_counts;
    private LinearLayout mMy_renshu_counts;
    private LinearLayout mMy_guanzhu;
    private LinearLayout mMy_fans;
    private TextView mMy_vip_kaitong;
    private LinearLayout mMy_money;
    private LinearLayout mMy_kefu;
    private LinearLayout mMy_set;
    private IMypresenter mypresenter;
    private SwipeRefreshLayout mMy_sw;
    private CircleImageView mMy_icon;
    private TextView mMy_call_counts;
    private TextView mMy_preson_counts;
    private TextView mMy_guanzhu_counts;
    private TextView mMy_fans_counts;
    private NotificationManager mNotificationManager;
    private LinearLayout mMy_lin_shou;
    private LinearLayout mMy_lin_state;
    private LinearLayout mMy_lin_rizhi;
    private LinearLayout mMy_lin_qianbao;
    private LinearLayout mMy_xian;
    private String image;
    private TextView mMy_daoqi,mMy_tishi;
    private ImageView mMy_dengji;
    private ImageView mMy_vip;
    private String strTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my, container, false);
        initview();
        mNotificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        mypresenter = new Mypresenter(this);
        mypresenter.loadData(Sputils.getInstance().getuser_id());
        return rootView;
    }
    private void initview() {
        mMy_dengji=rootView.findViewById(R.id.mMy_dengji);
        mMy_vip=rootView.findViewById(R.id.mMy_vip);
        mMy_daoqi=rootView.findViewById(R.id.mMy_daoqi);
        mMy_icon = rootView.findViewById(R.id.mMy_icon);
        mMy_icon.setOnClickListener(this);
        mMy_name = (TextView) rootView.findViewById(R.id.mMy_name);
        mMy_id = (TextView) rootView.findViewById(R.id.mMy_id);
        mMy_copy = (TextView) rootView.findViewById(R.id.mMy_copy);
        mMy_copy.setOnClickListener(this);
        mMy_bianji = (TextView) rootView.findViewById(R.id.mMy_bianji);
        mMy_bianji.setOnClickListener(this);
        mMy_boda_counts = (LinearLayout) rootView.findViewById(R.id.mMy_boda_counts);
        mMy_boda_counts.setOnClickListener(this);
        mMy_renshu_counts = (LinearLayout) rootView.findViewById(R.id.mMy_renshu_counts);
        mMy_renshu_counts.setOnClickListener(this);
        mMy_guanzhu = (LinearLayout) rootView.findViewById(R.id.mMy_guanzhu);
        mMy_guanzhu.setOnClickListener(this);
        mMy_fans = (LinearLayout) rootView.findViewById(R.id.mMy_fans);
        mMy_fans.setOnClickListener(this);
        mMy_vip_kaitong = (TextView) rootView.findViewById(R.id.mMy_vip_kaitong);
        mMy_vip_kaitong.setOnClickListener(this);
        mMy_money = (LinearLayout) rootView.findViewById(R.id.mMy_money);
        mMy_money.setOnClickListener(this);
        mMy_kefu = (LinearLayout) rootView.findViewById(R.id.mMy_kefu);
        mMy_kefu.setOnClickListener(this);
        mMy_set = (LinearLayout) rootView.findViewById(R.id.mMy_set);
        mMy_set.setOnClickListener(this);

        mMy_call_counts = rootView.findViewById(R.id.mMy_call_counts);
        mMy_preson_counts = rootView.findViewById(R.id.mMy_preson_counts);
        mMy_guanzhu_counts = rootView.findViewById(R.id.mMy_guanzhu_counts);
        mMy_fans_counts = rootView.findViewById(R.id.mMy_fans_counts);

        mMy_lin_shou=rootView.findViewById(R.id.mMy_lin_shou);
        mMy_lin_shou.setOnClickListener(this);
        mMy_lin_state=rootView.findViewById(R.id.mMy_lin_state);
        mMy_lin_state.setOnClickListener(this);
        mMy_lin_rizhi=rootView.findViewById(R.id.mMy_lin_rizhi);
        mMy_lin_rizhi.setOnClickListener(this);
        mMy_lin_qianbao=rootView.findViewById(R.id.mMy_lin_qianbao);
        mMy_lin_qianbao.setOnClickListener(this);
        mMy_xian=rootView.findViewById(R.id.mMy_xian);
        mMy_tishi=rootView.findViewById(R.id.mMy_tishi);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mMy_icon:
                Intent intent8 = new Intent(getContext(), ImageActivity.class);
                intent8.putExtra("image",Sputils.getInstance().getImage());
                startActivity(intent8);
                break;
            case R.id.mMy_copy:
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(mMy_id.getText());
                Toast.makeText(getContext(), "复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mMy_bianji:
                Intent intent5 = new Intent(getContext(), PhotosActivity.class);
                startActivityForResult(intent5, 1);
                break;
            case R.id.mMy_boda_counts:
//                Intent intent8 = new Intent(getContext(), TelephoneActivity.class);
//                startActivity(intent8);
                break;
            case R.id.mMy_renshu_counts:
                break;
            case R.id.mMy_guanzhu:
                Intent intent1 = new Intent(getContext(), MyfansActivity.class);
                intent1.putExtra("biao","1");
                startActivity(intent1);
                break;
            case R.id.mMy_fans:
                Intent intent2 = new Intent(getContext(), MyfansActivity.class);
                intent2.putExtra("biao","2");
                startActivity(intent2);
                break;
            case R.id.mMy_vip_kaitong:
                if (Sputils.getInstance().getviptype().contains("1")){
                    Intent intent4 = new Intent(getContext(), VipActivity.class);
                    startActivity(intent4);
                }else {
                    Intent intent4 = new Intent(getContext(), VipActivity.class);
                    intent4.putExtra("date",strTime);
                    startActivity(intent4);
                }
                break;
            case R.id.mMy_money:
                Intent intent3 = new Intent(getContext(), MymoneyActivity.class);
                startActivity(intent3);
                break;
            case R.id.mMy_kefu:
                NimUIKit.startP2PSession(getContext(), "uans90321609143164");
                break;
            case R.id.mMy_set:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.mMy_lin_shou:
                Intent intent6 = new Intent(getContext(), ShouruActivity.class);
                startActivity(intent6);
                break;
            case R.id.mMy_lin_state:
                Intent intent7 = new Intent(getContext(), StateActivity.class);
                startActivity(intent7);
                break;
            case R.id.mMy_lin_rizhi:
                Toast.makeText(getContext(), "暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mMy_lin_qianbao:
                Intent intent9 = new Intent(getContext(), MymoneyActivity.class);
                startActivity(intent9);
                break;
        }
    }

    @Override
    public void showDatamy(Mybean mybean){
        if (mybean.getData().getVip().contains("3")||mybean.getData().getVip().contains("2")){
            mMy_vip.setVisibility(View.VISIBLE);
        }else {
            mMy_vip.setVisibility(View.GONE);
        }
        Sputils.getInstance().setMoney(mybean.getData().getMoney()+"");
        if (mybean.getData().getLevel().contains("lv1")&&mybean.getData().getLevel().length()==3){
            mMy_dengji.setImageResource(R.mipmap.laver_one);
        }else if (mybean.getData().getLevel().contains("lv2")){
            mMy_dengji.setImageResource(R.mipmap.laver_two);
        }else if (mybean.getData().getLevel().contains("lv3")){
            mMy_dengji.setImageResource(R.mipmap.laver_three);
        }else if (mybean.getData().getLevel().contains("lv4")){
            mMy_dengji.setImageResource(R.mipmap.laver_gour);
        }else if (mybean.getData().getLevel().contains("lv5")){
            mMy_dengji.setImageResource(R.mipmap.laver_five);
        }else if (mybean.getData().getLevel().contains("lv6")){
            mMy_dengji.setImageResource(R.mipmap.laver_six);
        }else if (mybean.getData().getLevel().contains("lv7")){
            mMy_dengji.setImageResource(R.mipmap.laver_seven);
        }else if (mybean.getData().getLevel().contains("lv8")){
            mMy_dengji.setImageResource(R.mipmap.laver_eight);
        }else if (mybean.getData().getLevel().contains("lv9")){
            mMy_dengji.setImageResource(R.mipmap.laver_nine);
        }else if (mybean.getData().getLevel().contains("lv10")){
            mMy_dengji.setImageResource(R.mipmap.laver_ten);
        }else if (mybean.getData().getLevel().contains("lv11")){
            mMy_dengji.setImageResource(R.mipmap.laver_tenone);
        }
        Sputils.getInstance().setImage(mybean.getData().getAvatar()+"");
        Sputils.getInstance().setvip_u(mybean.getData().getVip() + "");
        Glide.with(getContext()).load(mybean.getData().getAvatar()).into(mMy_icon);
        mMy_name.setText(mybean.getData().getNickname() + "");
        mMy_id.setText("ID:"+mybean.getData().getUser_id() + "");
        mMy_copy.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mMy_copy.getPaint().setAntiAlias(true);//抗锯齿
        if (mybean.getData().getType().contains("2")){
            mMy_call_counts.setText(mybean.getData().getWait_call_frequency() + "");
            mMy_preson_counts.setText(mybean.getData().getWait_call_number() + "");
        }else {
            mMy_call_counts.setText(mybean.getData().getWait_call_frequency() + "");
            mMy_preson_counts.setText(mybean.getData().getWait_call_number() + "");
        }
        mMy_guanzhu_counts.setText(mybean.getData().getFollow() + "");
        mMy_fans_counts.setText(mybean.getData().getFans() + "");
        if (mybean.getData().getType().contains("2")){
            mMy_xian.setVisibility(View.VISIBLE);
            mMy_money.setVisibility(View.GONE);
        }else {
            mMy_xian.setVisibility(View.GONE);
            mMy_xian.setVisibility(View.GONE);
        }
        if (mybean.getData().getVip().contains("1")){
            Sputils.getInstance().setviptype("1");
            mMy_tishi.setText("开通超级VIP，立享多项特权");
            mMy_daoqi.setVisibility(View.GONE);
        }else {
            Sputils.getInstance().setviptype("3");
            mMy_tishi.setText("正在享受超级VIP专属特权");
            mMy_daoqi.setVisibility(View.VISIBLE);
            mMy_vip_kaitong.setText("立即续费");
            strTime= getStrTime(mybean.getData().getVipendtime()+"");
            mMy_daoqi.setText(strTime +"到期");
        }
    }
    @Override
    public void showDatamyf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            mypresenter.loadData(Sputils.getInstance().getuser_id());
        }
    }
    //时间戳转字符串
    public static String getStrTime(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        long  l = Long.valueOf(timeStamp);
        //Log.e("date",l+"");
        timeString = sdf.format(new Date(l*1000l));//单位秒
        return timeString;
    }

    @Override
    public void onResume() {
        super.onResume();
        mypresenter.loadData(Sputils.getInstance().getuser_id());
    }
}
