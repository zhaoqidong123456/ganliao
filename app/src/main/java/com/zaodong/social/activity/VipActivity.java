package com.zaodong.social.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.activity.start.PayActivity;
import com.zaodong.social.adapter.AutoPollAdapter;
import com.zaodong.social.adapter.AutoPollAdapter1;
import com.zaodong.social.adapter.VipAdapter;
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
import com.zaodong.social.view.Paoview;
import com.zaodong.social.view.Piecevieww;
import com.gyf.barlibrary.ImmersionBar;
import com.netease.nim.uikit.common.ui.dialog.AlertDialog;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
public class VipActivity extends AppCompatActivity implements View.OnClickListener, Piecevieww, Paoview {

    private ImageButton mVip_back;
    private CircleImageView mVip_icon;
    private TextView mVip_xifei_date,mVip_tishi;
    private RecyclerView mVip_recy;
    private TextView mVip_liji;
    private ImmersionBar immersionBar;
    private IPiecepresenter piecepresenter;
    private ArrayList<Vipbean.DataBean.ListBean> arrayList=new ArrayList<>();
    private VipAdapter vipAdapter;
    private String image;
    private String price;
    private String goodsid;
    private String pricewriting;
    private AutoPollRecyclerView mVip_recy_pao;
    private IPaopresenter paopresenter;
    private ArrayList<Paobeanvip.DataBean> arrayList1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
        paopresenter=new Paopresenter(this);
        paopresenter.loadDatavip("1");
        piecepresenter=new Piecepresenter(this);
        piecepresenter.loadDatavip(Sputils.getInstance().getuser_id(),"2");
        initView();
        if (Sputils.getInstance().getviptype().contains("1")){
            mVip_tishi.setText("开通超级VIP，立享多项特权");
            mVip_xifei_date.setVisibility(View.GONE);
        }else {
            Intent intent = getIntent();
            String date = intent.getStringExtra("date");
            mVip_tishi.setText("正在享受超级VIP专属特权");
            mVip_xifei_date.setVisibility(View.VISIBLE);
            mVip_xifei_date.setText(date+"到期");
            mVip_liji.setText("立即续费");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar!=null){
            immersionBar.destroy();
        }
    }
    private void initView() {
        mVip_recy_pao=findViewById(R.id.mVip_recy_pao);
        mVip_tishi=findViewById(R.id.mVip_tishi);
        mVip_back = (ImageButton) findViewById(R.id.mVip_back);
        mVip_icon = (CircleImageView) findViewById(R.id.mVip_icon);
        mVip_xifei_date = (TextView) findViewById(R.id.mVip_xifei_date);
        mVip_recy = (RecyclerView) findViewById(R.id.mVip_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mVip_recy.setLayoutManager(gridLayoutManager);
        mVip_liji = (TextView) findViewById(R.id.mVip_liji);
        mVip_liji.setOnClickListener(this);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_y);
        shake.setAnimationListener(new ReStartAnimationListener());
        shake.reset();
        shake.setStartTime(1000);
        shake.setFillAfter(true);
        mVip_liji.startAnimation(shake);
        mVip_back.setOnClickListener(this);
    }
    private AlertDialog mAlertDialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mVip_back:
                finish();
                break;
            case R.id.mVip_liji:
                if (string1.length()<=0){
                    Toast.makeText(this, "请选择充值金额", Toast.LENGTH_SHORT).show();
                }else {
                    if (mAlertDialog == null) {
                        mAlertDialog = new AlertDialog.Builder(this)
                                .setContentView(R.layout.xuanze_item)
                                .fullWidth()
                                .setOnClickListener(R.id.all_read_wei, v1 -> {
                                    Intent intent3 = new Intent(this, PayActivity.class);
                                    intent3.putExtra("biao","wei");
                                    intent3.putExtra("zhifu","http://klpay.skrfun.cn/api.php?s=/wxh5/placeOrder&price="+price+"&goods_id="+goodsid+"&subject="+pricewriting+"&user_id="+Sputils.getInstance().getuser_id()+"&channel="+ RetrofitUrl.channel);
                                    startActivity(intent3);
                                    mAlertDialog.dismiss();
                                })
                                .setOnClickListener(R.id.all_read_zhi, v1 -> {
                                    Intent intent3 = new Intent(this, PayActivity.class);
                                    intent3.putExtra("biao","zhi");
                                    intent3.putExtra("zhifu","http://klpay.skrfun.cn/api.php?s=/alipayh5/placeOrder&price="+price+"&goods_id="+goodsid+"&subject="+pricewriting+"&user_id="+Sputils.getInstance().getuser_id()+"&channel="+ RetrofitUrl.channel);
                                    startActivity(intent3);
                                    mAlertDialog.dismiss();
//
                                })
                                .create();
                    }
                    mAlertDialog.show();
                }
                break;
        }
    }
    private String string1="";

    @Override
    public void showData(Paobean paobean) {

    }
    @Override
    public void showDatavip(Paobeanvip paobeanvip) {
        arrayList.clear();
        arrayList1.addAll(paobeanvip.getData());
        AutoPollAdapter1 adapter = new AutoPollAdapter1(this, arrayList1);
        mVip_recy_pao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mVip_recy_pao.setAdapter(adapter);
        if (true){
            //保证itemCount的总个数宽度超过屏幕宽度->自己处理
            mVip_recy_pao.start();
        }
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
    @Override
    public void showData(Piecebean piecebean) {

    }

    @Override
    public void showDatf(Yzmfbean yzmfbean) {

    }
    @Override
    public void showDatavip(Vipbean piecebean) {
        Glide.with(this).load(Sputils.getInstance().getImage()+"").into(mVip_icon);
        arrayList.clear();
        arrayList.addAll(piecebean.getData().getList());
        vipAdapter=new VipAdapter(arrayList,this);
        mVip_recy.setAdapter(vipAdapter);
        vipAdapter.notifyDataSetChanged();
        vipAdapter.setOnItemListener(new VipAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos) {
                vipAdapter.setDefSelect(pos);
                string1=arrayList.get(pos).getId()+"";
                price = arrayList.get(pos).getPrice()+"";
                goodsid = arrayList.get(pos).getGoodsid();
                pricewriting = arrayList.get(pos).getName();
            }
        });
    }
    @Override
    public void showDatfvip(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean+"", Toast.LENGTH_SHORT).show();
    }
}
