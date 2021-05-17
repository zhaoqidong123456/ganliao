package com.zaodong.social.presenter.vip;

import android.util.Log;

import com.zaodong.social.bean.Piecebean;
import com.zaodong.social.bean.Vipbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Piecevieww;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Piecepresenter implements IPiecepresenter{
    private Service service;
    private Piecevieww piecevieww;

    public Piecepresenter(Piecevieww piecevieww) {
        this.piecevieww = piecevieww;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }
    @Override
    public void loadData(String userid, String type) {
        final String string= RetrofitUrl.key+"android"+type+userid+RetrofitUrl.version1;
        Log.e("canshu",string);
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", "android");
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("type",type);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.shangpin(stringStringHashMap)
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
                            //Log.e("shang",string3);
                            Gson gson = new Gson();
                            if (string3.contains("good")){
                                Piecebean piecebean = gson.fromJson(string3, Piecebean.class);
                                piecevieww.showData(piecebean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                piecevieww.showDatf(yzmfbean);
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
    public void loadDatavip(String userid, String type) {
        final String string= RetrofitUrl.key+"android"+type+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", "android");
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("type",type);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        Log.e("qianming",string);
        service.vip(stringStringHashMap)
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
                            Log.e("vip",string3);
                            Gson gson = new Gson();
                            if (string3.contains("money")){
                                Vipbean vipbean = gson.fromJson(string3, Vipbean.class);
                                piecevieww.showDatavip(vipbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                piecevieww.showDatfvip(yzmfbean);
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
