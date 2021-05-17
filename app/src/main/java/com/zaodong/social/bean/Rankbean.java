package com.zaodong.social.bean;

import java.util.List;

public class Rankbean {
    /**
     * code : 1
     * msg : 成功
     * time : 1617242485
     * data : [{"user_id":9,"value":"6666","type":"2","nickname":"谁家的宝宝呀","vip":"1","online":"2","avatar":"http://gljy.ldnxw.com/uploads/20201221/d5bacf3ec2df8cc74da758a391e27acc.jpg","level":"lv11"},{"user_id":4,"value":"128","type":"2","nickname":"弯弯","vip":"1","online":"1","avatar":"http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg","level":"lv11"}]
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
         * user_id : 9
         * value : 6666
         * type : 2
         * nickname : 谁家的宝宝呀
         * vip : 1
         * online : 2
         * avatar : http://gljy.ldnxw.com/uploads/20201221/d5bacf3ec2df8cc74da758a391e27acc.jpg
         * level : lv11
         */

        private int user_id;
        private String value;
        private String type;
        private String nickname;
        private String vip;
        private String online;
        private String avatar;
        private String level;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
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
    }
}
