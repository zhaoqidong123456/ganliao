package com.zaodong.social.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.zaodong.social.R;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.JokerAdapter;
import com.zaodong.social.bean.Jokerbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IYoujiepresenter;
import com.zaodong.social.presenter.Youjiepresenter;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Youjiaview;

import java.util.ArrayList;

public class YoujiaActivity extends AppCompatActivity implements OnClickListener, Youjiaview {

    private ImageButton mYoujiaBack;
    private RecyclerView mYoujiaRecy;
    private SwipeRefreshLayout mYoujiaSw;
    private IYoujiepresenter youjiepresenter;
    private TextView mYoujiaText;
    private JokerAdapter jokerAdapter;
    private ArrayList<Jokerbean.DataBean> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youjia);
        youjiepresenter = new Youjiepresenter(this);
        initStatusBar();
        initView();
        youjiepresenter.loadData(Sputils.getInstance().getuser_id(), "1", "100");
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initView() {
        mYoujiaBack = findViewById(R.id.mYoujia_back);
        mYoujiaBack.setOnClickListener(this);
        mYoujiaRecy = findViewById(R.id.mYoujia_recy);
        mYoujiaSw = findViewById(R.id.mYoujia_sw);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mYoujiaRecy.setLayoutManager(gridLayoutManager);
        mYoujiaSw.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mYoujiaSw.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mYoujiaSw.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mYoujiaSw.setSize(SwipeRefreshLayout.LARGE);
        //设置下拉刷新的监听
        mYoujiaSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                youjiepresenter.loadData(Sputils.getInstance().getuser_id(), "1", "100");
                mYoujiaSw.setRefreshing(false);
            }
        });
        mYoujiaText = findViewById(R.id.mYoujia_text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mYoujia_back:
                finish();
                break;
        }
    }
    @Override
    public void showData(Jokerbean jokerbean) {
        arrayList.clear();
        arrayList.addAll(jokerbean.getData());
        if (arrayList.size()>0){
            mYoujiaRecy.setVisibility(View.VISIBLE);
            mYoujiaText.setVisibility(View.GONE);
            jokerAdapter=new JokerAdapter(arrayList,this);
            mYoujiaRecy.setAdapter(jokerAdapter);
            jokerAdapter.notifyDataSetChanged();
            jokerAdapter.setOnItemClickwang(new JokerAdapter.OnItemClickwang() {
                @Override
                public void OnItemClickwang(View view, int position) {
                    Intent intent = new Intent(YoujiaActivity.this, DetailsActivity.class);
                    intent.putExtra(DetailsActivity.DETAIL_ID,jokerbean.getData().get(position).getUser_id()+"");
                    startActivity(intent);
                }
            });
        }else {
            mYoujiaText.setVisibility(View.VISIBLE);
            mYoujiaRecy.setVisibility(View.GONE);
        }
    }
    @Override
    public void showDataf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}