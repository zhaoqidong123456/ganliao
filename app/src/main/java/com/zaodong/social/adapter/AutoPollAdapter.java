package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.netease.nim.avchatkit.common.recyclerview.holder.BaseViewHolder;
import com.zaodong.social.R;
import com.zaodong.social.bean.Paobean;

import java.util.ArrayList;
import java.util.List;

public class AutoPollAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private final Context mContext;
    private final ArrayList<Paobean.DataBean> mData;
    public AutoPollAdapter(Context context, ArrayList<Paobean.DataBean> mData) {
        this.mContext = context;
        this.mData = mData;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pao_vip, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Paobean.DataBean dataBean = mData.get(position % mData.size());
        holder.setText(R.id.mPao_name,dataBean.getNickname()+"");
        holder.setText(R.id.mPao_date,dataBean.getValue()+"");
    }
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
