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
import com.zaodong.social.bean.Matebean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MateAdapter extends RecyclerView.Adapter<MateAdapter.Holder> {
    private ArrayList<Matebean.DataBean> arrayList;
    private Context context;

    public MateAdapter(ArrayList<Matebean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mate_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Matebean.DataBean dataBean = arrayList.get(position);
        holder.mMate_suoyin.setText(position+1+"");
        Glide.with(context).load(dataBean.getAvatar()).into(holder.circleImageView);
        if (position==0){
            holder.mMate_bei.setBackgroundResource(R.mipmap.ranking_img_crowna_one);
            holder.mMate_bei.setVisibility(View.VISIBLE);
        }else if (position==1){
            holder.mMate_bei.setBackgroundResource(R.mipmap.ranking_img_crowna_two);
            holder.mMate_bei.setVisibility(View.VISIBLE);
        }else if (position==2){
            holder.mMate_bei.setBackgroundResource(R.mipmap.ranking_img_crowna_three);
            holder.mMate_bei.setVisibility(View.VISIBLE);
        }else {
            holder.mMate_bei.setVisibility(View.GONE);
        }
        holder.mMate_counts.setText(dataBean.getPrice()+"");
        holder.mMate_jibie.setText(dataBean.getLevel()+"");
        holder.mMate_name.setText(dataBean.getNickname()+"");
        String vip = dataBean.getVip()+"";
        if (vip.contains("1")){
            holder.mMate_vip.setVisibility(View.GONE);
        }else {
            holder.mMate_vip.setVisibility(View.VISIBLE);
        }
        holder.itemView.setTag(position);
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
            mMate_suoyin=itemView.findViewById(R.id.mMate_suoyin);
            mMate_name=itemView.findViewById(R.id.mMate_name);
            mMate_jibie=itemView.findViewById(R.id.mMate_jibie);
            mMate_counts=itemView.findViewById(R.id.mMate_counts);
            circleImageView=itemView.findViewById(R.id.mMate_icon);
            mMate_bei=itemView.findViewById(R.id.mMate_bei);
            mMate_vip=itemView.findViewById(R.id.mMate_vip);
        }
    }
}
