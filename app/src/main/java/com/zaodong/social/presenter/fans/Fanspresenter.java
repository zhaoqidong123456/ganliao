package com.zaodong.social.presenter.fans;

import android.util.Log;

import com.zaodong.social.bean.Fansbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Fansview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Fanspresenter implements IFanspresenter{
    private Service service;
    private Fansview fansview;

    public Fanspresenter(Fansview fansview) {
        this.fansview = fansview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }
    @Override
    public void loadData(String userid, String type, String page, String record) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+page+record+type+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("page",page);
        stringStringHashMap.put("type",type);
        stringStringHashMap.put("record_per_page",record);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.fans(stringStringHashMap)
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
                            Log.e("fans",string3);
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Fansbean fansbean = gson.fromJson(string3, Fansbean.class);
                                fansview.showfans(fansbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                fansview.showFansf(yzmfbean);
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
