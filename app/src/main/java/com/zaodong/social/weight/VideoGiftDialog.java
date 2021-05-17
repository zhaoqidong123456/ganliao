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
import com.zaodong.social.presenter.vip.Giftpresenter;
import com.zaodong.social.presenter.vip.IGiftpresenter;
import com.zaodong.social.utils.MD5Utils;
import com.zaodong.social.view.Giftview;
import com.zaodong.social.view.Myview;
import com.zaodong.social.view.Yxview;
import com.google.gson.Gson;
import com.netease.nim.avchatkit.AVChaitKitManager;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.opensource.svgaplayer.SVGAImageView;

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
public class VideoGiftDialog implements Yxview, Myview, Giftview {
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
    private Context container;

    private static volatile VideoGiftDialog instance = null;
    private Service service;

    private String userID = null;
    private String sessionId = null;
    private SVGAImageView svgaImageView;
    private SendGiftbean sendGiftbean;
    private IYxpresenter yxpresenter;
    private IMypresenter mypresenter;
    private IGiftpresenter giftpresenter;
    private long currentChatId1;

    private VideoGiftDialog(Context container, String user_id, String sessionId, SVGAImageView svgaImageView,long currentChatId) {
        this.userID = user_id;
        this.container = container;
        this.sessionId = sessionId;
        currentChatId1=currentChatId;
        Sputils.getInstance().setCurrentChatId(currentChatId1+"");
        this.svgaImageView = svgaImageView;
        mypresenter=new Mypresenter(this);
        giftpresenter=new Giftpresenter(this);
        popupWindow1 = new PopupWindow();
        gift_array = new ArrayList<>();
        service = RetrofitUtils.getRetrofitUtils().getService();
        init();
        giftpresenter.loadData(Sputils.getInstance().getuser_id());
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
        instance.svgaImageView =null;
        instance = null;
    };

    public static VideoGiftDialog getInstance(Context container, String yxid, String sessionId, SVGAImageView svgaImageView,long currentChatId) {
        instance = new VideoGiftDialog(container, yxid, sessionId,svgaImageView,currentChatId);
        return instance;
    }
    private void init() {
        yxpresenter=new Yxpresenter(this);
        view = LayoutInflater.from(container).inflate(R.layout.gift_item, null);
        mGift_chong = view.findViewById(R.id.mGift_chong);
        mGift_yu = view.findViewById(R.id.mGift_yu);
        mGift_yu.setText(Sputils.getInstance().getMoney());
        mGift_zeng = view.findViewById(R.id.mGift_zeng);
        mGift_wai = view.findViewById(R.id.mGift_wai);
        mGift_edit = view.findViewById(R.id.mGift_edit);
        mGift_recy = view.findViewById(R.id.mGift_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container, 4);
        giftAdapter = new GiftAdapter(gift_array, container);
        mGift_recy.setAdapter(giftAdapter);
        giftAdapter.notifyDataSetChanged();
        mGift_recy.setLayoutManager(gridLayoutManager);
        mGift_yu = view.findViewById(R.id.mGift_yu);
        mGift_chong = view.findViewById(R.id.mGift_chong);
        mGift_zeng = view.findViewById(R.id.mGift_zeng);
        mGift_edit = view.findViewById(R.id.mGift_edit);
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
                Intent intent = new Intent(container, MymoneyActivity.class);
                container.startActivity(intent);
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
                popupWindow1.dismiss();
                if (id == 0) {
                    Toast.makeText(container, "请选择礼物", Toast.LENGTH_SHORT).show();
                } else {
                    yxpresenter.loadData(userID);
                }
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
                                Toast.makeText(context, "赠送成功", Toast.LENGTH_SHORT).show();
                                sendGiftbean = gson.fromJson(string3, SendGiftbean.class);
                                GiftsAttachmentBean bean = new GiftsAttachmentBean();
                                if (gift_array != null && gift_array.size() > 0) {
                                    bean = gift_array.get(a1);
                                }
                                mypresenter.loadData(Sputils.getInstance().getuser_id());
                                AVChaitKitManager.getInstance().kitListener.sendVideoGiftsMessage(context,sessionId,bean,mGift_edit.getText().toString(),svgaImageView,bean.getEffectfile());
                            } else {
                                sendGiftbean = gson.fromJson(string3, SendGiftbean.class);
                                Toast.makeText(context,sendGiftbean.getMsg()+"" , Toast.LENGTH_SHORT).show();
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
        Log.e("schsgvd",currentChatId1+"");
        loadDatazeng(container, Sputils.getInstance().getuser_id(), yxbean.getData().getUser_id()+"", currentChatId1+"", id + "", price, mGift_edit.getText().toString(), "1", "", "");
    }
    @Override
    public void showDatamy(Mybean mybean) {
        Sputils.getInstance().setMoney(mybean.getData().getMoney());
        mGift_yu.setText(mybean.getData().getMoney()+"");
    }
    @Override
    public void showDatamyf(Yzmfbean yzmfbean) {
    }
    @Override
    public void showData(Giftbean giftbean) {
            gift_array.clear();
            gift_array.addAll(giftbean.getData());
            init();
            popupWindow1.showAtLocation(view.findViewById(R.id.mGift_recy), Gravity.CENTER | Gravity.CENTER, 0, 0);
    }

    @Override
    public void showDataf(Yzmfbean yzmfbean) {

    }

    @Override
    public void showDatasend(SendGiftbean sendGiftbean) {

    }

    @Override
    public void showDatafsend(Yzmfbean yzmfbean) {

    }
}
