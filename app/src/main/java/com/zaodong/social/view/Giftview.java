package com.zaodong.social.view;

import com.zaodong.social.bean.Giftbean;
import com.zaodong.social.bean.SendGiftbean;
import com.zaodong.social.bean.Yzmfbean;

public interface Giftview {
    void showData(Giftbean giftbean);
    void showDataf(Yzmfbean yzmfbean);


    void showDatasend(SendGiftbean sendGiftbean);
    void showDatafsend(Yzmfbean yzmfbean);
}
