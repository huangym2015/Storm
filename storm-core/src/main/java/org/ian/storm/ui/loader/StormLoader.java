package org.ian.storm.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import org.ian.storm.R;
import org.ian.storm.util.dimen.DimenUtil;

import java.util.ArrayList;

/**
 * Created by ian on 2017/8/19.
 */

public class StormLoader {

    private static final int LOADER_SIZE_SCALE = 8;  //缩放比

    private static final int LOADER_OFFSET_SCALE = 10;  //偏移量

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();  //统一管理Loader，集合

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();  //默认样式

    //可以传枚举类型
    public static void showLoading(Context context, Enum<LoaderStyle> style) {
        showLoading(context, style.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);

        dialog.setContentView(avLoadingIndicatorView);

        //获取屏高和屏宽
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        //创建window对象
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            //宽和高缩小8倍
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            //偏移值
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);  //统一管理，Loader
        dialog.show();
    }

    //默认样式的显示，context最好为当前的上下文，如果是application的context在webui上会出错
    public static void showLoading(Context context) {

        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();  // 可以执行uncancel的回调函数
                    //dialog.dismiss(); //单纯不显示
                }
            }
        }
    }

}



















