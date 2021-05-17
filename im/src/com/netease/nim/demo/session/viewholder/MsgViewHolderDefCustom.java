package com.netease.nim.demo.session.viewholder;

import com.netease.nim.uikit.extension.DefaultCustomAttachment;
import com.netease.nim.uikit.extension.GiftsAttachment;
import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderText;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;

/**
 * Created by zhoujianghua on 2015/8/4.
 */
public class MsgViewHolderDefCustom extends MsgViewHolderText {

    public MsgViewHolderDefCustom(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected String getDisplayText() {
        MsgAttachment attachment = message.getAttachment();
        if (attachment instanceof DefaultCustomAttachment) {
            DefaultCustomAttachment defaultCustomAttachment = (DefaultCustomAttachment) attachment;
            return "type: " + defaultCustomAttachment.getType() + ", data: " + defaultCustomAttachment.getContent();
        } else if (attachment instanceof GiftsAttachment) {
            GiftsAttachment giftsAttachment = (GiftsAttachment) attachment;
            return "type: " + giftsAttachment.getType() + ", data: " + giftsAttachment.packData().toJSONString();
        }
        return "xxxxxxxxx";
    }
}
