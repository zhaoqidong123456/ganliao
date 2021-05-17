package com.zaodong.social.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netease.nim.uikit.common.ui.dialog.AlertDialog;
import com.zaodong.social.R;
import com.zaodong.social.activity.start.PayActivity;
import com.zaodong.social.adapter.AutoPollAdapter;
import com.zaodong.social.adapter.PieceAdapter;
import com.zaodong.social.bean.Paobean;
import com.zaodong.social.bean.Paobeanvip;
import com.zaodong.social.bean.Piecebean;
import com.zaodong.social.bean.Vipbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IPaopresenter;
import com.zaodong.social.presenter.Paopresenter;
import com.zaodong.social.presenter.vip.IPiecepresenter;
import com.zaodong.social.presenter.vip.Piecepresenter;
import com.zaodong.social.utils.AutoPollRecyclerView;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Paoview;
import com.zaodong.social.view.Piecevieww;

import java.util.ArrayList;
import java.util.List;

public class MymoneyActivity extends AppCompatActivity implements View.OnClickListener, Piecevieww, Paoview {

    private ImageButton mMymoney_back;
    private TextView mMymoney_mingxi;
    private ImageView mActive_youjia;
    private TextView mMoney_yu;
    private RecyclerView mMoney_recy;
    private IPiecepresenter piecepresenter;
    private ArrayList<Piecebean.DataBean.ListBean> arrayList = new ArrayList<>();
    private PieceAdapter pieceAdapter;
    private TextView mMoney_chongzhi;
    private AutoPollRecyclerView mRecyclerView;
    private IPaopresenter paopresenter;
    private ArrayList<Paobean.DataBean> arrayListpao=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymoney);
        initStatusBar();
        initView();
        paopresenter=new Paopresenter(this);
        paopresenter.loadData("2");
        piecepresenter = new Piecepresenter(this);
        piecepresenter.loadData(Sputils.getInstance().getuser_id(), "1");
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initView() {
        mMymoney_back = (ImageButton) findViewById(R.id.mMymoney_back);
        mMymoney_mingxi = (TextView) findViewById(R.id.mMymoney_mingxi);
        mMymoney_mingxi.setOnClickListener(this);
        mActive_youjia = (ImageView) findViewById(R.id.mMoney_youjia);
        mActive_youjia.setOnClickListener(this);
        mMoney_yu = (TextView) findViewById(R.id.mMoney_yu);
        mMoney_recy = (RecyclerView) findViewById(R.id.mMoney_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mMoney_recy.setLayoutManager(gridLayoutManager);
        mMymoney_back.setOnClickListener(this);
        mMoney_chongzhi = (TextView) findViewById(R.id.mMoney_chongzhi);
        mMoney_chongzhi.setOnClickListener(this);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_y);
        shake.setAnimationListener(new ReStartAnimationListener());
        shake.reset();
        shake.setStartTime(1000);
        shake.setFillAfter(true);
        mMoney_chongzhi.startAnimation(shake);
        mRecyclerView = findViewById(R.id.mPao_recy);
    }
    @Override
    public void showData(Paobean paobean) {
        arrayListpao.clear();
        arrayListpao.addAll(paobean.getData());
        AutoPollAdapter adapter = new AutoPollAdapter(this, arrayListpao);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(adapter);
        if (true){
            //保证itemCount的总个数宽度超过屏幕宽度->自己处理
            mRecyclerView.start();
        }
    }
    @Override
    public void showDatavip(Paobeanvip paobeanvip) {

    }
    private class ReStartAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            animation.reset();
            animation.setAnimationListener(new ReStartAnimationListener());
            animation.start();
        }

        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    }

    private AlertDialog mAlertDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mMymoney_back:
                finish();
                break;
            case R.id.mMoney_chongzhi:
                if (string1.length() <= 0) {
                    Toast.makeText(this, "请选择充值金额", Toast.LENGTH_SHORT).show();
                } else {
                    if (mAlertDialog == null) {
                        mAlertDialog = new AlertDialog.Builder(this)
                                .setContentView(R.layout.xuanze_item)
                                .fullWidth()
                                .setOnClickListener(R.id.all_read_wei, v1 -> {
                                    Intent intent3 = new Intent(this, PayActivity.class);
                                    intent3.putExtra("biao", "wei");
                                    intent3.putExtra("zhifu", "http://klpay.skrfun.cn/api.php?s=/wxh5/placeOrder&price=" + price + "&goods_id=" + goodsid + "&subject=" + pricewriting + "&user_id=" + Sputils.getInstance().getuser_id() + "&channel="+RetrofitUrl.channel);
                                    startActivity(intent3);
                                    mAlertDialog.dismiss();
                                })
                                .setOnClickListener(R.id.all_read_zhi, v1 -> {
                                    Intent intent3 = new Intent(this, PayActivity.class);
                                    intent3.putExtra("biao", "zhi");
                                    intent3.putExtra("zhifu", "http://klpay.skrfun.cn/api.php?s=/alipayh5/placeOrder&price=" + price + "&goods_id=" + goodsid + "&subject=" + pricewriting + "&user_id=" + Sputils.getInstance().getuser_id() + "&channel="+RetrofitUrl.channel);
                                    startActivity(intent3);
                                    mAlertDialog.dismiss();
//
                                })
                                .create();
                    }
                    mAlertDialog.show();
                }
                break;
            case R.id.mMymoney_mingxi:
                Intent intent = new Intent(this, LiushuiActivity.class);
                startActivity(intent);
                break;
            case R.id.mMoney_youjia:
                Intent intent1 = new Intent(this, YoujiaActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private String pricewriting;
    private String goodsid;
    private String price;
    private Dialog dialog;
    private String string1 = "";

    @Override
    public void showData(final Piecebean piecebean) {
        Glide.with(this).load(piecebean.getData().getDiscount()).into(mActive_youjia);
        mMoney_yu.setText(piecebean.getData().getMoney() + "");
        arrayList.clear();
        arrayList.addAll(piecebean.getData().getList());
        pieceAdapter = new PieceAdapter(arrayList, this);
        mMoney_recy.setAdapter(pieceAdapter);
        pieceAdapter.notifyDataSetChanged();
        pieceAdapter.setOnItemListener(new PieceAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos) {
                string1 = arrayList.get(pos).getId() + "";
                pieceAdapter.setDefSelect(pos);
                price = arrayList.get(pos).getPrice();
                goodsid = arrayList.get(pos).getGoodsid();
                pricewriting = arrayList.get(pos).getName();
                Log.e("gooid", goodsid + "");
            }
        });
    }

    @Override
    public void showDatf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDatavip(Vipbean piecebean) {

    }

    @Override
    public void showDatfvip(Yzmfbean yzmfbean) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        piecepresenter.loadData(Sputils.getInstance().getuser_id(), "1");
    }
}
