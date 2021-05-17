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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zaodong.social.R;
import com.zaodong.social.bean.Madoubean;

import java.util.ArrayList;

public class ActiveAdapter4 extends RecyclerView.Adapter<ActiveAdapter4.Holder>{
    private ArrayList<Madoubean.DataBean> arrayList;
    private Context context;
    private OnItemClickma onItemClickma;

    public ActiveAdapter4(ArrayList<Madoubean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ActiveAdapter4.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.active_four_item,parent,false);
        ActiveAdapter4.Holder holder = new ActiveAdapter4.Holder(view);
        return holder;
    }
    public interface OnItemClickma{
        void OnItemClickma(View view,int position);
    }
    public void setOnItemClickma(OnItemClickma onItemClickma){
        this.onItemClickma=onItemClickma;
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveAdapter4.Holder holder, int position) {
        Madoubean.DataBean dataBean = arrayList.get(position);
        holder.mStart_two_biao.setText(dataBean.getLabel().get(0)+"");
        holder.mStart_one_piece.setText(dataBean.getCallwriting()+"");
        if (dataBean.getOnline().contains("1")){
            holder.mStart_one_time.setBackgroundResource(R.drawable.zaixian);
        }else {
            holder.mStart_one_time.setBackgroundResource(R.drawable.buzaixian);
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
        RoundedCorners roundedCorners = new RoundedCorners(25);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);
        Glide.with(context)
                .asBitmap()
                .load(dataBean.getBackgroundimages())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .apply(coverRequestOptions)
                .into(holder.mStart_one_icon);
        holder.mStart_one_name.setText(dataBean.getNickname()+"");
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickma!=null){
                    onItemClickma.OnItemClickma(view, (Integer) view.getTag());
                }
            }
        });
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
        private ImageView mYonghu_lavel;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mStart_one_icon=itemView.findViewById(R.id.mStart_four_image);
            mStart_one_piece=itemView.findViewById(R.id.mStart_four_piece);
            mStart_one_time=itemView.findViewById(R.id.mStart_four_time);
            mStart_one_boda=itemView.findViewById(R.id.mStart_four_boda);
            mStart_two_biao=itemView.findViewById(R.id.mStart_four_biao);
            mStart_one_name=itemView.findViewById(R.id.mStart_four_name);
            mYonghu_lavel=itemView.findViewById(R.id.mStart_four_level);

        }
    }
}
