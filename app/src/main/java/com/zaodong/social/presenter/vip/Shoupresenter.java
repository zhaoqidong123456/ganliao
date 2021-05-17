package com.zaodong.social.presenter.vip;

import com.zaodong.social.bean.Shoubean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Shouview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Shoupresenter implements IShoupresenter{
    private Service service;
    private Shouview shouview;

    public Shoupresenter(Shouview shouview) {
        this.shouview = shouview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadData(String userid, String page, String counts) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+page+counts+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("page",page);
        stringStringHashMap.put("record_per_page",counts);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.shouru(stringStringHashMap)
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
                            //Log.e("shouru",string3);
                            Gson gson = new Gson();
                            if (string3.contains("data")){
                                Shoubean shoubean = gson.fromJson(string3, Shoubean.class);
                                shouview.showData(shoubean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                shouview.showDataf(yzmfbean);
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
