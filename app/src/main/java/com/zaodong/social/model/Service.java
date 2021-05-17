package com.zaodong.social.model;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2019/9/25 0016.
 */
//网络请求的接口Service
public interface Service{
    //获取手机验证码
    @FormUrlEncoded
    @POST("api/sms/send")
    Observable<ResponseBody> hyzm(@FieldMap Map<String,String> map);


    //登录
    @FormUrlEncoded
    @POST("api/user/mobilelogin")
    Observable<ResponseBody> login(@FieldMap Map<String,String> map);

    //首页活跃用户
    @FormUrlEncoded
    @POST("api/index/active")
    Observable<ResponseBody> active(@FieldMap Map<String,String> map);

    //首页麻豆广场
    @FormUrlEncoded
    @POST("api/index/activesquarelist")
    Observable<ResponseBody> madou(@FieldMap Map<String,String> map);


    //首页王牌
    @FormUrlEncoded
    @POST("api/index/trumplist")
    Observable<ResponseBody> joker(@FieldMap Map<String,String> map);


    //视频秀
    @FormUrlEncoded
    @POST("api/videoshow/videolist")
    Observable<ResponseBody> video(@FieldMap Map<String,String> map);


    //排行榜
    @FormUrlEncoded
    @POST("api/ranking/ranklist")
    Observable<ResponseBody> rank(@FieldMap Map<String,String> map);


    //关注粉丝
    @FormUrlEncoded
    @POST("api/homepage/followlist")
    Observable<ResponseBody> fans(@FieldMap Map<String,String> map);

    //主播主页
    @FormUrlEncoded
    @POST("api/homepage/index")
    Observable<ResponseBody> details(@FieldMap Map<String,String> map);

    //亲密榜
    @FormUrlEncoded
    @POST("api/homepage/intimatelist")
    Observable<ResponseBody> intimatelist(@FieldMap Map<String,String> map);

    //我的主页
    @FormUrlEncoded
    @POST("api/user/my")
    Observable<ResponseBody> my(@FieldMap Map<String,String> map);


    //我的收入明细
    @FormUrlEncoded
    @POST("api/user/myincome")
    Observable<ResponseBody> my_liushui(@FieldMap Map<String,String> map);

    //关注/取消
    @FormUrlEncoded
    @POST("api/homepage/setfollow")
    Observable<ResponseBody> guanzhu(@FieldMap Map<String,String> map);

    //商品列表
    @FormUrlEncoded
    @POST("api/common/goodslist")
    Observable<ResponseBody> shangpin(@FieldMap Map<String,String> map);

    //vip列表
    @FormUrlEncoded
    @POST("api/common/goodslist")
    Observable<ResponseBody> vip(@FieldMap Map<String,String> map);

    //礼物列表
    @FormUrlEncoded
    @POST("api/common/giftlist")
    Observable<ResponseBody> gift(@FieldMap Map<String,String> map);

    //下单接口
    @FormUrlEncoded
    @POST("api.php?m=api&c=index&a=index")
    Observable<ResponseBody> xiadan(@FieldMap Map<String,String> map);

    //首页搜索
    @FormUrlEncoded
    @POST("api/index/searchlist")
    Observable<ResponseBody> sousuo(@FieldMap Map<String,String> map);

    //首页搜索热门推荐
    @FormUrlEncoded
    @POST("api/index/searchhotlist")
    Observable<ResponseBody> sou_re(@FieldMap Map<String,String> map);

    //上传图片
    @Multipart
    @POST("api/Common/upload")
    Observable<ResponseBody> image_shangchuan(@Part MultipartBody.Part file);

    //获取用户信息
    @FormUrlEncoded
    @POST("api/user/userinfo")
    Observable<ResponseBody> presonal(@FieldMap Map<String,String> map);

    //修改用户信息
    @FormUrlEncoded
    @POST("api/user/profile")
    Observable<ResponseBody> save_presonal(@FieldMap Map<String,String> map);

    //明细
    @FormUrlEncoded
    @POST("api/user/myincome")
    Observable<ResponseBody> shouru(@FieldMap Map<String,String> map);

    //更改用户在线状态
    @FormUrlEncoded
    @POST("api/user/onlinestatus")
    Observable<ResponseBody> state(@FieldMap Map<String,String> map);

    //通话记录
    @FormUrlEncoded
    @POST("api/call/usercalllog")
    Observable<ResponseBody> tele_jilu(@FieldMap Map<String,String> map);

    //通话记录
    @FormUrlEncoded
    @POST("api/videoshow/praise")
    Observable<ResponseBody> dianan(@FieldMap Map<String,String> map);

    //送礼物
    @FormUrlEncoded
    @POST("api/user/givinggift")
    Observable<ResponseBody> send_gift(@FieldMap Map<String,String> map);

    //根据云信id获取用户id
    @FormUrlEncoded
    @POST("api/common/userid")
    Observable<ResponseBody> yx_id(@FieldMap Map<String,String> map);

    //通话发起
    @FormUrlEncoded
    @POST("api/call/usercall")
    Observable<ResponseBody> telephone_start(@FieldMap Map<String, String> map);

    //通话挂断
    @FormUrlEncoded
    @POST("api/call/userhangup")
    Observable<ResponseBody> telephone_up(@FieldMap Map<String, String> map);

    //通话拒绝
    @FormUrlEncoded
    @POST("api/call/userclickrefuse")
    Observable<ResponseBody> telephone_false(@FieldMap Map<String, String> map);

    //通话接受
    @FormUrlEncoded
    @POST("api/call/userclickaccept")
    Observable<ResponseBody> telephone_true(@FieldMap Map<String, String> map);


    //用户列表
    @FormUrlEncoded
    @POST("api/user/userlist")
    Observable<ResponseBody> yonghu(@FieldMap Map<String, String> map);


    //升级
    @FormUrlEncoded
    @POST("api/common/edition")
    Observable<ResponseBody> shengji(@FieldMap Map<String, String> map);


    //配置信息
    @FormUrlEncoded
    @POST("api/common/config")
    Observable<ResponseBody> peizhi(@FieldMap Map<String, String> map);


    //优价专区
    @FormUrlEncoded
    @POST("api/index/discountlist")
    Observable<ResponseBody> youjia(@FieldMap Map<String, String> map);


    //配置信息
    @FormUrlEncoded
    @POST("api/videoshow/browse")
    Observable<ResponseBody> liulan(@FieldMap Map<String, String> map);


    //跑马灯
    @FormUrlEncoded
    @POST("api/user/horseracelamp")
    Observable<ResponseBody> paomadeng(@FieldMap Map<String, String> map);

    //获取漂流瓶文案
    @FormUrlEncoded
    @POST("api/bottle/getbottle")
    Observable<ResponseBody> piao_wen(@FieldMap Map<String, String> map);

    //漂流瓶撩她
    @FormUrlEncoded
    @POST("api/bottle/touchher")
    Observable<ResponseBody> piao_liao(@FieldMap Map<String, String> map);


    //通话详情
    @FormUrlEncoded
    @POST("api/call/usercallinfo")
    Observable<ResponseBody> tong(@FieldMap Map<String, String> map);
}
