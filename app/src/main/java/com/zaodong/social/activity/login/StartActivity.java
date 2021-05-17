package com.zaodong.social.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.zaodong.social.R;
import com.zaodong.social.base.MyApplication;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.ui.LaunchUi;
import com.gyf.barlibrary.ImmersionBar;

public class StartActivity extends AppCompatActivity {
    private int a=2;
    private ImmersionBar immersionBar;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    a--;
                    if (a > 0){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                        //// send message
                        break;
                    } else if (a==0){
                        if (Sputils.getInstance().gettoken().length()>0){
                                Intent intent = new Intent(StartActivity.this, LaunchUi.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                    }
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
        MyApplication.context=this;
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar!=null){
            immersionBar.destroy();
        }
    }
}
