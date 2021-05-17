package com.zaodong.social.activity.presonal;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.zaodong.social.R;
import com.zaodong.social.bean.Statebean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.login.state.IStatepresenter;
import com.zaodong.social.presenter.login.state.Statepresenter;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Stateview;

public class StateActivity extends AppCompatActivity implements View.OnClickListener, Stateview {

    private ImageButton mState_back;
    private ImageView mState_true;
    private ImageView mState_false;
    private IStatepresenter statepresenter;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        initStatusBar();
        initView();
        statepresenter=new Statepresenter(this);
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    private void initView() {
        mState_back = (ImageButton) findViewById(R.id.mState_back);
        mState_true = (ImageView) findViewById(R.id.mState_true);
        mState_true.setOnClickListener(this);
        mState_false = (ImageView) findViewById(R.id.mState_false);
        mState_false.setOnClickListener(this);
        mState_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mState_back:
                finish();
                break;
            case R.id.mState_true:
                a=0;
                statepresenter.loadData(Sputils.getInstance().getuser_id(),"1");
                break;
            case R.id.mState_false:
                a=1;
                statepresenter.loadData(Sputils.getInstance().getuser_id(),"3");
                break;
        }
    }
    @Override
    public void showDatastate(Statebean statebean) {
        Toast.makeText(this, statebean.getMsg()+"", Toast.LENGTH_SHORT).show();
        if (a==0){
            mState_true.setBackgroundResource(R.mipmap.state_true);
            mState_false.setBackgroundResource(R.mipmap.state_false);
        }else if (a==1){
            mState_true.setBackgroundResource(R.mipmap.state_false);
            mState_false.setBackgroundResource(R.mipmap.state_true);
        }
    }
    @Override
    public void showDatastatef(Yzmfbean statebean) {
        Toast.makeText(this, statebean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
