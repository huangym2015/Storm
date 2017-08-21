package org.ian.storm.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.ian.storm.app.Storm;

/**
 * Created by ian on 2017/8/20.
 */

//工具包
public class DimenUtil {
    //获取屏宽
    public static int getScreenWidth(){
        final Resources resources = Storm.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.widthPixels;
    }

    //获取屏高
    public static int getScreenHeight(){
        final Resources resources = Storm.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.heightPixels;
    }
}










