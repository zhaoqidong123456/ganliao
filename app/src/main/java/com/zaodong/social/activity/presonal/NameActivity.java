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

public class NameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mName_back;
    private TextView mName_save;
    private EditText mName_edit_entri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
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
        mName_back = (ImageButton) findViewById(R.id.mName_back);
        mName_save = (TextView) findViewById(R.id.mName_save);
        mName_save.setOnClickListener(this);
        mName_edit_entri = (EditText) findViewById(R.id.mName_edit_entri);
        mName_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mName_back:
                finish();
                break;
            case R.id.mName_save:
                if (mName_edit_entri.getText().toString().length()<=0){
                    Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = getIntent();
                    intent.putExtra("name",mName_edit_entri.getText().toString());
                    setResult(222,intent);
                    finish();
                }
                break;
        }
    }
}
