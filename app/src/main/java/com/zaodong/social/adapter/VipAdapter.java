package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaodong.social.R;
import com.zaodong.social.bean.Vipbean;

import java.util.ArrayList;

public class VipAdapter extends RecyclerView.Adapter<VipAdapter.Holder> {
    private ArrayList<Vipbean.DataBean.ListBean> arrayList;
    private Context context;
    private int defItem = -1;//默认值
    private OnItemListener onItemListener;

    public VipAdapter(ArrayList<Vipbean.DataBean.ListBean> arrayList, Context context) {
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vip_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Vipbean.DataBean.ListBean listBean = arrayList.get(position);
        holder.mVip_title.setText(listBean.getName()+"");
        holder.mVip_price.setText("¥"+listBean.getPricewriting()+"");
        holder.mVip_counts.setText(listBean.getDaypricewriting()+"");
        if (defItem != -1)
        {
            /*点的位置跟点击的textview位置一样设置点击后的不同样式*/
            if (defItem == position)
            {
                /*设置选中的样式*/
                holder.mVip_lin.setBackgroundResource(R.drawable.piece_zhong);
            } else {
               /*其他的变为未选择状态
                 *设置未选中的样式
                */
                holder.mVip_lin.setBackgroundResource(R.drawable.piece_wei);
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
        private TextView mVip_title,mVip_price,mVip_counts;
        private LinearLayout mVip_lin;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mVip_title=itemView.findViewById(R.id.mVip_title);
            mVip_price=itemView.findViewById(R.id.mVip_price);
            mVip_counts=itemView.findViewById(R.id.mVip_counts);
            mVip_lin=itemView.findViewById(R.id.mVip_lin);
        }
    }
}
