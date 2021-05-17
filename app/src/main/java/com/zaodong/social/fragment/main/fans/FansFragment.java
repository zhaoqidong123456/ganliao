package com.zaodong.social.fragment.main.fans;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zaodong.social.R;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.FansAdapter;
import com.zaodong.social.bean.Fansbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.fans.Fanspresenter;
import com.zaodong.social.presenter.fans.IFanspresenter;
import com.zaodong.social.view.Fansview;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FansFragment extends Fragment implements Fansview {


    private View view;
    private RecyclerView recyclerView;
    private IFanspresenter fanspresenter;
    private RelativeLayout mAtt_null;
    private FansAdapter fansAdapter;
    private ArrayList<Fansbean.DataBean> arrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fans, container, false);
        initview();
        return view;
    }

    private void initview() {
        mAtt_null=view.findViewById(R.id.mfans_null);
        recyclerView=view.findViewById(R.id.mfans_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        fanspresenter=new Fanspresenter(this);
        fanspresenter.loadData(Sputils.getInstance().getuser_id(),"2","1","100");
    }

    @Override
    public void showfans(Fansbean fansbean) {
        arrayList.clear();
        arrayList.addAll(fansbean.getData());
        if (arrayList.size()>0){
            mAtt_null.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            fansAdapter=new FansAdapter(arrayList,getContext());
            recyclerView.setAdapter(fansAdapter);
            fansAdapter.notifyDataSetChanged();
            fansAdapter.setFansItemClick(new FansAdapter.FansItemClick() {
                @Override
                public void FansItemClick(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra(DetailsActivity.DETAIL_ID,fansbean.getData().get(position).getUser_id()+"");
                    startActivity(intent);
                }
            });
        }else {
            mAtt_null.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }
    @Override
    public void showFansf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
