package com.zaodong.social.presenter.zhifu;

import android.util.Log;

import com.zaodong.social.bean.Jilutelefalsebean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Jiluteleview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class JiluTelepresenter implements IJiluTelepresenter {
    private Service service;
    private Jiluteleview jiluteleview;

    public JiluTelepresenter(Jiluteleview jiluteleview) {
        this.jiluteleview = jiluteleview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void showDatafalse(String userid, String type, String page, String counts) {
        final String string= RetrofitUrl.key+ RetrofitUrl.channel+page+counts+type+userid+ RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("page",page);
        stringStringHashMap.put("type",type);
        stringStringHashMap.put("record_per_page",counts);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version", RetrofitUrl.version1);
        service.tele_jilu(stringStringHashMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string3 = responseBody.string();
                            Log.e("jilu",string3);
                            Gson gson = new Gson();
                            if (string3.contains("data")){
                                Jilutelefalsebean jilutelefalsebean = gson.fromJson(string3, Jilutelefalsebean.class);
                                jiluteleview.showDatafalse(jilutelefalsebean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                jiluteleview.showDatafalsef(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void showDatatrue(String userid, String type, String page, String counts) {
        final String string= RetrofitUrl.key+ RetrofitUrl.channel+page+counts+type+userid+ RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("page",page);
        stringStringHashMap.put("type",type);
        stringStringHashMap.put("record_per_page",counts);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version", RetrofitUrl.version1);
        service.tele_jilu(stringStringHashMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string3 = responseBody.string();
                            Gson gson = new Gson();
                            if (string3.contains("data")){
                                Jilutelefalsebean jilutelefalsebean = gson.fromJson(string3, Jilutelefalsebean.class);
                                jiluteleview.showDatatrue(jilutelefalsebean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                jiluteleview.showDatatruef(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
