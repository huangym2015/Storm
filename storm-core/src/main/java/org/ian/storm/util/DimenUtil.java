package org.ian.storm.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.ian.storm.app.Storm;

/**
 * Created by ian on 2017/8/20.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Storm.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Storm.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.heightPixels;
    }
}










