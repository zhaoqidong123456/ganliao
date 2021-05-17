package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaodong.social.R;

import java.util.List;

public class BiaoAdapter extends RecyclerView.Adapter<BiaoAdapter.Holder> {
    private List<String> list;
    private Context context;

    public BiaoAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.biao_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String s = list.get(position);
        if (position%3==0){
            holder.mIntro_biao.setText(s);
            holder.mIntro_biao.setBackgroundResource(R.drawable.biao_three);
        }else if (position%2==0){
            holder.mIntro_biao.setText(s);
            holder.mIntro_biao.setBackgroundResource(R.drawable.biao_two);
        }else if (position%1==0){
            holder.mIntro_biao.setText(s);
            holder.mIntro_biao.setBackgroundResource(R.drawable.buzaixian);
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView mIntro_biao;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mIntro_biao=itemView.findViewById(R.id.mIntro_biao);
        }
    }
}
