package com.zaodong.social.adapter;

import android.content.Context;
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
import com.zaodong.social.roundimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class AddCarImage extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    public static final int TYPE_ADD = 0;
    public static final int TYPE_IMAGE = 1;
    private int width = 0;

    private ImageClick imageClick;
    public interface ImageClick{
        void ImageClick(View view,int position);
    }
    public void setImageClick(ImageClick imageClick){
        this.imageClick=imageClick;
    }
    public AddCarImage(Context context, int width){
        this.mContext = context;
        this.width = width;
    }
    public void upDate(List<String> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMAGE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.idd_image, parent, false);
            return new AddCarImage.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.jiahao_buju, parent, false);
            return new AddCarImage.AddViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof AddCarImage.ViewHolder) {
            final AddCarImage.ViewHolder viewHolder = (AddCarImage.ViewHolder) holder;
            if (position==0){
                viewHolder.mIdd_xian.setVisibility(View.VISIBLE);
            }else {
                viewHolder.mIdd_xian.setVisibility(View.GONE);
            }
            Glide.with(mContext)
                    .load(mData.get(position))
                    .into(viewHolder.mImageView);
            viewHolder.itemView.setTag(position);
            viewHolder.mImageRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (imageClick!=null){
                        imageClick.ImageClick(view, position);
                    }
                }
            });
        } else if (holder instanceof AddCarImage.AddViewHolder) {
            final AddCarImage.AddViewHolder addViewHolder = (AddCarImage.AddViewHolder) holder;
            ViewGroup.LayoutParams layoutParams = addViewHolder.mLayout.getLayoutParams();
            layoutParams.height = width;
            layoutParams.width = width;
            addViewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnItemClickListen) {
                        mOnItemClickListen.onItemClick(addViewHolder.mLayout, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mData.size() <= 0) {
            return 1;
        }
        if (mData.size() == 9) {
            return 9;
        }
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size()==9){
            return TYPE_IMAGE;
        }
        if (mData.size() <= 0 || mData.size() <= position) {
            return TYPE_ADD;
        } else {
            return TYPE_IMAGE;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView mImageView;
        ImageView mImageRemove;
        TextView mIdd_xian;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_img);
            mImageRemove = itemView.findViewById(R.id.mIdd_image);
            mIdd_xian=itemView.findViewById(R.id.mIdd_xian);
        }
    }
    class AddViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLayout;

        public AddViewHolder(View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.upload_photo_layout);
        }
    }
    public OnItemClickListen mOnItemClickListen;

    public void setOnItemClickListen(OnItemClickListen onItemClickListen) {
        this.mOnItemClickListen = onItemClickListen;
    }

    public interface OnItemClickListen {
        void onItemClick(View view, int position);
    }
}
