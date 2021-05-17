package com.zaodong.social.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;

import java.util.ArrayList;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.Holder> {
    private ArrayList<GiftsAttachmentBean> arrayList;
    private Context context;
    private int defItem = -1;//默认值
    private OnItemListener onItemListener;
    public GiftAdapter(ArrayList<GiftsAttachmentBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public void setOnItemListener(OnItemListener onItemListener)
    {
        this.onItemListener = onItemListener;
    }

    //设置点击事件
    public interface OnItemListener
    {
        void onClick(View v, int pos);
    }
    //获取点击的位置
    public void setDefSelect(int position)
    {
        this.defItem = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_gift_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        GiftsAttachmentBean dataBean = arrayList.get(position);
        Glide.with(context).load(dataBean.getPicimage()).into(holder.mLiwu_image);
        holder.mLiwu_counts.setText(dataBean.getPricewriting()+"");
        holder.mliwu_name.setText(dataBean.getName()+"");
        if (defItem != -1)
        {
            /*点的位置跟点击的textview位置一样设置点击后的不同样式*/
            if (defItem == position)
            {
                /*设置选中的样式*/
                holder.mLiwu_lin.setBackgroundResource(R.drawable.piece_zhong);
            } else {
               /*其他的变为未选择状态
                 *设置未选中的样式
                */
                holder.mLiwu_lin.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener!=null){
                    onItemListener.onClick(view,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private LinearLayout mLiwu_lin;
        private ImageView mLiwu_image;
        private TextView mliwu_name,mLiwu_counts;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mLiwu_lin=itemView.findViewById(R.id.mLiwu_lin);
            mLiwu_image=itemView.findViewById(R.id.mLiwu_image);
            mLiwu_counts=itemView.findViewById(R.id.mLiwu_counts);
            mliwu_name=itemView.findViewById(R.id.mliwu_name);

        }
    }
}
