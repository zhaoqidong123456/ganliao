package com.zaodong.social.bean;

import java.util.List;

public class Vipbean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617776605
     * data : {"money":"0.00","discount":"http://gljy.ldnxw.com/uploads/20210103/9e6857c7b6e632baf5d913ce5c1bc1ae.png","list":[{"id":1,"goodsid":"ganliao.vip.68","name":"月卡会员","price":"30.00","pricewriting":"30","days":30,"daypricewriting":"1.0元/天"},{"id":2,"goodsid":"ganliao.vip.88","name":"季卡会员","price":"68.00","pricewriting":"68","days":90,"daypricewriting":"0.7元/天"},{"id":3,"goodsid":"ganliao.vip.98","name":"年卡会员","price":"128.00","pricewriting":"128","days":365,"daypricewriting":"0.3元/天"}]}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 0.00
         * discount : http://gljy.ldnxw.com/uploads/20210103/9e6857c7b6e632baf5d913ce5c1bc1ae.png
         * list : [{"id":1,"goodsid":"ganliao.vip.68","name":"月卡会员","price":"30.00","pricewriting":"30","days":30,"daypricewriting":"1.0元/天"},{"id":2,"goodsid":"ganliao.vip.88","name":"季卡会员","price":"68.00","pricewriting":"68","days":90,"daypricewriting":"0.7元/天"},{"id":3,"goodsid":"ganliao.vip.98","name":"年卡会员","price":"128.00","pricewriting":"128","days":365,"daypricewriting":"0.3元/天"}]
         */

        private String money;
        private String discount;
        private List<ListBean> list;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * goodsid : ganliao.vip.68
             * name : 月卡会员
             * price : 30.00
             * pricewriting : 30
             * days : 30
             * daypricewriting : 1.0元/天
             */

            private int id;
            private String goodsid;
            private String name;
            private String price;
            private String pricewriting;
            private int days;
            private String daypricewriting;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
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

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public String getDaypricewriting() {
                return daypricewriting;
            }

            public void setDaypricewriting(String daypricewriting) {
                this.daypricewriting = daypricewriting;
            }
        }
    }
}
