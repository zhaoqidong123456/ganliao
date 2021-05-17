package com.zaodong.social.bean;

import java.io.Serializable;
import java.util.List;

public class Detailsbean implements Serializable {
    /**
     * code : 1
     * msg : 成功
     * time : 1617274611
     * data : {"user_id":4,"type":"2","yx_accid":"bffw89971606810099","yx_token":"cccfdb11ae1ab3e3c83f2e3694a3bc9a","vip":"1","online":"1","nickname":"弯弯","avatar":"http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg","level":"lv11","callprice":300,"callwriting":"300钻/分钟","city":"大理","gender":0,"age":24,"label":["性感","漂亮","可爱"],"bio":"可咸可甜，人间小可爱","backgroundimages":["http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg"],"videoimages":[{"video_id":1,"cover":"http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov","praise":4,"browse":25,"gift":0,"is_praise":0},{"video_id":2,"cover":"http://gljy.ldnxw.com/uploads/20201221/a419dd01fee49c449ebdba5713841ebb.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/710ad2a2a8c7c4cc62f48f91416827e7.mov","praise":0,"browse":11,"gift":0,"is_praise":0}],"photoimages":["http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg","http://gljy.ldnxw.com/uploads/20201221/a419dd01fee49c449ebdba5713841ebb.jpg","http://gljy.ldnxw.com/uploads/20201221/1bdc34da6731ee2a192bde7bfebd52ea.jpg"],"photoimages_count":3,"video_count":2,"is_follow":1,"follow":0,"fans":5,"intimate":[{"user_id":12,"price":"2048","type":"2","vip":"2","nickname":"人间蜜桃","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650700-wsIDRQkymG20201222232500.jpg","label":["性感"],"level":"lv11"}]}
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
         * type : 2
         * yx_accid : bffw89971606810099
         * yx_token : cccfdb11ae1ab3e3c83f2e3694a3bc9a
         * vip : 1
         * online : 1
         * nickname : 弯弯
         * avatar : http://gljy.ldnxw.com/uploads/20201221/d73920b15c7d31739c9a484ff5d2d703.jpg
         * level : lv11
         * callprice : 300
         * callwriting : 300钻/分钟
         * city : 大理
         * gender : 0
         * age : 24
         * label : ["性感","漂亮","可爱"]
         * bio : 可咸可甜，人间小可爱
         * backgroundimages : ["http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg"]
         * videoimages : [{"video_id":1,"cover":"http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov","praise":4,"browse":25,"gift":0,"is_praise":0},{"video_id":2,"cover":"http://gljy.ldnxw.com/uploads/20201221/a419dd01fee49c449ebdba5713841ebb.jpg","url":"http://gljy.ldnxw.com/uploads/20201221/710ad2a2a8c7c4cc62f48f91416827e7.mov","praise":0,"browse":11,"gift":0,"is_praise":0}]
         * photoimages : ["http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg","http://gljy.ldnxw.com/uploads/20201221/a419dd01fee49c449ebdba5713841ebb.jpg","http://gljy.ldnxw.com/uploads/20201221/1bdc34da6731ee2a192bde7bfebd52ea.jpg"]
         * photoimages_count : 3
         * video_count : 2
         * is_follow : 1
         * follow : 0
         * fans : 5
         * intimate : [{"user_id":12,"price":"2048","type":"2","vip":"2","nickname":"人间蜜桃","avatar":"https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650700-wsIDRQkymG20201222232500.jpg","label":["性感"],"level":"lv11"}]
         */

        private int user_id;
        private String type;
        private String yx_accid;
        private String yx_token;
        private String vip;
        private String online;
        private String nickname;
        private String avatar;
        private String level;
        private int callprice;
        private String callwriting;
        private String city;
        private int gender;
        private int age;
        private String bio;
        private int photoimages_count;
        private int video_count;
        private int is_follow;
        private int follow;
        private int fans;
        private Object label;
        private List<String> backgroundimages;
        private List<VideoimagesBean> videoimages;
        private List<String> photoimages;
        private List<IntimateBean> intimate;

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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public int getPhotoimages_count() {
            return photoimages_count;
        }

        public void setPhotoimages_count(int photoimages_count) {
            this.photoimages_count = photoimages_count;
        }

        public int getVideo_count() {
            return video_count;
        }

        public void setVideo_count(int video_count) {
            this.video_count = video_count;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
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

        public Object getLabel() {
            return label;
        }

        public void setLabel(Object label) {
            this.label = label;
        }

        public List<String> getBackgroundimages() {
            return backgroundimages;
        }

        public void setBackgroundimages(List<String> backgroundimages) {
            this.backgroundimages = backgroundimages;
        }

        public List<VideoimagesBean> getVideoimages() {
            return videoimages;
        }

        public void setVideoimages(List<VideoimagesBean> videoimages) {
            this.videoimages = videoimages;
        }

        public List<String> getPhotoimages() {
            return photoimages;
        }

        public void setPhotoimages(List<String> photoimages) {
            this.photoimages = photoimages;
        }

        public List<IntimateBean> getIntimate() {
            return intimate;
        }

        public void setIntimate(List<IntimateBean> intimate) {
            this.intimate = intimate;
        }

        public static class VideoimagesBean {
            /**
             * video_id : 1
             * cover : http://gljy.ldnxw.com/uploads/20201221/a293b19e5b32f623e125eac53e408578.jpg
             * url : http://gljy.ldnxw.com/uploads/20201221/e4b5957e5a807cf82307f1da8af7abb8.mov
             * praise : 4
             * browse : 25
             * gift : 0
             * is_praise : 0
             */

            private int video_id;
            private String cover;
            private String url;
            private int praise;
            private int browse;
            private int gift;
            private int is_praise;

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
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

        public static class IntimateBean {
            /**
             * user_id : 12
             * price : 2048
             * type : 2
             * vip : 2
             * nickname : 人间蜜桃
             * avatar : https://bucket-nxjy.oss-cn-shanghai.aliyuncs.com/631FB943-D5CF-45E8-BCF6-1608650700-wsIDRQkymG20201222232500.jpg
             * label : ["性感"]
             * level : lv11
             */

            private int user_id;
            private String price;
            private String type;
            private String vip;
            private String nickname;
            private String avatar;
            private String level;
            private Object label;

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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }
        }
    }
}
