package com.netease.nim.uikit.bean;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/27 11:04
 * @Version:
 */
public class GiftsAttachmentBean {

    private int id;
    private String name;
    private String price;
    private String pricewriting;
    private String picimage;
    private String effectfile;
    private int weigh;
    private String status;
    private int createtime;
    private int umber;

    public int getUmber() {
        return umber;
    }

    public void setUmber(int umber) {
        this.umber = umber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricewriting() {
        return pricewriting;
    }

    public void setPricewriting(String pricewriting) {
        this.pricewriting = pricewriting;
    }

    public String getPicimage() {
        return picimage;
    }

    public void setPicimage(String picimage) {
        this.picimage = picimage;
    }

    public String getEffectfile() {
        return effectfile;
    }

    public void setEffectfile(String effectfile) {
        this.effectfile = effectfile;
    }

    public int getWeigh() {
        return weigh;
    }

    public void setWeigh(int weigh) {
        this.weigh = weigh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "GiftsAttachmentBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", pricewriting='" + pricewriting + '\'' +
                ", picimage='" + picimage + '\'' +
                ", effectfile='" + effectfile + '\'' +
                ", weigh=" + weigh +
                ", status='" + status + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
