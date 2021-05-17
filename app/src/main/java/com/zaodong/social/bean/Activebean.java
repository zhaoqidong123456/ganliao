package com.zaodong.social.bean;

import java.util.List;

public class Activebean {


    /**
     * code : 1
     * msg : 成功
     * time : 1617162279
     * data : {"banner":[{"image":"http://gljy.ldnxw.com/uploads/20210115/cf2554552fa733ea7586e92485840068.jpg","url":"https://www.baidu.com","jump":"1"},{"image":"http://gljy.ldnxw.com/uploads/20210115/f8aa2ec68dc7f56cce80b49d8583903f.jpg","url":"","jump":"3"},{"image":"http://gljy.ldnxw.com/uploads/20210115/73758dfe8a71e4cec9c41697ef01ca94.jpeg","url":"","jump":"2"},{"image":"http://gljy.ldnxw.com/uploads/20210115/38e26e97f8c71c0e18df23ef90d7e0b8.jpg","url":"","jump":"4"},{"image":"http://gljy.ldnxw.com/uploads/20210115/5691356600febf4cf6da8e9313e6747f.jpg","url":"","jump":"5"}],"item":[{"user_id":4,"type":"2","yx_accid":"bffw89971606810099","yx_token":"cccfdb11ae1ab3e3c83f2e3694a3bc9a","online":"1","nickname":"弯弯","avatar":"http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg","level":"lv11","callprice":300,"callwriting":"300钻/分钟","label":["性感","漂亮","可爱"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg"},{"user_id":13,"type":"2","yx_accid":"uuks99791608029934","yx_token":"85ce097c2c78077d566b1dc9d39aed05","online":"1","nickname":"你的小心脏","avatar":"http://gljy.ldnxw.com/uploads/20201225/6959c03a1b6095dd3313b30bf351c200.jpg","level":"lv8","callprice":400,"callwriting":"400钻/分钟","label":["性感","漂亮","美丽"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201225/6959c03a1b6095dd3313b30bf351c200.jpg"},{"user_id":47,"type":"2","yx_accid":"kmre93301608544431","yx_token":"7b3515ebf7be3d418d828f4ab460c9ae","online":"1","nickname":"为你表演的小牛奶","avatar":"http://gljy.ldnxw.com/uploads/20201221/acf616239a2f6c5b39439e422db8ff7a.jpg","level":"lv1","callprice":600,"callwriting":"600钻/分","label":["性感","漂亮","妩媚"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/d851c48ea4ad0fa4651d35aa005a1cf2.jpg"}],"push":[{"user_id":9,"type":"2","yx_accid":"vwbk93831607572047","yx_token":"c94a1acb2ca1c7366fcf7efa22ecbf3b","online":"2","nickname":"谁家的宝宝呀","avatar":"http://gljy.ldnxw.com/uploads/20201221/d5bacf3ec2df8cc74da758a391e27acc.jpg","level":"lv11","callprice":300,"callwriting":"300钻/分钟","label":["可爱","制服","性感"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/5dd7d9d2ee12c7f55c3c49f1651dce87.jpg"},{"user_id":12,"type":"2","yx_accid":"dkdf10091607941466","yx_token":"550b232348b1947d8909552e2bf9db60","online":"2","nickname":"人间蜜桃","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650700-wsIDRQkymG20201222232500.jpg","level":"lv11","callprice":40,"callwriting":"10钻/分钟","label":["性感"],"backgroundimages":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650476-Rtr5p9Ahmd20201222232116.jpg"},{"user_id":13,"type":"2","yx_accid":"uuks99791608029934","yx_token":"85ce097c2c78077d566b1dc9d39aed05","online":"1","nickname":"你的小心脏","avatar":"http://gljy.ldnxw.com/uploads/20201225/6959c03a1b6095dd3313b30bf351c200.jpg","level":"lv8","callprice":400,"callwriting":"400钻/分钟","label":["性感","漂亮","美丽"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201225/6959c03a1b6095dd3313b30bf351c200.jpg"},{"user_id":15,"type":"2","yx_accid":"fumg14161608102515","yx_token":"1e5d01f5165864b794ad04c61dbc620e","online":"2","nickname":"奶白甜","avatar":"http://gljy.ldnxw.com/uploads/20201221/dfc90683001373d103acc91fc71f3baf.jpg","level":"lv11","callprice":400,"callwriting":"400钻/分钟","label":["性感","漂亮","妩媚"],"backgroundimages":"http://gljy.ldnxw.com/uploads/20201221/0dc2be4ec1f8faf3fdbf1ed6c74bd0d2.jpg"}]}
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
        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        private String discount;
        private List<BannerBean> banner;
        private List<ItemBean> item;
        private List<PushBean> push;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public List<PushBean> getPush() {
            return push;
        }

        public void setPush(List<PushBean> push) {
            this.push = push;
        }

        public static class BannerBean {
            /**
             * image : http://gljy.ldnxw.com/uploads/20210115/cf2554552fa733ea7586e92485840068.jpg
             * url : https://www.baidu.com
             * jump : 1
             */

            private String image;
            private String url;
            private String jump;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getJump() {
                return jump;
            }

            public void setJump(String jump) {
                this.jump = jump;
            }
        }

        public static class ItemBean {
            /**
             * user_id : 4
             * type : 2
             * yx_accid : bffw89971606810099
             * yx_token : cccfdb11ae1ab3e3c83f2e3694a3bc9a
             * online : 1
             * nickname : 弯弯
             * avatar : http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg
             * level : lv11
             * callprice : 300
             * callwriting : 300钻/分钟
             * label : ["性感","漂亮","可爱"]
             * backgroundimages : http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg
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

        public static class PushBean {
            /**
             * user_id : 9
             * type : 2
             * yx_accid : vwbk93831607572047
             * yx_token : c94a1acb2ca1c7366fcf7efa22ecbf3b
             * online : 2
             * nickname : 谁家的宝宝呀
             * avatar : http://gljy.ldnxw.com/uploads/20201221/d5bacf3ec2df8cc74da758a391e27acc.jpg
             * level : lv11
             * callprice : 300
             * callwriting : 300钻/分钟
             * label : ["可爱","制服","性感"]
             * backgroundimages : http://gljy.ldnxw.com/uploads/20201221/5dd7d9d2ee12c7f55c3c49f1651dce87.jpg
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
}
