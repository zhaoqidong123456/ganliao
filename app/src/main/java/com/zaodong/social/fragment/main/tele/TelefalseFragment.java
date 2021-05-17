package com.zaodong.social.fragment.main.tele;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zaodong.social.R;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.JiluAdapter;
import com.zaodong.social.bean.Jilutelefalsebean;
import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.zhifu.IJiluTelepresenter;
import com.zaodong.social.presenter.zhifu.JiluTelepresenter;
import com.zaodong.social.view.Jiluteleview;
import com.zaodong.social.view.Telephoneview;
import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;

import java.util.ArrayList;

public class TelefalseFragment extends Fragment  implements Jiluteleview, Telephoneview {
    private View view;
    private RecyclerView mTeleRecyFalse;
    private IJiluTelepresenter jiluTelepresenter;
    private LinearLayout mTeleFalseWu;
    private ArrayList<Jilutelefalsebean.DataBean> arrayList=new ArrayList<>();
    private JiluAdapter jiluAdapter;
    private ITelephonepresenter telephonepresenter;
    private String yx_accid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_telefalse, container, false);
        jiluTelepresenter = new JiluTelepresenter(this);
        telephonepresenter=new Telephonepresenter(this);
        jiluTelepresenter.showDatafalse(Sputils.getInstance().getuser_id(), "1", "1", "100");
        initView();
        return view;
    }
    private void initView() {
        mTeleRecyFalse = view.findViewById(R.id.mTele_recy_false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mTeleRecyFalse.setLayoutManager(linearLayoutManager);
        mTeleFalseWu = view.findViewById(R.id.mTele_false_wu);
    }
    @Override
    public void showDatafalse(Jilutelefalsebean jilutelefalsebean) {
        arrayList.clear();
        arrayList.addAll(jilutelefalsebean.getData());

        if (arrayList.size()>0){
            mTeleRecyFalse.setVisibility(View.VISIBLE);
            mTeleFalseWu.setVisibility(View.GONE);
            jiluAdapter=new JiluAdapter(arrayList,getContext());
            mTeleRecyFalse.setAdapter(jiluAdapter);
            jiluAdapter.notifyDataSetChanged();
            jiluAdapter.setOnItemClickboda1(new JiluAdapter.OnItemClickboda() {
                @Override
                public void OnItemClickboda1(View view, int position) {
                    yx_accid = arrayList.get(position).getYx_accid();
                    if (Sputils.getInstance().gettype().contains("2")){
                        telephonepresenter.showDataStart(arrayList.get(position).getUser_id()+"",Sputils.getInstance().getuser_id());
                    }else {
                        telephonepresenter.showDataStart(Sputils.getInstance().getuser_id(),arrayList.get(position).getUser_id()+"");
                    }
                }
            });
            jiluAdapter.setOnIcon(new JiluAdapter.OnIcon() {
                @Override
                public void OnIcon(View view, int position) {
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra(DetailsActivity.DETAIL_ID,arrayList.get(position).getUser_id()+"");
                    startActivity(intent);
                }
            });
        }else {
            mTeleRecyFalse.setVisibility(View.GONE);
            mTeleFalseWu.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showDataStart(Telebeanstart telebeanstart) {
        if (telebeanstart.getCode()==2000){
            AVChatKit.outgoingCall(getContext(), yx_accid, UserInfoHelper.getUserDisplayName(yx_accid), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
        }else {
            Toast.makeText(getContext(), telebeanstart.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {
        if (yzmfbean.getMsg().contains("余额不足")){
            Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MymoneyActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDatafalse(Telebeanfalse telebeanstart) {

    }

    @Override
    public void showDatafalsef(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDatatrue(Telebeantrue telebeanstart) {

    }

    @Override
    public void showDatatrue(Jilutelefalsebean jilutelefalsebean) {

    }
    @Override
    public void showDatatruef(Yzmfbean jilutelefalsebean) {

    }

    @Override
    public void showDataup(Telebeanup telebeanup) {

    }

    @Override
    public void showDataupf(Yzmfbean yzmfbean) {

    }
}