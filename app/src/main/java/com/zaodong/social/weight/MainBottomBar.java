package com.zaodong.social.weight;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;

import java.util.ArrayList;
import java.util.List;

public class MainBottomBar extends FrameLayout implements View.OnClickListener {
    private Context mContext;

    private int index = 2;//默认选中中间，下标从0开始
    private int lastIndex = 2; //记录上一次标签

    private LinearLayout mLinSolution, mLinService, mLinAspect, mLinAppliance, mLinMy;
    private ImageView mImgSolution, mImgService, mImgAspect, mImgAppliance, mImgMy;
    private TextView mTxtSolution, mTxtService, mTxtAspect, mTxtAppliance, mTxtMy;

    private List<ImageView> imageViewList = new ArrayList<>();
    private List<TextView> textViewList = new ArrayList<>();
    private int[] imgSelects = {R.mipmap.nav_icon_home_c, R.mipmap.nav_icon_ranking, R.mipmap.nav_icon_video_cai, R.mipmap.nav_icon_news_cai, R.mipmap.nav_icon_my_cai};
    private int[] imgNormal = {R.mipmap.nav_icon_home, R.mipmap.nav_icon_ranking_h, R.mipmap.nav_icon_video, R.mipmap.nav_icon_news, R.mipmap.nav_icon_my};
    private OnTabItemSelectedListener listener;

    public void setListener(OnTabItemSelectedListener listener) {
        this.listener = listener;
    }

    public MainBottomBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MainBottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MainBottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


    private void initView(Context context) {
        this.mContext = context;
        View view = inflate(mContext, R.layout.main_bottom_bar, this);
        mLinSolution = view.findViewById(R.id.tab_solution_lin);
        mLinService = view.findViewById(R.id.tab_service_lin);
        mLinAspect = view.findViewById(R.id.tab_aspect_lin);
        mLinAppliance = view.findViewById(R.id.tab_appliance_lin);
        mLinMy = view.findViewById(R.id.tab_my_lin);

        mImgSolution = view.findViewById(R.id.tab_solution_img);
        mImgService = view.findViewById(R.id.tab_service_img);
        mImgAspect = view.findViewById(R.id.tab_aspect_img);
        mImgAppliance = view.findViewById(R.id.tab_appliance_img);
        mImgMy = view.findViewById(R.id.tab_my_img);
        imageViewList.add(mImgSolution);
        imageViewList.add(mImgService);
        imageViewList.add(mImgAspect);
        imageViewList.add(mImgAppliance);
        imageViewList.add(mImgMy);

        mTxtSolution = view.findViewById(R.id.tab_solution_txt);
        mTxtService = view.findViewById(R.id.tab_service_txt);
        mTxtAspect = view.findViewById(R.id.tab_aspect_txt);
        mTxtAppliance = view.findViewById(R.id.tab_appliance_txt);
        mTxtMy = view.findViewById(R.id.tab_my_txt);
        textViewList.add(mTxtSolution);
        textViewList.add(mTxtService);
        textViewList.add(mTxtAspect);
        textViewList.add(mTxtAppliance);
        textViewList.add(mTxtMy);
        bindClick();
        selectText(lastIndex, index);
        selectImg(lastIndex, index);
    }

    /**
     * 绑定点击
     */
    private void bindClick() {
        mLinSolution.setOnClickListener(this);
        mLinService.setOnClickListener(this);
        mLinAspect.setOnClickListener(this);
        mLinAppliance.setOnClickListener(this);
        mLinMy.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_solution_lin:
                index = 0;
                break;
            case R.id.tab_service_lin:
                index = 1;
                break;
            case R.id.tab_aspect_lin:
                index = 2;
                break;
            case R.id.tab_appliance_lin:
                index = 3;
                break;
            case R.id.tab_my_lin:
                index = 4;
                break;
        }

        setSelect(index);
        if (listener == null) {
            return;
        }
        listener.onTabItemSelected(index);
    }


    /**
     * 改字体样式
     */
    private void selectText(int lastIndex, int index) {
        textViewList.get(lastIndex).setTextColor(Color.parseColor("#adadad"));
        textViewList.get(index).setTextColor(Color.parseColor("#EF709D"));

    }

    /**
     * 改图标样式
     */
    private void selectImg(int lastIndex, int index) {
        int normal = imgNormal[lastIndex];
        int select = imgSelects[index];
        Glide.with(mContext).load(normal).into(imageViewList.get(lastIndex));
        Glide.with(mContext).load(select).into(imageViewList.get(index));
    }

    /**
     * 设置选中项
     */
    public void setSelect(int index) {
        selectText(lastIndex, index);
        selectImg(lastIndex, index);
        lastIndex = index;

    }

    /**
     * 对外接口获取选中下标
     */
    public interface OnTabItemSelectedListener {
        void onTabItemSelected(int position);
    }


}
