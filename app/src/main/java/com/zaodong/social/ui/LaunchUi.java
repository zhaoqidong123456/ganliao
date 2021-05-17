package com.zaodong.social.ui;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gyf.barlibrary.ImmersionBar;
import com.zaodong.social.R;
import com.zaodong.social.activity.login.LoginActivity;
import com.zaodong.social.base.MyApplication;
import com.zaodong.social.bean.Versionbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.fragment.main.MyFragment;
import com.zaodong.social.fragment.main.RankFragment;
import com.zaodong.social.fragment.main.StartFragment;
import com.zaodong.social.fragment.main.ZongVideoFragment;
import com.zaodong.social.fragment.main.fans.AtttofansFragment;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IVersionpresenter;
import com.zaodong.social.presenter.Versionpresenter;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Versionview;
import com.zaodong.social.weight.CustomViewPager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.netease.nim.demo.config.preference.Preferences;
import com.netease.nim.demo.login.LogoutHelper;
import com.netease.nim.demo.main.helper.SystemMessageUnreadManager;
import com.netease.nim.demo.main.reminder.ReminderItem;
import com.netease.nim.demo.main.reminder.ReminderManager;
import com.netease.nim.uikit.business.recent.RecentContactsFragment;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.support.permission.MPermission;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionGranted;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionNeverAskAgain;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class LaunchUi extends AppCompatActivity implements ReminderManager.UnreadNumChangedCallback, Versionview {
    private String[] mTitles = {"首页", "排行榜", "视频秀", "消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.nav_icon_home, R.mipmap.nav_icon_ranking_h, R.mipmap.nav_icon_video,
            R.mipmap.nav_icon_news, R.mipmap.nav_icon_my};
    private int[] mIconSelectIds = {
            R.mipmap.nav_icon_home_c, R.mipmap.nav_icon_ranking, R.mipmap.nav_icon_video_cai,
            R.mipmap.nav_icon_news_cai, R.mipmap.nav_icon_my_cai};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonTabLayout bottomBar;
    private IVersionpresenter versionpresenter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private CustomViewPager mViewPager;

    private StartFragment startFragment;
    private RankFragment rankFragment;
    private ZongVideoFragment videoFragment;
    private RecentContactsFragment newsFragment;

    private AtttofansFragment atttofansFragment;
    private MyFragment myFragment;
    private long firstTime = 0;
    private static final int BASIC_PERMISSION_REQUEST_CODE = 100;

    private String typedata;
    private String refresh_address;
    private String content;
    private ImmersionBar immersionBar;


    private static final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_ui);
        MyApplication.context=this;
        versionpresenter=new Versionpresenter(this);
        versionpresenter.loadData(Sputils.getInstance().getuser_id());
        bottomBar = findViewById(R.id.bottom_bar);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        bottomBar.setTabData(mTabEntities);
        startFragment = new StartFragment();
        rankFragment = new RankFragment();
        videoFragment = new ZongVideoFragment();
        newsFragment = new RecentContactsFragment();
        myFragment = new MyFragment();
        atttofansFragment=new AtttofansFragment();
        mFragments.add(startFragment);
        mFragments.add(rankFragment);
        mFragments.add(videoFragment);
        if (Sputils.getInstance().gettype().contains("1")){
            mFragments.add(newsFragment);
        }else {
            mFragments.add(atttofansFragment);
        }
        mFragments.add(myFragment);

        mViewPager = findViewById(R.id.viewpage);
        mViewPager.setSlideEnabled(false);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        bottomBar.setTabData(mTabEntities);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                if (position==0){
                    ImmersionBar.with(LaunchUi.this)
                            .reset()
                            .transparentStatusBar()
                            .statusBarColor(R.color.white)
                            .statusBarDarkFont(true)
                            .init();
                }
                if (position==1){
                    immersionBar
                            .with(LaunchUi.this)
                            .reset()
                            .fullScreen(true)
                            .transparentBar()
                            .init();
                }
                if (position==2){
                    immersionBar
                            .with(LaunchUi.this)
                            .reset()
                            .fullScreen(true)
                            .transparentBar()
                            .init();
                }
                if (position==3){
                    ImmersionBar.with(LaunchUi.this)
                            .reset()
                            .transparentStatusBar()
                            .statusBarColor(R.color.white)
                            .statusBarDarkFont(true)
                            .init();
                    bottomBar.hideMsg(3);
                }
                if (position==4){
                    immersionBar
                            .with(LaunchUi.this)
                            .reset()
                            .fullScreen(true)
                            .transparentBar()
                            .init();
                }
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
        mViewPager.setCurrentItem(0);
        //设置未读消息背景
        MyApplication.context = this;
        ReminderManager.getInstance().registerUnreadNumChangedCallback(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        int totalUnreadCount = NIMClient.getService(MsgService.class).getTotalUnreadCount();
        //bottomBar.setMsgMargin(3,20,30);
        bottomBar.showMsg(3,totalUnreadCount);
        ImmersionBar.with(this)
                .reset()
                .transparentStatusBar()
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(
                new Observer<StatusCode>() {
                    public void onEvent(StatusCode status) {
                        //获取状态的描述
                        String desc = status.getDesc();
                        if (status.wontAutoLogin()) {
                            Toast.makeText(LaunchUi.this, "监测到账号在其他端登录", Toast.LENGTH_SHORT).show();
                            Sputils.getInstance().clear();
                            NIMClient.getService(AuthService.class).logout();
                            Preferences.saveUserToken("");
                            LogoutHelper.logout();
                            Intent intent = new Intent(LaunchUi.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, true);
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }
    /**
     * 注册/注销系统消息未读数变化
     */
    private void registerSystemMessageObservers(boolean register) {
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(
                sysMsgUnreadCountChangedObserver, register);
    }

    private Observer<Integer> sysMsgUnreadCountChangedObserver = (Observer<Integer>) unreadCount -> {
        SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unreadCount);
        ReminderManager.getInstance().updateContactUnreadNum(unreadCount);
       // Log.e("deghjf",unreadCount+"");
    };
    @Override
    public void showData(Versionbean versionbean) {
        content = versionbean.getData().getContent();
        typedata = versionbean.getData().getTypedata();
        refresh_address = versionbean.getData().getRefresh_address();
        if (versionbean.getData().getVersion().contains(RetrofitUrl.version1)){
            Toast.makeText(this, "当前为最新版本", Toast.LENGTH_SHORT).show();
        }else {
            Start();
            popupWindow1.showAtLocation(view.findViewById(R.id.mVersion_quxiao), Gravity.CENTER | Gravity.CENTER, 0, 0);
        }
    }

    //调用相册 相机
    private View view;
    private PopupWindow popupWindow1=new PopupWindow();
    private TextView mVersoion_text,mVersoion_quxiao,mVersoion_sure;
    private void Start() {
        view = LayoutInflater.from(this).inflate(R.layout.version_item, null);
        mVersoion_text = view.findViewById(R.id.mVersion_text);
        mVersoion_quxiao = view.findViewById(R.id.mVersion_quxiao);
        mVersoion_sure = view.findViewById(R.id.mVersion_sure);
        mVersoion_text.setText(content);
        if (typedata.contains("2")){

        }else if (typedata.contains("3")){
            mVersoion_quxiao.setVisibility(View.GONE);
        }
        popupWindow1 = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);

        mVersoion_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
            }
        });
        mVersoion_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(refresh_address);
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }
    @Override
    public void showDataf(Yzmfbean versionbean) {

    }
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        logout();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BusEvent result) {
        int integer = result.getCode();
        if (integer == 100) {
            int totalUnreadCount = NIMClient.getService(MsgService.class).getTotalUnreadCount();
            //bottomBar.setMsgMargin(3,20,30);
            bottomBar.showMsg(3,totalUnreadCount);
        }
    }
    public static final String EXTRA_APP_QUIT = "LaunchUI.logout";

    public void logout() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_APP_QUIT)) {
            intent.removeExtra(EXTRA_APP_QUIT);
            Sputils.getInstance().clear();
            NIMClient.getService(AuthService.class).logout();
            Preferences.saveUserToken("");
            LogoutHelper.logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void requestBasicPermission() {
        MPermission.printMPermissionResult(true, this, BASIC_PERMISSIONS);
        MPermission.with(LaunchUi.this).setRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(BASIC_PERMISSIONS).request();
    }
    /**
     * 查询系统消息未读数
     */
    private void requestSystemMessageUnreadCount() {
        int unread = NIMClient.getService(SystemMessageService.class)
                .querySystemMessageUnreadCountBlock();
        SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unread);
        ReminderManager.getInstance().updateContactUnreadNum(unread);
        if (unread!=0){
            bottomBar.showMsg(3, unread);
        }
    }
    @Override
    public void onUnreadNumChanged(ReminderItem item) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
        }
    }
    //双击两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(LaunchUi.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
                videoFragment.setUserVisibleHint(false);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        try {
            ToastHelper.showToast(this, "授权成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MPermission.printMPermissionResult(false, this, BASIC_PERMISSIONS);
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    @OnMPermissionNeverAskAgain(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        try {
            ToastHelper.showToast(this, "未全部授权，部分功能可能无法正常运行！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MPermission.printMPermissionResult(false, this, BASIC_PERMISSIONS);
    }
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";
        String SYSTEM_HOME_KEY_LONG = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                  videoFragment.setUserVisibleHint(false);
                }
            }else {
                videoFragment.setUserVisibleHint(false);
            }
        }
    };
}
