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
import com.zaodong.social.model.Sputils;
import com.zaodong.social.utils.GlideBlurTransformation;
import com.zaodong.social.utils.ZQImageViewRoundOval;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Holder> {
    private ArrayList<String> arrayList;
    private Context context;
    private OnImageClick onImageClick;

    public ImageAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    public interface OnImageClick{
        void OnImageClick(View view,int position);
    }
    public void setOnImageClick(OnImageClick onImageClick){
        this.onImageClick=onImageClick;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        String s = arrayList.get(position);
        RoundedCorners roundedCorners = new RoundedCorners(20);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);
        if (Sputils.getInstance().getvip_u().contains("1")){
            Glide.with(context)
                    .load(s)
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
            Glide.with(context).asBitmap().load(s).into(holder.mImage_image);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onImageClick!=null){
                    onImageClick.OnImageClick(view, (Integer) view.getTag());
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
