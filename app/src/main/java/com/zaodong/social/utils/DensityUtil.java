package com.zaodong.social.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = getMetric(context);
        if (metrics != null)
            return metrics.heightPixels;
        else
            return 801;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = getMetric(context);
        if (metrics != null)
            return metrics.widthPixels;
        else
            return 481;
    }

    public static int getScreenResolution(Context context) {
        DisplayMetrics metrics = getMetric(context);
        if (metrics != null)
            return metrics.widthPixels * metrics.heightPixels;
        else
            return 480 * 800;
    }

    public static Point getScreenDimension(Context context) {
        DisplayMetrics metrics = getMetric(context);
        if (metrics != null)
            return new Point(metrics.widthPixels, metrics.heightPixels);
        return new Point(480, 800);
    }

    /**
     * x = screen width; y = screen height; in dp.
     */
    public static Point getScreenDimensionInDp(Context context) {
        DisplayMetrics metrics = getMetric(context);
        if (metrics != null) {
            float density = metrics.density == 0 ? 1 : metrics.density;
            return new Point((int) (metrics.widthPixels / density),
                    (int) (metrics.heightPixels / density));
        }
        return new Point(320, 534); // 480X800 with hdpi
    }

    public static DisplayMetrics getMetric(Context context) {
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager winMgr = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            winMgr.getDefaultDisplay().getMetrics(metrics);
            return metrics;
        } catch (Exception e) {
        }
        return null;
    }

    public static int measureViewHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }

    public static int measureViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }
}
