package com.netease.nim.demo.session.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netease.nim.demo.R;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.extension.GiftsAttachment;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/27 11:34
 * @Version:
 */
public class MsgViewHolderGifts extends MsgViewHolderBase {

    private ImageView imageViewRight, imageViewLeft;
    private TextView content;
    private LinearLayout countRootRight, countRootLeft;
    private RelativeLayout left;
    private RelativeLayout right;
    private TextView content1;

    public MsgViewHolderGifts(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    public int getContentResId() {
        return R.layout.nim_message_item_gifts;
    }

    @Override
    protected int rightBackground() {
        return R.drawable.multi_select_bg;
    }

    @Override
    public void inflateContentView() {
        imageViewRight = findViewById(R.id.message_item_gifts_image_right);
        countRootRight = findViewById(R.id.message_item_gifts_count_right);
        imageViewLeft = findViewById(R.id.message_item_gifts_image_left);
        countRootLeft = findViewById(R.id.message_item_gifts_count_left);
        content = findViewById(R.id.message_item_gifts_content);
        left = findViewById(R.id.message_gifts_left);
        right = findViewById(R.id.message_gifts_right);
        content1 = findViewById(R.id.message_gifts_content);
    }


    @Override
    public void bindContentView() {
        left.setVisibility(isReceivedMessage()?View.VISIBLE:View.GONE);
        right.setVisibility(isReceivedMessage()?View.GONE:View.VISIBLE);

        content1.setText(isReceivedMessage()?"收到一个":"送给她一个");

        MsgAttachment attachment = message.getAttachment();
        if (attachment instanceof GiftsAttachment) {
            GiftsAttachment giftsAttachment = (GiftsAttachment) attachment;
            GiftsAttachmentBean giftsAttachmentBean = giftsAttachment.getGiftsAttachmentBean();
            content.setText(TextUtils.isEmpty(giftsAttachmentBean.getName()) ? "" : giftsAttachmentBean.getName());
            Glide.with(context).load(giftsAttachmentBean.getPicimage()).into(isReceivedMessage() ? imageViewLeft : imageViewRight);
            (isReceivedMessage() ? countRootLeft : countRootRight).removeAllViews();
            ImageView view = new ImageView(context);
            view.setImageResource(R.drawable.numbericon_animate_x);
            (isReceivedMessage() ? countRootLeft : countRootRight).addView(view);
            int umber = giftsAttachmentBean.getUmber();
            String count = String.valueOf(umber);
            for (int i = 0; i < count.length(); i++) {
                char c = count.charAt(i);
                int imageCount = Integer.parseInt(String.valueOf(c));
                ImageView imageView = new ImageView(context);
                if (imageCount == 0) {
                    imageView.setImageResource(R.drawable.numbericon_animate_0);
                } else if (imageCount == 1) {
                    imageView.setImageResource(R.drawable.numbericon_animate_1);
                } else if (imageCount == 2) {
                    imageView.setImageResource(R.drawable.numbericon_animate_2);
                } else if (imageCount == 3) {
                    imageView.setImageResource(R.drawable.numbericon_animate_3);
                } else if (imageCount == 4) {
                    imageView.setImageResource(R.drawable.numbericon_animate_4);
                } else if (imageCount == 5) {
                    imageView.setImageResource(R.drawable.numbericon_animate_5);
                } else if (imageCount == 6) {
                    imageView.setImageResource(R.drawable.numbericon_animate_6);
                } else if (imageCount == 7) {
                    imageView.setImageResource(R.drawable.numbericon_animate_7);
                } else if (imageCount == 8) {
                    imageView.setImageResource(R.drawable.numbericon_animate_8);
                } else if (imageCount == 9) {
                    imageView.setImageResource(R.drawable.numbericon_animate_9);
                }
                (isReceivedMessage() ? countRootLeft : countRootRight).addView(imageView);
            }
        }
    }

}
