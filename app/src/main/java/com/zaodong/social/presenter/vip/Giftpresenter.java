package com.zaodong.social.presenter.vip;

import android.util.Log;

import com.zaodong.social.bean.Giftbean;
import com.zaodong.social.bean.SendGiftbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Giftview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Giftpresenter implements IGiftpresenter{
    private Service service;
    private Giftview giftview;

    public Giftpresenter(Giftview giftview) {
        this.giftview = giftview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }

    @Override
    public void loadData(String userid) {
        final String string= RetrofitUrl.key+RetrofitUrl.channel+userid+RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.gift(stringStringHashMap)
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
                            //Log.e("gift",string3);
                            Gson gson = new Gson();
                            if (string3.contains("id")){
                                Giftbean giftbean = gson.fromJson(string3, Giftbean.class);
                                giftview.showData(giftbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                giftview.showDataf(yzmfbean);
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
    public void loadDatazeng(String userid, String Au_userid, String channal_id, String gift_id, String liwu_price, String number, String video_type, String video_id, String video_userid) {
        final String string= RetrofitUrl.key+Au_userid+RetrofitUrl.channel+channal_id+gift_id+number+liwu_price+userid+RetrofitUrl.version1+video_id+video_type+video_userid;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("anchor_user_id",Au_userid);
        stringStringHashMap.put("channel_id",channal_id);
        stringStringHashMap.put("gift_id",gift_id);
        stringStringHashMap.put("price",liwu_price);
        stringStringHashMap.put("number",number);
        stringStringHashMap.put("video_type",video_type);
        stringStringHashMap.put("video_id",video_id);
        stringStringHashMap.put("user_id",userid);
        stringStringHashMap.put("video_user_id",video_userid);
        stringStringHashMap.put("sig",string2);
        stringStringHashMap.put("version",RetrofitUrl.version1);
        service.send_gift(stringStringHashMap)
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
                            Log.e("send",string3);
                            Gson gson = new Gson();
                            if (string3.contains("2000")){
                                SendGiftbean sendGiftbean = gson.fromJson(string3, SendGiftbean.class);
                                giftview.showDatasend(sendGiftbean);
                            }else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                giftview.showDatafsend(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e){

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
