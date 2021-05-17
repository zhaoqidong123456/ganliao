package com.netease.nim.uikit.extension;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.bean.TestUserBean;

public class GiftsAttachment extends CustomAttachment {
    public GiftsAttachment() {
        super(CustomAttachmentType.gifts);
    }
    @Override
    protected void parseData(JSONObject data) {
        JSONObject gift = data.getJSONObject("gift");
        giftsAttachmentBean = JSON.parseObject(gift.toJSONString(), GiftsAttachmentBean.class);
    }
    @Override
    public JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put("gift",JSONObject.toJSONString(giftsAttachmentBean));
        TestUserBean testUserBean = new TestUserBean();
        data.put("user",JSONObject.toJSONString(testUserBean));
        return data;
    }
    private GiftsAttachmentBean giftsAttachmentBean ;

    public GiftsAttachmentBean getGiftsAttachmentBean() {
        return giftsAttachmentBean;
    }

    public void setGiftsAttachmentBean(GiftsAttachmentBean giftsAttachmentBean) {
        this.giftsAttachmentBean = giftsAttachmentBean;
    }

}
