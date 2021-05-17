package com.zaodong.social.activity.presonal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zaodong.social.R;
import com.zaodong.social.utils.StatusBarUtils;

public class EntriesActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mEntri_back;
    private TextView mEntri_save;
    private EditText mEntri_edit_entri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        initView();
        initStatusBar();
    }

    private void initView() {
        mEntri_back = (ImageButton) findViewById(R.id.mEntri_back);
        mEntri_save = (TextView) findViewById(R.id.mEntri_save);
        mEntri_save.setOnClickListener(this);
        mEntri_edit_entri = (EditText) findViewById(R.id.mEntri_edit_entri);
        mEntri_back.setOnClickListener(this);
    }
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mEntri_back:
                finish();
                break;
            case R.id.mEntri_save:
                if (mEntri_edit_entri.getText().toString().length()<=0){
                    Toast.makeText(this, "请输入签名", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    intent.putExtra("entri",mEntri_edit_entri.getText().toString());
                    setResult(111,intent);
                    finish();
                }
                break;
        }
    }
}
