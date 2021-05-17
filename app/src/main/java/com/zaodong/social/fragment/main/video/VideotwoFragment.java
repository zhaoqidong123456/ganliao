package com.zaodong.social.fragment.main.video;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.zaodong.social.R;
import com.zaodong.social.activity.MymoneyActivity;
import com.zaodong.social.adapter.GiftAdapter;
import com.zaodong.social.bean.Dianbean;
import com.zaodong.social.bean.Giftbean;
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
import com.zaodong.social.presenter.ITelephonepresenter;
import com.zaodong.social.presenter.Telephonepresenter;
import com.zaodong.social.presenter.video.IVideopresenter;
import com.zaodong.social.presenter.video.Videopresenter;
import com.zaodong.social.presenter.vip.Giftpresenter;
import com.zaodong.social.presenter.vip.IGiftpresenter;
import com.zaodong.social.utils.LoadDialogUtils;
import com.zaodong.social.utils.OnViewPagerListener;
import com.zaodong.social.utils.ViewPagerLayoutManager;
import com.zaodong.social.view.Dianview;
import com.zaodong.social.view.Giftview;
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

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideotwoFragment extends Fragment implements Videoview, Dianview, Giftview,Telephoneview {
    private View view;
    private IVideopresenter videopresenter;
    private ArrayList<Videobean.DataBean> arrayList = new ArrayList<>();
    private RecyclerView mVideo_recy;
    private ViewPagerLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private VideoView videoView;
    private IDianpresenter dianpresenter;
    private IGiftpresenter giftpresenter;
    private String user_id;
    private int id = 0;
    private String price;
    private String url;
    private String contactId;
    private ITelephonepresenter telephonepresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_videotwo2, container, false);
        telephonepresenter=new Telephonepresenter(this);
        mSVGAKninghtood =view.findViewById(R.id.mSVGAKninghtood);
        giftpresenter = new Giftpresenter(this);
        dianpresenter = new Dianpresenter(this);
        mVideo_recy = view.findViewById(R.id.mVideo_recy);
        videopresenter = new Videopresenter(this);
//        videopresenter.loadData(Sputils.getInstance().getuser_id(), "1", "100");
        return view;
    }

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
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                playVideo(0);
            }
        });
    }

    @SuppressLint("NewApi")
    private void playVideo(int position) {
        View itemView = mVideo_recy.getChildAt(0);
//        videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                } else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index) {
        View itemView = mVideo_recy.getChildAt(index);
//        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
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
        if (telebeanstart.getCode()==2000){
            AVChatKit.outgoingCall(getContext(), contactId, UserInfoHelper.getUserDisplayName(contactId), AVChatType.VIDEO.getValue(), AVChatActivity.FROM_INTERNAL);
        }else {
            Toast.makeText(getContext(), telebeanstart.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDataStartf(Yzmfbean yzmfbean) {
        if (yzmfbean.getMsg().contains("余额不足")){
            Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(),MymoneyActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
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


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        public MyAdapter() {
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
            Glide.with(getContext()).load(arrayList.get(position).getCover()).into(holder.circleImageView);
            Glide.with(getContext()).load(arrayList.get(position).getAvatar()).into(holder.img_thumb);
            holder.mVideo_name.setText(arrayList.get(position).getNickname() + "");
            holder.mVideo_qianming.setText(arrayList.get(position).getBio() + "");
            holder.videoView.setVideoURI(Uri.parse(arrayList.get(position).getUrl()));
            holder.share_icon.setText(arrayList.get(position).getGift()+"");
//            holder.videoView.setVideoURI(Uri.parse(arrayList.get(position).getUrl()));
            holder.tv_dianzan.setText(arrayList.get(position).getPraise() + "");
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
                public void onClick(View view) {
                    if (arrayList.get(position).getIs_praise() == 0) {
                        dianpresenter.loadData(Sputils.getInstance().getuser_id(), arrayList.get(position).getUser_id() + "", "1");
                        Drawable drawable1 = getResources().getDrawable(R.mipmap.dianzan_hong);
                        holder.tv_dianzan.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, null);
                        if (holder.tv_dianzan.getText().toString().contains("w")) {
                        } else {
                            String s = holder.tv_dianzan.getText().toString();
                            int i = Integer.parseInt(s);
                            holder.tv_dianzan.setText(i + 1 + "");
                        }
                    } else {
                        dianpresenter.loadData(Sputils.getInstance().getuser_id(), arrayList.get(position).getUser_id() + "", "2");
                        Drawable drawable1 = getResources().getDrawable(R.mipmap.dianzan);
                        holder.tv_dianzan.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, null);
                        if (holder.tv_dianzan.getText().toString().contains("w")) {
                        } else {
                            String s = holder.tv_dianzan.getText().toString();
                            int i = Integer.parseInt(s);
                            holder.tv_dianzan.setText(i - 1 + "");
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
                        contactId =arrayList.get(position).getYx_accid()+"";
                        dialog = LoadDialogUtils.createLoadingDialog(getContext(), "");
                        giftpresenter.loadData(Sputils.getInstance().getuser_id());
                    }
                }
            });
            holder.share_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Sputils.getInstance().getuser_id().contains(arrayList.get(position).getUser_id()+"")){
                        Toast.makeText(getContext(), "不能给自己发视频", Toast.LENGTH_SHORT).show();
                    }else {
                        contactId=arrayList.get(position).getYx_accid()+"";
                        telephonepresenter.showDataStart(Sputils.getInstance().getuser_id(),arrayList.get(position).getUser_id()+"");
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_thumb;
            VideoView videoView;
            ImageView img_play;
            TextView tv_comments;
            RelativeLayout rootView;
            TextView share_icon;
            private TextView tv_dianzan;
            private CircleImageView circleImageView;
            private TextView mVideo_name;
            private TextView mVideo_qianming,share_phone;

            public ViewHolder(View itemView) {
                super(itemView);
                share_phone=itemView.findViewById(R.id.share_phone);
                img_thumb = itemView.findViewById(R.id.img_thumb);
//                videoView = itemView.findViewById(R.id.video_view);
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
                //Log.e("gift", "选中" + pos);
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
        if (sendGiftbean.getCode()==2000){
            GiftsAttachment giftsAttachment = new GiftsAttachment();
            GiftsAttachmentBean bean = new GiftsAttachmentBean();
            if (gift_array != null && gift_array.size() > 0) {
                bean = gift_array.get(a1);
            }
            bean.setUmber(Integer.parseInt(mGift_edit.getText().toString()));
            giftsAttachment.setGiftsAttachmentBean(bean);
            IMMessage stickerMessage = MessageBuilder.createCustomMessage(contactId, SessionTypeEnum.P2P, "礼物", giftsAttachment, new CustomMessageConfig());
            NIMClient.getService(MsgService.class).sendMessage(stickerMessage,true);

            //UIKitManager.getInstance().sendGiftsListener.sendGiftsMessage(container,sessionId,bean,mGift_edit.getText().toString());
            Toast.makeText(getContext(), "赠送成功", Toast.LENGTH_SHORT).show();
            mSVGAKninghtood.setVisibility(View.VISIBLE);
            try {
                new SVGAParser(getContext()).decodeFromURL(new URL(gift_array.get(a1).getEffectfile()), new SVGAParser.ParseCompletion() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
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
        }else {
            Toast.makeText(getContext(), sendGiftbean.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void showDatafsend(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}