package com.zaodong.social.bean;

import java.util.List;

public class Loginbean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617159593
     * data : {"user_id":115,"yx_accid":"erus57001617073258","yx_token":"5dc2f58e7e9eb2881a4996ad76aaf01a","online":"1","type":"1","channel":"ganliao","nickname":"188****4656","mobile":"18835794656","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/common/head.png","level":"lv1","gender":0,"age":21,"bio":"","money":"0.00","vip":"1","vipstarttime":0,"vipendtime":0,"backgroundimages":[""],"callprice":0,"callwriting":"","city":"","label":"","videoshowimage":"","videoimages":null,"photoimages":null,"callbackgroundimage":"","follow":0,"fans":0,"call_frequency":0,"call_number":0,"wait_call_frequency":0,"wait_call_number":0}
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
         * user_id : 115
         * yx_accid : erus57001617073258
         * yx_token : 5dc2f58e7e9eb2881a4996ad76aaf01a
         * online : 1
         * type : 1
         * channel : ganliao
         * nickname : 188****4656
         * mobile : 18835794656
         * avatar : https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/common/head.png
         * level : lv1
         * gender : 0
         * age : 21
         * bio :
         * money : 0.00
         * vip : 1
         * vipstarttime : 0
         * vipendtime : 0
         * backgroundimages : [""]
         * callprice : 0
         * callwriting :
         * city :
         * label :
         * videoshowimage :
         * videoimages : null
         * photoimages : null
         * callbackgroundimage :
         * follow : 0
         * fans : 0
         * call_frequency : 0
         * call_number : 0
         * wait_call_frequency : 0
         * wait_call_number : 0
         */

        private int user_id;
        private String yx_accid;
        private String yx_token;
        private String online;
        private String type;
        private String channel;
        private String nickname;
        private String mobile;
        private String avatar;
        private String level;
        private int gender;
        private int age;
        private String bio;
        private String money;
        private String vip;
        private int vipstarttime;
        private int vipendtime;
        private int callprice;
        private String callwriting;
        private String city;
        private String label;
        private String videoshowimage;
        private Object videoimages;
        private Object photoimages;
        private String callbackgroundimage;
        private int follow;
        private int fans;
        private int call_frequency;
        private int call_number;
        private int wait_call_frequency;
        private int wait_call_number;
        private List<String> backgroundimages;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public int getVipstarttime() {
            return vipstarttime;
        }

        public void setVipstarttime(int vipstarttime) {
            this.vipstarttime = vipstarttime;
        }

        public int getVipendtime() {
            return vipendtime;
        }

        public void setVipendtime(int vipendtime) {
            this.vipendtime = vipendtime;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getVideoshowimage() {
            return videoshowimage;
        }

        public void setVideoshowimage(String videoshowimage) {
            this.videoshowimage = videoshowimage;
        }

        public Object getVideoimages() {
            return videoimages;
        }

        public void setVideoimages(Object videoimages) {
            this.videoimages = videoimages;
        }

        public Object getPhotoimages() {
            return photoimages;
        }

        public void setPhotoimages(Object photoimages) {
            this.photoimages = photoimages;
        }

        public String getCallbackgroundimage() {
            return callbackgroundimage;
        }

        public void setCallbackgroundimage(String callbackgroundimage) {
            this.callbackgroundimage = callbackgroundimage;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getCall_frequency() {
            return call_frequency;
        }

        public void setCall_frequency(int call_frequency) {
            this.call_frequency = call_frequency;
        }

        public int getCall_number() {
            return call_number;
        }

        public void setCall_number(int call_number) {
            this.call_number = call_number;
        }

        public int getWait_call_frequency() {
            return wait_call_frequency;
        }

        public void setWait_call_frequency(int wait_call_frequency) {
            this.wait_call_frequency = wait_call_frequency;
        }

        public int getWait_call_number() {
            return wait_call_number;
        }

        public void setWait_call_number(int wait_call_number) {
            this.wait_call_number = wait_call_number;
        }

        public List<String> getBackgroundimages() {
            return backgroundimages;
        }

        public void setBackgroundimages(List<String> backgroundimages) {
            this.backgroundimages = backgroundimages;
        }
    }
}
