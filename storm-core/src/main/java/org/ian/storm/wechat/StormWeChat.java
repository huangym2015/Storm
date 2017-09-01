package org.ian.storm.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.ian.storm.app.ConfigKeys;
import org.ian.storm.app.Storm;
import org.ian.storm.wechat.callbacks.IWeChatSigInCallback;

/**
 * Created by ian on 2017/8/30.
 */

public class StormWeChat {
    static final String APP_ID = Storm.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Storm.getConfiguration(ConfigKeys.WA_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;

    private IWeChatSigInCallback mSigInCallback=null;

    private static final class Holder{
        private static final StormWeChat INSTANCE =new StormWeChat();
    }

    public  static StormWeChat getInstance(){
        return Holder.INSTANCE;
    }

    private StormWeChat(){
        final Activity activity = Storm.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);

    }
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public StormWeChat onSigInSucess(IWeChatSigInCallback callback){
        this.mSigInCallback =callback;
        return this;
    }

    public IWeChatSigInCallback getSigInCallback(){
        return mSigInCallback;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
