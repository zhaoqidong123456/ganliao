package com.netease.nim.uikit.interfaces;

import android.view.View;
import android.widget.EditText;

import com.netease.nim.uikit.business.session.module.Container;
import com.netease.nim.uikit.common.media.imagepicker.Constants;

public interface Fasonginterface {
    void Sendtext(EditText editText, Container container, boolean isRobotSession, View view);
}
