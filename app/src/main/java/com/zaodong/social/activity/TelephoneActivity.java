package com.zaodong.social.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.zaodong.social.R;
import com.zaodong.social.base.MyApplication;
import com.zaodong.social.fragment.main.tele.TelefalseFragment;
import com.zaodong.social.fragment.main.tele.TeletrueFragment;
import com.zaodong.social.utils.StatusBarUtils;

public class TelephoneActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton mTeleBack;
    private RadioGroup mTeleRg;
    private RadioButton mTeleRb1;
    private RadioButton mTeleRb2;
    private FrameLayout mTeleFrame;
    private FragmentTransaction transaction;
    private TelefalseFragment telefalseFragment;
    private TeletrueFragment teletrueFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        MyApplication.context=this;
        initStatusBar();
        initView();
        loadData();
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initView() {
        mTeleBack = findViewById(R.id.mTele_back);
        mTeleBack.setOnClickListener(this);
        mTeleRg = findViewById(R.id.mTele_rg);
        mTeleRb1 = findViewById(R.id.mTele_rb1);
        mTeleRb2 = findViewById(R.id.mTele_rb2);
        mTeleFrame = findViewById(R.id.mTele_frame);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mTele_back:
                finish();
                break;
        }
    }
    private void loadData() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        transaction = supportFragmentManager.beginTransaction();
        hideFragment();
        if (telefalseFragment == null) {
            telefalseFragment = new TelefalseFragment();
            transaction.add(R.id.mTele_frame, telefalseFragment);
        }
        transaction.show(telefalseFragment);
        transaction.commit();
        mTeleRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                transaction = supportFragmentManager.beginTransaction();
                hideFragment();
                switch (checkedId) {
                    case R.id.mTele_rb1:
                        if (telefalseFragment == null) {
                            telefalseFragment = new TelefalseFragment();
                            transaction.add(R.id.mTele_frame, telefalseFragment);
                        }
                        transaction.show(telefalseFragment);
                        transaction.commit();
                        break;
                    case R.id.mTele_rb2:
                        if (teletrueFragment == null) {
                            teletrueFragment = new TeletrueFragment();
                            transaction.add(R.id.mTele_frame, teletrueFragment);
                        }
                        transaction.show(teletrueFragment);
                        transaction.commit();
                        //startActivity(new Intent(MainActivity.this, com.example.qingtian_live.nim.main.activity.MainActivity.class));
                        break;
                }
            }
        });
    }
    private void hideFragment() {
        if (telefalseFragment != null) {
            //隐藏
            transaction.hide(telefalseFragment);
        }
        if (teletrueFragment != null) {
            //隐藏
            transaction.hide(teletrueFragment);
        }
    }
}