package com.zaodong.social.presenter.start;

import android.util.Log;

import com.zaodong.social.bean.Detailsbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Detailsview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Detailspresenter implements IDetailspresenter{
    private Service service;
    private Detailsview detailsview;

    public Detailspresenter(Detailsview detailsview) {
        this.detailsview = detailsview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }
    @Override
    public void loadData(String userid, String anchor) {
        final String string= RetrofitUrl.key+anchor+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("anchor_user_id",anchor);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.details(stringStringHashMap)
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
                            Log.e("details",string3);
                            Gson gson = new Gson();
                            if (string3.contains("user_id")){
                                Detailsbean detailsbean = gson.fromJson(string3, Detailsbean.class);
                                detailsview.showDataDetails(detailsbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                detailsview.showDataDetailsf(yzmfbean);
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
