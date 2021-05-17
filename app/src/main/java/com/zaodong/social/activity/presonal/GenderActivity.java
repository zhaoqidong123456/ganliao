package com.zaodong.social.activity.presonal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aigestudio.wheelpicker.WheelPicker;
import com.zaodong.social.R;
import com.zaodong.social.utils.StatusBarUtils;

import java.util.ArrayList;

public class GenderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mGender_back;
    private TextView mGender_save;
    private TextView mGender_edit_entri;
    WheelPicker wheelPicker;
    private String string;
    private ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        arrayList.add("男");
        arrayList.add("女");
        initView();
        initStatusBar();
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }

    private void initView() {
        mGender_back = (ImageButton) findViewById(R.id.mGender_back);
        mGender_save = (TextView) findViewById(R.id.mGender_save);
        mGender_save.setOnClickListener(this);
        mGender_edit_entri = (TextView) findViewById(R.id.mGender_edit_entri);
        mGender_back.setOnClickListener(this);
        wheelPicker = findViewById(R.id.mGender_wap);
        wheelPicker.setIndicatorColor(Color.parseColor("#000000"));
        wheelPicker.setCyclic(false);
        // 查看是否循环显示
        wheelPicker.isCyclic();
        //设置是否有指示器，设置后选中项的上下会用线框柱
        wheelPicker.setIndicator(true);
        wheelPicker.setIndicatorColor(0xFF123456); //16进制
        wheelPicker.setIndicatorSize(3); //单位是px
        // 设置是否有幕布，设置后选中项会被指定的颜色覆盖，默认false
        wheelPicker.setCurtain(false);
        wheelPicker.setCurtainColor(0xFF777777);
        // 设置是否有空气感，设置后上下边缘会渐变为透明，默认false
        wheelPicker.setAtmospheric(true);
        // 设置是否有卷曲感，不能微调卷曲幅度，默认false
        wheelPicker.setCurved(true);
        // 设置item的排列，左中右，默认中
        wheelPicker.setItemAlign(WheelPicker.ALIGN_CENTER);
        wheelPicker.setData(arrayList);
        //选择监听器，会监听被选中的item（滑动停止后），需要自己做强制类型转换
        wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                string= (String) data;
                mGender_edit_entri.setText(string);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mGender_back:
                finish();
                break;
            case R.id.mGender_save:
                if (mGender_edit_entri.getText().toString().length()<=0){
                    Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = getIntent();
                    intent.putExtra("gender",mGender_edit_entri.getText().toString());
                    setResult(333,intent);
                    finish();
                }
                break;
        }
    }
}
