package org.ian.storm.app;

import org.ian.storm.util.storage.StormPreference;

/**
 * Created by ian on 2017/8/28.
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        StormPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return StormPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}



