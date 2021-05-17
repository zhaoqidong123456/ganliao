package com.zaodong.social.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.zaodong.social.R;
import com.zaodong.social.utils.FullScreenVideoView;
import com.zaodong.social.utils.LoadDialogUtils;
import com.gyf.barlibrary.ImmersionBar;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private FullScreenVideoView mVideo_videoview;
    private ImageButton mVideo_back;
    private ImmersionBar immersionBar;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
        Intent intent = getIntent();
        String video = intent.getStringExtra("video");
        initView();
        dialog= LoadDialogUtils.createLoadingDialog(this,"");
        mVideo_videoview.setVideoURI(Uri.parse(video));
        mVideo_videoview.start();
        LoadDialogUtils.closeDialog(dialog);
    }

    private void initView() {
        mVideo_videoview = (FullScreenVideoView) findViewById(R.id.mVideo_videoview);
        mVideo_back = (ImageButton) findViewById(R.id.mVideo_back);
        mVideo_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mVideo_back:
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
