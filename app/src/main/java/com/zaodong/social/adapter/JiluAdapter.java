package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.bean.Jilutelefalsebean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class JiluAdapter extends RecyclerView.Adapter<JiluAdapter.Holder> {
    private ArrayList<Jilutelefalsebean.DataBean> arrayList;
    private Context context;
    private OnItemClickboda onItemClickboda1;


    private OnIcon onIcon;




    public JiluAdapter(ArrayList<Jilutelefalsebean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jilu_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    public interface OnItemClickboda{
        void OnItemClickboda1(View view,int position);
    }


    public void setOnItemClickboda1(OnItemClickboda onItemClickboda1){
        this.onItemClickboda1=onItemClickboda1;
    }


    public interface OnIcon{
        void OnIcon(View view,int position);
    }

    public void setOnIcon(OnIcon onIcon){
        this.onIcon=onIcon;

    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Jilutelefalsebean.DataBean dataBean = arrayList.get(position);
        Glide.with(context).load(dataBean.getAvatar()).into(holder.mJiluImage);
        holder.mJiluName.setText(dataBean.getNickname()+"");
        holder.mJiluPrice.setText(dataBean.getMoney()+"钻");
        String strTime = getStrTime(dataBean.getCreatetime() + "");
        holder.mJiluDate.setText(strTime);
        holder.mJiluBoda.setText("拨打");
        holder.mJiluBoda.setTag(position);
        holder.itemView.setTag(position);
        holder.mJiluBoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickboda1!=null){
                    onItemClickboda1.OnItemClickboda1(view, (Integer) view.getTag());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onIcon!=null){
                    onIcon.OnIcon(view, (Integer) view.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        private CircleImageView mJiluImage;
        private TextView mJiluName;
        private TextView mJiluPrice;
        private TextView mJiluDate;
        private TextView mJiluBoda;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mJiluImage = itemView.findViewById(R.id.mJilu_image);
            mJiluName =itemView.findViewById(R.id.mJilu_name);
            mJiluPrice =itemView.findViewById(R.id.mJilu_price);
            mJiluDate = itemView.findViewById(R.id.mJilu_date);
            mJiluBoda = itemView.findViewById(R.id.mJilu_boda);
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
