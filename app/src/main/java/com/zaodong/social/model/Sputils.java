package com.zaodong.social.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.zaodong.social.base.MyApplication;

public class Sputils {
    private static Sputils spUtils;
    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    public static final String FILE_NAME = "user_data";
    private String image;

    public Sputils(){
        //创建对象
        sharedPreferences = MyApplication.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    //单列模式
    public static Sputils getInstance(){
        if (spUtils == null){
            synchronized (Sputils.class){
                if (spUtils ==null)
                    spUtils = new Sputils();
            }
        }
        return spUtils;
    }
    public void clear(){
        editor.clear();
        editor.commit();
    }
    //保存token
    public void settoken(String string){
        sharedPreferences.edit().putString("token",string).commit();
    }
    public String gettoken(){
        return sharedPreferences.getString("token","");

    }

    public void setuser_id(String user_id) {
        sharedPreferences.edit().putString("userid",user_id).commit();
    }
    public String getuser_id(){
        return sharedPreferences.getString("userid","");
    }

    public void setweek(String s) {
        sharedPreferences.edit().putString("week",s).commit();
    }
    public String getweek(){
        return sharedPreferences.getString("week","");
    }

    public void setau_id(String s) {
        sharedPreferences.edit().putString("au_id",s).commit();
    }
    public String getau_id(){
        return sharedPreferences.getString("au_id","");
    }


    public void setvip_u(String s) {
        sharedPreferences.edit().putString("vip",s).commit();
    }
    public String getvip_u(){
        return sharedPreferences.getString("vip","");
    }


    public void settype(String s) {
        sharedPreferences.edit().putString("type",s).commit();
    }
    public String gettype(){
        return sharedPreferences.getString("type","");
    }


    public void setyunxin_id(String s) {
        sharedPreferences.edit().putString("yunxin_id",s).commit();
    }
    public String getyunxin_id(){
        return sharedPreferences.getString("yunxin_id","");
    }


    //记录是否是编辑状态
    public void setbianji(String string){
        sharedPreferences.edit().putString("bianji",string).commit();
    }
    public String getbianji(){
        return sharedPreferences.getString("bianji","");
    }

    //需要删除的图片地址
    public void setdelete(String string){
        sharedPreferences.edit().putString("delete",string).commit();
    }
    public String getdelete(){
        return sharedPreferences.getString("delete","");
    }

    public void setImage(String image) {
        sharedPreferences.edit().putString("image",image).commit();
    }

    public String getImage() {
        return sharedPreferences.getString("image","");
    }

    public void setviptype(String viptype) {
        sharedPreferences.edit().putString("viptype",viptype).commit();
    }

    public String getviptype() {
        return sharedPreferences.getString("viptype","");
    }

    public void setnickname(String s) {
        sharedPreferences.edit().putString("nickname",s).commit();
    }

    public String getnickname() {
        return sharedPreferences.getString("nickname","");
    }


    public void setCallprice(String s) {
        sharedPreferences.edit().putString("Callprice",s).commit();
    }

    public String getCallprice() {
        return sharedPreferences.getString("Callprice","");
    }
    public void setCallvideo(String s) {
        sharedPreferences.edit().putString("video",s).commit();
    }

    public String getCallvideo() {
        return sharedPreferences.getString("video","");
    }

    public void setMoney(String s) {
        sharedPreferences.edit().putString("money",s).commit();
    }

    public String getMoney() {
        return sharedPreferences.getString("money","");
    }



    public void setTiao(String s) {
        sharedPreferences.edit().putString("tiao",s).commit();
    }

    public String getTiao() {
        return sharedPreferences.getString("tiao","");
    }


    public void setTiao_sheng(String s) {
        sharedPreferences.edit().putString("tiao_sheng",s).commit();
    }

    public String getTiao_sheng() {
        return sharedPreferences.getString("tiao_sheng","");
    }

    public void setCurrentChatId(String s) {
        sharedPreferences.edit().putString("currentChatId",s).commit();
    }
    public String getCurrentChatId() {
        return sharedPreferences.getString("currentChatId","");
    }
}
