package com.zaodong.social.presenter;

import android.util.Log;

import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Liuview;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public
class Liupresenter implements ILiupresenter{
    private Service service;
    private Liuview liuview;

    public Liupresenter(Liuview liuview) {
        this.liuview = liuview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void showData(String id) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+RetrofitUrl.version1+id;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("video_user_id",id);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.liulan(stringStringHashMap)
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
                            Log.e("liulan",string3);
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
