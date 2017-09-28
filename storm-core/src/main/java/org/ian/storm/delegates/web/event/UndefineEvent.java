package org.ian.storm.delegates.web.event;

import android.util.Log;

/**
 * Created by ian on 2017/9/28.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.e("UndefineEvent",params);
        return null;
    }
}
