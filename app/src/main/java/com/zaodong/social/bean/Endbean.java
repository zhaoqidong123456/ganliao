package com.zaodong.social.bean;

public class Endbean {

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
        private String duration;
        private int total_consumption;
        private int duration_consumption;
        private int gift_consumption;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getTotal_consumption() {
            return total_consumption;
        }

        public void setTotal_consumption(int total_consumption) {
            this.total_consumption = total_consumption;
        }

        public int getDuration_consumption() {
            return duration_consumption;
        }

        public void setDuration_consumption(int duration_consumption) {
            this.duration_consumption = duration_consumption;
        }

        public int getGift_consumption() {
            return gift_consumption;
        }

        public void setGift_consumption(int gift_consumption) {
            this.gift_consumption = gift_consumption;
        }
    }
}
