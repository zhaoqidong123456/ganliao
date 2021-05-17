package com.zaodong.social.presenter;

public interface ITelephonepresenter {
    void showDataStart(String userid, String au_id);
    void showDatafalse(String userid, String au_id);
    void showDatatrue(String userid, String au_id, String tong_id);
    void showDataup(String userid, String au_id);
}
