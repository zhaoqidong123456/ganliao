package com.netease.nim.uikit.business.recent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.common.ui.drop.DropFake;
import com.netease.nim.uikit.common.ui.imageview.HeadImageView;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.ArrayList;
import java.util.List;

public class RecentContactsItem extends RelativeLayout {


    private Context context;
    private int lastUnreadCount = 0;

    protected FrameLayout portraitPanel;

    protected ImageView imgHead;

    protected TextView tvNickname;

    protected TextView tvMessage;

    protected TextView tvDatetime;

    protected ImageView imgMsgStatus;

    protected View bottomLine;

    protected View topLine;

    // 未读红点（一个占坑，一个全屏动画）
    protected DropFake tvUnread;

    private ImageView imgUnreadExplosion;

    protected TextView tvOnlineState;

    private static Integer labelWidth;

    public RecentContactsItem(Context context) {
        this(context, null);
    }

    public RecentContactsItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecentContactsItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecentContactsItem);
        Drawable image = a.getDrawable(R.styleable.RecentContactsItem_contact_image);

        String title = a.getString(R.styleable.RecentContactsItem_contact_title);
        String subTitle = a.getString(R.styleable.RecentContactsItem_contact_subtitle);
        String time = a.getString(R.styleable.RecentContactsItem_contact_time);
        int unRead = a.getInteger(R.styleable.RecentContactsItem_contact_unread, -1);

        imgHead.setImageDrawable(image);
        tvNickname.setText(title);
        tvOnlineState.setText(subTitle);
        tvDatetime.setText(time);
        if (unRead > 0) {
            tvUnread.setVisibility(VISIBLE);
            tvUnread.setText(unRead + "");
        }
    }
    private void initView() {
        inflate(context, R.layout.contact_recent_list_recording, this);
        this.portraitPanel = findViewById(R.id.portrait_panel);
        this.imgHead = findViewById(R.id.img_head);
        this.tvNickname = findViewById(R.id.tv_nickname);
        this.tvMessage = findViewById(R.id.tv_message);
        this.tvUnread = findViewById(R.id.unread_number_tip);
        this.imgUnreadExplosion = findViewById(R.id.unread_number_explosion);
        this.tvDatetime = findViewById(R.id.tv_date_time);
        this.imgMsgStatus = findViewById(R.id.img_msg_status);
        this.bottomLine = findViewById(R.id.bottom_line);
        this.topLine = findViewById(R.id.top_line);
        this.tvOnlineState = findViewById(R.id.tv_online_state);
        tvUnread.setOnClickListener(v -> {

        });
    }
}
