package com.zaodong.social.bean;

import java.util.List;

public class Matebean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617334210
     * data : [{"user_id":45,"price":"300816","type":"1","vip":"2","nickname":"汉堡王","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/E8DCDA60-9BDC-4537-8519-1608984396-83Oxb5czX020201226200636.jpg","label":"","level":"lv11"},{"user_id":12,"price":"2048","type":"2","vip":"2","nickname":"人间蜜桃","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650700-wsIDRQkymG20201222232500.jpg","label":"性感","level":"lv11"},{"user_id":70,"price":"1954","type":"1","vip":"2","nickname":"David","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/D2B6148C-DC25-4089-A8BF-1609069025-cnSvFkiWC220201227193705.jpg","label":"","level":"lv2"},{"user_id":10,"price":"1024","type":"1","vip":"2","nickname":"哈哈哈哈哈2","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/A084D7E5-DB72-42FB-B604-1608865659-VfvJ7Spj2G20201225110738.jpg","label":"","level":"lv11"},{"user_id":54,"price":"578","type":"1","vip":"1","nickname":"你六?。","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/common/head.png","label":"","level":"lv3"},{"user_id":82,"price":"256","type":"1","vip":"2","nickname":"周霏","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/19F5F0F0-E147-4E7B-9BD2-1609340905-YV8E0w38Cv20201230230825.jpg","label":"","level":"lv2"}]
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 45
         * price : 300816
         * type : 1
         * vip : 2
         * nickname : 汉堡王
         * avatar : https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/E8DCDA60-9BDC-4537-8519-1608984396-83Oxb5czX020201226200636.jpg
         * label :
         * level : lv11
         */

        private int user_id;
        private String price;
        private String type;
        private String vip;
        private String nickname;
        private String avatar;
        private String label;
        private String level;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
