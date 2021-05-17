package com.zaodong.social.view;

public class ChuanBean {

    /**
     * code : 1
     * msg : 上传成功
     * time : 1617781958
     * data : {"url":"/uploads/20210407/c216947c910c1b8b7b339cc01967812c.jpg","fullurl":"http://gljy.ldnxw.com/uploads/20210407/c216947c910c1b8b7b339cc01967812c.jpg"}
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
         * url : /uploads/20210407/c216947c910c1b8b7b339cc01967812c.jpg
         * fullurl : http://gljy.ldnxw.com/uploads/20210407/c216947c910c1b8b7b339cc01967812c.jpg
         */

        private String url;
        private String fullurl;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFullurl() {
            return fullurl;
        }

        public void setFullurl(String fullurl) {
            this.fullurl = fullurl;
        }
    }
}
