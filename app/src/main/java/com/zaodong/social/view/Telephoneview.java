package com.zaodong.social.view;


import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Yzmfbean;

public interface Telephoneview {
    void showDataStart(Telebeanstart telebeanstart);
    void showDataStartf(Yzmfbean yzmfbean);

    void showDatafalse(Telebeanfalse telebeanstart);
    void showDatafalsef(Yzmfbean yzmfbean);

    void showDatatrue(Telebeantrue telebeanstart);
    void showDatatruef(Yzmfbean yzmfbean);

    void showDataup(Telebeanup telebeanup);
    void showDataupf(Yzmfbean yzmfbean);
}
