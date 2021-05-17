package com.zaodong.social.bean;

import java.util.List;

public class Fansbean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617249655
     * data : [{"user_id":4,"type":"2","yx_accid":"bffw89971606810099","yx_token":"cccfdb11ae1ab3e3c83f2e3694a3bc9a","nickname":"弯弯","vip":"1","avatar":"http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg","level":"lv11","bio":"可咸可甜，人间小可爱","label":["性感","漂亮","可爱"],"online":"1","callprice":300,"callwriting":"300钻/分钟"}]
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
         * user_id : 4
         * type : 2
         * yx_accid : bffw89971606810099
         * yx_token : cccfdb11ae1ab3e3c83f2e3694a3bc9a
         * nickname : 弯弯
         * vip : 1
         * avatar : http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg
         * level : lv11
         * bio : 可咸可甜，人间小可爱
         * label : ["性感","漂亮","可爱"]
         * online : 1
         * callprice : 300
         * callwriting : 300钻/分钟
         */

        private int user_id;
        private String type;
        private String yx_accid;
        private String yx_token;
        private String nickname;
        private String vip;
        private String avatar;
        private String level;
        private String bio;
        private String online;
        private int callprice;
        private String callwriting;
        private List<String> label;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getYx_accid() {
            return yx_accid;
        }

        public void setYx_accid(String yx_accid) {
            this.yx_accid = yx_accid;
        }

        public String getYx_token() {
            return yx_token;
        }

        public void setYx_token(String yx_token) {
            this.yx_token = yx_token;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public int getCallprice() {
            return callprice;
        }

        public void setCallprice(int callprice) {
            this.callprice = callprice;
        }

        public String getCallwriting() {
            return callwriting;
        }

        public void setCallwriting(String callwriting) {
            this.callwriting = callwriting;
        }

        public List<String> getLabel() {
            return label;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }
    }
}
