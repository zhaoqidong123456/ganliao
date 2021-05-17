package com.zaodong.social.presenter.login.state;

import android.util.Log;

import com.zaodong.social.bean.Statebean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Stateview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Statepresenter implements IStatepresenter{
    private Service service;
    private Stateview stateview;

    public Statepresenter(Stateview stateview) {
        this.stateview = stateview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadData(String userid, String online) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+online+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("online",online);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        Log.e("111",string2);
        service.state(stringStringHashMap)
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
                            Log.e("state",string3);
                            Gson gson = new Gson();
                            if (string3.contains("1")){
                                Statebean statebean = gson.fromJson(string3, Statebean.class);
                                stateview.showDatastate(statebean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                stateview.showDatastatef(yzmfbean);
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
