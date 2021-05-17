package com.zaodong.social.fragment.main.rank;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.RankAdapter;
import com.zaodong.social.bean.Rankbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.rank_p.IRankpresenter;
import com.zaodong.social.presenter.rank_p.Rankpresenter;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.view.Rankview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RichFragment extends Fragment implements View.OnClickListener, Rankview {

    private CircleImageView mRich_ya;
    private TextView mRich_ya_name;
    private TextView mRich_ya_counts;
    private CircleImageView mRich_guan;
    private TextView mRich_guan_name;
    private TextView mRich_guan_counts;
    private CircleImageView mRich_ji;
    private TextView mRich_ji_name;
    private TextView mRich_ji_counts;
    private View rootView;
    private IRankpresenter rankpresenter;
    private RecyclerView mRich_recy;

    private RankAdapter rankAdapter;
    private ArrayList<Rankbean.DataBean> arrayList1=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_rich, container, false);
        rankpresenter=new Rankpresenter(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initview();
        return rootView;
    }
    private void initview() {
        mRich_recy=rootView.findViewById(R.id.mRich_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRich_recy.setLayoutManager(linearLayoutManager);
        mRich_ya = (CircleImageView) rootView.findViewById(R.id.mRich_ya);
        mRich_ya.setOnClickListener(this);
        mRich_ya_name = (TextView) rootView.findViewById(R.id.mRich_ya_name);
        mRich_ya_counts = (TextView) rootView.findViewById(R.id.mRich_ya_counts);
        mRich_guan = (CircleImageView) rootView.findViewById(R.id.mRich_guan);
        mRich_guan.setOnClickListener(this);
        mRich_guan_name = (TextView) rootView.findViewById(R.id.mRich_guan_name);
        mRich_guan_counts = (TextView) rootView.findViewById(R.id.mRich_guan_counts);
        mRich_ji = (CircleImageView) rootView.findViewById(R.id.mRich_ji);
        mRich_ji.setOnClickListener(this);
        mRich_ji_name = (TextView) rootView.findViewById(R.id.mRich_ji_name);
        mRich_ji_counts = (TextView) rootView.findViewById(R.id.mRich_ji_counts);
        rankpresenter.loadData(Sputils.getInstance().getuser_id(),"2",Sputils.getInstance().getweek());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BusEvent result) {
        int integer = result.getCode();
        if (integer == 10086) {
            rankpresenter.loadData(Sputils.getInstance().getuser_id(),"2",Sputils.getInstance().getweek());

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.mRich_guan:
//                if (arrayList.size()>0){
//                    Intent intent1 = new Intent(getContext(), DetailsActivity.class);
//                    intent1.putExtra(DetailsActivity.DETAIL_ID,arrayList.get(0).getUser_id()+"");
//                    startActivity(intent1);
//                }else {
//                }
//                break;
//            case R.id.mRich_ya:
//                if (arrayList.size()>1){
//                    Intent intent = new Intent(getContext(), DetailsActivity.class);
//                    intent.putExtra(DetailsActivity.DETAIL_ID,arrayList.get(1).getUser_id()+"");
//                    startActivity(intent);
//                }else {
//
//                }
//                break;
//            case R.id.mRich_ji:
//                if (arrayList.size()>2){
//                    Intent intent = new Intent(getContext(), DetailsActivity.class);
//                    intent.putExtra(DetailsActivity.DETAIL_ID,arrayList.get(2).getUser_id()+"");
//                    startActivity(intent);
//                }else {
//                }
//                break;
        }
    }
    private ArrayList<Rankbean.DataBean> arrayList=new ArrayList<>();
    @Override
    public void showRank(Rankbean rankbean) {
        arrayList.clear();
        arrayList.addAll(rankbean.getData());
        arrayList1.clear();
        List<Rankbean.DataBean> data = rankbean.getData();
        if (data.size()>3){
            for (int i = 0; i <data.size(); i++) {
                if (i<=2){

                }else {
                    arrayList1.add(data.get(i));
                }
            }
        }
        rankAdapter=new RankAdapter(arrayList1,getContext());
        mRich_recy.setAdapter(rankAdapter);
        rankAdapter.notifyDataSetChanged();
//        rankAdapter.setOnItemRank(new RankAdapter.OnItemRank() {
//            @Override
//            public void OnItemRank(View view, int position) {
//                Intent intent = new Intent(getContext(), DetailsActivity.class);
//                intent.putExtra(DetailsActivity.DETAIL_ID,arrayList1.get(position).getUser_id()+"");
//                startActivity(intent);
//            }
//        });
        if (arrayList.size()>2){
            Glide.with(getContext()).load(rankbean.getData().get(0).getAvatar()).into(mRich_guan);
            mRich_guan_name.setText(rankbean.getData().get(0).getNickname()+"");
            mRich_guan_counts.setText(rankbean.getData().get(0).getValue()+"");

            Glide.with(getContext()).load(rankbean.getData().get(1).getAvatar()).into(mRich_ya);
            mRich_ya_name.setText(rankbean.getData().get(1).getNickname()+"");
            mRich_ya_counts.setText(rankbean.getData().get(1).getValue()+"");

            Glide.with(getContext()).load(rankbean.getData().get(2).getAvatar()).into(mRich_ji);
            mRich_ji_name.setText(rankbean.getData().get(2).getNickname()+"");
            mRich_ji_counts.setText(rankbean.getData().get(2).getValue()+"");


        }else if (arrayList.size()==1){
            Glide.with(getContext()).load(rankbean.getData().get(0).getAvatar()).into(mRich_guan);
            mRich_guan_name.setText(rankbean.getData().get(0).getNickname()+"");
            mRich_guan_name.setText(rankbean.getData().get(0).getValue()+"");

            Glide.with(getContext()).load("").into(mRich_ya);
            mRich_ya_name.setText("昵称");
            mRich_ya_counts.setText("0");

            Glide.with(getContext()).load("").into(mRich_ji);
            mRich_ji_name.setText("昵称");
            mRich_ji_counts.setText("0");
        }else if (arrayList.size()==2){
            Glide.with(getContext()).load(rankbean.getData().get(0).getAvatar()).into(mRich_guan);
            mRich_guan_name.setText(rankbean.getData().get(0).getNickname()+"");
            mRich_guan_counts.setText(rankbean.getData().get(0).getValue()+"");

            Glide.with(getContext()).load(rankbean.getData().get(1).getAvatar()).into(mRich_ya);
            mRich_ya_name.setText(rankbean.getData().get(1).getNickname()+"");
            mRich_ya_counts.setText(rankbean.getData().get(1).getValue()+"");

            Glide.with(getContext()).load("").into(mRich_ji);
            mRich_ji_name.setText("昵称");
            mRich_ji_counts.setText("0");

        }else if (arrayList.size()==0){
            Glide.with(getContext()).load("").into(mRich_guan);
            mRich_guan_name.setText("昵称");
            mRich_guan_counts.setText("0");

            Glide.with(getContext()).load("").into(mRich_ya);
            mRich_ya_name.setText("昵称");
            mRich_ya_counts.setText("0");

            Glide.with(getContext()).load("").into(mRich_ji);
            mRich_ji_name.setText("昵称");
            mRich_ji_counts.setText("0");
        }
    }

    @Override
    public void showRankf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
