package com.zaodong.social.presenter;

import android.util.Log;

import com.zaodong.social.bean.Versionbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Versionview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Versionpresenter implements IVersionpresenter {
    private Service service;
    private Versionview versionview;
    public Versionpresenter(Versionview versionview) {
        this.versionview = versionview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }
    @Override
    public void loadData(String version) {
        final String string= RetrofitUrl.key+ RetrofitUrl.channel+version+ RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("user_id",version);
        stringStringHashMap.put("version", RetrofitUrl.version1);
        service.shengji(stringStringHashMap)
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
                            Log.e("shengji",string3);
                            Gson gson = new Gson();
                            if (string3.contains("暂无更新")){
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                versionview.showDataf(yzmfbean);
                            }else {
                                Versionbean versionbean = gson.fromJson(string3, Versionbean.class);
                                versionview.showData(versionbean);
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
