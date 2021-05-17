package com.zaodong.social.presenter;

import android.util.Log;

import com.zaodong.social.bean.Yonghubean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Yonghuview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public
class Yonghupresenter implements IYonghupresenter{
    private Service service;
    private Yonghuview yonghuview;

    public Yonghupresenter(Yonghuview yonghuview) {
        this.yonghuview = yonghuview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void showData(String userid, String type, String isonline, String isrecharge, String page, String record_per_page) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+isonline+isrecharge+page+record_per_page+type+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("isonline",isonline);
        stringStringHashMap.put("isrecharge",isrecharge);
        stringStringHashMap.put("page",page);
        stringStringHashMap.put("type",type);
        stringStringHashMap.put("record_per_page",record_per_page);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        stringStringHashMap.put("page",page);
        service.yonghu(stringStringHashMap)
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
                            Log.e("string3",string3);
                            Gson gson = new Gson();
                            if (string3.contains("data")){
                                Yonghubean yonghubean = gson.fromJson(string3, Yonghubean.class);
                                yonghuview.showData(yonghubean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                yonghuview.showDataf(yzmfbean);
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
