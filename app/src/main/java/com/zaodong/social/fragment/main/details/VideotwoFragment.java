package com.zaodong.social.fragment.main.details;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaodong.social.R;
import com.zaodong.social.activity.VideoActivity;
import com.zaodong.social.activity.VipActivity;
import com.zaodong.social.adapter.VideoAdapter;
import com.zaodong.social.bean.Detailsbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.start.Detailspresenter;
import com.zaodong.social.presenter.start.IDetailspresenter;
import com.zaodong.social.view.Detailsview;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideotwoFragment extends Fragment implements Detailsview {
    private View view;
    private RecyclerView mVideotwo_recy;
    private ArrayList<Detailsbean.DataBean.VideoimagesBean> arrayList = new ArrayList<>();
    private TextView mVideo_two_xian;
    private VideoAdapter videoAdapter;
    private IDetailspresenter detailspresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_videotwo, container, false);
        detailspresenter=new Detailspresenter(this);
        initview();
        detailspresenter.loadData(Sputils.getInstance().getuser_id(),Sputils.getInstance().getau_id());
        return view;
    }

    private void initview() {
        mVideotwo_recy=view.findViewById(R.id.mVideotwo_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mVideotwo_recy.setLayoutManager(gridLayoutManager);
        mVideo_two_xian=view.findViewById(R.id.mVideo_two_xian);
    }

    @Override
    public void showDataDetails(Detailsbean detailsbean) {
        arrayList.clear();
        arrayList.addAll(detailsbean.getData().getVideoimages());
        if (arrayList.size() > 0) {
            mVideotwo_recy.setVisibility(View.VISIBLE);
            mVideo_two_xian.setVisibility(View.GONE);
            videoAdapter=new VideoAdapter(arrayList,getContext());
            mVideotwo_recy.setAdapter(videoAdapter);
            videoAdapter.notifyDataSetChanged();
            videoAdapter.setOnvideoClick(new VideoAdapter.OnvideoClick() {
                @Override
                public void OnvideoClick(View view, int position) {
                    if (Sputils.getInstance().getvip_u().contains("1")){
                        Start();
                        popupWindow1.showAtLocation(popview.findViewById(R.id.mKai_wai), Gravity.CENTER | Gravity.CENTER, 0, 0);
                    }else {
                        Intent intent = new Intent(getContext(), VideoActivity.class);
                        intent.putExtra("video",arrayList.get(position).getUrl());
                        startActivity(intent);
                    }
                }
            });
        } else {
            mVideotwo_recy.setVisibility(View.GONE);
            mVideo_two_xian.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void showDataDetailsf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    private View popview;
    private PopupWindow popupWindow1=new PopupWindow();
    private TextView mKai_liji;
    private RelativeLayout mKai_wai;
    //调用相册 相机
    private void Start() {
        popview = LayoutInflater.from(getContext()).inflate(R.layout.kai_vip_item, null);
        mKai_liji=popview.findViewById(R.id.mKai_liji);
        mKai_wai=popview.findViewById(R.id.mKai_wai);
        popupWindow1 = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);
        mKai_wai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
            }
        });
        mKai_liji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), VipActivity.class);
                startActivity(intent);
                popupWindow1.dismiss();
            }
        });
    }
}
