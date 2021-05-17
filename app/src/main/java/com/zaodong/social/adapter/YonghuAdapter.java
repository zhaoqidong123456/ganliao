package com.zaodong.social.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.bean.Yonghubean;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class YonghuAdapter extends RecyclerView.Adapter<YonghuAdapter.Holder>  implements View.OnClickListener {
    private ArrayList<Yonghubean.DataBean> arrayList;
    private Context context;
    private OnYonguDaZaho onYonguDaZaho;
    private EditText editText;

    public YonghuAdapter(ArrayList<Yonghubean.DataBean> arrayList, Context context, EditText editText) {
        this.arrayList = arrayList;
        this.context = context;
        this.editText = editText;
    }

    @Override
    public void onClick(View view) {
        if (onYonguDaZaho!=null){
            onYonguDaZaho.OnYonguDaZaho(view, (Integer) view.getTag());
        }
    }

    public interface OnYonguDaZaho{
        void OnYonguDaZaho(View view,int position);
    }

    public void setOnYonguDaZaho(OnYonguDaZaho onYonguDaZaho){
        this.onYonguDaZaho=onYonguDaZaho;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yonghu_item,parent,false);
        Holder holder = new Holder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Yonghubean.DataBean dataBean = arrayList.get(position);
        Glide.with(context).load(dataBean.getAvatar()).into(holder.image);
        holder.name.setText(dataBean.getNickname()+"");
        if (dataBean.getVip().contains("2")||dataBean.getVip().contains("3")){
            holder.mYonghu_svip.setVisibility(View.VISIBLE);
        }else {
            holder.mYonghu_svip.setVisibility(View.GONE);
        }

        if (dataBean.getLevel().contains("lv1")&&dataBean.getLevel().length()==3){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_one);
        }else if (dataBean.getLevel().contains("lv2")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_two);
        }else if (dataBean.getLevel().contains("lv3")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_three);
        }else if (dataBean.getLevel().contains("lv4")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_gour);
        }else if (dataBean.getLevel().contains("lv5")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_five);
        }else if (dataBean.getLevel().contains("lv6")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_six);
        }else if (dataBean.getLevel().contains("lv7")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_seven);
        }else if (dataBean.getLevel().contains("lv8")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_eight);
        }else if (dataBean.getLevel().contains("lv9")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_nine);
        }else if (dataBean.getLevel().contains("lv10")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_ten);
        }else if (dataBean.getLevel().contains("lv11")){
            holder.mYonghu_lavel.setImageResource(R.mipmap.laver_tenone);
        }
        if (dataBean.getOnline().contains("1")){
            holder.zaixian.setVisibility(View.VISIBLE);
        }else {
            holder.zaixian.setVisibility(View.GONE);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length()<=0){
                    Toast.makeText(context, "请输入打招呼的文案", Toast.LENGTH_SHORT).show();
                }else {
                    String account = arrayList.get(position).getYx_accid();
                    SessionTypeEnum sessionType = SessionTypeEnum.P2P;
                    IMMessage textMessage = MessageBuilder.createTextMessage(account, sessionType, editText.getText().toString());
                    NIMClient.getService(MsgService.class).sendMessage(textMessage, false).setCallback(new RequestCallback<Void>() {
                        @Override
                        public void onSuccess(Void param) {
                            holder.hujiao.setText("发送中");
                            holder.hujiao.setEnabled(false);
                            Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailed(int code) {
                            Toast.makeText(context, "发送成功1   "+code, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onException(Throwable exception) {
                            Toast.makeText(context, "发送成功1", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private CircleImageView image;
        private TextView name,zaixian,hujiao;
        private ImageView mYonghu_lavel,mYonghu_svip;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.mYonghu_image);
            name=itemView.findViewById(R.id.mYonghu_name);
            zaixian=itemView.findViewById(R.id.mYonghu_zaixian);
            hujiao=itemView.findViewById(R.id.mYonghu_hujiao);
            mYonghu_lavel=itemView.findViewById(R.id.mYonghu_lavel);
            mYonghu_svip=itemView.findViewById(R.id.mYonghu_svip);
        }
    }
}
