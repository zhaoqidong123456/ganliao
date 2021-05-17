package com.zaodong.social.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zaodong.social.R;
import com.zaodong.social.adapter.LiushuiAdapter;
import com.zaodong.social.adapter.ShouAdapter;
import com.zaodong.social.bean.Shoubean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.vip.IShoupresenter;
import com.zaodong.social.presenter.vip.Shoupresenter;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Shouview;

import java.util.ArrayList;

public class LiushuiActivity extends AppCompatActivity implements View.OnClickListener , Shouview {

    private ImageButton mLiushui_back;
    private RecyclerView mLiushui_recy;
    private LiushuiAdapter liushuiAdapter;
    private LinearLayout mTeleFalseWu;
    private IShoupresenter shoupresenter;
    private ShouAdapter shouAdapter;
    private ArrayList<Shoubean.DataBean.ListBean> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liushui);
        initStatusBar();
        initView();
        shoupresenter=new Shoupresenter(this);
        shoupresenter.loadData(Sputils.getInstance().getuser_id(),"1","100");
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initView() {
        mLiushui_back = (ImageButton) findViewById(R.id.mLiushui_back);
        mLiushui_recy = (RecyclerView) findViewById(R.id.mLiushui_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mLiushui_recy.setLayoutManager(linearLayoutManager);
        mLiushui_back.setOnClickListener(this);
        mTeleFalseWu = findViewById(R.id.mTele_false_wu);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLiushui_back:
                finish();
                break;
        }
    }
    @Override
    public void showData(Shoubean shoubean) {
        arrayList.clear();
        arrayList.addAll(shoubean.getData().getList());
        if (arrayList.size()>0){
            mLiushui_recy.setVisibility(View.VISIBLE);
            mTeleFalseWu.setVisibility(View.GONE);
            shouAdapter=new ShouAdapter(arrayList,this);
            mLiushui_recy.setAdapter(shouAdapter);
            shouAdapter.notifyDataSetChanged();
        }else {
            mLiushui_recy.setVisibility(View.GONE);
            mTeleFalseWu.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showDataf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
