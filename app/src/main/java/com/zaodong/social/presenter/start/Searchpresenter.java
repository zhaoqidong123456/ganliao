package com.zaodong.social.presenter.start;

import com.zaodong.social.bean.Jokerbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Searcview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Searchpresenter implements ISearchpresenter{
    private Service service;
    private Searcview searcview;

    public Searchpresenter(Searcview searcview) {
        this.searcview = searcview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadData(String userid, String keyword) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+keyword+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("keyword",keyword);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.sousuo(stringStringHashMap).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string3 = responseBody.string();
                            //Log.e("search",string3);
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Jokerbean jokerbean = gson.fromJson(string3, Jokerbean.class);
                                searcview.showdata(jokerbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                searcview.showdataf(yzmfbean);
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
    public void loadDatare(String userid) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.sou_re(stringStringHashMap)
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
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Jokerbean jokerbean = gson.fromJson(string3, Jokerbean.class);
                                searcview.showdatat(jokerbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                searcview.showdataff(yzmfbean);
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
