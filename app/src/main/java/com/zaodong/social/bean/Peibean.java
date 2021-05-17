package com.zaodong.social.bean;

public class Peibean {

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
        private String customer_service;
        private String chat_number;
        private String customer_service_news;
        private int count;

        public String getCustomer_service() {
            return customer_service;
        }

        public void setCustomer_service(String customer_service) {
            this.customer_service = customer_service;
        }

        public String getChat_number() {
            return chat_number;
        }

        public void setChat_number(String chat_number) {
            this.chat_number = chat_number;
        }

        public String getCustomer_service_news() {
            return customer_service_news;
        }

        public void setCustomer_service_news(String customer_service_news) {
            this.customer_service_news = customer_service_news;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
