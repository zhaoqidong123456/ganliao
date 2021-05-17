package com.zaodong.social.model;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {
    public static RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    //网络请求工具
    public RetrofitUtils() {
        Interceptor inter = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                Request build = builder.build();
                Response response = chain.proceed(build);
                if (TextUtils.isEmpty(response.cacheControl().toString())) {
                    return response.newBuilder().addHeader("Cache-Control", "max-age=60*60*24").build();
                }
                return response;
            }
        };
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //setlevel用来设置日志打印的级别，共包括了四个级别：NONE,BASIC,HEADERS,BODY
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(40, TimeUnit.SECONDS)//设置读取超时时间
                .addInterceptor(logging).addInterceptor(inter).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUrl.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //单例模式
    public static RetrofitUtils getRetrofitUtils() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }

    public Service getService() {
//        if (NetworkUtil.isNetAvailable()) {
//
//        }
        return retrofit.create(Service.class);
    }
}
