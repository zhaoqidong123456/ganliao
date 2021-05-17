package com.zaodong.social.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.bean.AVChatVideoBean;
import com.netease.nim.avchatkit.interfaces.IExtendMessage;
import com.netease.nim.uikit.bean.TestUserBean;
import com.zaodong.social.R;
import com.zaodong.social.base.MyApplication;
import com.zaodong.social.bean.Loginbean;
import com.zaodong.social.bean.Yzmbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.login.Hyzmprensenter;
import com.zaodong.social.presenter.login.IHyzmprensenter;
import com.zaodong.social.ui.LaunchUi;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Yzmview;
import com.gyf.barlibrary.ImmersionBar;
import com.netease.nim.demo.DemoCache;
import com.netease.nim.demo.config.preference.Preferences;
import com.netease.nim.demo.config.preference.UserPreferences;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Yzmview {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mLogin_phone;
    private EditText mLogin_yzm;
    private TextView mLogin_hyzm;
    private LinearLayout mLogin_login;
    private TimeCount time;
    private ImmersionBar immersionBar;
    private IHyzmprensenter hyzmprensenter;
    private long firstTime = 0;
    private TextView mLoginYongXie;
    private TextView mLoginYinXie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyApplication.context=this;
        immersionBar=ImmersionBar.with(this);
        immersionBar.init();
        hyzmprensenter=new Hyzmprensenter(this);
        time = new TimeCount(60000, 1000);
        initView();
    }
    private void initView() {
        mLogin_phone = (EditText) findViewById(R.id.mLogin_phone);
        mLogin_yzm = (EditText) findViewById(R.id.mLogin_yzm);
        mLogin_hyzm = (TextView) findViewById(R.id.mLogin_hyzm);
        mLogin_hyzm.setOnClickListener(this);
        mLogin_login = (LinearLayout) findViewById(R.id.mLogin_login);
        mLogin_login.setOnClickListener(this);
        mLoginYongXie = findViewById(R.id.mLogin_yong_xie);
        mLoginYongXie.setOnClickListener(this);
        mLoginYinXie = findViewById(R.id.mLogin_yin_xie);
        mLoginYinXie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mLogin_hyzm:
                if (mLogin_phone.getText().toString().length()!=11){
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }else {
                    String string= RetrofitUrl.key+"ganliao"+mLogin_phone.getText().toString()+"1.0.0";
                    String s = MD5Utils.MD5(string);
                    String s1 = s.toUpperCase();
                    hyzmprensenter.loadHyzm(mLogin_phone.getText().toString(),s1);
                    time.start();
                }
                break;
            case R.id.mLogin_login:
                if (mLogin_phone.getText().toString().length()!=11){
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }else if (mLogin_yzm.getText().toString().length()<=0){
                    Toast.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
                }else {
                    hyzmprensenter.loadLogin(mLogin_phone.getText().toString(),mLogin_yzm.getText().toString());
                }
                break;
            case R.id.mLogin_yong_xie:
                Intent intent = new Intent(this, AgreementActivity.class);
                intent.putExtra("title","用户协议");
                intent.putExtra("xieyi","http://haipi.kaiyundashi.cn/kongyou_register.html");
                startActivity(intent);
                break;
            case R.id.mLogin_yin_xie:
                Intent intent1 = new Intent(this, AgreementActivity.class);
                intent1.putExtra("title","隐私协议");
                intent1.putExtra("xieyi","http://haipi.kaiyundashi.cn/kongyou_privacy.html");
                startActivity(intent1);
                break;
        }
    }
    @Override
    public void showDataYzm(Yzmbean yzmbean) {
        Toast.makeText(this, "短信发送成功，请注意查收", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showDatayzmf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showDataLogin(Loginbean yzmbean) {
        if (yzmbean==null || yzmbean.getData()==null){
            ToastHelper.showToast(this,"登录失败");
            return;
        }
        Loginbean.DataBean data = yzmbean.getData();

        NimUIKit.login(new LoginInfo(data.getYx_accid(), data.getYx_token(), null, 0),
                new RequestCallback<LoginInfo>() {

                    @Override
                    public void onSuccess(LoginInfo param) {
                        LogUtil.i(TAG, "login success");
//                        onLoginDone();
                        DemoCache.setAccount(data.getYx_accid());
                        saveLoginInfo(data.getYx_accid(), data.getYx_token());
                        // 初始化消息提醒配置
                        initNotificationConfig();
                        // 进入主界面
                        Sputils.getInstance().settoken(yzmbean.getData().getYx_token());
                        Sputils.getInstance().setuser_id(yzmbean.getData().getUser_id()+"");
                        Sputils.getInstance().setvip_u(yzmbean.getData().getVip()+"");
                        Sputils.getInstance().settype(yzmbean.getData().getType()+"");
                        Sputils.getInstance().setImage(yzmbean.getData().getAvatar()+"");

                        Sputils.getInstance().setnickname(yzmbean.getData().getNickname()+"");
                        Sputils.getInstance().setCallprice(yzmbean.getData().getCallprice()+"");
                        Sputils.getInstance().setMoney(yzmbean.getData().getMoney()+"");
                        Preferences.saveUser_id(yzmbean.getData().getUser_id()+"");

                        TestUserBean testUserBean = new TestUserBean();
                        testUserBean.setAvatar(Sputils.getInstance().getImage());//头像
                        testUserBean.setType(Sputils.getInstance().gettype());
                        testUserBean.setYx_accid(yzmbean.getData().getYx_token());
                        testUserBean.setUser_id(Sputils.getInstance().getuser_id());
                        testUserBean.setMoney(Sputils.getInstance().getMoney());
                        testUserBean.setCallprice(Sputils.getInstance().getCallprice());
                        testUserBean.setNickname(Sputils.getInstance().getnickname()+"");
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, LaunchUi.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailed(int code) {
//                        onLoginDone();
                        if (code == 302 || code == 404) {
                            ToastHelper.showToast( LoginActivity.this,
                                    com.netease.nim.demo.R.string.login_failed);
                        } else {
                            ToastHelper.showToast( LoginActivity.this,
                                    "登录失败: " + code);
                        }
                    }
                    @Override
                    public void onException(Throwable exception) {
                        ToastHelper.showToast( LoginActivity.this,
                                com.netease.nim.demo.R.string.login_exception);
//                        onLoginDone();
                    }
                });

    }

    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);

    }

    private void initNotificationConfig() {
        // 初始化消息提醒
        NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
        // 加载状态栏配置
        StatusBarNotificationConfig statusBarNotificationConfig = UserPreferences.getStatusConfig();
        if (statusBarNotificationConfig == null) {
            statusBarNotificationConfig = DemoCache.getNotificationConfig();
            UserPreferences.setStatusConfig(statusBarNotificationConfig);
        }
        // 更新配置
        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
    }

    @Override
    public void showDataLoginf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mLogin_hyzm.setClickable(false);
            mLogin_hyzm.setText(millisUntilFinished / 1000 +"s");
        }
        @Override
        public void onFinish() {
            mLogin_hyzm.setText("重新获取");
            mLogin_hyzm.setClickable(true);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar!=null){
            immersionBar.destroy();
        }
    }
    //双击两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(LoginActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
