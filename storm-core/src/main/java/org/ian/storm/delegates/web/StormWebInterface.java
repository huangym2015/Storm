package org.ian.storm.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import org.ian.storm.delegates.web.event.Event;
import org.ian.storm.delegates.web.event.EventManager;


/**
 * Created by ian on 2017/9/21.
 */

//原生与web进行交互
final class StormWebInterface {
    private final WebDelegate DELEGATE;

    public StormWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

   static StormWebInterface create(WebDelegate delegate){
        return new StormWebInterface(delegate);
    }


    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return  null;
    }
}
