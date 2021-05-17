package com.zaodong.social.activity.start;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.zaodong.social.R;
import com.zaodong.social.utils.StatusBarUtils;

import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private String zhifu;

    AlertDialog alertDialog;
    private TextView tv_wechat_success;
    private TextView tv_wechat_failure;
    private ImageButton mPayBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        initStatusBar();
        mWebView = findViewById(R.id.wv_pay_h5);
        Intent intent = getIntent();
        zhifu = intent.getStringExtra("zhifu");
        String biao = intent.getStringExtra("biao");

        mWebView.getSettings().setJavaScriptEnabled(true);//支持JS
        mWebView.getSettings().setDomStorageEnabled(true);//解决显示不全的问题
        if (biao.contains("wei")){
            initdata("http://klpay.skrfun.cn", zhifu);
        }else {
            zhifubao(zhifu);
        }
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(this, R.color.white);
        }
    }

    public void zhifubao(String zhifu){
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Log.i("test", "test" + "支付Url跳转处理：" + url);
                if (url.contains("platformapi/startApp")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                        && (url.contains("platformapi") && url.contains("startApp"))) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } else if (url.equals("yianapp://activity/OpenVIPSuccessActivity")) {
                    setResult(201);
                    finish();
                } else if (url.startsWith("yianapp://close")) {
                    finish();
                } else {
                    webView.loadUrl(url);
                }
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView,
                                                              WebResourceRequest webResourceRequest) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(zhifu);
    }

    //调起微信h5支付
    private void initdata(final String referer, String weburl) {
        Map<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("Referer", referer);
        mWebView.loadUrl(weburl, extraHeaders);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                if (url == null) return false;
                try {
                    //弹窗
                    initdialog();
                    WindowManager m = PayActivity.this.getWindowManager();
                    Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
                    WindowManager.LayoutParams p = alertDialog.getWindow().getAttributes();  //获取对话框当前的参数值
                    //p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
                    p.width = (int) (d.getWidth() * 0.75);    //宽度设置为屏幕的0.5
                    alertDialog.getWindow().setAttributes(p);     //设置生效

                    if (url.startsWith("weixin://wap/pay?")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    } else if (url.startsWith("https://wx.tenpay.com")) {
                        Map<String, String> extraHeaders = new HashMap<>();
                        extraHeaders.put("Referer", referer);
                        view.loadUrl(url, extraHeaders);
                        return true;
                    }
                } catch (Exception e) {
                    alertDialog.show();
                    //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }
                // 在APP内部打开链接，不要调用系统浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }

    //微信h5支付弹窗
    private void initdialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PayActivity.this, R.style.AlertDialog);
        View inflate = LayoutInflater.from(PayActivity.this).inflate(R.layout.dialog_wechat_h5_pay, null);
        tv_wechat_success = (TextView) inflate.findViewById(R.id.tv_wechat_success);
        tv_wechat_failure = (TextView) inflate.findViewById(R.id.tv_wechat_failure);

        tv_wechat_success.setOnClickListener(this);
        tv_wechat_failure.setOnClickListener(this);
        dialog.setView(inflate);
        alertDialog = dialog.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(false);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mPay_back:
                finish();
                break;
        }
    }
    private void initView() {
        mPayBack = findViewById(R.id.mPay_back);
        mPayBack.setOnClickListener(this);
    }
}
