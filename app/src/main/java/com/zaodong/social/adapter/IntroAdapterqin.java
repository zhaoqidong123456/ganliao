package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.bean.Detailsbean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class IntroAdapterqin extends RecyclerView.Adapter<IntroAdapterqin.Holder>{
    private ArrayList<Detailsbean.DataBean.IntimateBean> arrayList;
    private Context context;
    private OnItemClick onItemClick;

    public IntroAdapterqin(ArrayList<Detailsbean.DataBean.IntimateBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public interface OnItemClick{
        void OnItemClick(View view,int position);
    }
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.qinmi,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Detailsbean.DataBean.IntimateBean intimateBean = arrayList.get(position);
        Glide.with(context).load(intimateBean.getAvatar()).into(holder.mIntro_image);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick!=null){
                    onItemClick.OnItemClick(view, (Integer) view.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private CircleImageView mIntro_image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mIntro_image=itemView.findViewById(R.id.mIntro_image);
        }
    }
}
