package org.ian.storm.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.delegates.web.WebDelegate;
import org.ian.storm.delegates.web.WebDelegateImpl;

/**
 * Created by ian on 2017/9/26.
 */

public class Router {
    public Router() {
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();

    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handlerWebUrl(WebDelegate delegate, String url) {
        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        final StormDelegate topDelegate = delegate.getTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);

        topDelegate.start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }


    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate webDelegate, String url) {
        loadPage(webDelegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL); //跳转到拨号页面
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }

}
