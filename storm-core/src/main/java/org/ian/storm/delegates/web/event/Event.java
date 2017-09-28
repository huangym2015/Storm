package org.ian.storm.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.delegates.web.WebDelegate;

/**
 * Created by ian on 2017/9/28.
 */

public abstract class Event implements IEvent {
    private Context mContext =null;
    private String mAction =null;
    private WebDelegate mDelegate=null;
    private String mUrl = null;
    private WebView mWebView = null;

    public WebView getWebView() {
        return mDelegate.getWebView();
    }


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate delegate) {
        this.mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
