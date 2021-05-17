package com.zaodong.social.bean;

import java.util.List;

public class Piecebean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617431872
     * data : {"money":"0.00","discount":"http://gljy.ldnxw.com/uploads/20210103/9e6857c7b6e632baf5d913ce5c1bc1ae.png","list":[{"id":4,"goodsid":"ganliao.zua.838","name":"2100钻石","price":"30.00","pricewriting":"30","value":2100,"valuewriting":"2100钻石"},{"id":5,"goodsid":"ganliao.zua.1888","name":"4780钻石","price":"68.00","pricewriting":"68","value":4780,"valuewriting":"4780钻石"},{"id":6,"goodsid":"ganliao.zua.2758","name":"7600钻石","price":"108.00","pricewriting":"108","value":7600,"valuewriting":"7600钻石"},{"id":7,"goodsid":"ganliao.zua.5288","name":"36600钻石","price":"518.00","pricewriting":"518","value":36600,"valuewriting":"36600钻石"},{"id":8,"goodsid":"ganliao.zua.10888","name":"58000钻石","price":"818.00","pricewriting":"818","value":58000,"valuewriting":"58000钻石"},{"id":9,"goodsid":"ganliao.zua.17288","name":"78000钻石","price":"1098.00","pricewriting":"1098","value":78000,"valuewriting":"78000钻石"}]}
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
         * list : [{"id":4,"goodsid":"ganliao.zua.838","name":"2100钻石","price":"30.00","pricewriting":"30","value":2100,"valuewriting":"2100钻石"},{"id":5,"goodsid":"ganliao.zua.1888","name":"4780钻石","price":"68.00","pricewriting":"68","value":4780,"valuewriting":"4780钻石"},{"id":6,"goodsid":"ganliao.zua.2758","name":"7600钻石","price":"108.00","pricewriting":"108","value":7600,"valuewriting":"7600钻石"},{"id":7,"goodsid":"ganliao.zua.5288","name":"36600钻石","price":"518.00","pricewriting":"518","value":36600,"valuewriting":"36600钻石"},{"id":8,"goodsid":"ganliao.zua.10888","name":"58000钻石","price":"818.00","pricewriting":"818","value":58000,"valuewriting":"58000钻石"},{"id":9,"goodsid":"ganliao.zua.17288","name":"78000钻石","price":"1098.00","pricewriting":"1098","value":78000,"valuewriting":"78000钻石"}]
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
             * id : 4
             * goodsid : ganliao.zua.838
             * name : 2100钻石
             * price : 30.00
             * pricewriting : 30
             * value : 2100
             * valuewriting : 2100钻石
             */

            private int id;
            private String goodsid;
            private String name;
            private String price;
            private String pricewriting;
            private int value;
            private String valuewriting;
            private int isCheck;//0,未选中；1选中

            public int getIsCheck() {
                return isCheck;
            }

            public void setIsCheck(int isCheck) {
                this.isCheck = isCheck;
            }

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

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getValuewriting() {
                return valuewriting;
            }

            public void setValuewriting(String valuewriting) {
                this.valuewriting = valuewriting;
            }
        }
    }
}
