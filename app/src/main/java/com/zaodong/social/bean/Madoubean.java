package com.zaodong.social.bean;

import java.util.List;

public  class Madoubean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617175649
     * data : [{"user_id":11,"type":"2","yx_accid":"mcat16151607656734","yx_token":"63a83c1a7326a3aae1f49ff91903345c","online":"1","nickname":"肉丸","avatar":"http://gljy.ldnxw.com/uploads/20201225/14dcce0e3f64a3854ffcc5ef8db0e71f.jpg","level":"lv8","callprice":600,"callwriting":"600钻/分钟","label":["性感","漂亮","可爱"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/37e33c6b2e529ab139effee68ca4b07d.jpg"},{"user_id":16,"type":"2","yx_accid":"eaey48911608102519","yx_token":"61e2190d603b9617602299b7bf925436","online":"1","nickname":"茶茶","avatar":"http://gljy.ldnxw.com/uploads/20201221/789fe1fcfcd1aedfbc4611412a18d6f4.jpg","level":"lv6","callprice":400,"callwriting":"400钻/分钟","label":["可爱","制服","性感"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/789fe1fcfcd1aedfbc4611412a18d6f4.jpg"},{"user_id":19,"type":"2","yx_accid":"weca92631608102528","yx_token":"4cafdb438812f962893aca560e1ce16f","online":"1","nickname":"给你一口甜甜","avatar":"http://gljy.ldnxw.com/uploads/20201221/2312ccb039ec3f467d215348a09be89f.jpg","level":"lv1","callprice":400,"callwriting":"400钻/分钟","label":["性感","漂亮","美丽"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/2312ccb039ec3f467d215348a09be89f.jpg"},{"user_id":20,"type":"2","yx_accid":"rbbd19781608102532","yx_token":"92ac9a77451a7e89f1ec644bbb834a3f","online":"1","nickname":"火火","avatar":"http://gljy.ldnxw.com/uploads/20201221/26d3c46cc5354d3141ccca612d87a751.jpg","level":"lv1","callprice":400,"callwriting":"400钻/分钟","label":["可爱","制服","性感"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/26d3c46cc5354d3141ccca612d87a751.jpg"},{"user_id":22,"type":"2","yx_accid":"uzhw30331608102540","yx_token":"5f8fed70b99d33bf55643151e8bc0a52","online":"1","nickname":"林蜜蜜","avatar":"http://gljy.ldnxw.com/uploads/20201221/08cd313bbe4e2d45db91b3335b0b54af.jpg","level":"lv1","callprice":300,"callwriting":"300钻/分钟","label":["性感","漂亮","制服"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/08cd313bbe4e2d45db91b3335b0b54af.jpg"},{"user_id":24,"type":"2","yx_accid":"vgar98091608102545","yx_token":"d91d3871884158489c5b66caf118c70a","online":"1","nickname":"棒棒糖吖。","avatar":"http://gljy.ldnxw.com/uploads/20201221/80af377ac6b6236471e3263d9a7af142.jpg","level":"lv11","callprice":400,"callwriting":"400钻/分","label":["性感","漂亮","可爱"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/80af377ac6b6236471e3263d9a7af142.jpg"},{"user_id":50,"type":"2","yx_accid":"uspl74131608544852","yx_token":"af335e2f211b352afc6b0028bd18269a","online":"1","nickname":"小猪猪","avatar":"http://gljy.ldnxw.com/uploads/20201221/1faecfccc809b2740b059b9982bb4db2.jpg","level":"lv1","callprice":300,"callwriting":"300钻/分","label":["性感","可爱"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/6c7248da2cdafb6b6b98d395831bfc69.jpg"},{"user_id":51,"type":"2","yx_accid":"zbrw49521608544988","yx_token":"0ce6b6c58757f6f58080a51eeed85d6c","online":"1","nickname":"欣瑶","avatar":"http://gljy.ldnxw.com/uploads/20201221/af0bd503b8ecb439691c74a190f136c4.jpg","level":"lv1","callprice":400,"callwriting":"400钻/分","label":["性感","漂亮","可爱"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/6b203c278ac6182b81df05510f6dcbb8.jpg"},{"user_id":52,"type":"2","yx_accid":"cnur39171608545072","yx_token":"dfabd2c48f6e9481494a825f4a4a1eaf","online":"1","nickname":"羞涩妹妹","avatar":"http://gljy.ldnxw.com/uploads/20201221/40089a5c6291e4eb19c1148f590f7a29.jpg","level":"lv1","callprice":500,"callwriting":"500钻/分","label":["性感","漂亮"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/dbb9cc7a58bada05933ede27277a83bf.jpg"}]
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
         * user_id : 11
         * type : 2
         * yx_accid : mcat16151607656734
         * yx_token : 63a83c1a7326a3aae1f49ff91903345c
         * online : 1
         * nickname : 肉丸
         * avatar : http://gljy.ldnxw.com/uploads/20201225/14dcce0e3f64a3854ffcc5ef8db0e71f.jpg
         * level : lv8
         * callprice : 600
         * callwriting : 600钻/分钟
         * label : ["性感","漂亮","可爱"]
         * backgroundimages : http://gljy.ldnxw.com/uploads/20201221/37e33c6b2e529ab139effee68ca4b07d.jpg
         */

        private int user_id;
        private String type;
        private String yx_accid;
        private String yx_token;
        private String online;
        private String nickname;
        private String avatar;
        private String level;
        private int callprice;
        private String callwriting;
        private String backgroundimages;
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

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getBackgroundimages() {
            return backgroundimages;
        }

        public void setBackgroundimages(String backgroundimages) {
            this.backgroundimages = backgroundimages;
        }

        public List<String> getLabel() {
            return label;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }
    }
}
