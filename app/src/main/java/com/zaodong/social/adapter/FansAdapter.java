package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.bean.Fansbean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.Holder> {
    private ArrayList<Fansbean.DataBean> arrayList;
    private Context context;
    private FansItemClick fansItemClick;
    public FansAdapter(ArrayList<Fansbean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fans_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }
    public interface FansItemClick{
        void FansItemClick(View view,int position);
    }
    public void setFansItemClick(FansItemClick fansItemClick){
        this.fansItemClick=fansItemClick;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Fansbean.DataBean dataBean = arrayList.get(position);
        Glide.with(context).load(dataBean.getAvatar()).into(holder.circleImageView);
        holder.mFans_name.setText(dataBean.getNickname()+"");
        if (dataBean.getBio().length()<=0){
            holder.mFans_qianming.setText("这家伙很懒，什么都没留下");
        }else {
            holder.mFans_qianming.setText(dataBean.getBio()+"");
        }
        if (dataBean.getVip().contains("2")||dataBean.getVip().contains("3")){
            holder.mFans_vip.setVisibility(View.VISIBLE);
        }else {
            holder.mFans_vip.setVisibility(View.GONE);
        }
        if (dataBean.getLevel().contains("lv1")&&dataBean.getLevel().length()==3){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_one);
        }else if (dataBean.getLevel().contains("lv2")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_two);
        }else if (dataBean.getLevel().contains("lv3")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_three);
        }else if (dataBean.getLevel().contains("lv4")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_gour);
        }else if (dataBean.getLevel().contains("lv5")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_five);
        }else if (dataBean.getLevel().contains("lv6")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_six);
        }else if (dataBean.getLevel().contains("lv7")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_seven);
        }else if (dataBean.getLevel().contains("lv8")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_eight);
        }else if (dataBean.getLevel().contains("lv9")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_nine);
        }else if (dataBean.getLevel().contains("lv10")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_ten);
        }else if (dataBean.getLevel().contains("lv11")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_tenone);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fansItemClick!=null){
                    fansItemClick.FansItemClick(view, (Integer) view.getTag());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;
        private TextView mFans_name,mFans_qianming;
        private ImageView mYonghu_lavel,mFans_vip;
        public Holder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.mFans_icon);
            mFans_name=itemView.findViewById(R.id.mFans_name);
            mFans_qianming=itemView.findViewById(R.id.mFans_qianming);
            mYonghu_lavel=itemView.findViewById(R.id.mFans_jibie);
            mFans_vip=itemView.findViewById(R.id.mFans_vip);
        }
    }
}
