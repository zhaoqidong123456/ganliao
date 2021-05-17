package com.zaodong.social.fragment.main.details;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zaodong.social.R;
import com.zaodong.social.activity.IntiActivity;
import com.zaodong.social.adapter.BiaoAdapter;
import com.zaodong.social.adapter.IntroAdapterqin;
import com.zaodong.social.bean.Detailsbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.start.Detailspresenter;
import com.zaodong.social.presenter.start.IDetailspresenter;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.view.Detailsview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment implements Detailsview, View.OnClickListener {
    private View rootView;
    private RecyclerView mIntro_recy;
    private TextView mIntro_boy;
    private TextView mIntro_id;
    private TextView mIntro_city;
    private TextView mIntro_qianming;
    private RecyclerView mIntro_recy_biao;
    private IDetailspresenter detailspresenter;
    private IntroAdapterqin introAdapterqin;
    private ArrayList<Detailsbean.DataBean.IntimateBean> arrayList=new ArrayList<>();
    private BiaoAdapter biaoAdapter;
    private LinearLayout mIntro_qinmi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_intro, container, false);
        detailspresenter=new Detailspresenter(this);
        initview();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        detailspresenter.loadData(Sputils.getInstance().getuser_id(),Sputils.getInstance().getau_id());
        return rootView;
    }
    private void initview() {
        mIntro_qinmi=rootView.findViewById(R.id.mIntro_qinmi);
        mIntro_qinmi.setOnClickListener(this);
        mIntro_recy = (RecyclerView) rootView.findViewById(R.id.mIntro_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mIntro_recy.setLayoutManager(linearLayoutManager);
        mIntro_boy = (TextView) rootView.findViewById(R.id.mIntro_boy);
        mIntro_id = (TextView) rootView.findViewById(R.id.mIntro_id);
        mIntro_city = (TextView) rootView.findViewById(R.id.mIntro_city);
        mIntro_qianming = (TextView) rootView.findViewById(R.id.mIntro_qianming);
        mIntro_recy_biao = (RecyclerView) rootView.findViewById(R.id.mIntro_recy_biao);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        mIntro_recy_biao.setLayoutManager(linearLayoutManager1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BusEvent result) {
        int integer = result.getCode();
        if (integer == 10087) {
            detailspresenter.loadData(Sputils.getInstance().getuser_id(),Sputils.getInstance().getau_id());
        }
    }


    @Override
    public void showDataDetails(final Detailsbean detailsbean) {
        arrayList.clear();
        arrayList.addAll(detailsbean.getData().getIntimate());
        introAdapterqin=new IntroAdapterqin(arrayList,getContext());
        mIntro_recy.setAdapter(introAdapterqin);
        introAdapterqin.notifyDataSetChanged();
        introAdapterqin.setOnItemClick(new IntroAdapterqin.OnItemClick() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), IntiActivity.class);
                startActivity(intent);
            }
        });
        String gender = detailsbean.getData().getGender()+"";
        if (gender.contains("0")){
            mIntro_boy.setText("性别："+"女");
        }else {
            mIntro_boy.setText("性别："+"男");
        }
        mIntro_id.setText("ID:"+detailsbean.getData().getUser_id()+"");
        mIntro_city.setText(detailsbean.getData().getCity()+"");
        mIntro_qianming.setText(detailsbean.getData().getBio()+"");
        Object label = detailsbean.getData().getLabel();
        List<String> result = new ArrayList<>();
        if (label instanceof ArrayList<?>) {
            for (Object o : (List<?>) label) {
                result.add(String.class.cast(o));
            }
        }
        biaoAdapter=new BiaoAdapter(result,getContext());
        mIntro_recy_biao.setAdapter(biaoAdapter);
        biaoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataDetailsf(Yzmfbean yzmfbean) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mIntro_qinmi:
                Intent intent = new Intent(getContext(), IntiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
