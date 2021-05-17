package com.zaodong.social.view;

import com.zaodong.social.bean.Activebean;
import com.zaodong.social.bean.Madoubean;
import com.zaodong.social.bean.Yzmfbean;

public interface Activeview{
    void showData(Activebean activebean);
    void showDataf(Yzmfbean activebean);


    void showDataMadou(Madoubean activebean);
    void showDataMadouf(Yzmfbean activebean);
}
