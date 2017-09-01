package org.ian.storm.wechat.templates;

import org.ian.storm.wechat.BaseWXEntryActivity;
import org.ian.storm.wechat.StormWeChat;

/**
 * Created by ian on 2017/8/30.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0); //无动画finish;
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        StormWeChat.getInstance().getSigInCallback().onSignInSuccess(userInfo);

    }
}
