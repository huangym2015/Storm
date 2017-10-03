package org.ian.storm.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import org.ian.storm.app.AccountManager;
import org.ian.storm.app.IUserChecker;
import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.R2;
import org.ian.storm.ui.launcher.ILauncherListener;
import org.ian.storm.ui.launcher.OnLauncherFinishTag;
import org.ian.storm.ui.launcher.ScrollLauncherTag;
import org.ian.storm.util.storage.StormPreference;
import org.ian.storm.util.timer.BaseTimerTask;
import org.ian.storm.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ian on 2017/8/24.
 */

public class LauncherDeleaget extends StormDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;


    private Timer mTimer = null;
    private int mCount = 5; //倒计时的数字

    private ILauncherListener mILauncherListener = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer(); //初始化计时器，进行倒数
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!StormPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否已经登录APP
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

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() { // 跳过按钮的点击事件
        if (mTimer != null) { //判断对象是否存在
            mTimer.cancel();
            mTimer = null;
            //Toast.makeText(getContext(), "clickTimeView", Toast.LENGTH_LONG).show();
            checkIsShowScroll();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000); //任务，延迟，间隔
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            //时间到了，关闭此页面，判断是否启动轮播页面
//                          Toast.makeText(getContext(), "cancel", Toast.LENGTH_LONG).show();
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
