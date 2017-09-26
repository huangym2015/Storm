package org.ian.storm.delegates.web.client;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.ian.storm.delegates.web.WebDelegate;
import org.ian.storm.delegates.web.route.Router;

/**
 * Created by ian on 2017/9/26.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;


    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    //旧版的，兼容老机器
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("shouldORL",url);
        return Router.getInstance().handlerWebUrl(DELEGATE,url);
    }
}
