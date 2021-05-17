package com.zaodong.social.presenter;

import android.util.Log;

import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.view.ChuanBean;
import com.zaodong.social.view.Chuanview;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class Chuanpresenter implements IChuanpresenter{
    private Service service;
    private Chuanview chuanview;

    public Chuanpresenter(Chuanview chuanview) {
        this.chuanview = chuanview;
        service= RetrofitUtils.getRetrofitUtils().getService();
    }
    @Override
    public void loadData(String file) {
        File file1 = new File(file);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file1.getName(),requestFile);
        service.image_shangchuan(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            //Log.e("url",string);
                            if (string.contains("1")){
                                Gson gson = new Gson();
                                ChuanBean chuanBean = gson.fromJson(string, ChuanBean.class);
                                chuanview.showData(chuanBean);
                            }else {
                                Gson gson = new Gson();
                                Yzmfbean yzmfbean = gson.fromJson(string, Yzmfbean.class);
                                chuanview.showDataCuo(yzmfbean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("image",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("image","oncomlete");
                    }
        });
    }
}
