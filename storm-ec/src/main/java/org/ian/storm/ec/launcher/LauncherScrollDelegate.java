package org.ian.storm.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import org.ian.storm.app.AccountManager;
import org.ian.storm.app.IUserChecker;
import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ui.launcher.ILauncherListener;
import org.ian.storm.ui.launcher.LauncherHolderCreator;
import org.ian.storm.ui.launcher.OnLauncherFinishTag;
import org.ian.storm.ui.launcher.ScrollLauncherTag;
import org.ian.storm.util.storage.StormPreference;

import java.util.ArrayList;

/**
 * Created by ian on 2017/8/24.
 */

public class LauncherScrollDelegate extends StormDelegate implements OnItemClickListener{

    private ConvenientBanner<Integer> mConvenientBanner =null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private  void initBanner(){
        //初始化Banner的图片
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})  //官方中为下方的圆圈图片，此处为框架建议自建资源
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)  //圆圈的位置
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    private ILauncherListener mILauncherListener = null;

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onItemClick(int i) {
        if (i ==INTEGERS.size()-1) { //如果点击的是最后一个
            StormPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }
}
