package com.zaodong.social.weight;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaodong.social.R;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.adapter.GiftAdapter;
import com.zaodong.social.bean.Giftbean;
import com.zaodong.social.bean.Mybean;
import com.zaodong.social.bean.SendGiftbean;
import com.zaodong.social.bean.Yxbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.RetrofitUrl;
import com.zaodong.social.model.RetrofitUtils;
import com.zaodong.social.model.Service;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IYxpresenter;
import com.zaodong.social.presenter.Yxpresenter;
import com.zaodong.social.presenter.login.IMypresenter;
import com.zaodong.social.presenter.login.Mypresenter;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Myview;
import com.zaodong.social.view.Yxview;
import com.google.gson.Gson;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.UIKitManager;
import com.netease.nim.uikit.business.session.module.Container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * @Description:
 * @Author: wangzs
 * @Date: 2021/4/26 21:12
 * @Version:
 */
public class GiftDialog implements Yxview, Myview {
    private View view;
    private PopupWindow popupWindow1;
    private RecyclerView mGift_recy;
    private TextView mGift_yu, mGift_chong, mGift_zeng, mGift_wai;
    private EditText mGift_edit;
    private ArrayList<GiftsAttachmentBean> gift_array;
    private GiftAdapter giftAdapter;
    private int a1;
    private String price;
    private int id = 0;
    private Container container;
    private IYxpresenter yxpresenter;
    private IMypresenter mypresenter;

    private static volatile GiftDialog instance = null;
    private Service service;

    private String userID = null;
    private String sessionId = null;
    private String money;

    private GiftDialog(Container container, String user_id,String sessionId) {
        this.userID = user_id;
        this.container = container;
        this.sessionId = sessionId;

//        mypresenter.loadData(Sputils.getInstance().getuser_id());
        popupWindow1 = new PopupWindow();
        gift_array = new ArrayList<>();
        service = RetrofitUtils.getRetrofitUtils().getService();
        init();
    }

    public static void cleanAllData() {
        if (instance == null) {
            return;
        }
        instance.view = null;
        instance.popupWindow1 = null;
        instance.mGift_recy = null;
        instance.mGift_yu = null;
        instance.mGift_chong = null;
        instance.mGift_zeng = null;
        instance.mGift_wai = null;
        instance.mGift_edit = null;
        instance.gift_array = null;
        instance.giftAdapter = null;
        instance.a1 = -1;
        instance.price = null;
        instance.id = 0;
        instance.container = null;
        instance.service = null;
        instance.userID = null;
        instance.sessionId = null;

        instance = null;
    }

    ;

    public static GiftDialog getInstance(Container container, String user_id,String sessionId) {
        if (instance == null) {
            synchronized (GiftDialog.class) {
                if (instance == null) {
                    instance = new GiftDialog(container, user_id,sessionId);
                }
            }
        }
        return instance;
    }
    private void init() {
        mypresenter=new Mypresenter(this);
        yxpresenter=new Yxpresenter(this);
        view = LayoutInflater.from(container.activity).inflate(R.layout.gift_item, null);
        mGift_chong = view.findViewById(R.id.mGift_chong);
        mGift_yu = view.findViewById(R.id.mGift_yu);
        mGift_yu.setText(Sputils.getInstance().getMoney()+"");
        mGift_zeng = view.findViewById(R.id.mGift_zeng);
        mGift_wai = view.findViewById(R.id.mGift_wai);
        mGift_edit = view.findViewById(R.id.mGift_edit);
        mGift_recy = view.findViewById(R.id.mGift_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.activity, 4);
        giftAdapter = new GiftAdapter(gift_array, container.activity);
        mGift_recy.setAdapter(giftAdapter);
        giftAdapter.notifyDataSetChanged();
        mGift_recy.setLayoutManager(gridLayoutManager);
        popupWindow1 = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);
        giftAdapter.setOnItemListener(new GiftAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos) {
                giftAdapter.setDefSelect(pos);
                id = gift_array.get(pos).getId();
                price = gift_array.get(pos).getPrice();
                Log.e("gift", "选中" + pos);
                a1 = pos;
            }
        });
        mGift_chong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
                Intent intent = new Intent(container.activity, MymoneyActivity.class);
                container.activity.startActivity(intent);
            }
        });
        mGift_wai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
            }
        });
        mGift_zeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == 0) {
                    Toast.makeText(container.activity, "请选择礼物", Toast.LENGTH_SHORT).show();
                } else {
                    yxpresenter.loadData(userID);
                }
                popupWindow1.dismiss();
            }
        });
    }
    public void loadData() {
        String string = RetrofitUrl.key + RetrofitUrl.channel + userID + RetrofitUrl.version1;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id", userID);
        stringStringHashMap.put("sig", string2);
        stringStringHashMap.put("version", RetrofitUrl.version1);
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
                            Log.e("gift", string3);
                            Gson gson = new Gson();
                            if (string3.contains("成功")) {
                                Giftbean giftbean = gson.fromJson(string3, Giftbean.class);
                                gift_array.clear();
                                gift_array.addAll(giftbean.getData());
                                if (!container.activity.isFinishing()) {
                                    popupWindow1.showAtLocation(view.findViewById(R.id.mGift_recy), Gravity.CENTER | Gravity.CENTER, 0, 0);
                                }
//                                LoadDialogUtils.closeDialog(dialog);
                            } else {
                                Yzmfbean yzmfbean = gson.fromJson(string3, Yzmfbean.class);
                                Toast.makeText(container.activity, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    public void loadDatazeng(Context context, String userid, String Au_userid, String channal_id, String gift_id, String liwu_price, String number, String video_type, String video_id, String video_userid) {
        final String string = RetrofitUrl.key + Au_userid + RetrofitUrl.channel + channal_id + gift_id + number + liwu_price + userid + RetrofitUrl.version1 + video_id + video_type + video_userid;
        String string1 = MD5Utils.MD5(string);
        String string2 = string1.toUpperCase();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("channel", RetrofitUrl.channel);
        stringStringHashMap.put("user_id", userid);
        stringStringHashMap.put("anchor_user_id", Au_userid);
        stringStringHashMap.put("channel_id", channal_id);
        stringStringHashMap.put("gift_id", gift_id);
        stringStringHashMap.put("price", liwu_price);
        stringStringHashMap.put("number", number);
        stringStringHashMap.put("video_type", video_type);
        stringStringHashMap.put("video_id", video_id);
        stringStringHashMap.put("user_id", userid);
        stringStringHashMap.put("video_user_id", video_userid);
        stringStringHashMap.put("sig", string2);
        stringStringHashMap.put("version", RetrofitUrl.version1);
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
                            Log.e("============", string3);
                            Gson gson = new Gson();
                            if (string3.contains("2000")) {
                                SendGiftbean sendGiftbean = gson.fromJson(string3, SendGiftbean.class);
                                GiftsAttachmentBean bean = new GiftsAttachmentBean();
                                if (gift_array != null && gift_array.size() > 0) {
                                    bean = gift_array.get(a1);
                                }
                                mypresenter.loadData(Sputils.getInstance().getuser_id());
                                UIKitManager.getInstance().sendGiftsListener.sendGiftsMessage(container,sessionId,bean,mGift_edit.getText().toString());
                            } else {
                                SendGiftbean sendGiftbean = gson.fromJson(string3, SendGiftbean.class);
                                Toast.makeText(context, sendGiftbean.getMsg()+"", Toast.LENGTH_SHORT).show();
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
    @Override
    public void showdata(Yxbean yxbean) {
        loadDatazeng(container.activity, Sputils.getInstance().getuser_id(), yxbean.getData().getUser_id()+"", "", id + "", price, mGift_edit.getText().toString(), "", "", "");
    }

    @Override
    public void showDatamy(Mybean mybean) {
       Sputils.getInstance().setMoney(mybean.getData().getMoney());
        mGift_yu.setText(mybean.getData().getMoney());
    }

    @Override
    public void showDatamyf(Yzmfbean yzmfbean) {

    }
}
