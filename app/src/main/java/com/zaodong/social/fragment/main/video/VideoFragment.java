package com.zaodong.social.fragment.main.video;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.GiftAdapter;
import com.zaodong.social.bean.Dianbean;
import com.zaodong.social.bean.EventBusBean;
import com.zaodong.social.bean.Giftbean;
import com.zaodong.social.bean.Mybean;
import com.zaodong.social.bean.SendGiftbean;
import com.zaodong.social.bean.Telebeanfalse;
import com.zaodong.social.bean.Telebeanstart;
import com.zaodong.social.bean.Telebeantrue;
import com.zaodong.social.bean.Telebeanup;
import com.zaodong.social.bean.Videobean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.Dianpresenter;
import com.zaodong.social.presenter.IDianpresenter;
import com.zaodong.social.presenter.ILiupresenter;
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.Liupresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.login.IMypresenter;
import com.zaodong.social.presenter.login.Mypresenter;
import com.zaodong.social.presenter.video.IVideopresenter;
import com.zaodong.social.presenter.video.Videopresenter;
import com.zaodong.social.presenter.vip.Giftpresenter;
import com.zaodong.social.presenter.vip.IGiftpresenter;
import com.zaodong.social.ui.JzvdStdTikTok;
import com.zaodong.social.utils.LoadDialogUtils;
import com.zaodong.social.utils.OnViewPagerListener;
import com.zaodong.social.utils.ViewPagerLayoutManager;
import com.zaodong.social.view.Dianview;
import com.zaodong.social.view.Giftview;
import com.zaodong.social.view.Liuview;
import com.zaodong.social.view.Myview;
import com.zaodong.social.view.Telephoneview;
import com.zaodong.social.view.Videoview;
import com.netease.nim.avchatkit.AVChatKit;
import com.netease.nim.avchatkit.activity.AVChatActivity;
import com.netease.nim.uikit.extension.GiftsAttachment;
import com.netease.nim.uikit.bean.GiftsAttachmentBean;
import com.netease.nim.uikit.business.uinfo.UserInfoHelper;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cn.jzvd.JZDataSource;
import cn.jzvd.JZMediaAliyun;
import cn.jzvd.Jzvd;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements Videoview, Dianview, Giftview, Telephoneview, Liuview, Myview {
    private View view;
    private IVideopresenter videopresenter;
    private ArrayList<Videobean.DataBean> arrayList = new ArrayList<>();
    private RecyclerView mVideo_recy;
    private ViewPagerLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private IDianpresenter dianpresenter;
    private IGiftpresenter giftpresenter;
    private String user_id;
    private int id = 0;
    private String price;
    private String contactId;
    private ITelephonepresenter telephonepresenter;
    private boolean isCreate = false;
    private JzvdStdTikTok player;
    private int fragmentType = 0;
    private boolean isPlay = false;
    private SwipeRefreshLayout mVideo_sw;
    private IMypresenter mypresenter;
    public VideoFragment(int type) {
        fragmentType = type;
    }
    private ILiupresenter liupresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video, container, false);
        isCreate = true;
        mypresenter=new Mypresenter(this);
        liupresenter=new Liupresenter(this);
        getActivity().registerReceiver(mHomeKeyEventReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        mSVGAKninghtood = view.findViewById(R.id.mSVGAKninghtood);
        telephonepresenter = new Telephonepresenter(this);
        giftpresenter = new Giftpresenter(this);
        mVideo_recy = view.findViewById(R.id.mVideo_recy);
        dianpresenter = new Dianpresenter(this);
        videopresenter = new Videopresenter(this);
        if (fragmentType == 0) {
            videopresenter.loadData(Sputils.getInstance().getuser_id(), "1", "100");
        }
        EventBus.getDefault().register(this);
        return view;
    }

    private int mCurrentPosition;

    @Override
    public void showvideo(Videobean videobean) {
        arrayList.clear();
        arrayList.addAll(videobean.getData());
        mLayoutManager = new ViewPagerLayoutManager(getContext(), OrientationHelper.VERTICAL);
        mAdapter = new MyAdapter();
        mVideo_recy.setLayoutManager(mLayoutManager);
        mVideo_recy.setAdapter(mAdapter);
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                autoPlayVideo(0);
                liupresenter.showData(arrayList.get(0).getUser_id()+"");
            }
            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurrentPosition == position) {
                    Jzvd.releaseAllVideos();
                }
            }
            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) {
                    return;
                }
                autoPlayVideo(position);
                mCurrentPosition = position;
                liupresenter.showData(arrayList.get(position).getUser_id()+"");
            }
        });

        mVideo_recy.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
            }
            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.jzvdStd);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos();
                    }
                }
            }
        });
    }
    private void autoPlayVideo(int postion) {
        if (mVideo_recy == null || mVideo_recy.getChildAt(0) == null) {
            return;
        }
        player = mVideo_recy.getChildAt(0).findViewById(R.id.jzvdStd);
        ImageView iv = mVideo_recy.getChildAt(0).findViewById(R.id.img_thumb);
        iv.setVisibility(View.GONE);
        if (player != null) {
            player.setMediaInterface(JZMediaAliyun.class);
            player.startVideo();
        }
    }


    @Override
    public void showVideof(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(Dianbean dianbean) {

    }

    @Override
    public void showDataf(Yzmfbean yzmfbean) {

    }

    @Override
    public void showDataStart(Telebeanstart telebeanstart) {
        if (telebeanstart.getCode() == 2000) {
            AVChatKit.outgoingCall(getContext(), contactId, UserInfoHelper.getUserDisplayName(contactId), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
        } else {
            Toast.makeText(getContext(), telebeanstart.getMsg() + "", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {
        if (yzmfbean.getMsg().contains("余额不足")) {
            Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MymoneyActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDatafalse(Telebeanfalse telebeanstart) {

    }

    @Override
    public void showDatafalsef(Yzmfbean yzmfbean) {

    }

    @Override
    public void showDatatrue(Telebeantrue telebeanstart) {

    }

    @Override
    public void showDatatruef(Yzmfbean yzmfbean) {

    }

    @Override
    public void showDataup(Telebeanup telebeanup) {

    }

    @Override
    public void showDataupf(Yzmfbean yzmfbean) {
    }

    @Override
    public void showDatamy(Mybean mybean) {
        Sputils.getInstance().setMoney(mybean.getData().getMoney());
    }

    @Override
    public void showDatamyf(Yzmfbean yzmfbean) {

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private int a=0;
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view_pager, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Glide.with(getContext()).load(arrayList.get(position).getAvatar()).into(holder.circleImageView);
            Glide.with(getContext()).load(arrayList.get(position).getAvatar()).into(holder.img_thumb);
            Glide.with(holder.jzvdStd.getContext()).load(arrayList.get(position).getAvatar()).into(holder.jzvdStd.posterImageView);

            holder.mVideo_name.setText(arrayList.get(position).getNickname() + "");
            holder.mVideo_qianming.setText(arrayList.get(position).getBio() + "");
            JZDataSource jzDataSource = new JZDataSource(arrayList.get(position).getUrl(),
                    "");
            jzDataSource.looping = true;
            holder.jzvdStd.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
            holder.tv_dianzan.setText(arrayList.get(position).getPraise() + "");
            holder.share_icon.setText(arrayList.get(position).getGift() + "");
            if (arrayList.get(position).getIs_praise() == 0) {
                Drawable drawable1 = getResources().getDrawable(R.mipmap.dianzan);
                holder.tv_dianzan.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, null);
            } else {
                Drawable drawable1 = getResources().getDrawable(R.mipmap.dianzan_hong);
                holder.tv_dianzan.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, null);
            }

            holder.tv_comments.setText(arrayList.get(position).getBrowse() + "");
            holder.tv_dianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if (arrayList.get(position).getIs_praise()==0){
                        a=0;
                    }else if (arrayList.get(position).getIs_praise()==1){
                        a=1;
                    }
                    if (arrayList.get(position).getIs_praise() == 0&&a==0) {
                        a=1;
                            dianpresenter.loadData(Sputils.getInstance().getuser_id(), arrayList.get(position).getUser_id() + "", "1");
                            Drawable drawable1 = getResources().getDrawable(R.mipmap.dianzan_hong);
                            holder.tv_dianzan.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, null);
                            if (holder.tv_dianzan.getText().toString().contains("w")) {
                            } else {
                                String s = holder.tv_dianzan.getText().toString();
                                int i = Integer.parseInt(s);
                                holder.tv_dianzan.setText(i + 1 + "");
                        }
                            arrayList.get(position).setIs_praise(1);
                    } else if (arrayList.get(position).getIs_praise()==1&&a==1){
                        a=0;
                        dianpresenter.loadData(Sputils.getInstance().getuser_id(), arrayList.get(position).getUser_id() + "", "2");
                        Drawable drawable1 = getResources().getDrawable(R.mipmap.dianzan);
                        holder.tv_dianzan.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, null);
                        if (holder.tv_dianzan.getText().toString().contains("w")) {
                        } else {
                            String s = holder.tv_dianzan.getText().toString();
                            int i = Integer.parseInt(s);
                            holder.tv_dianzan.setText(i - 1 + "");
                            arrayList.get(position).setIs_praise(0);
                        }
                    }
                }
            });
            holder.share_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user_id = arrayList.get(position).getUser_id() + "";
                    if (Sputils.getInstance().getuser_id().equals(arrayList.get(position).getUser_id())) {
                        Toast.makeText(getContext(), "不能给自己送礼物哦～", Toast.LENGTH_SHORT).show();
                    } else if (Sputils.getInstance().gettype().contains("2")) {
                        Toast.makeText(getContext(), "主播无法发送礼物", Toast.LENGTH_SHORT).show();
                    } else {
                        contactId = arrayList.get(position).getYx_accid() + "";
                        dialog = LoadDialogUtils.createLoadingDialog(getContext(), "");
                        giftpresenter.loadData(Sputils.getInstance().getuser_id());
                    }
                }
            });
            holder.share_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if (PackageManager.PERMISSION_GRANTED ==   ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)) {
                        if (Sputils.getInstance().getuser_id().contains(arrayList.get(position).getUser_id() + "")) {
                            Toast.makeText(getContext(), "不能给自己发视频", Toast.LENGTH_SHORT).show();
                        } else if (Sputils.getInstance().gettype().contains(arrayList.get(position).getType())) {
                            Toast.makeText(getContext(), "主播与主播不能通话", Toast.LENGTH_SHORT).show();
                        }else {
                            contactId = arrayList.get(position).getYx_accid() + "";
                            telephonepresenter.showDataStart(Sputils.getInstance().getuser_id(), arrayList.get(position).getUser_id() + "");
                        }
                    }else{
                        //提示用户开户权限 申请权限
                        String[] perms = {"android.permission.CAMERA","android.permission.MODIFY_AUDIO_SETTINGS","android.permission.RECORD_AUDIO"};
                        ActivityCompat.requestPermissions(getActivity(),perms, 100);
                    }
                }
            });
            holder.circleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Sputils.getInstance().setau_id(arrayList.get(position).getUser_id()+"");
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra(DetailsActivity.DETAIL_ID,arrayList.get(position).getUser_id()+"");
                    startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_thumb;
            JzvdStdTikTok jzvdStd;
            ImageView img_play;
            TextView tv_comments;
            RelativeLayout rootView;
            TextView share_icon;
            private TextView tv_dianzan;
            private CircleImageView circleImageView;
            private TextView mVideo_name;
            private TextView mVideo_qianming, share_phone;

            public ViewHolder(View itemView) {
                super(itemView);
                share_phone = itemView.findViewById(R.id.share_phone);
                img_thumb = itemView.findViewById(R.id.img_thumb);
                jzvdStd = itemView.findViewById(R.id.jzvdStd);
                img_play = itemView.findViewById(R.id.img_play);
                tv_comments = itemView.findViewById(R.id.tv_comments);
                rootView = itemView.findViewById(R.id.root_view);
                share_icon = itemView.findViewById(R.id.share_icon);
                tv_dianzan = itemView.findViewById(R.id.tv_dianzan);
                circleImageView = itemView.findViewById(R.id.mVideo_icon);
                mVideo_name = itemView.findViewById(R.id.mVideo_name);
                mVideo_qianming = itemView.findViewById(R.id.mVideo_qianming);
            }
        }
    }


    private Dialog dialog;
    private View view1;
    private PopupWindow popupWindow1 = new PopupWindow();
    private RecyclerView mGift_recy;
    private TextView mGift_yu, mGift_chong, mGift_zeng, mGift_wai;
    private EditText mGift_edit;
    private ArrayList<GiftsAttachmentBean> gift_array = new ArrayList<>();
    private GiftAdapter giftAdapter;
    private int a1;

    //调用相册 相机
    private void Start() {
        view1 = LayoutInflater.from(getContext()).inflate(R.layout.gift_item, null);
        mGift_chong = view1.findViewById(R.id.mGift_chong);
        mGift_yu = view1.findViewById(R.id.mGift_yu);
        mGift_yu.setText(Sputils.getInstance().getMoney());
        mGift_zeng = view1.findViewById(R.id.mGift_zeng);
        mGift_wai = view1.findViewById(R.id.mGift_wai);
        mGift_edit = view1.findViewById(R.id.mGift_edit);
        mGift_recy = view1.findViewById(R.id.mGift_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        mGift_recy.setLayoutManager(gridLayoutManager);
        giftAdapter = new GiftAdapter(gift_array, getContext());
        mGift_recy.setAdapter(giftAdapter);
        giftAdapter.notifyDataSetChanged();
        popupWindow1 = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);
        giftAdapter.setOnItemListener(new GiftAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos) {
                giftAdapter.setDefSelect(pos);
                id = gift_array.get(pos).getId();
                price = gift_array.get(pos).getPrice();
                Log.e("gift", "选中" + pos);
                a1 = pos;
            }
        });
        mGift_chong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
                Intent intent = new Intent(getContext(), MymoneyActivity.class);
                startActivity(intent);
            }
        });
        mGift_wai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow1.dismiss();
            }
        });
        mGift_zeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == 0) {
                    Toast.makeText(getContext(), "请选择礼物", Toast.LENGTH_SHORT).show();
                } else {
                    popupWindow1.dismiss();
                    giftpresenter.loadDatazeng(Sputils.getInstance().getuser_id(), user_id, "", id + "", price, mGift_edit.getText().toString(), "2", "", user_id);
                }
            }
        });
    }

    @Override
    public void showData(Giftbean giftbean) {
        gift_array.clear();
        gift_array.addAll(giftbean.getData());
        Start();
        popupWindow1.showAtLocation(view1.findViewById(R.id.mGift_recy), Gravity.CENTER | Gravity.CENTER, 0, 0);
        LoadDialogUtils.closeDialog(dialog);

    }

    private SVGAImageView mSVGAKninghtood;

    @Override
    public void showDatasend(SendGiftbean sendGiftbean) {
        mypresenter.loadData(Sputils.getInstance().getuser_id());
        if (sendGiftbean.getCode() == 2000) {
            GiftsAttachment giftsAttachment = new GiftsAttachment();
            GiftsAttachmentBean bean = new GiftsAttachmentBean();
            if (gift_array != null && gift_array.size() > 0) {
                bean = gift_array.get(a1);
            }
            bean.setUmber(Integer.parseInt(mGift_edit.getText().toString()));
            giftsAttachment.setGiftsAttachmentBean(bean);
            IMMessage stickerMessage = MessageBuilder.createCustomMessage(contactId, SessionTypeEnum.P2P, "礼物", giftsAttachment, new CustomMessageConfig());
            NIMClient.getService(MsgService.class).sendMessage(stickerMessage, true);

            //UIKitManager.getInstance().sendGiftsListener.sendGiftsMessage(container,sessionId,bean,mGift_edit.getText().toString());
            Toast.makeText(getContext(), "赠送成功", Toast.LENGTH_SHORT).show();
            mSVGAKninghtood.setVisibility(View.VISIBLE);
            try {
                new SVGAParser(getContext()).decodeFromURL(new URL(gift_array.get(a1).getEffectfile()), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        if (mSVGAKninghtood != null) {
                            mSVGAKninghtood.setVideoItem(videoItem);
                            mSVGAKninghtood.stepToFrame(0, true);
                        }
                    }

                    @Override
                    public void onError() {
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), sendGiftbean.getMsg() + "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDatafsend(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("setUserVisibleHint", fragmentType + "");
        if (isVisibleToUser) {
            if (isCreate && fragmentType == 1) {
                if (null != videopresenter) {
                    videopresenter.loadData(Sputils.getInstance().getuser_id(), "1", "100");
                    isCreate = false;
                    isPlay = true;
                }
            }

        } else {
            if (null != player) {
                player.releaseAllVideos();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ReceiveEventBus(EventBusBean eventBusBean) {
        if (null != player) {
            player.releaseAllVideos();
        }
    }
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";
        String SYSTEM_HOME_KEY_LONG = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    player.releaseAllVideos();
                }
            }
        }
    };
}
