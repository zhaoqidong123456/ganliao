package com.zaodong.social.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zaodong.social.R;
import com.zaodong.social.bean.Detailsbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.utils.GlideBlurTransformation;
import com.zaodong.social.utils.ZQImageViewRoundOval;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> {
    private ArrayList<Detailsbean.DataBean.VideoimagesBean> arrayList;
    private Context context;
    private OnvideoClick onvideoClick;

    public VideoAdapter(ArrayList<Detailsbean.DataBean.VideoimagesBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public interface OnvideoClick{
        void OnvideoClick(View view,int position);
    }
    public void setOnvideoClick(OnvideoClick onvideoClick){
        this.onvideoClick=onvideoClick;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_chakan,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        Detailsbean.DataBean.VideoimagesBean videoimagesBean = arrayList.get(position);
        RoundedCorners roundedCorners = new RoundedCorners(20);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);
        if (Sputils.getInstance().getvip_u().contains("1")){
            Glide.with(context)
                    .load(videoimagesBean.getCover())
                    .apply(RequestOptions.bitmapTransform(new GlideBlurTransformation(context)))
                    .into(new ViewTarget<ImageView, Drawable>(holder.mImage_image) {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            Drawable current = resource.getCurrent();
                            //设置背景图
                            //image2.setBackground(current);
                            //设置图片
                            holder.mImage_image.setImageDrawable(current);
                        }
                    });
        }else {
            Glide.with(context).asBitmap().load(videoimagesBean.getCover()).into(holder.mImage_image);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onvideoClick!=null){
                    onvideoClick.OnvideoClick(view, (Integer) view.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ZQImageViewRoundOval mImage_image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mImage_image=itemView.findViewById(R.id.mImage_image);
            mImage_image.setType(ZQImageViewRoundOval.TYPE_ROUND);
            mImage_image.setRoundRadius(20);//矩形凹行大小
        }
    }
}
