package com.zaodong.social.bean;

public class Mybean{

    /**
     * code : 1
     * msg : 成功
     * time : 1617344035
     * data : {"user_id":4,"yx_accid":"bffw89971606810099","yx_token":"cccfdb11ae1ab3e3c83f2e3694a3bc9a","online":"1","type":"2","channel":"ganliao","nickname":"弯弯","mobile":"15012652055","avatar":"http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg","level":"lv11","gender":0,"age":24,"bio":"可咸可甜，人间小可爱","money":"306676.00","vip":"1","vipstarttime":0,"vipendtime":0,"videoimages":"http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov,http://gljy.ldnxw.com/uploads/20201221/710ad2a2a8c7c4cc62f48f91416827e7.mov","backgroundimages":["http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg"],"callprice":300,"callwriting":"300钻/分钟","city":"大理","label":"性感,漂亮,可爱","callvideo":"http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov","follow":0,"fans":5,"call_frequency":0,"call_number":0,"wait_call_frequency":55,"wait_call_number":6}
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
         * user_id : 4
         * yx_accid : bffw89971606810099
         * yx_token : cccfdb11ae1ab3e3c83f2e3694a3bc9a
         * online : 1
         * type : 2
         * channel : ganliao
         * nickname : 弯弯
         * mobile : 15012652055
         * avatar : http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg
         * level : lv11
         * gender : 0
         * age : 24
         * bio : 可咸可甜，人间小可爱
         * money : 306676.00
         * vip : 1
         * vipstarttime : 0
         * vipendtime : 0
         * videoimages : http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov,http://gljy.ldnxw.com/uploads/20201221/710ad2a2a8c7c4cc62f48f91416827e7.mov
         * backgroundimages : ["http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg"]
         * callprice : 300
         * callwriting : 300钻/分钟
         * city : 大理
         * label : 性感,漂亮,可爱
         * callvideo : http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov
         * follow : 0
         * fans : 5
         * call_frequency : 0
         * call_number : 0
         * wait_call_frequency : 55
         * wait_call_number : 6
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
        private String videoimages;
        private int callprice;
        private String callwriting;
        private String city;
        private String label;
        private String callvideo;
        private int follow;
        private int fans;
        private int call_frequency;
        private int call_number;
        private int wait_call_frequency;
        private int wait_call_number;
        private Object backgroundimages;

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

        public String getVideoimages() {
            return videoimages;
        }

        public void setVideoimages(String videoimages) {
            this.videoimages = videoimages;
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

        public String getCallvideo() {
            return callvideo;
        }

        public void setCallvideo(String callvideo) {
            this.callvideo = callvideo;
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

        public Object getBackgroundimages() {
            return backgroundimages;
        }

        public void setBackgroundimages(Object backgroundimages) {
            this.backgroundimages = backgroundimages;
        }
    }
}
