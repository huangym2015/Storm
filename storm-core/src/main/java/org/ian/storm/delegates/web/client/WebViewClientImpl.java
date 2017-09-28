package org.ian.storm.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.ian.storm.app.Storm;
import org.ian.storm.delegates.IPageLoadListener;
import org.ian.storm.delegates.web.WebDelegate;
import org.ian.storm.delegates.web.route.Router;
import org.ian.storm.ui.loader.StormLoader;


/**
 * Created by ian on 2017/9/26.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    private IPageLoadListener mIPageLoadListener =null;
    private static final Handler HANDLER = Storm.getHandler();

    public void setPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener=listener;
    }

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

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }
        StormLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                StormLoader.stopLoading();
            }
        },1000);
    }
}
