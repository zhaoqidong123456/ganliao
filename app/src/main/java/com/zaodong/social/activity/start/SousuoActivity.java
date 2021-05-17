package com.zaodong.social.activity.start;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zaodong.social.R;
import com.zaodong.social.adapter.JokerAdapter;
import com.zaodong.social.bean.Jokerbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.start.ISearchpresenter;
import com.zaodong.social.presenter.start.Searchpresenter;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.Searcview;

import java.util.ArrayList;

public class SousuoActivity extends AppCompatActivity implements Searcview, View.OnClickListener {

    private RecyclerView mSou_recy;
    private TextView mSousuo_zanwu;
    private RecyclerView mSou_recy2;
    private ISearchpresenter searchpresenter;
    private EditText mSou_edit;
    private ArrayList<Jokerbean.DataBean> arrayList = new ArrayList<>();
    private ArrayList<Jokerbean.DataBean> arrayList1 = new ArrayList<>();
    private JokerAdapter jokerAdapter;
    private TextView mSou_quxiao;
    private LinearLayout mSousuo_tui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        initView();
        initStatusBar();
        searchpresenter = new Searchpresenter(this);
        searchpresenter.loadDatare(Sputils.getInstance().getuser_id());
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }

    private void initView() {
        mSousuo_tui=findViewById(R.id.mSousuo_tui);
        mSou_edit = (EditText) findViewById(R.id.mSou_edit);
        mSou_recy = (RecyclerView) findViewById(R.id.mSou_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mSou_recy.setLayoutManager(gridLayoutManager);
        mSousuo_zanwu = (TextView) findViewById(R.id.mSousuo_zanwu);
        mSou_recy2 = (RecyclerView) findViewById(R.id.mSou_recy2);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 2);
        mSou_recy2.setLayoutManager(gridLayoutManager1);
        mSou_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//关闭软键盘
                    if (mSou_edit.getText().toString().length() > 0) {
                        mSou_recy2.setVisibility(View.GONE);
                        mSousuo_tui.setVisibility(View.GONE);
                        searchpresenter.loadData(Sputils.getInstance().getuser_id(), mSou_edit.getText().toString());
                    } else {
                        Toast.makeText(SousuoActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        mSou_quxiao = (TextView) findViewById(R.id.mSou_quxiao);
        mSou_quxiao.setOnClickListener(this);
    }

    @Override
    public void showdata(final Jokerbean activebean) {
        arrayList.clear();
        arrayList.addAll(activebean.getData());
        if (arrayList.size() <= 0) {
            mSousuo_zanwu.setVisibility(View.VISIBLE);
            mSou_recy.setVisibility(View.GONE);
        } else {
            mSousuo_zanwu.setVisibility(View.GONE);
            mSou_recy.setVisibility(View.VISIBLE);
            jokerAdapter = new JokerAdapter(arrayList, this);
            mSou_recy.setAdapter(jokerAdapter);
            jokerAdapter.notifyDataSetChanged();
            jokerAdapter.setOnItemClickwang(new JokerAdapter.OnItemClickwang() {
                @Override
                public void OnItemClickwang(View view, int position) {
                    Intent intent = new Intent(SousuoActivity.this, DetailsActivity.class);
                    intent.putExtra(DetailsActivity.DETAIL_ID,activebean.getData().get(position).getUser_id()+"");
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void showdataf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showdatat(final Jokerbean jokerbean) {
        arrayList1.clear();
        arrayList1.addAll(jokerbean.getData());
        jokerAdapter = new JokerAdapter(arrayList1, this);
        mSou_recy2.setAdapter(jokerAdapter);
        jokerAdapter.notifyDataSetChanged();
        jokerAdapter.setOnItemClickwang(new JokerAdapter.OnItemClickwang() {
            @Override
            public void OnItemClickwang(View view, int position) {
                Sputils.getInstance().setau_id(jokerbean.getData().get(position).getUser_id()+"");
                Intent intent = new Intent(SousuoActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,jokerbean.getData().get(position).getUser_id()+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void showdataff(Yzmfbean yzmfbean) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mSou_quxiao:
                finish();
                break;
        }
    }
}
