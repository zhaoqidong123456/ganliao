package com.zaodong.social.view;

import com.zaodong.social.bean.Presonalbean;
import com.zaodong.social.bean.Savebean;
import com.zaodong.social.bean.Yzmfbean;

public interface Presonalview {
    void showDatap(Presonalbean presonalbean);
    void showDatapf(Yzmfbean yzmfbean);


    void showDatasave(Savebean savebean);
    void showDatapfsave(Yzmfbean yzmfbean);
}
