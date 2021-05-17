package com.zaodong.social.fragment.main.details;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zaodong.social.R;
import com.zaodong.social.activity.ImageActivity;
import com.zaodong.social.activity.VipActivity;
import com.zaodong.social.adapter.ImageAdapter;
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
public class ImageFragment extends Fragment implements Detailsview {
    private RecyclerView recyclerView;
    private View view;
    private ArrayList<String> arrayList=new ArrayList<>();
    private ImageAdapter imageAdapter;
    private IDetailspresenter detailspresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView=view.findViewById(R.id.mImage_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        detailspresenter=new Detailspresenter(this);
        detailspresenter.loadData(Sputils.getInstance().getuser_id(),Sputils.getInstance().getau_id());
        return view;
    }
    @Override
    public void showDataDetails(Detailsbean detailsbean) {
        arrayList.clear();
        if (detailsbean.getData().getPhotoimages().size()>0){
            for (int i = 0; i < detailsbean.getData().getPhotoimages().size(); i++) {
                arrayList.add(detailsbean.getData().getPhotoimages().get(i));
            }
            imageAdapter=new ImageAdapter(arrayList,getContext());
            recyclerView.setAdapter(imageAdapter);
            imageAdapter.notifyDataSetChanged();
            imageAdapter.setOnImageClick(new ImageAdapter.OnImageClick() {
                @Override
                public void OnImageClick(View view, int position) {
                    if (Sputils.getInstance().getvip_u().contains("1")){
                        Start();
                        popupWindow1.showAtLocation(popview.findViewById(R.id.mKai_wai), Gravity.CENTER | Gravity.CENTER, 0, 0);
                    }else {
                        Intent intent = new Intent(getContext(), ImageActivity.class);
                        intent.putExtra("image",arrayList.get(position));
                        startActivity(intent);
                    }
                }
            });
        }
    }
    @Override
    public void showDataDetailsf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
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
