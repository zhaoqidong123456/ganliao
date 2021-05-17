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
import com.zaodong.social.bean.Fansbean;

import java.util.ArrayList;

public class ActiveAdapter extends RecyclerView.Adapter<ActiveAdapter.Holder> {
    private ArrayList<Fansbean.DataBean> arrayList;
    private Context context;
    private OnItemClickatt onItemClickatt;

    public ActiveAdapter(ArrayList<Fansbean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.active_item_one,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    public interface OnItemClickatt{
        void OnItemClickatt(View view,int position);
    }
    public void setOnItemClickatt(OnItemClickatt onItemClickatt){
        this.onItemClickatt=onItemClickatt;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Fansbean.DataBean itemBean = arrayList.get(position);
        holder.mStart_one_biao.setText(itemBean.getLabel().get(0)+"");
        holder.mStart_one_piece.setText(itemBean.getCallwriting()+"");
        if (itemBean.getOnline().contains("1")){
            holder.mStart_one_time.setBackgroundResource(R.drawable.zaixian);
        }else {
            holder.mStart_one_time.setBackgroundResource(R.drawable.buzaixian);
        }
        if (itemBean.getLevel().contains("lv1")&&itemBean.getLevel().length()==3){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_one);
        }else if (itemBean.getLevel().contains("lv2")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_two);
        }else if (itemBean.getLevel().contains("lv3")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_three);
        }else if (itemBean.getLevel().contains("lv4")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_gour);
        }else if (itemBean.getLevel().contains("lv5")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_five);
        }else if (itemBean.getLevel().contains("lv6")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_six);
        }else if (itemBean.getLevel().contains("lv7")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_seven);
        }else if (itemBean.getLevel().contains("lv8")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_eight);
        }else if (itemBean.getLevel().contains("lv9")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_nine);
        }else if (itemBean.getLevel().contains("lv10")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_ten);
        }else if (itemBean.getLevel().contains("lv11")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_tenone);
        }
        RoundedCorners roundedCorners = new RoundedCorners(25);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);//不做内存缓存
        Glide.with(context)
                .asBitmap()
                .load(itemBean.getAvatar())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .apply(coverRequestOptions)
                .into(holder.mStart_one_icon);
        holder.mStart_one_name.setText(itemBean.getNickname()+"");
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickatt!=null){
                    onItemClickatt.OnItemClickatt(view, (Integer) view.getTag());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private ImageView mYonghu_lavel;
        private TextView mStart_one_biao;
        private TextView mStart_one_piece,mStart_one_time,mStart_one_name;
        private ImageView mStart_one_boda;
        private ImageView mStart_one_icon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mStart_one_icon=itemView.findViewById(R.id.mStart_one_image);
            mStart_one_piece=itemView.findViewById(R.id.mStart_one_piece);
            mStart_one_time=itemView.findViewById(R.id.mStart_one_time);
            mStart_one_boda=itemView.findViewById(R.id.mStart_one_boda);
            mStart_one_biao=itemView.findViewById(R.id.mStart_one_biao);
            mStart_one_name=itemView.findViewById(R.id.mStart_one_name);
            mYonghu_lavel=itemView.findViewById(R.id.mStart_one_level);
        }
    }
}
