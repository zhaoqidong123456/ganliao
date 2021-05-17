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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zaodong.social.R;
import com.zaodong.social.bean.Activebean;

import java.util.ArrayList;

public class ActiveAdapter2 extends RecyclerView.Adapter<ActiveAdapter2.Holder>{
    private ArrayList<Activebean.DataBean.ItemBean> arrayList;
    private Context context;

    public ActiveAdapter2(ArrayList<Activebean.DataBean.ItemBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.active_item_two,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Activebean.DataBean.ItemBean itemBean = arrayList.get(position);
        holder.mStart_two_biao.setText(itemBean.getLabel().get(0)+"");
        holder.mStart_one_piece.setText(itemBean.getCallwriting()+"");
        if (itemBean.getOnline().contains("1")){
            holder.mStart_one_time.setBackgroundResource(R.drawable.zaixian);
        }else {
            holder.mStart_one_time.setBackgroundResource(R.drawable.buzaixian);
        }
        RoundedCorners roundedCorners = new RoundedCorners(25);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);
        Glide.with(context)
                .asBitmap()
                .load(itemBean.getBackgroundimages())
                .apply(coverRequestOptions)
                .into(holder.mStart_one_icon);
        holder.mStart_one_name.setText(itemBean.getNickname()+"");
        holder.mStart_two_level.setText(itemBean.getLevel()+"");
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private TextView mStart_two_biao;
        private TextView mStart_one_piece,mStart_one_time,mStart_one_name;
        private ImageView mStart_one_boda;
        private ImageView mStart_one_icon;
        private TextView mStart_two_level;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mStart_one_icon=itemView.findViewById(R.id.mStart_two_image);
            mStart_one_piece=itemView.findViewById(R.id.mStart_two_piece);
            mStart_one_time=itemView.findViewById(R.id.mStart_two_time);
            mStart_one_boda=itemView.findViewById(R.id.mStart_two_boda);
            mStart_two_biao=itemView.findViewById(R.id.mStart_two_biao);
            mStart_one_name=itemView.findViewById(R.id.mStart_two_name);
            mStart_two_level=itemView.findViewById(R.id.mStart_two_level);

        }
    }
}
