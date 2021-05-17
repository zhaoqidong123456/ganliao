package com.zaodong.social.activity.presonal;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaodong.social.R;
import com.zaodong.social.adapter.ShouAdapter;
import com.zaodong.social.bean.Shoubean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.vip.IShoupresenter;
import com.zaodong.social.presenter.vip.Shoupresenter;
import com.zaodong.social.view.Shouview;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
public class ShouruActivity extends AppCompatActivity implements View.OnClickListener, Shouview {
    private ImageButton mShou_back;
    private TextView mShou_yu;
    private RecyclerView mShou_recy;
    private IShoupresenter shoupresenter;
    private ImmersionBar immersionBar;
    private ArrayList<Shoubean.DataBean.ListBean> arrayList = new ArrayList<>();
    private ShouAdapter shouAdapter;
    private LinearLayout mTeleFalseWu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouru);
        //initStatusBar();
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
        initView();
        shoupresenter=new Shoupresenter(this);
        shoupresenter.loadData(Sputils.getInstance().getuser_id(),"1","100");
    }
    private void initView() {
        mShou_back = (ImageButton) findViewById(R.id.mShou_back);
        mShou_yu = (TextView) findViewById(R.id.mShou_yu);
        mShou_recy = (RecyclerView) findViewById(R.id.mShou_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mShou_recy.setLayoutManager(linearLayoutManager);
        mShou_back.setOnClickListener(this);
        mTeleFalseWu = findViewById(R.id.mTele_false_wu);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mShou_back:
                finish();
                break;
        }
    }
    @Override
    public void showData(Shoubean shoubean) {
        mShou_yu.setText(shoubean.getData().getMoney() + "");
        arrayList.clear();
        arrayList.addAll(shoubean.getData().getList());
        if (arrayList.size()>0){
            mShou_recy.setVisibility(View.VISIBLE);
            mTeleFalseWu.setVisibility(View.GONE);
            shouAdapter=new ShouAdapter(arrayList,this);
            mShou_recy.setAdapter(shouAdapter);
            shouAdapter.notifyDataSetChanged();
        }else {
            mShou_recy.setVisibility(View.GONE);
            mTeleFalseWu.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showDataf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar!=null){
            immersionBar.destroy();
        }
    }
}
