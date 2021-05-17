package com.zaodong.social.fragment.main;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zaodong.social.R;
import com.zaodong.social.adapter.MyPagerAdapter;
import com.zaodong.social.fragment.main.rank.CharmFragment;
import com.zaodong.social.fragment.main.rank.RichFragment;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.utils.ModifyTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ModifyTabLayout mRank_tablayout;
    private TextView mRank_day;
    private TextView mRank_week;
    private ViewPager mRank_viewpager;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> list_Title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rank, container, false);
        Sputils.getInstance().setweek("1");
        initview();
        return view;
    }

    private void initview() {
        mRank_tablayout = view.findViewById(R.id.mRank_tablayout);
        mRank_tablayout.setViewHeight(dp2px(35));
        mRank_tablayout.setBottomLineWidth(dp2px(10));
        mRank_tablayout.setBottomLineHeight(dp2px(3));
        mRank_tablayout.setBottomLineHeightBgResId(R.color.white);
        mRank_tablayout.setItemInnerPaddingLeft(dp2px(6));
        mRank_tablayout.setItemInnerPaddingRight(dp2px(6));
        mRank_tablayout.setmTextColorSelect(ContextCompat.getColor(getContext(), R.color.white));
        mRank_tablayout.setmTextColorUnSelect(ContextCompat.getColor(getContext(), R.color.white));
        mRank_tablayout.setTextSize(16);
        mRank_viewpager=view.findViewById(R.id.mRank_viewpager);
        mRank_day=view.findViewById(R.id.mRank_day);
        mRank_day.setOnClickListener(this);
        mRank_week=view.findViewById(R.id.mRank_week);
        mRank_week.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new CharmFragment());
        fragmentList.add(new RichFragment());
        list_Title.add("魅力榜");
        list_Title.add("富豪榜");
        mRank_viewpager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(), getContext(), fragmentList, list_Title));
        mRank_tablayout.setupWithViewPager(mRank_viewpager);//此方法就是让tablayout和ViewPager联动
    }
    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    String data;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mRank_day:
                Sputils.getInstance().setweek("1");
                EventBus.getDefault().post(new BusEvent(10086, data, new Object()));
                mRank_day.setTextColor(Color.parseColor("#EF709D"));
                mRank_day.setBackgroundResource(R.drawable.changtiao);
                mRank_week.setTextColor(Color.parseColor("#c2c2c2"));
                mRank_week.setBackgroundColor(Color.parseColor("#00000000"));
                break;
            case R.id.mRank_week:
                Sputils.getInstance().setweek("2");
                EventBus.getDefault().post(new BusEvent(10086, data, new Object()));
                mRank_day.setTextColor(Color.parseColor("#c2c2c2"));
                mRank_week.setBackgroundResource(R.drawable.changtiao);
                mRank_week.setTextColor(Color.parseColor("#ef709d"));
                mRank_day.setBackgroundColor(Color.parseColor("#00000000"));
                break;
        }
    }
}
