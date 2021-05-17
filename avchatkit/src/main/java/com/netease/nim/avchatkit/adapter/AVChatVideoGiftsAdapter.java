package com.netease.nim.avchatkit.adapter;

import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netease.nim.avchatkit.R;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseQuickAdapter;
import com.netease.nim.uikit.common.ui.recyclerview.holder.BaseViewHolder;

import java.util.List;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 5/10/21 5:41 PM
 * @Version:
 */
public class AVChatVideoGiftsAdapter extends BaseQuickAdapter<GiftsAttachmentBean, BaseViewHolder> {

    public AVChatVideoGiftsAdapter(RecyclerView recyclerView, List<GiftsAttachmentBean> list) {
        super(recyclerView, R.layout.item_chat_gifts, list);
    }
    @Override
    protected void convert(BaseViewHolder helper, GiftsAttachmentBean item, int position, boolean isScrolling) {
        helper.setText(R.id.item_chat_gifts_name,item.getName()+"");
        helper.setText(R.id.item_chat_gifts_counts,"送给她"+item.getUmber()+"个");
        ImageView imageView = helper.getView(R.id.item_chat_gifts_image);
        Glide.with(mContext).load(item.getPicimage()).into(imageView);
    }
}
