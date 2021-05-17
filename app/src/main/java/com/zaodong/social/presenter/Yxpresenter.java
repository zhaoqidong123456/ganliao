package com.zaodong.social.presenter;

import com.zaodong.social.bean.Yxbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Yxview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Yxpresenter implements IYxpresenter{
    private Service service;
    private Yxview yxview;

    public Yxpresenter(Yxview yxview) {
        this.yxview = yxview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadData(String yx_id) {
        final String string= RetrofitUrl.key+ RetrofitUrl.channel+RetrofitUrl.version1+yx_id;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("yx_accid",yx_id);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version", RetrofitUrl.version1);
        service.yx_id(stringStringHashMap)
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
                            //Log.e("yunxin",string3);
                            if (string3.contains("yx_accid")){
                                Gson gson = new Gson();
                                Yxbean yxbean = gson.fromJson(string3, Yxbean.class);
                                yxview.showdata(yxbean);
                            }else {

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
