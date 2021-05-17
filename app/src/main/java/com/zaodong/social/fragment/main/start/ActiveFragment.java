package com.zaodong.social.fragment.main.start;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zaodong.social.R;
import com.zaodong.social.activity.YoujiaActivity;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.ActiveAdapter3;
import com.zaodong.social.adapter.ActiveAdapter4;
import com.zaodong.social.bean.Activebean;
import com.zaodong.social.bean.Madoubean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.start.Activepresenter;
import com.zaodong.social.presenter.start.IActivepresenter;
import com.zaodong.social.utils.LoadDialogUtils;
import com.zaodong.social.utils.MyLoder;
import com.zaodong.social.view.Activeview;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends Fragment implements Activeview, View.OnClickListener {
    private View view;
    private IActivepresenter activepresenter;
    private ArrayList<String> banner_title = new ArrayList<>();
    private ArrayList<String> banner_image = new ArrayList<>();
    private Banner banner;
    private RecyclerView recyclerView3, recyclerView4;
    private ActiveAdapter3 activeAdapter3;
    private ActiveAdapter4 activeAdapter4;
    private ArrayList<Activebean.DataBean.ItemBean> arrayList_recy1 = new ArrayList<>();
    private ArrayList<Activebean.DataBean.ItemBean> arrayList_recy2 = new ArrayList<>();
    private ArrayList<Activebean.DataBean.PushBean> arrayList_recy3 = new ArrayList<>();
    private ArrayList<Madoubean.DataBean> arrayList_recy4 = new ArrayList<>();
    private Banner mActive_banner;
    private ImageView mStart_one_image;
    private TextView mStart_one_biao;
    private TextView mStart_one_piece;
    private TextView mStart_one_time;
    private TextView mStart_one_name;
    private TextView mStart_one_level;
    private ImageView mStart_one_boda;
    private ImageView mStart_two_image;
    private TextView mStart_two_biao;
    private TextView mStart_two_piece;
    private TextView mStart_two_time;
    private TextView mStart_two_name;
    private TextView mStart_two_level;
    private ImageView mStart_two_boda;
    private ImageView mStart_twoa_image;
    private TextView mStart_twoa_biao;
    private TextView mStart_twoa_piece;
    private TextView mStart_twoa_time;
    private TextView mStart_twoa_name;
    private TextView mStart_twoa_level;
    private ImageView mStart_twoa_boda;
    private ImageView mActive_youjia;
    private Dialog dialog;
    private List<Activebean.DataBean.ItemBean> item=new ArrayList<>();
    private SwipeRefreshLayout mActiveSw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_active, container, false);
        activepresenter=new Activepresenter(this);
        initview();
        return view;
    }
    private void initview() {
        mActiveSw =view.findViewById(R.id.mActive_sw);
        mActiveSw.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mActiveSw.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mActiveSw.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mActiveSw.setSize(SwipeRefreshLayout.LARGE);
        //设置下拉刷新的监听
        mActiveSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int netType = getNetType(getContext());
                if (netType==-1){
                    LoadDialogUtils.closeDialog(dialog);
                    Toast.makeText(getContext(), "请检查网络连接是否正常", Toast.LENGTH_SHORT).show();
                }else {
                    activepresenter.loadDataActivemadou(Sputils.getInstance().getuser_id(), "1", "20");
                    activepresenter.loadDataActive(Sputils.getInstance().getuser_id());
                }
                mActiveSw.setRefreshing(false);
            }
        });
        mActive_youjia=view.findViewById(R.id.mActive_youjia);
        mActive_youjia.setOnClickListener(this);
        mStart_one_image=view.findViewById(R.id.mStart_one_image);
        mStart_one_image.setOnClickListener(this);
        mStart_one_piece=view.findViewById(R.id.mStart_one_piece);
        mStart_one_time=view.findViewById(R.id.mStart_one_time);
        mStart_one_boda=view.findViewById(R.id.mStart_one_boda);
        mStart_one_boda.setOnClickListener(this);
        mStart_one_biao=view.findViewById(R.id.mStart_one_biao);
        mStart_one_name=view.findViewById(R.id.mStart_one_name);
        mStart_one_level=view.findViewById(R.id.mStart_one_level);

        mStart_two_image=view.findViewById(R.id.mStart_two_image);
        mStart_two_image.setOnClickListener(this);
        mStart_two_piece=view.findViewById(R.id.mStart_two_piece);
        mStart_two_time=view.findViewById(R.id.mStart_two_time);
        mStart_two_boda=view.findViewById(R.id.mStart_two_boda);
        mStart_two_boda.setOnClickListener(this);
        mStart_two_biao=view.findViewById(R.id.mStart_two_biao);
        mStart_two_name=view.findViewById(R.id.mStart_two_name);
        mStart_two_level=view.findViewById(R.id.mStart_two_level);

        mStart_twoa_image=view.findViewById(R.id.mStart_twoa_image);
        mStart_twoa_image.setOnClickListener(this);
        mStart_twoa_piece=view.findViewById(R.id.mStart_twoa_piece);
        mStart_twoa_time=view.findViewById(R.id.mStart_twoa_time);
        mStart_twoa_boda=view.findViewById(R.id.mStart_twoa_boda);
        mStart_twoa_boda.setOnClickListener(this);
        mStart_twoa_biao=view.findViewById(R.id.mStart_twoa_biao);
        mStart_twoa_name=view.findViewById(R.id.mStart_twoa_name);
        mStart_twoa_level=view.findViewById(R.id.mStart_twoa_level);
        recyclerView3 = view.findViewById(R.id.mActive_recy3);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView3.setLayoutManager(linearLayoutManager2);
        recyclerView4 = view.findViewById(R.id.mActive_recy4);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView4.setLayoutManager(gridLayoutManager);
        banner = view.findViewById(R.id.mActive_banner);
        dialog= LoadDialogUtils.createLoadingDialog(getContext(),"");
        int netType = getNetType(getContext());
        if (netType==-1){
            LoadDialogUtils.closeDialog(dialog);
            Toast.makeText(getContext(), "请检查网络连接是否正常", Toast.LENGTH_SHORT).show();
        }else {
            activepresenter.loadDataActive(Sputils.getInstance().getuser_id());
        }
    }

    private int getNetType(Context context) {
        int netType = -1;
        ConnectivityManager connMgr= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo= connMgr.getActiveNetworkInfo();
        if(networkInfo==null){
            return netType;
        }
        int nType=networkInfo.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            if(((NetworkInfo) networkInfo).getExtraInfo().toLowerCase().equals("cmnet")){
                netType= 3;//cmnet 网路
            }else {
                netType = 2;//wap网络
            }
        }else if(nType==ConnectivityManager.TYPE_WIFI){
            netType = 1;//wifi网路
        }
        return netType;
    }
    @Override
    public void showData(final Activebean activebean) {
        Glide.with(getContext())
                .asBitmap()
                .load(activebean.getData().getDiscount())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(mActive_youjia);
        item.clear();
        item.addAll(activebean.getData().getItem());
        for (int i = 0; i < activebean.getData().getBanner().size(); i++) {
            banner_image.add(activebean.getData().getBanner().get(i).getImage());
            banner_title.add(activebean.getData().getBanner().get(i).getJump());
        }
        if (banner_image.size()>0){
            banner.setVisibility(View.VISIBLE);
        }else {
            banner.setVisibility(View.GONE);
        }
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoder());
        //设置图片网址或地址的集合
        banner.setImages(banner_image);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setBannerTitles(banner_title);
        banner.setDelayTime(2000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //必须最后调用的方法，启动轮播图。
                .start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            banner.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
                }
            });
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            banner.setClipToOutline(true);
        }

        RoundedCorners roundedCorners = new RoundedCorners(25);//数字为圆角度数
        RequestOptions coverRequestOptions = new RequestOptions()
                .transforms(new CenterCrop(),roundedCorners);//不做内存缓存

        List<Activebean.DataBean.ItemBean> item = activebean.getData().getItem();
        Glide.with(getContext())
                .asBitmap()
                .load(activebean.getData().getItem().get(0).getAvatar())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .apply(coverRequestOptions)
                .into(mStart_one_image);
        mStart_one_biao.setText(activebean.getData().getItem().get(0).getLabel().get(0)+"");
        mStart_one_piece.setText(activebean.getData().getItem().get(0).getCallwriting()+"");
        if (activebean.getData().getItem().get(0).getOnline().contains("1")){
            mStart_one_time.setBackgroundResource(R.drawable.zaixian);
        }else if (activebean.getData().getItem().get(0).getOnline().contains("2")){
            mStart_one_time.setBackgroundResource(R.drawable.buzaixian);
        }else {
            mStart_one_time.setBackgroundResource(R.drawable.jiushi_yuan);
        }
        mStart_one_name.setText(activebean.getData().getItem().get(0).getNickname()+"");
        mStart_one_level.setText(activebean.getData().getItem().get(0).getLevel()+"");

        Glide.with(getContext())
                .asBitmap()
                .load(activebean.getData().getItem().get(1).getAvatar())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .apply(coverRequestOptions)
                .into(mStart_two_image);
        mStart_two_biao.setText(activebean.getData().getItem().get(1).getLabel().get(0)+"");
        mStart_two_piece.setText(activebean.getData().getItem().get(1).getCallwriting()+"");
        if (activebean.getData().getItem().get(1).getOnline().contains("1")){
            mStart_two_time.setBackgroundResource(R.drawable.zaixian);
        }else if (activebean.getData().getItem().get(1).getOnline().contains("2")){
            mStart_two_time.setBackgroundResource(R.drawable.buzaixian);
        }else {
            mStart_two_time.setBackgroundResource(R.drawable.jiushi_yuan);
        }
        mStart_two_name.setText(activebean.getData().getItem().get(1).getNickname()+"");
        mStart_two_level.setText(activebean.getData().getItem().get(1).getLevel()+"");

        Glide.with(getContext())
                .asBitmap()
                .load(activebean.getData().getItem().get(2).getAvatar())
                .placeholder(0)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .apply(coverRequestOptions)
                .into(mStart_twoa_image);
        mStart_twoa_biao.setText(activebean.getData().getItem().get(2).getLabel().get(0)+"");
        mStart_twoa_piece.setText(activebean.getData().getItem().get(2).getCallwriting()+"");
        if (activebean.getData().getItem().get(2).getOnline().contains("1")){
            mStart_twoa_time.setBackgroundResource(R.drawable.zaixian);
        }else if (activebean.getData().getItem().get(2).getOnline().contains("2")){
            mStart_twoa_time.setBackgroundResource(R.drawable.buzaixian);
        }else {
            mStart_twoa_time.setBackgroundResource(R.drawable.jiushi_yuan);
        }
        mStart_twoa_name.setText(activebean.getData().getItem().get(2).getNickname()+"");
        mStart_twoa_level.setText(activebean.getData().getItem().get(2).getLevel()+"");

        arrayList_recy3.clear();
        arrayList_recy3.addAll(activebean.getData().getPush());
        activeAdapter3 = new ActiveAdapter3(arrayList_recy3, getContext());
        recyclerView3.setAdapter(activeAdapter3);
        activeAdapter3.notifyDataSetChanged();
        activeAdapter3.setOnItemClickguan(new ActiveAdapter3.OnItemClickguan() {
            @Override
            public void OnItemClickguan(View view, int position) {
                intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,activebean.getData().getPush().get(position).getUser_id()+"");
                startActivity(intent);
            }
        });
        activepresenter.loadDataActivemadou(Sputils.getInstance().getuser_id(), "1", "20");
        LoadDialogUtils.closeDialog(dialog);
    }
    Intent intent=null;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mStart_one_image:
                intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,item.get(0).getUser_id()+"");
                startActivity(intent);
                break;
            case R.id.mStart_one_boda:
                break;
            case R.id.mStart_two_image:
                intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,item.get(1).getUser_id()+"");
                startActivity(intent);
                break;
            case R.id.mStart_two_boda:
                break;
            case R.id.mStart_twoa_image:
                intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,item.get(2).getUser_id()+"");
                startActivity(intent);
                break;
            case R.id.mStart_twoa_boda:
                break;
            case R.id.mActive_youjia:
                Intent intent = new Intent(getContext(), YoujiaActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void showDataf(Yzmfbean activebean) {
        Toast.makeText(getContext(), activebean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showDataMadou(final Madoubean activebean) {
        arrayList_recy4.clear();
        arrayList_recy4.addAll(activebean.getData());
        activeAdapter4 = new ActiveAdapter4(arrayList_recy4, getContext());
        recyclerView4.setAdapter(activeAdapter4);
        activeAdapter4.notifyDataSetChanged();
        activeAdapter4.setOnItemClickma(new ActiveAdapter4.OnItemClickma() {
            @Override
            public void OnItemClickma(View view, int position) {
                intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,activebean.getData().get(position).getUser_id()+"");
                startActivity(intent);
            }
        });
    }
    @Override
    public void showDataMadouf(Yzmfbean activebean) {
        Toast.makeText(getContext(), activebean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
