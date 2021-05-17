package com.zaodong.social.view;

import com.zaodong.social.bean.Loginbean;
import com.zaodong.social.bean.Yzmbean;
import com.zaodong.social.bean.Yzmfbean;

public interface Yzmview {
    void showDataYzm(Yzmbean yzmbean);
    void showDatayzmf(Yzmfbean yzmfbean);


    void showDataLogin(Loginbean yzmbean);
    void showDataLoginf(Yzmfbean yzmfbean);
}
