package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.bean.Rankbean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.Holder> {
    private ArrayList<Rankbean.DataBean> arrayList;
    private Context context;
    private OnItemRank onItemRank;

    public RankAdapter(ArrayList<Rankbean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public interface OnItemRank{
        void OnItemRank(View view,int position);
    }
    public void setOnItemRank(OnItemRank onItemRank){
        this.onItemRank=onItemRank;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Rankbean.DataBean dataBean = arrayList.get(position);
        holder.mMate_suoyin.setText(position+4+"");
        Glide.with(context).load(dataBean.getAvatar()).into(holder.circleImageView);
        holder.mMate_counts.setText(dataBean.getValue()+"");
        holder.mMate_jibie.setText(dataBean.getLevel()+"");
        holder.mMate_name.setText(dataBean.getNickname()+"");
        String vip = dataBean.getVip()+"";
        if (vip.contains("1")){
            holder.mMate_vip.setVisibility(View.GONE);
        }else {
            holder.mMate_vip.setVisibility(View.VISIBLE);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemRank!=null){
                    onItemRank.OnItemRank(view, (Integer) view.getTag());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private TextView mMate_suoyin,mMate_name,mMate_jibie,mMate_counts,mMate_vip;
        private CircleImageView circleImageView;
        private LinearLayout mMate_bei;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mMate_suoyin = itemView.findViewById(R.id.mMate_suoyin);
            mMate_name = itemView.findViewById(R.id.mMate_name);
            mMate_jibie = itemView.findViewById(R.id.mMate_jibie);
            mMate_counts = itemView.findViewById(R.id.mMate_counts);
            circleImageView = itemView.findViewById(R.id.mMate_icon);
            mMate_bei = itemView.findViewById(R.id.mMate_bei);
            mMate_vip = itemView.findViewById(R.id.mMate_vip);
        }
    }
}
