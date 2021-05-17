package com.zaodong.social.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zaodong.social.R;
import com.zaodong.social.activity.start.SousuoActivity;
import com.zaodong.social.adapter.MyPagerAdapter;
import com.zaodong.social.fragment.main.start.ActiveFragment;
import com.example.kulang_darechat.fragment.main.start.AttenFragment;
import com.zaodong.social.fragment.main.start.JokerFragment;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.utils.ModifyTabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ModifyTabLayout mStart_tablayout;
    private ViewPager mStart_viewpager;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> list_Title;
    private ImageView mStart_sousuo;
    String data11;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start2, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initview();
        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BusEvent result) {
        int integer = result.getCode();
        if (integer == 10099) {
            EventBus.getDefault().post(new BusEvent(10098, data11, new Object()));
        }
    }
    /**
     * dp转换成px
     */
    public int dp2px( float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private void initview() {
        mStart_sousuo=view.findViewById(R.id.mStart_sousuo);
        mStart_sousuo.setOnClickListener(this);
        mStart_tablayout = view.findViewById(R.id.mStart_tablayout);
        mStart_tablayout.setViewHeight(dp2px(35));
        mStart_tablayout.setBottomLineWidth(dp2px(10));
        mStart_tablayout.setBottomLineHeight(dp2px(3));
        mStart_tablayout.setBottomLineHeightBgResId(R.color.color_EF709D);
        mStart_tablayout.setItemInnerPaddingLeft(dp2px(6));
        mStart_tablayout.setItemInnerPaddingRight(dp2px(6));
        mStart_tablayout.setmTextColorSelect(ContextCompat.getColor(getContext(),R.color.color_14805E));
        mStart_tablayout.setmTextColorUnSelect(ContextCompat.getColor(getContext(),R.color.color_666666));
        mStart_tablayout.setTextSize(16);
        mStart_viewpager = view.findViewById(R.id.mStart_viewpager);
        mStart_viewpager.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new ActiveFragment());
        fragmentList.add(new JokerFragment());
        fragmentList.add(new AttenFragment());
        list_Title.add("活跃");
        list_Title.add("王牌");
        list_Title.add("关注");
        mStart_viewpager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(), getContext(), fragmentList, list_Title));
        mStart_tablayout.setupWithViewPager(mStart_viewpager);//此方法就是让tablayout和ViewPager联动
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mStart_sousuo:
                Intent intent = new Intent(getContext(), SousuoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
