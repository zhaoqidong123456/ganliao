package com.zaodong.social.fragment.main;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaodong.social.R;
import com.zaodong.social.adapter.MyPagerAdapter;
import com.zaodong.social.bean.EventBusBean;
import com.zaodong.social.fragment.main.video.VideoFragment;
import com.zaodong.social.utils.ModifyTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class ZongVideoFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ModifyTabLayout mStart_tablayout;
    private ViewPager mStart_viewpager;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> list_Title;
    private boolean isCreate = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_zong_video, container, false);
        initview();
        return view;
    }

    private void initview() {
        mStart_tablayout = view.findViewById(R.id.mZong_tab);
        mStart_tablayout.setViewHeight(dp2px(35));
        mStart_tablayout.setBottomLineWidth(dp2px(10));
        mStart_tablayout.setBottomLineHeight(dp2px(3));
        mStart_tablayout.setBottomLineHeightBgResId(R.color.color_EF709D);
        mStart_tablayout.setItemInnerPaddingLeft(dp2px(6));
        mStart_tablayout.setItemInnerPaddingRight(dp2px(6));
        mStart_tablayout.setmTextColorSelect(ContextCompat.getColor(getContext(), R.color.white));
        mStart_tablayout.setmTextColorUnSelect(ContextCompat.getColor(getContext(), R.color.white));
        mStart_tablayout.setTextSize(16);
        mStart_viewpager = view.findViewById(R.id.mZong_viewpager);


    }

    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setCode(EventBusBean.EVENT_FRAGMENT_HIDEN);
            EventBus.getDefault().post(eventBusBean);
        }else {
            if (!isCreate){
                fragmentList = new ArrayList<>();
                list_Title = new ArrayList<>();
                fragmentList.add(new VideoFragment(0));
                fragmentList.add(new VideoFragment(1));
                list_Title.add("在线");
                list_Title.add("推荐");
                mStart_viewpager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(), getContext(), fragmentList, list_Title));
                mStart_tablayout.setupWithViewPager(mStart_viewpager);
                isCreate = true;

            }
        }
    }

}