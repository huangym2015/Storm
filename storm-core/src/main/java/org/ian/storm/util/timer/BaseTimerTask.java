package org.ian.storm.util.timer;

import java.util.TimerTask;

/**
 * Created by ian on 2017/8/24.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener =null;
    public BaseTimerTask(ITimerListener timerListener){
        this.mITimerListener=timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}














