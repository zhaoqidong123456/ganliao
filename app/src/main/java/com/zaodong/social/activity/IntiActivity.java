package com.zaodong.social.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zaodong.social.R;
import com.zaodong.social.adapter.MateAdapter;
import com.zaodong.social.bean.Matebean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.rank_p.IMatepresenter;
import com.zaodong.social.presenter.rank_p.Matepresenter;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Mateview;

import java.util.ArrayList;

public class IntiActivity extends AppCompatActivity implements View.OnClickListener, Mateview {

    private ImageButton mInit_back;
    private RecyclerView mInit_recy;
    private IMatepresenter matepresenter;
    private MateAdapter mateAdapter;
    private ArrayList<Matebean.DataBean> arrayList=new ArrayList<>();
    private RelativeLayout mInto_null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inti);
        initStatusBar();
        matepresenter=new Matepresenter(this);
        matepresenter.loadmate(Sputils.getInstance().getau_id(),"1","100");
        initView();
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }

    private void initView() {
        mInto_null=findViewById(R.id.mInto_null);
        mInit_back = (ImageButton) findViewById(R.id.mInit_back);
        mInit_recy = (RecyclerView) findViewById(R.id.mInit_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mInit_recy.setLayoutManager(linearLayoutManager);
        mInit_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mInit_back:
                finish();
                break;
        }
    }

    @Override
    public void showDatamate(Matebean matebean) {
        arrayList.clear();
        arrayList.addAll(matebean.getData());
        if (arrayList.size()>0){
            mInto_null.setVisibility(View.GONE);
            mInit_recy.setVisibility(View.VISIBLE);
            mateAdapter=new MateAdapter(arrayList,this);
            mInit_recy.setAdapter(mateAdapter);
            mateAdapter.notifyDataSetChanged();
        }else {
            mInit_recy.setVisibility(View.GONE);
            mInto_null.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showDatamatef(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
