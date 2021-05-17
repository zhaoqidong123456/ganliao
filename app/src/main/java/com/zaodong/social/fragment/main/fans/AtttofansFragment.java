package com.zaodong.social.fragment.main.fans;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zaodong.social.R;
import com.zaodong.social.ZhuboFragment;
import com.zaodong.social.adapter.MyPagerAdapter;
import com.zaodong.social.utils.ModifyTabLayout;
import com.netease.nim.uikit.business.recent.RecentContactsFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AtttofansFragment extends Fragment {
    private View view;
    private ModifyTabLayout mAttTablayout;
    private ViewPager mAttViewpager;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> list_Title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_atttofans, container, false);
        initView();
        return view;
    }
    /**
     * dp转换成px
     */
    public int dp2px( float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private void initView() {
        mAttTablayout = view.findViewById(R.id.mAtt_tablayout);
        mAttTablayout.setViewHeight(dp2px(35));
        mAttTablayout.setBottomLineWidth(dp2px(10));
        mAttTablayout.setBottomLineHeight(dp2px(3));
        mAttTablayout.setBottomLineHeightBgResId(R.color.color_EF709D);
        mAttTablayout.setItemInnerPaddingLeft(dp2px(6));
        mAttTablayout.setItemInnerPaddingRight(dp2px(6));
        mAttTablayout.setmTextColorSelect(ContextCompat.getColor(getContext(),R.color.color_14805E));
        mAttTablayout.setmTextColorUnSelect(ContextCompat.getColor(getContext(),R.color.color_666666));
        mAttTablayout.setTextSize(16);
        mAttViewpager = view.findViewById(R.id.mAtt_viewpager);
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new RecentContactsFragment());
        fragmentList.add(new ZhuboFragment());
        list_Title.add("消息");
        list_Title.add("用户");
        mAttViewpager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(), getContext(), fragmentList, list_Title));
        mAttTablayout.setupWithViewPager(mAttViewpager);//此方法就是让tablayout和ViewPager联动
    }
}
