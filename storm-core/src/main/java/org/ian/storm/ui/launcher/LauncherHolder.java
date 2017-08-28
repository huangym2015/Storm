package org.ian.storm.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by ian on 2017/8/24.
 */

public class LauncherHolder  implements Holder<Integer>{

    private AppCompatImageView mImageView =null;  //要求每页装载的都是图片

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int i, Integer integer) {
        mImageView.setBackgroundResource(integer);
    }
}
