package com.zaodong.social.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.zaodong.social.R;
import com.zaodong.social.activity.presonal.AgeActivity;
import com.zaodong.social.activity.presonal.EntriesActivity;
import com.zaodong.social.activity.presonal.GenderActivity;
import com.zaodong.social.activity.presonal.NameActivity;
import com.zaodong.social.adapter.AddCarImage;
import com.zaodong.social.bean.Presonalbean;
import com.zaodong.social.bean.Savebean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.Chuanpresenter;
import com.zaodong.social.presenter.IChuanpresenter;
import com.zaodong.social.presenter.login.IPresonalpresenter;
import com.zaodong.social.presenter.login.Presonalpresenter;
import com.zaodong.social.utils.BitmapUtil;
import com.zaodong.social.utils.DensityUtil;
import com.zaodong.social.utils.LoadDialogUtils;
import com.zaodong.social.utils.StatusBarUtils;
import com.zaodong.social.view.ChuanBean;
import com.zaodong.social.view.Chuanview;
import com.zaodong.social.view.Presonalview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhotosActivity extends AppCompatActivity implements View.OnClickListener, Chuanview, Presonalview {

    private ImageButton mPhotos_back;
    private RecyclerView mPhotos_recy;
    //多图上传
    private ArrayList<String> mSelectImage = new ArrayList<>();//刚选择的
    private ArrayList<String> mUpLoadImage = new ArrayList<>();//需要上传的
    private ArrayList<String> resultImage = new ArrayList<>();//最终结果
    private AddCarImage mAddImageAdapter;
    private static int REQUEST_PHOTOS = 101;
    private static int REQUEST_CAMERA_PHOTO = 102;
    private IChuanpresenter chuanpresenter;
    private IPresonalpresenter presonalpresenter;
    private TextView mPhoto_save;
    private TextView mPhoto_name;
    private TextView mPhoto_gender;
    private TextView mPhoto_age;
    private TextView mPhoto_qian;
    private String citiesCommaSeparated1;
    private String s;
    private String citiesCommaSeparated;
    private LinearLayout mPhoto_name_lin;
    private LinearLayout mPhoto_gender_lin;
    private LinearLayout mPhoto_age_lin;
    private LinearLayout mPhoto_qian_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        initStatusBar();
        chuanpresenter = new Chuanpresenter(this);
        presonalpresenter = new Presonalpresenter(this);
        presonalpresenter.loadData(Sputils.getInstance().getuser_id());
        Sputils.getInstance().setbianji("0");
        initView();
    }
    private void initView() {
        mPhotos_back = (ImageButton) findViewById(R.id.mPhotos_back);
        mPhotos_recy = (RecyclerView) findViewById(R.id.mPhotos_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mPhotos_recy.setLayoutManager(gridLayoutManager);
        mPhotos_back.setOnClickListener(this);
        int itemWidth = (DensityUtil.getScreenWidth(this) - DensityUtil.dip2px(this, 160)) / 3;
        mAddImageAdapter = new AddCarImage(this, itemWidth);
        mPhotos_recy.setAdapter(mAddImageAdapter);
        mAddImageAdapter.setOnItemClickListen(new AddCarImage.OnItemClickListen() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getId() == R.id.upload_photo_layout) {
                    //添加照片
                    selectImage();
                } else if (view.getId() == R.id.remove) {
                    //移除
                    if (mUpLoadImage.size() > position) {
                        mSelectImage.remove(position);
                        mUpLoadImage.remove(position);
                    }
                    if (resultImage.size() > position) {
                        resultImage.remove(position);
                    }
                    mAddImageAdapter.upDate(mUpLoadImage);
                }
            }
        });
        mPhoto_save = (TextView) findViewById(R.id.mPhoto_save);
        mPhoto_save.setOnClickListener(this);
        mPhoto_name = (TextView) findViewById(R.id.mPhoto_name);
        mPhoto_gender = (TextView) findViewById(R.id.mPhoto_gender);
        mPhoto_age = (TextView) findViewById(R.id.mPhoto_age);
        mPhoto_qian = (TextView) findViewById(R.id.mPhoto_qian);
        mPhoto_name_lin = (LinearLayout) findViewById(R.id.mPhoto_name_lin);
        mPhoto_name_lin.setOnClickListener(this);
        mPhoto_gender_lin = (LinearLayout) findViewById(R.id.mPhoto_gender_lin);
        mPhoto_gender_lin.setOnClickListener(this);
        mPhoto_age_lin = (LinearLayout) findViewById(R.id.mPhoto_age_lin);
        mPhoto_age_lin.setOnClickListener(this);
        mPhoto_qian_lin = (LinearLayout) findViewById(R.id.mPhoto_qian_lin);
        mPhoto_qian_lin.setOnClickListener(this);
    }
    private String a="";
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mPhotos_back:
                finish();
                break;
            case R.id.mPhoto_save:
                if (arrayList.size() > 0) {
                    s = arrayList.get(0);
                    arrayList.remove(arrayList.get(0));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        citiesCommaSeparated1 = arrayList.stream().collect(Collectors.joining(","));
                        //Log.e("选择完成后",  citiesCommaSeparated1 + "");
                    }
                }
                if (mPhoto_gender.getText().toString().contains("男")){
                    a="1";
                }else {
                    a="0";
                }
                presonalpresenter.loadDatasave(Sputils.getInstance().getuser_id(), mPhoto_name.getText().toString(),a
                        , s, mPhoto_age.getText().toString(), mPhoto_qian.getText().toString(), citiesCommaSeparated1);
                break;
            case R.id.mPhoto_qian_lin:
                Intent intent = new Intent(this, EntriesActivity.class);
                startActivityForResult(intent, 111);
                break;
            case R.id.mPhoto_name_lin:
                Intent intent1 = new Intent(this, NameActivity.class);
                startActivityForResult(intent1, 222);
                break;
            case R.id.mPhoto_gender_lin:
                Intent intent2 = new Intent(this, GenderActivity.class);
                startActivityForResult(intent2, 333);
                break;
            case R.id.mPhoto_age_lin:
                Intent intent3 = new Intent(this, AgeActivity.class);
                startActivityForResult(intent3, 444);
                break;
        }
    }
    private void selectImage() {
        ImageSelector.builder()
                .useCamera(false) // 设置是否使用拍照
                .setSingle(false)  //设置是否单选
                .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                .setViewImage(true) //是否点击放大图片查看,，默认为true
                .start(this, REQUEST_PHOTOS); // 打开相册
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PHOTOS) {
                //相册
                //dialog1 = LoadDialogUtils.createLoadingDialog(this, "上传中");
                //mUpLoadImage.clear();
                ArrayList<String> images = data.getStringArrayListExtra(
                        ImageSelectorUtils.SELECT_RESULT);
                mUpLoadImage.addAll(images);
                mSelectImage.addAll(images);
                mAddImageAdapter.upDate(mUpLoadImage);
                upLoadImage();
                //mSelectImage.setVisibility(View.GONE);
                //   dialog();
            }
        } else {
        }

        if (requestCode == 111 && resultCode == 111) {
            String entri = data.getStringExtra("entri");
            mPhoto_qian.setText(entri);
        } else if (requestCode == 222 && resultCode == 222) {
            String name = data.getStringExtra("name");
            mPhoto_name.setText(name);
        } else if (requestCode == 333 && resultCode == 333) {
            String gender = data.getStringExtra("gender");
            mPhoto_gender.setText(gender);
        } else if (requestCode == 444 && resultCode == 444) {
            String age = data.getStringExtra("age");
            mPhoto_age.setText(age);
        }
    }


    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }

    private Dialog dialog1;

    private void upLoadImage() {
        dialog1 = LoadDialogUtils.createLoadingDialog(this, "上传中...");
        resultImage.clear();
        if (mUpLoadImage.size() > 0) {
            if (mUpLoadImage.get(0) != null) {
                for (int i = 0; i < mUpLoadImage.size(); i++) {
                    if (mUpLoadImage.get(i).contains("http")) {
                    } else {
                        String imgPath = BitmapUtil.compressImageUpload(mUpLoadImage.get(i));
                        chuanpresenter.loadData(imgPath);
                    }
                }
                //Log.e("chang_finish",mUpLoadImage.size()+"     "+arrayList.size());
                LoadDialogUtils.closeDialog(dialog1);
            }
        }
        //LoadDialogUtils.closeDialog(dialog);
    }

    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public void showData(ChuanBean chuanBean) {
        arrayList.add(chuanBean.getData().getFullurl() + "");
        //Log.e("chang_a",arrayList.size()+"");
    }

    @Override
    public void showDataCuo(Yzmfbean fbean) {
        Toast.makeText(this, fbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDatap(Presonalbean presonalbean) {
        mUpLoadImage.add(presonalbean.getData().getAvatar());
        arrayList.add(presonalbean.getData().getAvatar());
        String backgroundimages = presonalbean.getData().getBackgroundimages() + "";
        if (backgroundimages.length() > 20) {
            List<String> strings2 = Arrays.asList(presonalbean.getData().getBackgroundimages().split(","));
            for (int i = 0; i < strings2.size(); i++) {
                mUpLoadImage.add(strings2.get(i));
                arrayList.add(strings2.get(i));
            }
            mAddImageAdapter.upDate(mUpLoadImage);
        } else {
            mAddImageAdapter.upDate(mUpLoadImage);
        }
        //Log.e("chang",mUpLoadImage.size()+"");
        mAddImageAdapter.setImageClick(new AddCarImage.ImageClick() {
            @Override
            public void ImageClick(View view, int position) {
                if (arrayList.size() == mUpLoadImage.size()) {
                    if (mUpLoadImage.size() > 1) {
                        mUpLoadImage.remove(position);
                        arrayList.remove(position);
                        mAddImageAdapter.upDate(mUpLoadImage);
                    } else {
                        //Log.e("chang",mUpLoadImage.size()+"");
                        Toast.makeText(PhotosActivity.this, "至少留一张作为头像", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PhotosActivity.this, "有图片未上传成功，请返回重试", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        mPhoto_name.setText(presonalbean.getData().getNickname() + "");
        if (presonalbean.getData().getGender()==0) {
            mPhoto_gender.setText("女");
        } else {
            mPhoto_gender.setText("男");
        }
        mPhoto_age.setText(presonalbean.getData().getAge() + "");
        mPhoto_qian.setText(presonalbean.getData().getBio() + "");
    }

    @Override
    public void showDatapf(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDatasave(Savebean savebean) {
        Intent intent = getIntent();
        setResult(1, intent);
        finish();
    }

    @Override
    public void showDatapfsave(Yzmfbean yzmfbean) {
        Toast.makeText(this, yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }
}
