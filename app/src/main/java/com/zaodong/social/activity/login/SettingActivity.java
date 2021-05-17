package com.zaodong.social.activity.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zaodong.social.R;
import com.zaodong.social.base.MyApplication;
import com.zaodong.social.ui.LaunchUi;
import com.zaodong.social.utils.CleanCacheUtil;
import com.zaodong.social.utils.StatusBarUtils;
import com.netease.nim.uikit.common.ui.dialog.AlertDialog;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mAge_back;
    private TextView mSeting_huancun;
    private LinearLayout mSeting_clean;
    private TextView mSeting_version;
    private LinearLayout mSeting_jiancha;
    private TextView mSeting_exit;
    private String totalCacheSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        MyApplication.context=this;
        initView();
        initStatusBar();
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initView() {
        try {
            totalCacheSize = CleanCacheUtil.getTotalCacheSize(MyApplication.context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAge_back = (ImageButton) findViewById(R.id.mAge_back);
        mSeting_huancun = (TextView) findViewById(R.id.mSeting_huancun);
        mSeting_huancun.setText(totalCacheSize);
        mSeting_clean = (LinearLayout) findViewById(R.id.mSeting_clean);
        mSeting_clean.setOnClickListener(this);
        mSeting_version = (TextView) findViewById(R.id.mSeting_version);
        mSeting_jiancha = (LinearLayout) findViewById(R.id.mSeting_jiancha);
        mSeting_jiancha.setOnClickListener(this);
        mSeting_exit = (TextView) findViewById(R.id.mSeting_exit);
        mSeting_exit.setOnClickListener(this);
        mAge_back.setOnClickListener(this);
    }
    private AlertDialog mAlertDialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mAge_back:
                finish();
                break;
            case R.id.mSeting_exit:
                if (mAlertDialog == null) {
                    mAlertDialog = new AlertDialog.Builder(this)
                            .setContentView(com.netease.nim.uikit.R.layout.exit)
                            .fullWidth()
                            .setOnClickListener(com.netease.nim.uikit.R.id.all_read_cancel, v1 -> {
                                mAlertDialog.dismiss();
                            })
                            .setOnClickListener(com.netease.nim.uikit.R.id.all_read_ok, v1 -> {
                                mAlertDialog.dismiss();
                                Intent intent = new Intent();
                                intent.setClass(SettingActivity.this, LaunchUi.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                intent.putExtra(LaunchUi.EXTRA_APP_QUIT, false);
                                startActivity(intent);
                                finish();
                            })
                            .create();
                }
                mAlertDialog.show();
                break;
            case R.id.mSeting_clean:
                Toast.makeText(this, "已清除缓存", Toast.LENGTH_SHORT).show();
                CleanCacheUtil.clearAllCache(this);
                mSeting_huancun.setText("0.0M");
                break;
            case R.id.mSeting_jiancha:
                Toast.makeText(this, "当前为最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
