package com.zaodong.social.activity.presonal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zaodong.social.R;
import com.zaodong.social.utils.MDatePicker;
import com.zaodong.social.utils.StatusBarUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mAge_back;
    private TextView mAge_save;
    private TextView mAge_edit_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
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
        mAge_back = (ImageButton) findViewById(R.id.mAge_back);
        mAge_save = (TextView) findViewById(R.id.mAge_save);
        mAge_save.setOnClickListener(this);
        mAge_edit_age = (TextView) findViewById(R.id.mAge_edit_age);
        mAge_edit_age.setOnClickListener(this);
        mAge_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mAge_back:
                finish();
                break;
            case R.id.mAge_save:
                if (mAge_edit_age.getText().toString().contains("0")){
                    Intent intent = getIntent();
                    intent.putExtra("age",mAge_edit_age.getText().toString());
                    setResult(444,intent);
                    finish();
                }else {
                    Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mAge_edit_age:
                MDatePicker.create(this)
                        .setCanceledTouchOutside(true)
                        .setGravity(Gravity.BOTTOM)
                        .setSupportTime(true)
                        .setTwelveHour(true)
                        .setOnDateResultListener(new MDatePicker.OnDateResultListener() {
                            @Override
                            public void onDateResult(long date) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(date);
                                SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
                                dateFormat.applyPattern("yyyy-MM-dd");
                                mAge_edit_age.setText(dateFormat.format(new Date(date)));
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }
}
