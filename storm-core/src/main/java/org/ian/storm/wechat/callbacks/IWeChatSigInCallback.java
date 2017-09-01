package org.ian.storm.wechat.callbacks;

/**
 * Created by ian on 2017/8/30.
 */

public interface IWeChatSigInCallback {
    void onSignInSuccess(String userInfo);
}
