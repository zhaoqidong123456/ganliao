package com.zaodong.social.bean;

import com.netease.nim.uikit.bean.GiftsAttachmentBean;

import java.util.List;

public class Giftbean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617675027
     * data : [{"id":6,"name":"棒棒糖","price":"128.00","pricewriting":"128钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/03c517595fc0b216681123ed5ddd74fd.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/03c517595fc0b216681123ed5ddd74fd.png","weigh":10,"status":"1","createtime":1608964643},{"id":7,"name":"么么哒","price":"520.00","pricewriting":"520钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/8326f9398288725819a6c07c2f089591.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/8326f9398288725819a6c07c2f089591.png","weigh":20,"status":"1","createtime":1608964675},{"id":11,"name":"甜甜圈","price":"778.00","pricewriting":"778钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/0949688cda010f3e30e02d2e7dce436d.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/0949688cda010f3e30e02d2e7dce436d.png","weigh":25,"status":"1","createtime":1609043723},{"id":8,"name":"巧克力","price":"1314.00","pricewriting":"1314钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/5e18aa6eace1ff40f81849991484a057.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/32803e2484f29efb30b83936a1856bd3.svga","weigh":30,"status":"1","createtime":1608964776},{"id":5,"name":"仙女棒","price":"6666.00","pricewriting":"6666钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/dd88ac12cf433fcc65d1e6f5c5a0b3ed.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/d1ad6abfdbe42a8b7d3485d9fbd04be4.svga","weigh":40,"status":"1","createtime":1607430372},{"id":9,"name":"黄金跑车","price":"8888.00","pricewriting":"8888钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/7ce6ae4e08cb161e927f868a390b5a74.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/773f17f13e32ae8e3c175e47858b09ac.svga","weigh":50,"status":"1","createtime":1608965282},{"id":3,"name":"游艇","price":"12888.00","pricewriting":"12888钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/dbdba6a77828b7c24a8801e3f2e7517d.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/d812b59d0d8906561167e418e257a61b.svga","weigh":60,"status":"1","createtime":1607429865},{"id":1,"name":"火箭","price":"88888.00","pricewriting":"88888钻石","picimage":"http://gljy.ldnxw.com/uploads/20201227/7ec42856ac3f2c04f2a45ab8f41916b5.png","effectfile":"http://gljy.ldnxw.com/uploads/20201227/7e7f3e2bea7c7d2649faa615117e26b1.svga","weigh":75,"status":"1","createtime":1606572437}]
     */

    private int code;
    private String msg;
    private String time;
    private List<GiftsAttachmentBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<GiftsAttachmentBean> getData() {
        return data;
    }

    public void setData(List<GiftsAttachmentBean> data) {
        this.data = data;
    }

}
