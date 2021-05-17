package com.zaodong.social.presenter.login;

import android.util.Log;

import com.zaodong.social.bean.Loginbean;
import com.zaodong.social.bean.Yzmbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Yzmview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Hyzmprensenter implements IHyzmprensenter {
    private Service service;
    private Yzmview yzmview;

    public Hyzmprensenter(Yzmview yzmview) {
        this.yzmview = yzmview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadHyzm(String phone, String sig) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel","ganliao");
        stringStringHashMap.put("mobile",phone);
        stringStringHashMap.put("sig",sig);
        stringStringHashMap.put("version","1.0.0");
        service.hyzm(stringStringHashMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.e("yzm",string);
                            Gson gson = new Gson();
                            if (string.contains("1")){
                                Yzmbean yzmbean = gson.fromJson(string, Yzmbean.class);
                                yzmview.showDataYzm(yzmbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string, Yzmfbean.class);
                                yzmview.showDatayzmf(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("yzm",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadLogin(String phone, String yzm){
        final String string= RetrofitUrl.key+yzm+RetrofitUrl.channel+phone+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("mobile",phone);
        stringStringHashMap.put("captcha",yzm);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.login(stringStringHashMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string3 = responseBody.string();
                            Log.e("login",string3);
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Loginbean loginbean = gson.fromJson(string3, Loginbean.class);
                                yzmview.showDataLogin(loginbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                yzmview.showDataLoginf(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
