package com.zaodong.social.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaodong.social.R;
import com.zaodong.social.bean.Shoubean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShouAdapter extends RecyclerView.Adapter<ShouAdapter.Holer> {
    private ArrayList<Shoubean.DataBean.ListBean> arrayList;
    private Context context;
    public ShouAdapter(ArrayList<Shoubean.DataBean.ListBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public Holer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shouru_item, parent, false);
        Holer holer = new Holer(view);
        return holer;
    }
    @Override
    public void onBindViewHolder(@NonNull Holer holder, int position) {
        Shoubean.DataBean.ListBean listBean = arrayList.get(position);
        //Log.e("date",listBean.getCreatetime()+"");
        holder.mShouName.setText(listBean.getName()+"");
        if (listBean.getType().contains("2")){
            holder.mDataMoney.setText("+"+listBean.getValue()+"");
            holder.mDataMoney.setTextColor(Color.parseColor("#fff2352b"));
        }else {
            holder.mDataMoney.setText("-"+listBean.getValue()+"");
            holder.mDataMoney.setTextColor(Color.parseColor("#00FF1A"));
        }
        String strTime = getStrTime(listBean.getCreatetime() + "");
        holder.mShouDate.setText(strTime);
        holder.itemView.setTag(position);
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holer extends RecyclerView.ViewHolder {
        private TextView mShouName;
        private TextView mShouDate;
        private TextView mDataMoney;
        public Holer(@NonNull View itemView){
            super(itemView);
            mShouName = itemView.findViewById(R.id.mShou_name);
            mShouDate = itemView.findViewById(R.id.mShou_date);
            mDataMoney = itemView.findViewById(R.id.mData_money);
        }
    }
    //时间戳转字符串
    public static String getStrTime(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        long  l = Long.valueOf(timeStamp);
        //Log.e("date",l+"");
        timeString = sdf.format(new Date(l*1000l));//单位秒
        return timeString;
    }
}
