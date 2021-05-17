package com.zaodong.social.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.gyf.barlibrary.ImmersionBar;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mImage_back;
    private ImageView mImage_zhan;
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        initView();
        Glide.with(this).load(image).into(mImage_zhan);
    }
    private void initView() {
        mImage_back = (ImageButton) findViewById(R.id.mImage_back);
        mImage_zhan = (ImageView) findViewById(R.id.mImage_zhan);
        mImage_zhan.setOnClickListener(this);
        mImage_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mImage_back:
                finish();
                break;
            case R.id.mImage_zhan:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar!=null){
            immersionBar.destroy();
        }
    }
}
