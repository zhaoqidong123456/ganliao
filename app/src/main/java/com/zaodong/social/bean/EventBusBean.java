package com.zaodong.social.bean;

public class EventBusBean {
    public static final int EVENT_FRAGMENT_HIDEN = 11111;


    private String str;
    private int Code;

    public EventBusBean() {
    }

    public EventBusBean(String str, int code) {
        this.str = str;
        Code = code;
    }



    public String getStr() {
        return str;
    }

    public int getCode() {
        return Code;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setCode(int code) {
        Code = code;
    }
}
