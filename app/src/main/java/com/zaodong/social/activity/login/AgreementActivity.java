package com.zaodong.social.activity.login;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zaodong.social.R;
import com.zaodong.social.utils.StatusBarUtils;
public class AgreementActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton mAgreeBack;
    private TextView mAgreeTitle;
    private WebView mAgreeWeb;
    private String title;
    private String xieyi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        initStatusBar();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        xieyi = intent.getStringExtra("xieyi");
        initView();
    }
    private void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(AgreementActivity.this, R.color.white);
        }
    }
    private void initView(){
        mAgreeBack = findViewById(R.id.mAgree_back);
        mAgreeBack.setOnClickListener(this);
        mAgreeTitle = findViewById(R.id.mAgree_title);
        mAgreeTitle.setText(title);
        mAgreeWeb = findViewById(R.id.mAgree_web);
        mAgreeWeb.loadUrl(xieyi);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mAgree_back:
                finish();
                break;
        }
    }
}