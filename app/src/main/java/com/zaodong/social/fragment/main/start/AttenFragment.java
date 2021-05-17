package com.example.kulang_darechat.fragment.main.start;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.zaodong.social.R;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.ActiveAdapter;
import com.zaodong.social.bean.Fansbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.fans.Fanspresenter;
import com.zaodong.social.presenter.fans.IFanspresenter;
import com.zaodong.social.utils.BusEvent;
import com.zaodong.social.view.Fansview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttenFragment extends Fragment implements Fansview {

    private RecyclerView mAtten_recy;
    private SwipeRefreshLayout mAtten_sw;
    private View view;
    private ActiveAdapter activeAdapter;
    private IFanspresenter fanspresenter;
    private ArrayList<Fansbean.DataBean> arrayList = new ArrayList<>();
    private RelativeLayout mAtt_null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_atten, container, false);
        initview();
        fanspresenter = new Fanspresenter(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        fanspresenter.loadData(Sputils.getInstance().getuser_id(), "1", "1", "100");
        return view;
    }

    private void initview() {
        mAtt_null=view.findViewById(R.id.mAtt_null);
        mAtten_sw = view.findViewById(R.id.mAtten_sw);
        mAtten_sw.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mAtten_sw.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mAtten_sw.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mAtten_sw.setSize(SwipeRefreshLayout.LARGE);
        //设置下拉刷新的监听
        mAtten_sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fanspresenter.loadData(Sputils.getInstance().getuser_id(), "1", "1", "100");
                mAtten_sw.setRefreshing(false);
            }
        });
        mAtten_recy = view.findViewById(R.id.mAtten_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mAtten_recy.setLayoutManager(gridLayoutManager);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BusEvent result) {
        int integer = result.getCode();
        if (integer == 10098) {
            fanspresenter.loadData(Sputils.getInstance().getuser_id(), "1", "1", "100");
        }
    }
    @Override
    public void showfans(final Fansbean fansbean) {
        arrayList.clear();
        arrayList.addAll(fansbean.getData());
        if (arrayList.size()>0){
            mAtten_sw.setVisibility(View.VISIBLE);
            mAtt_null.setVisibility(View.GONE);
            activeAdapter = new ActiveAdapter(arrayList, getContext());
            mAtten_recy.setAdapter(activeAdapter);
            activeAdapter.notifyDataSetChanged();
            activeAdapter.setOnItemClickatt(new ActiveAdapter.OnItemClickatt() {
                @Override
                public void OnItemClickatt(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra(DetailsActivity.DETAIL_ID,fansbean.getData().get(position).getUser_id()+"");
                    startActivity(intent);
                }
            });
        }else {
            mAtten_sw.setVisibility(View.GONE);
            mAtt_null.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showFansf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }
}
