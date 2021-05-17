package com.zaodong.social;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaodong.social.adapter.YonghuAdapter;
import com.zaodong.social.bean.Yonghubean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.IYonghupresenter;
import com.zaodong.social.presenter.Yonghupresenter;
import com.zaodong.social.utils.LoadDialogUtils;
import com.zaodong.social.view.Yonghuview;

import java.util.ArrayList;

public class ZhuboFragment extends Fragment implements Yonghuview, View.OnClickListener {

    private RecyclerView mRecyAtt;
    private View view;
    private TextView mAttShaixuan;
    private IYonghupresenter yonghupresenter;
    private YonghuAdapter yonghuAdapter;
    private ArrayList<Yonghubean.DataBean> arrayList = new ArrayList<>();
    private EditText mAttEdit;
    private String type = "3";
    private String isonline = "1";
    private String isrecharge = "1";
    private TextView mShuaiPu;
    private TextView mShuaiVip;
    private TextView mShuaiZai;
    private TextView mShuaiLi;
    private TextView mShuaiQuxiao;
    private TextView mShuaiSure;
    private SwipeRefreshLayout mZhubo_sw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_zhubo, container, false);
        initView();
        yonghupresenter = new Yonghupresenter(this);
        yonghupresenter.showData(Sputils.getInstance().getuser_id(), type, isonline, isrecharge, "1", "100");
        return view;
    }

    private void initView() {
        mZhubo_sw=view.findViewById(R.id.mZhubo_sw);
        mRecyAtt = view.findViewById(R.id.mRecy_att);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyAtt.setLayoutManager(linearLayoutManager);
        mAttShaixuan = view.findViewById(R.id.mAtt_shaixuan);
        mAttShaixuan.setOnClickListener(this);
        mAttEdit = view.findViewById(R.id.mAtt_edit);
        mZhubo_sw.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mZhubo_sw.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mZhubo_sw.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mZhubo_sw.setSize(SwipeRefreshLayout.LARGE);
        //设置下拉刷新的监听
        mZhubo_sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                yonghupresenter.showData(Sputils.getInstance().getuser_id(), type, isonline, isrecharge, "1", "100");
                mZhubo_sw.setRefreshing(false);
            }
        });
    }
    @Override
    public void showData(Yonghubean yonghubean) {
        arrayList.clear();
        arrayList.addAll(yonghubean.getData());
        yonghuAdapter = new YonghuAdapter(arrayList, getContext(), mAttEdit);
        mRecyAtt.setAdapter(yonghuAdapter);
        yonghuAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mAtt_shaixuan:
                Start1();
                popupWindow11.showAtLocation(popview1.findViewById(R.id.mShuai_quxiao), Gravity.CENTER | Gravity.CENTER, 0, 0);
                break;
        }
    }

    private View popview1;
    private PopupWindow popupWindow11 = new PopupWindow();

    private int a=1;
    private int b=1;
    private int c=1;
    private int d=1;
    //调用相册 相机
    private void Start1() {
        popview1 = LayoutInflater.from(getContext()).inflate(R.layout.shuaixuan_item, null);
        mShuaiPu = popview1.findViewById(R.id.mShuai_pu);
        mShuaiVip = popview1.findViewById(R.id.mShuai_vip);
        mShuaiZai = popview1.findViewById(R.id.mShuai_zai);
        mShuaiLi = popview1.findViewById(R.id.mShuai_li);
        mShuaiQuxiao = popview1.findViewById(R.id.mShuai_quxiao);
        mShuaiSure = popview1.findViewById(R.id.mShuai_sure);
        //Log.e("hjsgechds",d+"    "+a+"   "+b+"   "+c);
        if (d==1){
            type="1";
            mShuaiPu.setTextColor(Color.parseColor("#EF709D"));
            mShuaiPu.setBackgroundResource(R.drawable.shuai_true);
        }else {
            type="";
            mShuaiPu.setTextColor(Color.parseColor("#ffcccccc"));
            mShuaiPu.setBackgroundResource(R.drawable.shuai_false);
        }

        if (a==1){
            type="2";
            mShuaiPu.setTextColor(Color.parseColor("#EF709D"));
            mShuaiPu.setBackgroundResource(R.drawable.shuai_true);
        }else {
            type="";
            mShuaiVip.setTextColor(Color.parseColor("#ffcccccc"));
            mShuaiVip.setBackgroundResource(R.drawable.shuai_false);
        }

        if (b==1){
            isonline="1";
            mShuaiZai.setTextColor(Color.parseColor("#EF709D"));
            mShuaiZai.setBackgroundResource(R.drawable.shuai_true);
        }else {
            isonline="";
            mShuaiZai.setTextColor(Color.parseColor("#ffcccccc"));
            mShuaiZai.setBackgroundResource(R.drawable.shuai_false);
        }

        if (c==1){
            isrecharge="1";
            mShuaiLi.setTextColor(Color.parseColor("#EF709D"));
            mShuaiLi.setBackgroundResource(R.drawable.shuai_true);
        }else {
            isrecharge="";
            mShuaiLi.setTextColor(Color.parseColor("#ffcccccc"));
            mShuaiLi.setBackgroundResource(R.drawable.shuai_false);
        }
        popupWindow11 = new PopupWindow(popview1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow11.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow11.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow11.setTouchable(true);
        popupWindow11.setFocusable(true);
        mShuaiPu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hjsgechds",d+"    "+a+"   "+b+"   "+c);
                if (d==0){
                    d=1;
                    type="1";
                    mShuaiPu.setTextColor(Color.parseColor("#EF709D"));
                    mShuaiPu.setBackgroundResource(R.drawable.shuai_true);
                }else if (d==1){
                    d=0;
                    type="";
                    mShuaiPu.setTextColor(Color.parseColor("#ffcccccc"));
                    mShuaiPu.setBackgroundResource(R.drawable.shuai_false);
                }
            }
        });
        mShuaiVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hjsgechds",d+"    "+a+"   "+b+"   "+c);
                if (a==0){
                    a=1;
                    type="2";
                    mShuaiVip.setTextColor(Color.parseColor("#EF709D"));
                    mShuaiVip.setBackgroundResource(R.drawable.shuai_true);
                }else if (a==1){
                    a=0;
                    type="1";
                    mShuaiVip.setTextColor(Color.parseColor("#ffcccccc"));
                    mShuaiVip.setBackgroundResource(R.drawable.shuai_false);
                }
            }
        });
        mShuaiZai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hjsgechds",d+"    "+a+"   "+b+"   "+c);
                if (b==0){
                    b=1;
                    isonline="1";
                    mShuaiZai.setTextColor(Color.parseColor("#EF709D"));
                    mShuaiZai.setBackgroundResource(R.drawable.shuai_true);
                }else if (b==1){
                    b=0;
                    isonline="";
                    mShuaiZai.setTextColor(Color.parseColor("#ffcccccc"));
                    mShuaiZai.setBackgroundResource(R.drawable.shuai_false);
                }
            }
        });
        mShuaiLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hjsgechds",d+"    "+a+"   "+b+"   "+c);
                if (c==0){
                    c=1;
                    isrecharge="1";
                    mShuaiLi.setTextColor(Color.parseColor("#EF709D"));
                    mShuaiLi.setBackgroundResource(R.drawable.shuai_true);
                }else if (c==1){
                    c=0;
                    isrecharge="";
                    mShuaiLi.setTextColor(Color.parseColor("#ffcccccc"));
                    mShuaiLi.setBackgroundResource(R.drawable.shuai_false);
                }
            }
        });
        mShuaiQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                a=1;
                b=1;
                c=1;
                d=1;
                popupWindow11.dismiss();

            }
        });
        mShuaiSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow11.dismiss();
                Log.e("hjsgechds",d+"    "+a+"   "+b+"   "+c);
                if (a==1&&d==0){
                    yonghupresenter.showData(Sputils.getInstance().getuser_id(), "2", isonline, isrecharge, "1", "100");
                }else if (a==0&&d==1){
                    yonghupresenter.showData(Sputils.getInstance().getuser_id(), "1", isonline, isrecharge, "1", "100");
                }else if (a==1&&d==1){
                    yonghupresenter.showData(Sputils.getInstance().getuser_id(), "3", isonline, isrecharge, "1", "100");
                }else if (a==0||d==0){
                    yonghupresenter.showData(Sputils.getInstance().getuser_id(), "", isonline, isrecharge, "1", "100");
                }else {
                    yonghupresenter.showData(Sputils.getInstance().getuser_id(), type, isonline, isrecharge, "1", "100");
                }

            }
        });
    }
}