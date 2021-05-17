package com.zaodong.social.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.zaodong.social.R;
import com.zaodong.social.adapter.MyPagerAdapter;
import com.zaodong.social.base.MyApplication;
import com.zaodong.social.fragment.main.fans.AttFragment;
import com.zaodong.social.fragment.main.fans.FansFragment;
import com.zaodong.social.utils.ModifyTabLayout;
import com.zaodong.social.utils.StatusBarUtils;

import java.util.ArrayList;

public class MyfansActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton mAtt_back;
    private ModifyTabLayout mAtt_tablayout;
    private ViewPager mAtt_viewpager;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> list_Title;
    private String biao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfans);
        initStatusBar();
        Intent intent = getIntent();
        biao = intent.getStringExtra("biao");
        initView();

        MyApplication.context = this;
        initview();
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initview() {
        mAtt_back=findViewById(R.id.mAtt_back);
        mAtt_back.setOnClickListener(this);
        mAtt_tablayout = findViewById(R.id.mAtt_tablayout);
        mAtt_tablayout.setViewHeight(dp2px(35));
        mAtt_tablayout.setBottomLineWidth(dp2px(10));
        mAtt_tablayout.setBottomLineHeight(dp2px(3));
        mAtt_tablayout.setBottomLineHeightBgResId(R.color.color_EF709D);
        mAtt_tablayout.setItemInnerPaddingLeft(dp2px(6));
        mAtt_tablayout.setItemInnerPaddingRight(dp2px(6));
        mAtt_tablayout.setmTextColorSelect(ContextCompat.getColor(this,R.color.color_14805E));
        mAtt_tablayout.setmTextColorUnSelect(ContextCompat.getColor(this,R.color.color_666666));
        mAtt_tablayout.setTextSize(16);
        mAtt_viewpager = findViewById(R.id.mAtt_viewpager);
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        if (biao.contains("1")){
            fragmentList.add(new AttFragment());
            fragmentList.add(new FansFragment());
            list_Title.add("关注");
            list_Title.add("粉丝");
        }else {
            fragmentList.add(new FansFragment());
            fragmentList.add(new AttFragment());
            list_Title.add("粉丝");
            list_Title.add("关注");
        }
        mAtt_viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), this, fragmentList, list_Title));
        mAtt_tablayout.setupWithViewPager(mAtt_viewpager);//此方法就是让tablayout和ViewPager联动
    }
    public int dp2px( float dpValue){
        float scale=getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
    private void initView() {
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mAtt_back:
                finish();
                break;
        }
    }
}
