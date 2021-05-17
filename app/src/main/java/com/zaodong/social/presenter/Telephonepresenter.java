package com.zaodong.social.presenter;

import android.util.Log;

import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Telephoneview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Telephonepresenter implements ITelephonepresenter {
    private Service service;
    private Telephoneview telephoneview;

    public Telephonepresenter(Telephoneview telephoneview) {
        this.telephoneview = telephoneview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }
    @Override
    public void showDataStart(String userid, String au_id) {
        final String string= RetrofitUrl.key+au_id+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("anchor_user_id",au_id);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.telephone_start(stringStringHashMap)
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
                            Log.e("start",string3);
                            Gson gson = new Gson();
                            if (string3.contains("2000")){
                                Telebeanstart telebeanstart = gson.fromJson(string3, Telebeanstart.class);
                                telephoneview.showDataStart(telebeanstart);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                telephoneview.showDataStartf(yzmfbean);
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
    public void showDatafalse(String userid, String au_id) {
        final String string= RetrofitUrl.key+au_id+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("anchor_user_id",au_id);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.telephone_false(stringStringHashMap)
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
                                Telebeanfalse telebeanfalse = gson.fromJson(string3, Telebeanfalse.class);
                                telephoneview.showDatafalse(telebeanfalse);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                telephoneview.showDatafalsef(yzmfbean);
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
    public void showDatatrue(String userid, String au_id, String tong_id) {
        final String string= RetrofitUrl.key+au_id+RetrofitUrl.channel+tong_id+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("anchor_user_id",au_id);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("channel_id",tong_id);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.telephone_true(stringStringHashMap)
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
                            //Log.e("tele_true",string3);
                            Gson gson = new Gson();
                            if (string3.contains("2000")){
                                Telebeantrue telebeantrue = gson.fromJson(string3, Telebeantrue.class);
                                telephoneview.showDatatrue(telebeantrue);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                telephoneview.showDatatruef(yzmfbean);
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
    public void showDataup(String userid, String au_id) {
        final String string= RetrofitUrl.key+au_id+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("anchor_user_id",au_id);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.telephone_up(stringStringHashMap)
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
                            //Log.e("tele_up",string3);
                            Gson gson = new Gson();
                            if (string3.contains("2000")){
                                Telebeanup telebeanup = gson.fromJson(string3, Telebeanup.class);
                                telephoneview.showDataup(telebeanup);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                telephoneview.showDataupf(yzmfbean);
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
