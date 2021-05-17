package com.zaodong.social.bean;

import java.util.ArrayList;

public class Videobean {

    /**
     * code : 1
     * msg : 成功
     * time : 1617184568
     * data : [{"user_id":4,"type":"2","yx_accid":"bffw89971606810099","yx_token":"cccfdb11ae1ab3e3c83f2e3694a3bc9a","online":"1","nickname":"弯弯","avatar":"http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg","level":"lv11","callprice":300,"callwriting":"300钻/分钟","bio":"可咸可甜，人间小可爱","label":"性感,漂亮,可爱","videoshowimage":"http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov","photoimages":"http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg,http://gljy.ldnxw.com/uploads/20201221/a419dd01fee49c449ebdba5713841ebb.jpg,http://gljy.ldnxw.com/uploads/20201221/1bdc34da6731ee2a192bde7bfebd52ea.jpg","cover":"http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov","praise":11,"browse":517,"gift":902,"is_praise":0},{"user_id":9,"type":"2","yx_accid":"vwbk93831607572047","yx_token":"c94a1acb2ca1c7366fcf7efa22ecbf3b","online":"2","nickname":"谁家的宝宝呀","avatar":"http://gljy.ldnxw.com/uploads/20201221/d5bacf3ec2df8cc74da758a391e27acc.jpg","level":"lv11","callprice":300,"callwriting":"300钻/分钟","bio":"你喜欢的样子我都有。","label":"可爱,制服,性感","videoshowimage":"http://gljy.ldnxw.com/uploads/20201221/044fbbe2fd5b51442c452b1b94ef22b6.mov","photoimages":"http://gljy.ldnxw.com/uploads/20201221/a3eaa26551deb13fa5a7825aa1654aa4.jpg,http://gljy.ldnxw.com/uploads/20201221/5dd7d9d2ee12c7f55c3c49f1651dce87.jpg","cover":"http://gljy.ldnxw.com/uploads/20201221/a3eaa26551deb13fa5a7825aa1654aa4.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/044fbbe2fd5b51442c452b1b94ef22b6.mov","praise":5,"browse":166,"gift":0,"is_praise":0},{"user_id":11,"type":"2","yx_accid":"mcat16151607656734","yx_token":"63a83c1a7326a3aae1f49ff91903345c","online":"1","nickname":"肉丸","avatar":"http://gljy.ldnxw.com/uploads/20201225/14dcce0e3f64a3854ffcc5ef8db0e71f.jpg","level":"lv8","callprice":600,"callwriting":"600钻/分钟","bio":"在吗，在就撩我呗，","label":"性感,漂亮,可爱","videoshowimage":"http://gljy.ldnxw.com/uploads/20201221/7727634941007239e75a931650d6f5ec.mov","photoimages":"http://gljy.ldnxw.com/uploads/20201221/37e33c6b2e529ab139effee68ca4b07d.jpg,http://gljy.ldnxw.com/uploads/20201221/493115dacd3430e2427ff96eba048bfb.jpg,http://gljy.ldnxw.com/uploads/20201221/93d0558edb287c2d7727ce0aea549989.jpg,http://gljy.ldnxw.com/uploads/20201221/b7f96bac0482f42cad90a1752b03a4fd.jpg","cover":"http://gljy.ldnxw.com/uploads/20201221/37e33c6b2e529ab139effee68ca4b07d.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/7727634941007239e75a931650d6f5ec.mov","praise":2,"browse":107,"gift":512,"is_praise":0},{"user_id":12,"type":"2","yx_accid":"dkdf10091607941466","yx_token":"550b232348b1947d8909552e2bf9db60","online":"2","nickname":"人间蜜桃","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650700-wsIDRQkymG20201222232500.jpg","level":"lv11","callprice":40,"callwriting":"10钻/分钟","bio":"如今最好，没有来日方长。","label":"性感","videoshowimage":"http://gljy.ldnxw.com/uploads/20201221/8c1a5cb70368c8b28f8bd9896f658ee7.mov","photoimages":"http://gljy.ldnxw.com/uploads/20201221/720ccfeb35ddcc57710ab84ba310a7db.jpg,http://gljy.ldnxw.com/uploads/20201221/b7fe5ded7d5b46f202e5641d12563084.jpg,http://gljy.ldnxw.com/uploads/20201221/750d8b45fceda5945d98028c36060b18.jpg","cover":"http://gljy.ldnxw.com/uploads/20201221/720ccfeb35ddcc57710ab84ba310a7db.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/8c1a5cb70368c8b28f8bd9896f658ee7.mov","praise":9,"browse":107,"gift":0,"is_praise":0}]
     */

    private int code;
    private String msg;
    private String time;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
         * bio : 可咸可甜，人间小可爱
         * label : 性感,漂亮,可爱
         * videoshowimage : http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov
         * photoimages : http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg,http://gljy.ldnxw.com/uploads/20201221/a419dd01fee49c449ebdba5713841ebb.jpg,http://gljy.ldnxw.com/uploads/20201221/1bdc34da6731ee2a192bde7bfebd52ea.jpg
         * cover : http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg
         * url : http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov
         * praise : 11
         * browse : 517
         * gift : 902
         * is_praise : 0
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
        private String bio;
        private String label;
        private String videoshowimage;
        private String photoimages;
        private String cover;
        private String url;
        private int praise;
        private int browse;
        private int gift;
        private int is_praise;

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

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
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

        public String getPhotoimages() {
            return photoimages;
        }

        public void setPhotoimages(String photoimages) {
            this.photoimages = photoimages;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public int getGift() {
            return gift;
        }

        public void setGift(int gift) {
            this.gift = gift;
        }

        public int getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(int is_praise) {
            this.is_praise = is_praise;
        }
    }
}
