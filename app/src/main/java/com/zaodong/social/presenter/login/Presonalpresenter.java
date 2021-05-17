package com.zaodong.social.presenter.login;

import android.util.Log;

import com.zaodong.social.bean.Presonalbean;
import com.zaodong.social.bean.Savebean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Presonalview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Presonalpresenter implements IPresonalpresenter{
    private Service service;
    private Presonalview presonalview;

    public Presonalpresenter(Presonalview presonalview) {
        this.presonalview = presonalview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadData(String userid) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.presonal(stringStringHashMap)
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
                            Log.e("preson",string3);
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Presonalbean presonalbean = gson.fromJson(string3, Presonalbean.class);
                                presonalview.showDatap(presonalbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                presonalview.showDatapf(yzmfbean);
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

    @Override
    public void loadDatasave(String userid, String username, String gengder, String avatar, String birthday, String bio, String back) {
        final String string= RetrofitUrl.key+avatar+back+bio+birthday+RetrofitUrl.channel+gengder+username+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();

        Log.e("save",string2);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("nickname",username);
        stringStringHashMap.put("gender",gengder);
        stringStringHashMap.put("avatar",avatar);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("birthday",birthday);
        stringStringHashMap.put("bio",bio);
        stringStringHashMap.put("backgroundimages",back);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.save_presonal(stringStringHashMap)
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
                            //Log.e("save",string3);
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Savebean savebean = gson.fromJson(string3, Savebean.class);
                                presonalview.showDatasave(savebean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                presonalview.showDatapfsave(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("save",e.getMessage()+"");
                    }
                    @Override
                    public void onComplete() {

                    }
                });

    }
}
